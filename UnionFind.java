import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Estrutura de Conjuntos Disjuntos (Union-Find), usada pelo Kruskal
 * para verificar rapidamente se duas arestas formariam um ciclo
 * (ou seja, se os dois vertices ja pertencem ao mesmo grupo/arvore).
 *
 * Com as duas otimizacoes classicas - compressao de caminho (path
 * compression) em find() e uniao por rank em union() - cada operacao
 * fica praticamente O(1) (mais precisamente, O(alfa(V)), a funcao
 * inversa de Ackermann, que cresce tao devagar que e tratada como
 * constante na pratica).
 */
public class UnionFind {

    private final Map<Character, Character> pai = new HashMap<>();
    private final Map<Character, Integer> rank = new HashMap<>();

    public UnionFind(List<Character> vertices) {
        for (char v : vertices) {
            pai.put(v, v);
            rank.put(v, 0);
        }
    }

    // encontra o "representante" do grupo de v, com compressao de caminho
    public char find(char v) {
        if (pai.get(v) != v) {
            pai.put(v, find(pai.get(v)));
        }
        return pai.get(v);
    }

    // une os grupos de a e b; retorna false se ja estavam no mesmo grupo (formaria ciclo)
    public boolean union(char a, char b) {
        char raizA = find(a);
        char raizB = find(b);

        if (raizA == raizB) {
            return false; // mesmo grupo -> uniria criaria um ciclo
        }

        if (rank.get(raizA) < rank.get(raizB)) {
            pai.put(raizA, raizB);
        } else if (rank.get(raizA) > rank.get(raizB)) {
            pai.put(raizB, raizA);
        } else {
            pai.put(raizB, raizA);
            rank.put(raizA, rank.get(raizA) + 1);
        }
        return true;
    }
}
