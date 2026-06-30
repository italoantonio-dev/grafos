/**
 * Aresta de um grafo NAO-DIRECIONADO e ponderado, usada pelos algoritmos
 * de Arvore Geradora Minima (Prim e Kruskal). Implementa Comparable para
 * que o Kruskal possa ordenar todas as arestas por peso crescente.
 */
public class ArestaMST implements Comparable<ArestaMST> {

    private final char origem;
    private final char destino;
    private final int peso;

    public ArestaMST(char origem, char destino, int peso) {
        this.origem = origem;
        this.destino = destino;
        this.peso = peso;
    }

    public char getOrigem() {
        return origem;
    }

    public char getDestino() {
        return destino;
    }

    public int getPeso() {
        return peso;
    }

    @Override
    public int compareTo(ArestaMST outra) {
        return Integer.compare(this.peso, outra.peso);
    }

    @Override
    public String toString() {
        return origem + "-" + destino + " (" + peso + ")";
    }
}
