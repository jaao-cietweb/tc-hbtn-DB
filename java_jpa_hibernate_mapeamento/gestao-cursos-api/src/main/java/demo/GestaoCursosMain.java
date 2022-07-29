package demo;

import entities.*;
import models.AlunoModel;
import models.CursoModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GestaoCursosMain {
    public static void main(String[] args) {
        Endereco endereco = new Endereco();
        endereco.setEndereco("oi");
        endereco.setBairro("oioi");
        endereco.setCep(738912739);
        endereco.setCidade("oioioi");
        endereco.setEstado("oioioioi");
        endereco.setLogradouro("ioioioio");
        endereco.setNumero("33333");

        Telefone telefone = new Telefone();
        telefone.setDDD("31");
        telefone.setNumero("773813216313");

        MaterialCurso materialCurso = new MaterialCurso();
        materialCurso.setUrl("www.wwwwww");

        Professor professor = new Professor();
        professor.setEmail("prof@email.com");
        professor.setMatricula("837283");
        professor.setNomeCompleto("vinicius");

        List<Endereco> enderecos = new ArrayList<>();
        enderecos.add(endereco);

        List<Telefone> telefones = new ArrayList<>();
        telefones.add(telefone);

        AlunoModel alunoModel = new AlunoModel();

        Aluno aluno = new Aluno ();
        aluno.setNomeCompleto("Joao Garcia");
        aluno.setMatricula("65577");
        aluno.setEmail("joaog@email.com");
        aluno.setNascimeto(new Date(1998));
        aluno.setEnderecos(enderecos);
        aluno.setTelefones(telefones);

        alunoModel.create(aluno);

        List<Aluno> alunos = alunoModel.findAll();
        System.out.println("Quantidade de alunos: "+ alunos.size());
        Aluno byId = alunoModel.findById(aluno.getId());
        System.out.println("Produto porcurado por id: " + byId.getNomeCompleto());
        aluno.setNomeCompleto("Joana G");
        alunoModel.update(aluno);

        Curso curso = new Curso();
        curso.setNome("curso");
        curso.setSigla("c");
        curso.setProfessor(professor);
        curso.setAlunos(alunos);
        curso.setMaterialCurso(materialCurso);

        CursoModel cursoModel = new CursoModel();

        cursoModel.create(curso);

        List<Curso> cursos = cursoModel.findAll();
        System.out.println("Quantidade de cursos: "+ cursos.size());
        Curso byId1 = cursoModel.findById(curso.getId());
        System.out.println("Produto porcurado por id: " + byId1.getNome());
        curso.setNome("curso 2");
        cursoModel.update(curso);




        cursoModel.delete(curso);
        alunoModel.delete(aluno);
    }
}
