package demo;

import entities.Pessoa;
import entities.Produto;
import models.PessoaModel;
import models.ProdutoModel;

import java.util.Date;
import java.util.List;

public class AdministrativoApp {
    public static void main(String[] args) {
        ProdutoModel produtoModel = new ProdutoModel();

        Produto p1 = new Produto();
        p1.setNome("TV");
        p1.setPreco(300.0);
        p1.setQuantidade(100);
        p1.setStatus(true);

        // 1) Criando um produto
        produtoModel.create(p1);

        //2) Buscando todos os produtos na base de dados
        List<Produto> produtos = produtoModel.findAll();
        System.out.println("Qtde de produtos encontrados : " + produtos.size());
        Produto byId = produtoModel.findById(p1);
        System.out.println("Produto procurado por id: " + byId.getNome());
        p1.setPreco(299.0);
        produtoModel.update(p1);
        produtoModel.delete(p1);

        System.out.println("____________________________________________-");
        PessoaModel pessoaModel = new PessoaModel();

        Pessoa pessoa1 = new Pessoa();
        pessoa1.setNome("Joao");
        pessoa1.setIdade(30);
        pessoa1.setCpf("99887765544");
        pessoa1.setEmail("joao@ciet.com");
        pessoa1.setDataNascimento(new Date("14/09/1998"));

        // 1) Criando um produto
        pessoaModel.create(pessoa1);


        List<Pessoa> pessoas = pessoaModel.findAll();
        System.out.println("Qtde de produtos encontrados : " + pessoas.size());
        Produto pessoabyId = pessoaModel.findById(pessoa1);
        System.out.println("Produto procurado por id: " + pessoabyId.getNome());
        pessoa1.setEmail("jaao@ciet.com");
        pessoaModel.update(pessoa1);
        pessoaModel.delete(pessoa1);
    }
}
