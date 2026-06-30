import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Algoritmo de Kruskal: Arvore Geradora Minima (MST) olhando o grafo
 * inteiro de uma vez. Ordena TODAS as arestas por peso e vai aceitando
 * as mais baratas, desde que nao formem ciclo com as ja aceitas.
 *
 * Complexidade: O(E log E)
 *   - ordenar as E arestas domina o custo: O(E log E)
 *   - cada operacao de Union-Find e praticamente O(1)
 *   - como E log E e equivalente a E log V (V <= E + 1 em grafos conectados),
 *     tambem e comum ver essa complexidade escrita como O(E log V)
 */
public class Kruskal {

    public static List<ArestaMST> executar(GrafoNaoDirecionado grafo) {
        List<ArestaMST> arestasOrdenadas = grafo.getTodasArestas();
        Collections.sort(arestasOrdenadas); // usa o compareTo de ArestaMST (por peso)

        UnionFind unionFind = new UnionFind(grafo.getVertices());
        List<ArestaMST> resultado = new ArrayList<>();
        int custoTotal = 0;
        int quantidadeVertices = grafo.getVertices().size();

        for (ArestaMST aresta : arestasOrdenadas) {
            boolean aceitou = unionFind.union(aresta.getOrigem(), aresta.getDestino());

            if (aceitou) {
                resultado.add(aresta);
                custoTotal += aresta.getPeso();
                System.out.printf("Aceita  %c-%c (peso %d)%n", aresta.getOrigem(), aresta.getDestino(), aresta.getPeso());
            } else {
                System.out.printf("Rejeita %c-%c (peso %d)  -> formaria ciclo%n", aresta.getOrigem(), aresta.getDestino(), aresta.getPeso());
            }

            if (resultado.size() == quantidadeVertices - 1) {
                break; // arvore completa: V-1 arestas
            }
        }

        System.out.println("\nArestas da MST: " + resultado);
        System.out.println("Custo total: " + custoTotal);
        return resultado;
    }
}
