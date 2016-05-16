package br.edu.iff.pooa20152.ppi.ppiapp.domain;


import org.json.JSONObject;

import java.util.List;

public class ProfessorService extends GenericService {

    public ProfessorService(String url, String id, String method, JSONObject params) {
        super(url, id, method, params,new Professor());
    }
    public ProfessorService(){
        super();
    }

    public List<Professor> getAll(String url) {

        List<Professor> listaProfessores = super.getAll(url,new Professor());

        return listaProfessores;

    }

    public Professor doGet(String url,String id){

        return (Professor) super.doGet(url,id,new Professor());
    }

    public Professor doDelete(String url,String id){

        return (Professor) super.doDelete(url,id);
    }

    public Professor doPut(String url, JSONObject params){
        return (Professor) super.doPut(url,params,new Professor());
    }

    public Professor doPost(String url, JSONObject params){
        return (Professor) super.doPost(url,params,new Professor());
    }

    public Professor execute(){
        return (Professor) super.execute();
    }
}
