package br.edu.iff.pooa20152.ppi.ppiapp.domain;


public class Professor {

    private Integer id;
    private String nome;
    private String formacao;

    public Professor(Integer id, String nome, String formacao) {
        this.id = id;
        this.nome = nome;
        this.formacao = formacao;
    }

    public Professor() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFormacao() {
        return formacao;
    }

    public void setFormacao(String formacao) {
        this.formacao = formacao;
    }

    @Override
    public String toString() {
        return "Professor{" +
                "id=" + id +
                '}';
    }
}
