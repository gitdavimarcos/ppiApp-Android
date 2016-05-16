package br.edu.iff.pooa20152.ppi.ppiapp;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.edu.iff.pooa20152.ppi.ppiapp.helper.RestFullHelper;

/**
 * Created by davi-bsi on 04/05/16.
 */
public class RestFullUnitTest {
    RestFullHelper http;
    JSONObject json;
    String id;
    String durl = "http://doml-pooa20152.herokuapp.com/empregadors";

    @Before
    public void setUp() throws Exception {
        http = new RestFullHelper();
        json = http.doPost(durl + ".json", getParams());
        id = Integer.toString(json.getInt("id")).trim();

    }

    @After
    public void tearDown() throws Exception {


    }

    @Test
    public void dogets() throws Exception {

        json = http.doGet(durl+".json");

        //assertEquals(12, json.);
        http.doDelete(durl+"/"+id+".json");
    }

    @Test
    public void doget() throws Exception {

        json = http.doGet(durl + "/" + id + ".json");

        Assert.assertEquals("100", json.getString("numero"));
        http.doDelete(durl+"/"+id+".json");
    }

    @Test
    public void doDelete() throws Exception {

        json = http.doDelete(durl+"/"+id+".json");

        Assert.assertEquals(null, json);
    }

    @Test
    public void doPost() throws Exception{

        Assert.assertEquals("Davi", json.getString("nome"));
        http.doDelete(durl+"/"+id+".json");

    }
    @Test
    public void doPut() throws Exception{

        JSONObject oPut = new JSONObject();
        oPut.put("nome","Gustavo:"+id);
        oPut.put("endereco","Av Presidente:"+id);
        oPut.put("numero","400:"+id);

        json = http.doPut(durl+"/"+id+".json",oPut);

        Assert.assertEquals("Gustavo:" + id, json.getString("nome"));
        http.doDelete(durl+"/"+id+".json");

    }


    private JSONObject getParams() {
        JSONObject params = new JSONObject();
        try {
            params.put("nome", "Davi");
            params.put("endereco", "R. das Flores");
            params.put("numero", "100");

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return params;
    }
}
