/**
 * Par (vertice, distancia/peso) usado como elemento da fila de prioridade
 * tanto no Dijkstra (distancia acumulada) quanto no Prim (peso da aresta).
 * Implementa Comparable para que o PriorityQueue saiba ordenar pelo menor valor.
 */
public class EstadoVertice implements Comparable<EstadoVertice> {

    private final char vertice;
    private final int valor;

    public EstadoVertice(char vertice, int valor) {
        this.vertice = vertice;
        this.valor = valor;
    }

    public char getVertice() {
        return vertice;
    }

    public int getValor() {
        return valor;
    }

    @Override
    public int compareTo(EstadoVertice outro) {
        return Integer.compare(this.valor, outro.valor);
    }
}
