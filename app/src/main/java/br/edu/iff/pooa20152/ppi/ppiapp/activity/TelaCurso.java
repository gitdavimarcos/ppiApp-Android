package br.edu.iff.pooa20152.ppi.ppiapp.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import br.edu.iff.pooa20152.ppi.ppiapp.R;
import br.edu.iff.pooa20152.ppi.ppiapp.domain.Curso;
import br.edu.iff.pooa20152.ppi.ppiapp.domain.CursoService;
import br.edu.iff.pooa20152.ppi.ppiapp.helper.RestFullHelper;

public class TelaCurso extends AppCompatActivity {

    private EditText edIdCurso;
    private EditText edNome;
    private EditText edDescricao;
    private EditText edProfessor;
    private final String TAG = "MAIN";
    private Button btCadastrar;
    private Button btMostrar;
    private Button btDeletar;
    private String durl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curso);

        edIdCurso = (EditText) findViewById(R.id.edIdCurso);
        edNome = (EditText) findViewById(R.id.edNome);
        edDescricao = (EditText) findViewById(R.id.edDescricao);
        edProfessor = (EditText) findViewById(R.id.edProfessor);

        durl = getString(R.string.URL);

        btMostrar = (Button) findViewById(R.id.btMostrar);
        btMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String filtro = edIdCurso.getText().toString();
                if (!filtro.equalsIgnoreCase("")) {
                    getInformationtoAPI();
                }
            }
        });

        btCadastrar = (Button) findViewById(R.id.btCadastrar);
        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edIdCurso.getText().toString()))
                    postInformationtoAPI();
                else
                    putInformationtoAPI();
            }

        });

        btDeletar = (Button) findViewById(R.id.btDeletar);
        btDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletarInformationtoAPI();
            }
        });
    }

    private void deletarInformationtoAPI() {
        Log.i(TAG, "Deletar ORDER");
        JSONObject params = null;

        CursoTask bgtDel = new CursoTask(
                durl + "/cursos",
                edIdCurso.getText().toString(),
                RestFullHelper.DELETAR, params);
        bgtDel.execute();
    }

    private void getInformationtoAPI() {
        JSONObject params = null;

        CursoTask bgtGet = new CursoTask(
                durl + "/cursos",
                edIdCurso.getText().toString(),
                RestFullHelper.GET, params);
        bgtGet.execute();
    }

    private void postInformationtoAPI() {
        Log.d(TAG, "POSTING ORDER");
        JSONObject params = new JSONObject();

        try {
            params.put("nome", edNome.getText().toString());
            params.put("descricao", edDescricao.getText().toString());
            params.put("professor_id", edProfessor.getText().toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        CursoTask bgtPost = new CursoTask(
                durl + "/cursos", null, RestFullHelper.POST, params);
        bgtPost.execute();
    }

    private void putInformationtoAPI() {
        Log.i(TAG, "PUT ORDER");
        JSONObject params = new JSONObject();

        try {
            params.put("id", edIdCurso.getText().toString());
            params.put("nome", edNome.getText().toString());
            params.put("descricao", edDescricao.getText().toString());
            params.put("professor_id", edProfessor.getText().toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        CursoTask bgtPut = new CursoTask(
                durl + "/cursos", edIdCurso.getText().toString(),
                RestFullHelper.PUT, params);
        bgtPut.execute();
    }

    private Context getContext() {
        return this;
    }

    private void alert(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }


    public class CursoTask extends AsyncTask<String, String, Curso> {
        String url = null;
        String method = null;
        String id = null;
        JSONObject params1 = null;

        ProgressDialog dialog;

        public CursoTask(String url, String id, String method, JSONObject params) {
            this.url = url;
            this.method = method;
            this.params1 = params;
            this.id = id;
        }

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(TelaCurso.this);
            dialog.show();
        }

        @Override
        protected void onPostExecute(Curso jsonObject) {

            if (jsonObject != null) {
                edIdCurso.setText(jsonObject.getId().toString());
                edNome.setText(jsonObject.getNome());
                edDescricao.setText(jsonObject.getDescricao());
                edProfessor.setText(jsonObject.getProfessor());
            }
            dialog.dismiss();
        }

        @Override
        protected Curso doInBackground(String... params) {
            CursoService cursoService = new CursoService(url, id, method, params1);
            Curso curso = cursoService.execute();
            return curso;
        }
    }
}