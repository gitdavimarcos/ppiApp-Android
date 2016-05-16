package br.edu.iff.pooa20152.ppi.ppiapp.domain;

import org.json.JSONObject;

import java.util.List;


public class CursoService extends GenericService {

    public CursoService(String url, String id, String method, JSONObject params) {
        super(url, id, method, params,new Curso());
    }
    public CursoService(){
        super();
    }

    public List<Curso> getAll(String url) {

        List<Curso> listaCursos = super.getAll(url,new Curso());

        return listaCursos;

    }

    public Curso doGet(String url,String id){

        return (Curso) super.doGet(url,id,new Curso());
    }

    public Curso doDelete(String url,String id){

        return (Curso) super.doDelete(url,id);
    }

    public Curso doPut(String url, JSONObject params){
        return (Curso) super.doPut(url,params,new Curso());
    }

    public Curso doPost(String url, JSONObject params){
        return (Curso) super.doPost(url,params,new Curso());
    }

    public Curso execute(){
        return (Curso) super.execute();
    }
}
