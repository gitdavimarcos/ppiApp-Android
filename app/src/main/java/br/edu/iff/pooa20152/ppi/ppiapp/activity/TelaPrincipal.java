package br.edu.iff.pooa20152.ppi.ppiapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import br.edu.iff.pooa20152.ppi.ppiapp.R;

public class TelaPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
    }

    public void chamarInscricao (View v) {
        startActivity(new Intent(this, TelaInscricao.class));
    }

    public void chamarProfessor (View v){
        startActivity(new Intent(this, TelaProfessor.class));
    }

    public void chamarCurso (View v) {
        startActivity(new Intent(this, TelaCurso.class));
    }

    public void chamarProAlu (View v) {
        startActivity(new Intent(this, Turma.class));
    }

    public void chamarNota (View v) {
        startActivity(new Intent(this, TelaLancarNota.class));
    }
}
