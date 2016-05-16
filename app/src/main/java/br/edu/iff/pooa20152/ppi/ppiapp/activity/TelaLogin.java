package br.edu.iff.pooa20152.ppi.ppiapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import br.edu.iff.pooa20152.ppi.ppiapp.R;

public class TelaLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void acessarTelaPrincipal (View v){
        startActivity(new Intent(this, TelaPrincipal.class));
        finish();
    }
}
