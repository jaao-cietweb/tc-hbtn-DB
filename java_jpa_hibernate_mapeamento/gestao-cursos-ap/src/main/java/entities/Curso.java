package entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String nome;
    String sigla;



    @OneToOne(cascade = CascadeType.ALL)
    MaterialCurso materialCurso;

    @OneToOne(cascade = CascadeType.ALL)
    Professor professor;

    @ManyToMany
    @JoinTable(name="curso_aluno",
            joinColumns={@JoinColumn(name="curso_id")},
            inverseJoinColumns={@JoinColumn(name="aluno_id")})
    List <Aluno> alunos;

    public MaterialCurso getMaterialCurso() {
        return materialCurso;
    }

    public void setMaterialCurso(MaterialCurso materialCurso) {
        this.materialCurso = materialCurso;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
}
