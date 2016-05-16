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
import br.edu.iff.pooa20152.ppi.ppiapp.domain.Professor;
import br.edu.iff.pooa20152.ppi.ppiapp.domain.ProfessorService;
import br.edu.iff.pooa20152.ppi.ppiapp.helper.RestFullHelper;

public class TelaProfessor extends AppCompatActivity {

    private EditText edIdProfessor;
    private EditText edProfessor;
    private EditText edFormacao;

    private final String TAG = "MAIN";
    private Button btCadastrar;
    private Button btMostrar;
    private Button btDeletar;
    private String durl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor);

        edIdProfessor = (EditText) findViewById(R.id.edIdProfessor);
        edProfessor = (EditText) findViewById(R.id.edProfessor);
        edFormacao = (EditText) findViewById(R.id.edFormacao);

        durl = getString(R.string.URL);

        btMostrar = (Button) findViewById(R.id.btMostrar);
        btMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String filtro = edIdProfessor.getText().toString();
                if (!filtro.equalsIgnoreCase("")) {
                    getInformationtoAPI();
                }
            }
        });

        btCadastrar = (Button) findViewById(R.id.btCadastrar);
        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edIdProfessor.getText().toString()))
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

        ProfessorTask bgtDel = new ProfessorTask(
                durl + "/professores",
                edIdProfessor.getText().toString(),
                RestFullHelper.DELETAR, params);
        bgtDel.execute();
    }

    private void getInformationtoAPI() {
        JSONObject params = null;

        ProfessorTask bgtGet = new ProfessorTask(
                durl + "/professores",
                edIdProfessor.getText().toString(),
                RestFullHelper.GET, params);
        bgtGet.execute();
    }

    private void postInformationtoAPI() {
        Log.d(TAG, "POSTING ORDER");
        JSONObject params = new JSONObject();

        try {
            params.put("nome", edProfessor.getText().toString());
            params.put("formacao", edFormacao.getText().toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ProfessorTask bgtPost = new ProfessorTask(
                durl + "/professores", null, RestFullHelper.POST, params);
        bgtPost.execute();

    }

    private void putInformationtoAPI() {
        Log.i(TAG, "PUT ORDER");
        JSONObject params = new JSONObject();

        try {
            params.put("id", edIdProfessor.getText().toString());
            params.put("nome", edProfessor.getText().toString());
            params.put("formacao", edFormacao.getText().toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ProfessorTask bgtPut = new ProfessorTask(
                durl + "/professores", edIdProfessor.getText().toString(),
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


    public class ProfessorTask extends AsyncTask<String, String, Professor> {
        String url = null;
        String method = null;
        String id = null;
        JSONObject params1 = null;

        ProgressDialog dialog;

        public ProfessorTask(String url, String id, String method, JSONObject params) {
            this.url = url;
            this.method = method;
            this.params1 = params;
            this.id = id;
        }

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(TelaProfessor.this);
            dialog.show();
        }

        @Override
        protected void onPostExecute(Professor jsonObject) {

            if (jsonObject != null) {
                edIdProfessor.setText(jsonObject.getId().toString());
                edProfessor.setText(jsonObject.getNome());
                edFormacao.setText(jsonObject.getFormacao());
            }
            dialog.dismiss();
        }

        @Override
        protected Professor doInBackground(String... params) {
            ProfessorService professorService = new ProfessorService(url, id, method, params1);
            Professor professor = professorService.execute();
            return professor;
        }
    }
}