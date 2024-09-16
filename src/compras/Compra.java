package compras;

import produtos.Produto;

public class Compra {

    private Produto produto;
    private int quantidade;
    private double margemGanho; // Percentual de ganho aplicado ao preço do produto

    public Compra(Produto produto, int quantidade, double margemGanho) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.margemGanho = margemGanho;
    }

    // Método para calcular o preço do produto com a margem de ganho aplicada
    public double calcularPrecoComGanho() {
        return produto.getPreco() * (1 + margemGanho / 100); // Aplica a porcentagem sobre o preço original
    }

    // Método para calcular o valor total da compra
    public double calcularTotal() {
        return calcularPrecoComGanho() * quantidade;
    }

    // Getters e Setters
    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getMargemGanho() {
        return margemGanho;
    }

    public void setMargemGanho(double margemGanho) {
        this.margemGanho = margemGanho;
    }

    @Override
    public String toString() {
        return "Compra: \n" +
               "Produto: " + produto.getNome() + "\n" +
               "Preço unitário (com ganho): " + calcularPrecoComGanho() + "\n" +
               "Quantidade: " + quantidade + "\n" +
               "Total: " + calcularTotal();
    }
}
