/**
 * Demonstracao pratica: Grupo 3 - Otimizacao em Grafos
 * Caminhos Minimos (Dijkstra, Bellman-Ford) e
 * Arvores Geradoras Minimas (Prim, Kruskal)
 *
 * Os grafos usados aqui sao EXATAMENTE os mesmos exemplos mostrados
 * nos slides da apresentacao, entao a saida no console deve bater com
 * as tabelas de "execucao passo a passo" e os resultados finais
 * exibidos nos slides 8, 9, 11, 16, 17 e 18.
 *
 * Como compilar e rodar (sem necessidade de Maven):
 *   javac *.java
 *   java Main
 */
public class Main {

    public static void main(String[] args) {

        linha("DIJKSTRA  -  caminho minimo (pesos nao-negativos)");
        GrafoDirecionado grafoDijkstra = construirGrafoDijkstra();
        Dijkstra.executar(grafoDijkstra, 'A');

        linha("BELLMAN-FORD  -  mesmo problema, com peso negativo");
        GrafoDirecionado grafoNegativo = construirGrafoComPesoNegativo();
        BellmanFord.executar(grafoNegativo, 'S');

        linha("PRIM  -  Arvore Geradora Minima (crescimento local)");
        GrafoNaoDirecionado grafoMST = construirGrafoMST();
        Prim.executar(grafoMST, 'A');

        linha("KRUSKAL  -  Arvore Geradora Minima (visao global)");
        Kruskal.executar(grafoMST);

        linha("RESUMO DE COMPLEXIDADE");
        imprimirResumoComplexidade();
    }

    // Grafo do slide 7-9: A,B,C,D,E
    private static GrafoDirecionado construirGrafoDijkstra() {
        GrafoDirecionado g = new GrafoDirecionado();
        g.adicionarAresta('A', 'B', 6);
        g.adicionarAresta('A', 'C', 2);
        g.adicionarAresta('C', 'B', 3);
        g.adicionarAresta('C', 'D', 5);
        g.adicionarAresta('B', 'D', 1);
        g.adicionarAresta('B', 'E', 2);
        g.adicionarAresta('D', 'E', 4);
        return g;
    }

    // Contraexemplo do slide 10-11: S,A,B com aresta de peso -3
    private static GrafoDirecionado construirGrafoComPesoNegativo() {
        GrafoDirecionado g = new GrafoDirecionado();
        g.adicionarAresta('S', 'A', 2);
        g.adicionarAresta('S', 'B', 1);
        g.adicionarAresta('A', 'B', -3);
        return g;
    }

    // Grafo do slide 15-18: A,B,C,D,E,F (nao-direcionado)
    private static GrafoNaoDirecionado construirGrafoMST() {
        GrafoNaoDirecionado g = new GrafoNaoDirecionado();
        g.adicionarAresta('A', 'B', 4);
        g.adicionarAresta('A', 'C', 2);
        g.adicionarAresta('B', 'C', 1);
        g.adicionarAresta('B', 'D', 5);
        g.adicionarAresta('C', 'D', 8);
        g.adicionarAresta('C', 'E', 10);
        g.adicionarAresta('D', 'E', 2);
        g.adicionarAresta('D', 'F', 6);
        g.adicionarAresta('E', 'F', 3);
        return g;
    }

    private static void imprimirResumoComplexidade() {
        System.out.println("Dijkstra       O((V+E) log V)   - V extracoes do heap + E relaxamentos, cada um O(log V)");
        System.out.println("Bellman-Ford   O(V * E)         - relaxa as E arestas, repetindo (V-1) vezes");
        System.out.println("Prim           O(E log V)       - igual ao Dijkstra: cada aresta pode entrar no heap uma vez");
        System.out.println("Kruskal        O(E log E)       - dominado pela ordenacao inicial das arestas");
    }

    private static void linha(String titulo) {
        System.out.println();
        System.out.println("============================================================");
        System.out.println(titulo);
        System.out.println("============================================================");
    }
}
