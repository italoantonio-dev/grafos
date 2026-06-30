# Demonstração prática em Java — Grupo 3

Implementação dos quatro algoritmos apresentados no seminário, usando
exatamente os mesmos grafos de exemplo mostrados nos slides. A saída
no console bate com as tabelas de execução passo a passo dos slides
8 (Dijkstra), 11 (Bellman-Ford), 16 (Prim) e 17 (Kruskal).

## Arquivos

- `Aresta.java` — aresta direcionada com peso (Dijkstra / Bellman-Ford)
- `GrafoDirecionado.java` — grafo direcionado, lista de adjacência
- `EstadoVertice.java` — par (vértice, valor) usado nas filas de prioridade
- `Dijkstra.java` — caminho mínimo, estratégia gulosa, O((V+E) log V)
- `BellmanFord.java` — caminho mínimo com suporte a pesos negativos, O(V·E)
- `ArestaMST.java` — aresta não-direcionada com peso (Prim / Kruskal)
- `GrafoNaoDirecionado.java` — grafo não-direcionado
- `UnionFind.java` — conjuntos disjuntos, usado pelo Kruskal
- `Prim.java` — Árvore Geradora Mínima, crescimento local, O(E log V)
- `Kruskal.java` — Árvore Geradora Mínima, visão global, O(E log E)
- `Main.java` — monta os grafos de exemplo e roda os quatro algoritmos em sequência

## Como compilar e rodar

Sem necessidade de Maven — são arquivos `.java` simples, sem `package`.

**Linha de comando (Windows, Linux ou Mac):**

```
javac -encoding UTF-8 *.java
java Main
```

**NetBeans / IntelliJ:**

Crie um projeto Java novo (sem Maven), copie todos os `.java` para
dentro de `src/`, e rode a classe `Main`. As IDEs já tratam encoding
automaticamente — só rode normalmente com o botão de Run.

## O que esperar na saída

A execução roda, em ordem: Dijkstra, Bellman-Ford, Prim, Kruskal e um
resumo final de complexidade. Os números devem bater exatamente com
os slides:

- Dijkstra (origem A): dist[B]=5, dist[C]=2, dist[D]=6, dist[E]=7;
  caminho mínimo A→E = A→C→B→E, peso total 7.
- Bellman-Ford (mesmo contraexemplo do slide 10): dist[B] = −1.
- Prim e Kruskal (mesmo grafo de 6 vértices): MST = {A-C, B-C, B-D, D-E, E-F},
  custo total 13 — idêntico nos dois algoritmos, ainda que a ordem em
  que as arestas são escolhidas seja diferente.

## Dica para a apresentação

Rodem o programa pelo menos uma vez ANTES do seminário, para garantir
que compila sem erros no computador/IDE que vão usar no dia. Se
quiserem demonstrar ao vivo, basta alternar para a IDE depois do
slide 20 e rodar a classe `Main` — a saída no console serve de prova
visual de que os algoritmos realmente encontram a solução ótima.
