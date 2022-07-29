package entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String nomeCompleto;
    String matricula;
    Date nascimeto;
    String email;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "Endereco_Id")
    List<Endereco> enderecos;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "Telefone_Id")
    List<Telefone> telefones;

    @ManyToMany(mappedBy = "alunos")
    List <Curso> cursos;

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Date getNascimeto() {
        return nascimeto;
    }

    public void setNascimeto(Date nascimeto) {
        this.nascimeto = nascimeto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
