import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Algoritmo de Dijkstra: caminho minimo de origem unica em grafos
 * com pesos NAO-NEGATIVOS. Estrategia gulosa (greedy) com fila de
 * prioridade (min-heap).
 *
 * Complexidade: O((V + E) log V)
 *   - cada vertice eh extraido do heap no maximo uma vez   -> O(V log V)
 *   - cada aresta pode gerar uma insercao no heap          -> O(E log V)
 *   - somando os dois termos dominantes: O((V + E) log V)
 */
public class Dijkstra {

    public static Map<Character, Integer> executar(GrafoDirecionado grafo, char origem) {
        Map<Character, Integer> dist = new HashMap<>();
        Map<Character, Character> predecessor = new HashMap<>();
        Set<Character> visitados = new HashSet<>();

        for (char v : grafo.getVertices()) {
            dist.put(v, Integer.MAX_VALUE);
        }
        dist.put(origem, 0);

        PriorityQueue<EstadoVertice> fila = new PriorityQueue<>();
        fila.add(new EstadoVertice(origem, 0));

        System.out.println("Origem: " + origem);
        int passo = 0;

        while (!fila.isEmpty()) {
            EstadoVertice atual = fila.poll();
            char u = atual.getVertice();

            // "lazy deletion": pode haver entradas desatualizadas na fila;
            // se o vertice ja foi finalizado, ignoramos esta entrada.
            if (visitados.contains(u)) {
                continue;
            }
            visitados.add(u);
            passo++;
            System.out.printf("Passo %d: visita %c  (dist = %d)%n", passo, u, dist.get(u));

            for (Aresta aresta : grafo.vizinhos(u)) {
                char v = aresta.getDestino();
                int novaDistancia = dist.get(u) + aresta.getPeso();

                // RELAXAMENTO: o mesmo conceito mostrado no slide 5
                if (novaDistancia < dist.get(v)) {
                    int anterior = dist.get(v);
                    dist.put(v, novaDistancia);
                    predecessor.put(v, u);
                    fila.add(new EstadoVertice(v, novaDistancia));
                    String distAnteriorTexto = (anterior == Integer.MAX_VALUE) ? "inf" : String.valueOf(anterior);
                    System.out.printf("   relaxa %c->%c (peso %d): dist[%c] %s -> %d%n",
                            u, v, aresta.getPeso(), v, distAnteriorTexto, novaDistancia);
                }
            }
        }

        System.out.println("\nDistancias finais a partir de " + origem + ":");
        for (char v : grafo.getVertices()) {
            System.out.printf("  dist[%c] = %d%n", v, dist.get(v));
        }

        imprimirCaminho(predecessor, origem, 'E', dist);
        return dist;
    }

    private static void imprimirCaminho(Map<Character, Character> predecessor, char origem, char destino, Map<Character, Integer> dist) {
        if (!dist.containsKey(destino) || dist.get(destino) == Integer.MAX_VALUE) {
            return;
        }
        StringBuilder caminho = new StringBuilder(String.valueOf(destino));
        char atual = destino;
        while (atual != origem && predecessor.containsKey(atual)) {
            atual = predecessor.get(atual);
            caminho.insert(0, atual + " -> ");
        }
        System.out.printf("%nCaminho minimo %c -> %c: %s  (peso total = %d)%n",
                origem, destino, caminho, dist.get(destino));
    }
}
