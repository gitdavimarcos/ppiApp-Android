package br.edu.iff.pooa20152.ppi.ppiapp.domain;


public class Curso {

    private Integer id;
    private String nome;
    private String descricao;
    private String professor;

    public Curso(Integer id, String nome, String formacao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.professor = professor;
    }

    public Curso() {

    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    @Override
    public String toString() {
        return "Curso{" +
                "id=" + id +
                '}';
    }
}
