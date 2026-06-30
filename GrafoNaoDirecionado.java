import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Grafo NAO-DIRECIONADO e ponderado, usado pelos algoritmos de
 * Arvore Geradora Minima: Prim e Kruskal.
 *
 * Mantem tanto a lista de todas as arestas (usada pelo Kruskal, que
 * precisa ordenar todas elas) quanto a lista de adjacencia (usada pelo
 * Prim, que precisa saber rapidamente quais arestas saem de um vertice).
 */
public class GrafoNaoDirecionado {

    private final Map<Character, List<ArestaMST>> adjacencia = new LinkedHashMap<>();
    private final List<ArestaMST> todasArestas = new ArrayList<>();

    public void adicionarVertice(char vertice) {
        adjacencia.putIfAbsent(vertice, new ArrayList<>());
    }

    public void adicionarAresta(char a, char b, int peso) {
        adicionarVertice(a);
        adicionarVertice(b);
        ArestaMST aresta = new ArestaMST(a, b, peso);
        todasArestas.add(aresta);
        // grafo nao-direcionado: a aresta aparece na lista de adjacencia dos dois lados
        adjacencia.get(a).add(aresta);
        adjacencia.get(b).add(new ArestaMST(b, a, peso));
    }

    public List<ArestaMST> vizinhos(char vertice) {
        return adjacencia.getOrDefault(vertice, new ArrayList<>());
    }

    public List<ArestaMST> getTodasArestas() {
        return new ArrayList<>(todasArestas);
    }

    public List<Character> getVertices() {
        return new ArrayList<>(adjacencia.keySet());
    }
}
