import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Algoritmo de Prim: Arvore Geradora Minima (MST) crescendo localmente
 * a partir de um vertice inicial. A cada passo, escolhe a aresta mais
 * barata que liga a arvore atual a um vertice de fora dela.
 *
 * Complexidade: O(E log V)
 *   - cada aresta pode ser inserida na fila de prioridade uma vez -> O(E log V)
 *   - muito parecido com o Dijkstra, mas comparando peso da aresta,
 *     nao distancia acumulada desde a origem.
 */
public class Prim {

    public static List<ArestaMST> executar(GrafoNaoDirecionado grafo, char origem) {
        Set<Character> naArvore = new HashSet<>();
        List<ArestaMST> resultado = new ArrayList<>();
        PriorityQueue<ArestaMST> fila = new PriorityQueue<>();

        naArvore.add(origem);
        fila.addAll(grafo.vizinhos(origem));
        int custoTotal = 0;

        System.out.println("Vertice inicial: " + origem);

        while (naArvore.size() < grafo.getVertices().size() && !fila.isEmpty()) {
            ArestaMST menorAresta = fila.poll();
            char candidato = menorAresta.getDestino();

            // se o destino ja esta na arvore, essa aresta formaria ciclo -> ignora
            if (naArvore.contains(candidato)) {
                continue;
            }

            naArvore.add(candidato);
            resultado.add(menorAresta);
            custoTotal += menorAresta.getPeso();
            System.out.printf("Adiciona aresta %c-%c (peso %d)  -> inclui %c na arvore%n",
                    menorAresta.getOrigem(), candidato, menorAresta.getPeso(), candidato);

            for (ArestaMST aresta : grafo.vizinhos(candidato)) {
                if (!naArvore.contains(aresta.getDestino())) {
                    fila.add(aresta);
                }
            }
        }

        System.out.println("\nArestas da MST: " + resultado);
        System.out.println("Custo total: " + custoTotal);
        return resultado;
    }
}
