public class Espiral{

    private String nomeProduto;
    private int quantidade;
    private double preco;

    public Espiral(){
        this.nomeProduto = " - ";
    }

    public void setNomeDoProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getNomeDoProduto() {
        return nomeProduto;
    }

    public int getQuantidade(){
        return quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public void venderProduto(){
        quantidade--;
    }

    public boolean isEmpty(){
        return quantidade == 0;
    }
}