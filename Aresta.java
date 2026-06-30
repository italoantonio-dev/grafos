/**
 * Representa uma aresta direcionada com peso, usada nos grafos
 * de Dijkstra e Bellman-Ford (lista de adjacencia).
 */
public class Aresta {

    private final char destino;
    private final int peso;

    public Aresta(char destino, int peso) {
        this.destino = destino;
        this.peso = peso;
    }

    public char getDestino() {
        return destino;
    }

    public int getPeso() {
        return peso;
    }

    @Override
    public String toString() {
        return destino + "(" + peso + ")";
    }
}
