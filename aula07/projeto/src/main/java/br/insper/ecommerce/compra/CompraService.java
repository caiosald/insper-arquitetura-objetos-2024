package br.insper.ecommerce.compra;
import br.insper.ecommerce.cliente.Cliente;
import br.insper.ecommerce.pagamento.MeioPagamento;


import java.util.ArrayList;
import java.util.Scanner;

public class CompraService {

    private ArrayList<Compra> compras = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    // 7 - Cadastrar Compra
    public void cadastrarCompra(Cliente cliente, ArrayList<Item> itens, MeioPagamento meioPagamento) {
        Compra compra = new Compra();
        compra.setCliente(cliente);
        compra.setMeioPagamento(meioPagamento);
        for (Item item : itens) {
            compra.adicionarItem(item);
        }
        compra.calculaPrecoTotal();
        compras.add(compra);
        System.out.println("Compra cadastrada com sucesso.");
    }

    // 8 - Listar Compras
    public void listarCompras() {
        System.out.println("Lista de compras:");
        for (Compra compra : compras) {
            System.out.println("Data da Compra: " + compra.getDataCompra());
            System.out.println("Cliente: " + compra.getCliente().getNome());
            System.out.println("Preço Total: R$" + compra.getPrecoTotal());
            System.out.println("Itens:");
            for (Item item : compra.getItens()) {
                System.out.println("  Produto: " + item.getProduto().getNome() + ", Quantidade: " + item.getQuantidade());
            }
            System.out.println("Meio de Pagamento: " + compra.getMeioPagamento().getClass().getSimpleName());
            System.out.println("-----");
        }
    }
}
