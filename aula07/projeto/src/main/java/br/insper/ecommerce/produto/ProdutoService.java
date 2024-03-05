package br.insper.ecommerce.produto;

import java.util.ArrayList;
import java.util.Iterator;

public class ProdutoService {

    private ArrayList<Produto> produtos = new ArrayList<>();

    // 4 - Cadastrar Produto
    public void cadastrarProduto(String nome, Double preco) {
        if (nome == null || nome.isEmpty() || preco == null || preco <= 0) {
            System.out.println("Dados do produto inválidos.");
        } else {
            Produto produto = new Produto(nome, preco);
            produtos.add(produto);
            System.out.println("Produto cadastrado com sucesso.");
        }
    }

    // 5 - Listar Produtos
    public void listarProdutos() {
        System.out.println("Lista de produtos:");
        for (Produto produto : produtos) {
            System.out.println("Nome: " + produto.getNome() + ", Preço: R$" + produto.getPreco());
        }
    }

    // 6 - Excluir Produto
    public void excluirProduto(String nome) {
        Iterator<Produto> iterator = produtos.iterator();
        while (iterator.hasNext()) {
            Produto produto = iterator.next();
            if (produto.getNome().equalsIgnoreCase(nome)) {
                iterator.remove();
                System.out.println("Produto removido com sucesso.");
                return;
            }
        }
        System.out.println("Produto não encontrado.");
    }

    public ArrayList<Produto> getProdutos() {
        return this.produtos;
    }
}
