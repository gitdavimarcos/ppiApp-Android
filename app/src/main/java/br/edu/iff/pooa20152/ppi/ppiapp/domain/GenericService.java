package br.edu.iff.pooa20152.ppi.ppiapp.domain;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import br.edu.iff.pooa20152.ppi.ppiapp.helper.RestFullHelper;


public class GenericService {

    private static String TAG = "GenericService";
    public boolean LOG_ON = false;
    RestFullHelper http = new RestFullHelper();
    String url = null;
    String method = null;
    String id = null;
    JSONObject params1 = null;
    Object domain = null;

    public GenericService(String url, String id, String method, JSONObject params, Object domain) {
        this.url = url;
        this.method = method;
        this.params1 = params;
        this.id = id;
        this.domain = domain;
    }

    public GenericService() {

    }

    public Object execute() {
        Object aObj = null;

        if (method.equalsIgnoreCase(RestFullHelper.GET)) {
            aObj = doGet(url, id, domain);
        }

        if (method.equalsIgnoreCase(RestFullHelper.DELETAR)) {
            aObj = doDelete(url, id);
        }

        if (method.equalsIgnoreCase(RestFullHelper.POST)) {
            aObj = doPost(url, params1, domain);
        }

        if (method.equalsIgnoreCase(RestFullHelper.PUT)) {
            aObj = doPut(url, params1, domain);
        }
        return aObj;
    }


    public Object doGet(String url, String id, Object domain) {

        JSONObject json = http.doGet(url + "/" + id + ".json");
        return instanceObject(json, domain);
    }

    public Object doDelete(String url, String id) {

        JSONObject json = http.doDelete(url + "/" + id + ".json");
        return null;
    }

    public Object doPut(String url, JSONObject params, Object domain) {

        JSONObject json = null;
        try {
            json = http.doPut(url + "/" + params.getString("id") + ".json", params);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return instanceObject(json, domain);
    }

    public Object doPost(String url, JSONObject params, Object domain) {

        JSONObject json = http.doPost(url + ".json", params);
        return instanceObject(json, domain);
    }

    /////Context context,
    public <T> List<T> getAll(String url, Object domain) {

        JSONObject json = http.doGet(url + ".json");

        if (LOG_ON) {
            Log.d(TAG, "URL -> " + url);

        }
        /* context, */
        List<T> lista = null;

        lista = parserJSON(json, domain);

        return lista;
    }

    ///private static , Context context,
    public <T> List<T> parserJSON(JSONObject obj, Object domain) {
        List<T> listas = new ArrayList<T>();
        try {

            JSONArray AjsonDomain = obj.getJSONArray(domain.getClass().getSimpleName().toLowerCase());
            // Insere cada carro na lista
            for (int i = 0; i < AjsonDomain.length(); i++) {
                JSONObject jsonDomain = AjsonDomain.getJSONObject(i);

                listas.add((T) instanceObject(jsonDomain, domain));

            }
            if (LOG_ON) {
                Log.d(TAG, " encontrados.");
            }
        } catch (JSONException e) {
            try {
                throw new IOException(e.getMessage(), e);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        return listas;
    }

    private Object instanceObject(JSONObject jsonDomain, Object domain) {
        Object oClass = null;
        try {
            oClass = domain.getClass().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        Field fe[] = oClass.getClass().getDeclaredFields();

        for (Field f : fe) {
            f.setAccessible(true);

            try {

                f.set(oClass, jsonDomain.get(f.getName()));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return oClass;
    }
}