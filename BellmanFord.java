import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Algoritmo de Bellman-Ford: caminho minimo de origem unica, suporta
 * pesos NEGATIVOS e detecta ciclos negativos. Estrategia: relaxar
 * TODAS as arestas, repetidamente.
 *
 * Complexidade: O(V * E)
 *   - repete o processo de relaxamento (V - 1) vezes
 *   - cada repeticao percorre todas as E arestas
 */
public class BellmanFord {

    public static Map<Character, Integer> executar(GrafoDirecionado grafo, char origem) {
        Map<Character, Integer> dist = new HashMap<>();
        for (char v : grafo.getVertices()) {
            dist.put(v, Integer.MAX_VALUE);
        }
        dist.put(origem, 0);

        int quantidadeVertices = grafo.getVertices().size();
        System.out.println("Origem: " + origem);

        for (int rodada = 1; rodada <= quantidadeVertices - 1; rodada++) {
            boolean houveMudanca = false;
            System.out.println("Rodada " + rodada + ":");

            for (char u : grafo.getVertices()) {
                if (dist.get(u) == Integer.MAX_VALUE) {
                    continue; // ainda nao alcancado, nada a relaxar a partir dele
                }
                for (Aresta aresta : grafo.vizinhos(u)) {
                    char v = aresta.getDestino();
                    int novaDistancia = dist.get(u) + aresta.getPeso();

                    if (novaDistancia < dist.get(v)) {
                        dist.put(v, novaDistancia);
                        houveMudanca = true;
                        System.out.printf("   relaxa %c->%c (peso %d): dist[%c] = %d%n",
                                u, v, aresta.getPeso(), v, novaDistancia);
                    }
                }
            }

            if (!houveMudanca) {
                System.out.println("   nenhuma mudanca -> convergiu antes do limite de rodadas");
                break;
            }
        }

        boolean temCicloNegativo = detectarCicloNegativo(grafo, dist);
        System.out.println(temCicloNegativo
                ? "\nATENCAO: ciclo de peso negativo detectado no grafo!"
                : "\nNenhum ciclo negativo detectado.");

        System.out.println("\nDistancias finais a partir de " + origem + ":");
        for (char v : grafo.getVertices()) {
            String valor = (dist.get(v) == Integer.MAX_VALUE) ? "inf" : String.valueOf(dist.get(v));
            System.out.printf("  dist[%c] = %s%n", v, valor);
        }
        return dist;
    }

    // Rodada extra (a V-esima): se ainda houver relaxamento possivel,
    // existe um ciclo de peso negativo alcancavel a partir da origem.
    private static boolean detectarCicloNegativo(GrafoDirecionado grafo, Map<Character, Integer> dist) {
        for (char u : grafo.getVertices()) {
            if (dist.get(u) == Integer.MAX_VALUE) {
                continue;
            }
            List<Aresta> vizinhos = grafo.vizinhos(u);
            for (Aresta aresta : vizinhos) {
                char v = aresta.getDestino();
                if (dist.get(u) + aresta.getPeso() < dist.get(v)) {
                    return true;
                }
            }
        }
        return false;
    }
}
