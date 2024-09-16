package vendas;

import produtos.Produto;

public class Venda {
    private Produto produto;
    private int quantidadeVendida;
    private double valorTotal;

    // Construtor
    public Venda(Produto produto, int quantidadeVendida, double valorTotal) {
        this.produto = produto;
        this.quantidadeVendida = quantidadeVendida;
        this.valorTotal = valorTotal;
    }

    // Getters
    public Produto getProduto() {
        return produto;
    }

    public int getQuantidadeVendida() {
        return quantidadeVendida;
    }

    public double getValorTotal() {
        return valorTotal;
    }
}
