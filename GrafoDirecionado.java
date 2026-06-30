import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Grafo direcionado e ponderado, representado por lista de adjacencia.
 * Usado pelos algoritmos de caminho minimo: Dijkstra e Bellman-Ford.
 */
public class GrafoDirecionado {

    // LinkedHashMap preserva a ordem de insercao dos vertices,
    // o que deixa a impressao dos resultados mais previsivel.
    private final Map<Character, List<Aresta>> adjacencia = new LinkedHashMap<>();

    public void adicionarVertice(char vertice) {
        adjacencia.putIfAbsent(vertice, new ArrayList<>());
    }

    public void adicionarAresta(char origem, char destino, int peso) {
        adicionarVertice(origem);
        adicionarVertice(destino);
        adjacencia.get(origem).add(new Aresta(destino, peso));
    }

    public List<Aresta> vizinhos(char vertice) {
        return adjacencia.getOrDefault(vertice, new ArrayList<>());
    }

    public List<Character> getVertices() {
        return new ArrayList<>(adjacencia.keySet());
    }
}
