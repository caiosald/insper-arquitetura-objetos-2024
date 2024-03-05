package br.insper.ecommerce;

import br.insper.ecommerce.cliente.Cliente;
import br.insper.ecommerce.cliente.ClienteService;
import br.insper.ecommerce.compra.CompraService;
import br.insper.ecommerce.compra.Item;
import br.insper.ecommerce.produto.Produto;
import br.insper.ecommerce.produto.ProdutoService;
import br.insper.ecommerce.pagamento.Pix;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ClienteService clienteService = new ClienteService();
        ProdutoService produtoService = new ProdutoService();
        CompraService compraService = new CompraService();

        String opcao = "0";

        while (!opcao.equalsIgnoreCase("9")) {
            System.out.println("""
                    1 - Cadastrar Cliente
                    2 - Listar Clientes
                    3 - Excluir Cliente
                    4 - Cadastrar Produto
                    5 - Listar Produtos
                    6 - Excluir Produto
                    7 - Cadastrar Compra
                    8 - Listar Compras
                    9 - Sair
                    """);
            opcao = scanner.nextLine();

            switch (opcao) {
                case "1" -> {
                    System.out.println("Digite o nome do cliente:");
                    String nome = scanner.nextLine();
                    System.out.println("Digite o CPF do cliente:");
                    String cpf = scanner.nextLine();
                    clienteService.cadastrarCliente(nome, cpf);
                }
                case "2" -> clienteService.listarClientes();
                case "3" -> {
                    System.out.println("Digite o CPF do cliente para deletar:");
                    String cpf = scanner.nextLine();
                    clienteService.excluirClientes(cpf);
                }
                case "4" -> {
                    System.out.println("Digite o nome do produto:");
                    String nome = scanner.nextLine();
                    System.out.println("Digite o preço do produto:");
                    Double preco = Double.parseDouble(scanner.nextLine());
                    produtoService.cadastrarProduto(nome, preco);
                }
                case "5" -> produtoService.listarProdutos();
                case "6" -> {
                    System.out.println("Digite o nome do produto para deletar:");
                    String nome = scanner.nextLine();
                    produtoService.excluirProduto(nome);
                }
                case "7" -> {
                    System.out.println("Digite o CPF do cliente para a compra:");
                    String cpfCliente = scanner.nextLine();
                    Cliente clienteEncontrado = null;
                    for (Cliente cliente : clienteService.getClientes()) { 
                        if (cliente.getCpf().equals(cpfCliente)) {
                            clienteEncontrado = cliente;
                            break;
                        }
                    }
                    if (clienteEncontrado == null) {
                        System.out.println("Erro: Cliente não cadastrado.");
                        break; // Sai deste case se o cliente não for encontrado
                    }
                
                    System.out.println("Digite o nome do produto para adicionar à compra:");
                    String nomeProduto = scanner.nextLine();
                    Produto produtoEncontrado = null;
                    for (Produto produto : produtoService.getProdutos()) { 
                        if (produto.getNome().equalsIgnoreCase(nomeProduto)) {
                            produtoEncontrado = produto;
                            break;
                        }
                    }
                    if (produtoEncontrado == null) {
                        System.out.println("Erro: Produto não cadastrado.");
                        break; // Sai deste case se o produto não for encontrado
                    }
                
                    System.out.println("Digite a quantidade do produto:");
                    int quantidade = Integer.parseInt(scanner.nextLine());
                
                    Item item = new Item(quantidade, produtoEncontrado);
                    ArrayList<Item> itens = new ArrayList<>();
                    itens.add(item);
                
                    compraService.cadastrarCompra(clienteEncontrado, itens, new Pix()); 
                }
                
                
                case "8" -> compraService.listarCompras();
                case "9" -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
        scanner.close();
    }
}
