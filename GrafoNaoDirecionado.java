
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.Random;

public class GrafoNaoDirecionado implements Grafo {

    protected ArrayList<Vertice> verticies;
    protected ArrayList<Aresta> arestas;

    GrafoNaoDirecionado() {
        verticies = new ArrayList<Vertice>();
        arestas = new ArrayList<Aresta>();
    }

    GrafoNaoDirecionado(Integer quantidadeVerticies) {
        verticies = new ArrayList<Vertice>();
        arestas = new ArrayList<Aresta>();

        for (Integer i = 1; i <= quantidadeVerticies; i++) {
            verticies.add(new Vertice(i.toString()));
        }
    }

    public ArrayList<Aresta> getArestas() {
        return arestas;
    }

    public ArrayList<Vertice> getVerticies() {
        return verticies;
    }

    public Boolean createAresta(String rotuloVertice1, String rotuloVertice2) {
        if (contemAresta(rotuloVertice1, rotuloVertice2)) {
            return false;
        } else {
            arestas.add(new Aresta(rotuloVertice1, rotuloVertice2));
            return true;
        }
    }

    public Boolean createAresta(String rotuloVertice1, String rotuloVertice2, Float peso) {
        if (contemAresta(rotuloVertice1, rotuloVertice2)) {
            return false;
        } else {
            arestas.add(new Aresta(rotuloVertice1, rotuloVertice2, peso));
            return true;
        }
    }

    public Boolean removeAresta(String rotuloVertice1, String rotuloVertice2) {
        var isAnyRemoved = arestas.removeIf((aresta) -> aresta.getRotuloVertice1().equals(rotuloVertice1) && aresta.getRotuloVertice2().equals(rotuloVertice2));
        if (isAnyRemoved) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean contemAresta(String rotuloVertice1, String rotuloVertice2) {
        return arestas.stream().anyMatch(aresta -> (aresta.getRotuloVertice1().equals(rotuloVertice1) && aresta.getRotuloVertice2().equals(rotuloVertice2))
                || (aresta.getRotuloVertice1().equals(rotuloVertice2) && aresta.getRotuloVertice2().equals(rotuloVertice1)));
    }

    public Boolean createVertice(String rotuloVertice) {
        if (contemVertice(rotuloVertice)) {
            return false;
        } else {
            verticies.add(new Vertice(rotuloVertice));
            return true;
        }
    }

    public Boolean createVertice(String rotuloVertice, Float peso) {
        if (contemVertice(rotuloVertice)) {
            return false;
        } else {
            verticies.add(new Vertice(rotuloVertice, peso));
            return true;
        }
    }

    public Boolean contemVertice(String rotuloVertice) {
        return verticies.stream().anyMatch(vertice -> vertice.getRotulo().equals(rotuloVertice));
    }

    public Boolean rotularVertice(String rotuloAtual, String novoRotulo) {
        Vertice vertice = encontrarVertice(rotuloAtual);
        if (vertice != null) {
            vertice.setRotulo(novoRotulo);
            arestas.forEach(aresta -> {
                if (aresta.getRotuloVertice1().equals(rotuloAtual)) {
                    aresta.setRotuloVertice1(novoRotulo);
                } else if (aresta.getRotuloVertice2().equals(rotuloAtual)) {
                    aresta.setRotuloVertice2(novoRotulo);
                }
            });
            return true;
        } else {
            return false;
        }
    }

    public Boolean ponderarVertice(String rotuloVertice, Float novoPeso) {
        Vertice vertice = encontrarVertice(rotuloVertice);
        if (vertice != null) {
            vertice.setPeso(novoPeso);
            return true;
        } else {
            return true;
        }
    }

    public Boolean rotularAresta(String rotuloAresta, String novoRotulo) {
        Aresta aresta = encontrarArestaPorRotulo(rotuloAresta);
        if (aresta != null) {
            aresta.setRotuloAresta(novoRotulo);
            return true;
        } else {
            return false;
        }
    }

    public Boolean ponderarAresta(String rotuloAresta1, String rotuloAresta2, Float novoPeso) {
        Aresta aresta = encontrarAresta(rotuloAresta1, rotuloAresta2);
        if (aresta != null) {
            aresta.setPeso(novoPeso);
            return true;
        } else {
            return true;
        }
    }

    public Aresta encontrarArestaPorRotulo(String rotulo) {
        return arestas.stream()
                .filter(aresta -> aresta.getRotuloAresta().equals(rotulo))
                .findFirst()
                .orElse(null);
    }

    public void mostrarMatrizAdjacencia() {
        int n = verticies.size();
        int[][] matrizAdjacencia = new int[n][n]; // Cria uma matriz de zeros

        for (Aresta aresta : arestas) {
            String rotulo1 = aresta.getRotuloVertice1();
            String rotulo2 = aresta.getRotuloVertice2();
            Vertice i = encontrarVertice(rotulo1);
            Vertice j = encontrarVertice(rotulo2);
            matrizAdjacencia[i.getId()][j.getId()] = 1;
            matrizAdjacencia[j.getId()][i.getId()] = 1; // Para grafos não direcionados
        }

        System.out.println("Matriz de Adjacencia:");
        System.out.print("  ");
        for (int i = 0; i < verticies.size(); i++) {
            System.out.print(verticies.get(i).getRotulo() + " ");
        }
        System.out.println();

        for (int i = 0; i < matrizAdjacencia.length; i++) {
            System.out.print(verticies.get(i).getRotulo() + " ");
            for (int j = 0; j < matrizAdjacencia[i].length; j++) {
                System.out.print(matrizAdjacencia[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void mostrarMatrizIncidencia() {
        int n = verticies.size();
        int m = arestas.size();
        int[][] matrizIncidencia = new int[n][m];  // Matriz n x m (n vértices, m arestas)

        // Preenche a matriz de incidência
        for (int k = 0; k < arestas.size(); k++) {
            Aresta aresta = arestas.get(k);
            String rotulo1 = aresta.getRotuloVertice1();
            String rotulo2 = aresta.getRotuloVertice2();
            Vertice vertice1 = encontrarVertice(rotulo1);
            Vertice vertice2 = encontrarVertice(rotulo2);

            // Para grafos não direcionados
            matrizIncidencia[vertice1.getId()][k] = 1;
            matrizIncidencia[vertice2.getId()][k] = 1;

            // Se for grafo direcionado, pode ser algo como:
            // matrizIncidencia[vertice1.getId()][k] = 1;   // origem
            // matrizIncidencia[vertice2.getId()][k] = -1;  // destino
        }

        System.out.println("Matriz de Incidencia:");
        System.out.print("   ");
        for (int i = 0; i < arestas.size(); i++) {
            System.out.print("e" + arestas.get(i).getId() + "  ");
        }
        System.out.println();

        // Imprime os vértices na primeira coluna
        for (int i = 0; i < n; i++) {
            System.out.print(verticies.get(i).getRotulo() + "| ");
            for (int j = 0; j < m; j++) {
                System.out.print(matrizIncidencia[i][j] + "   ");
            }
            System.out.println();
        }
    }

    public void mostrarListaAdjacencia(Boolean aux) {
        System.out.println("Lista de Adjacencia:");

        for (Vertice vertice : verticies) {
            System.out.print(vertice.getRotulo() + ": ");  // Imprime o rótulo do vértice

            // Encontrar todos os vértices adjacentes
            boolean primeiro = true;
            for (Aresta aresta : arestas) {
                String rotulo1 = aresta.getRotuloVertice1();
                String rotulo2 = aresta.getRotuloVertice2();

                if (rotulo1.equals(vertice.getRotulo())) {
                    Vertice adjacente = encontrarVertice(rotulo2);
                    if (!primeiro) {
                        System.out.print(", ");
                    }
                    System.out.print(adjacente.getRotulo());
                    primeiro = false;
                }
                if (rotulo2.equals(vertice.getRotulo())) {
                    Vertice adjacente = encontrarVertice(rotulo1);
                    if (!primeiro) {
                        System.out.print(", ");
                    }
                    System.out.print(adjacente.getRotulo());
                    primeiro = false;
                }
            }
            System.out.println();  // Pula linha para o próximo vértice
        }
    }

    public String mostrarConectividade() {
        Random random = new Random();
        int n = random.nextInt(0, verticies.size() - 1);

        Vertice inicial = verticies.get(n);

        Set<Vertice> visitados = new HashSet<>(); // Conjunto para evitar visitas repetidas
        Stack<Vertice> stack = new Stack<>();    // Pilha para rastrear os vértices durante a DFS

        stack.push(inicial); // Adiciona o vértice inicial à pilha

        while (!stack.isEmpty()) {
            Vertice atual = stack.pop(); // Remove o vértice do topo da pilha

            if (!visitados.contains(atual)) {
                visitados.add(atual); // Marca o vértice como visitado

                // Adiciona os vértices adjacentes à pilha
                for (Aresta aresta : arestas) {
                    if (aresta.getRotuloVertice1().equals(atual.getRotulo())) {
                        Vertice adjacente = encontrarVertice(aresta.getRotuloVertice2());
                        if (adjacente != null && !visitados.contains(adjacente)) {
                            stack.push(adjacente);
                        }
                    } else if (aresta.getRotuloVertice2().equals(atual.getRotulo())) {
                        Vertice adjacente = encontrarVertice(aresta.getRotuloVertice1());
                        if (adjacente != null && !visitados.contains(adjacente)) {
                            stack.push(adjacente);
                        }
                    }
                }
            }
        }

        return visitados.size() < verticies.size() ? "Grafo e desconexo" : "Grafo e conexo";
    }

    public static Grafo gerarGrafoAleatorio(int quantidadeVertices) {
        // Criar vértices
        Grafo grafo = new GrafoNaoDirecionado();
        for (int i = 1; i <= quantidadeVertices; i++) {
            grafo.createVertice(String.valueOf(i));
        }

        Random random = new Random();
        int maximoArestas = (quantidadeVertices * (quantidadeVertices - 1)) / 2; // Máximo de arestas possíveis
        int quantidadeArestas = random.nextInt(maximoArestas + 1); // Número aleatório de arestas

        // Criar arestas aleatórias
        while (grafo.getArestas().size() < quantidadeArestas) {
            int v1 = random.nextInt(quantidadeVertices); // Índice do vértice 1
            int v2 = random.nextInt(quantidadeVertices); // Índice do vértice 2

            // Garantir que os vértices são diferentes (evitar laços)
            if (v1 != v2) {
                String rotulo1 = grafo.getVerticies().get(v1).getRotulo();
                String rotulo2 = grafo.getVerticies().get(v2).getRotulo();

                // Garantir que a aresta não existe ainda
                if (!grafo.contemAresta(rotulo1, rotulo2)) {
                    grafo.createAresta(rotulo1, rotulo2);
                }
            }
        }

        return grafo;
    }

    public static Grafo gerarGrafoAleatorio(int quantidadeVertices, Integer quantidadeArestas) {
        int maximoArestas = (quantidadeVertices * (quantidadeVertices - 1)) / 2; // Máximo de arestas possíveis
        if (quantidadeArestas > maximoArestas) {
            System.out.println("quantidade de arestas maior que o limite permitido, Limite: " + maximoArestas + ", quantidadeArestas: " + quantidadeArestas);
            return null;
        }

        Grafo grafo = new GrafoNaoDirecionado();
        // Criar vértices
        for (int i = 1; i <= quantidadeVertices; i++) {
            grafo.createVertice(String.valueOf(i));
        }

        Random random = new Random();

        // Criar arestas aleatórias
        while (grafo.getArestas().size() < quantidadeArestas) {
            int v1 = random.nextInt(quantidadeVertices); // Índice do vértice 1
            int v2 = random.nextInt(quantidadeVertices); // Índice do vértice 2

            // Garantir que os vértices são diferentes (evitar laços)
            if (v1 != v2) {
                String rotulo1 = grafo.getVerticies().get(v1).getRotulo();
                String rotulo2 = grafo.getVerticies().get(v2).getRotulo();

                // Garantir que a aresta não existe ainda
                if (!grafo.contemAresta(rotulo1, rotulo2)) {
                    grafo.createAresta(rotulo1, rotulo2);
                }
            }
        }

        return grafo;
    }

    public ArrayList<Aresta> encontrarArestasPontesTarjan() {
        ArrayList<Aresta> pontes = new ArrayList<>();
        int[] discovery = new int[verticies.size()];
        int[] low = new int[verticies.size()];
        boolean[] visited = new boolean[verticies.size()];
        int time = 0;

        // Inicializar arrays
        for (int i = 0; i < verticies.size(); i++) {
            discovery[i] = -1;
            low[i] = -1;
        }

        // Executar Tarjan para cada componente conectado
        for (Vertice vertice : verticies) {
            if (!visited[vertice.getId()]) {
                tarjanDFS(vertice, null, discovery, low, visited, pontes, time);
            }
        }

        return pontes;
    }

    public ArrayList<Vertice> encontrarVerticesArticulacao() {
        ArrayList<Vertice> articulacoes = new ArrayList<>();
        int[] discovery = new int[verticies.size()];
        int[] low = new int[verticies.size()];
        boolean[] visited = new boolean[verticies.size()];
        boolean[] isArticulation = new boolean[verticies.size()];
        int time = 0;

        // Inicializar arrays
        for (int i = 0; i < verticies.size(); i++) {
            discovery[i] = -1;
            low[i] = -1;
        }

        // Executar Tarjan para cada componente conectado
        for (Vertice vertice : verticies) {
            if (!visited[vertice.getId()]) {
                tarjanDFSArticulacao(vertice, null, discovery, low, visited, isArticulation, time);
            }
        }

        // Adicionar vértices identificados como articulação à lista
        for (Vertice vertice : verticies) {
            if (isArticulation[vertice.getId()]) {
                articulacoes.add(vertice);
            }
        }

        return articulacoes;
    }

    private void tarjanDFS(Vertice u, Vertice parent, int[] discovery, int[] low, boolean[] visited, ArrayList<Aresta> pontes, int time) {
        visited[u.getId()] = true;
        discovery[u.getId()] = low[u.getId()] = time++;

        for (Aresta aresta : arestas) {
            Vertice v = null;

            // Encontrar o vértice adjacente
            if (aresta.getRotuloVertice1().equals(u.getRotulo())) {
                v = encontrarVertice(aresta.getRotuloVertice2());
            } else if (aresta.getRotuloVertice2().equals(u.getRotulo())) {
                v = encontrarVertice(aresta.getRotuloVertice1());
            }

            if (v == null || v.equals(parent)) {
                continue; // Ignorar a aresta de retorno para o pai
            }

            if (!visited[v.getId()]) {
                // Recursão para o vértice adjacente
                tarjanDFS(v, u, discovery, low, visited, pontes, time);

                // Atualizar o valor de "low" do vértice atual
                low[u.getId()] = Math.min(low[u.getId()], low[v.getId()]);

                // Verificar se a aresta é uma ponte
                if (low[v.getId()] > discovery[u.getId()]) {
                    pontes.add(aresta);
                }
            } else {
                // Atualizar "low" com a descoberta anterior
                low[u.getId()] = Math.min(low[u.getId()], discovery[v.getId()]);
            }
        }
    }

    private void tarjanDFSArticulacao(Vertice u, Vertice parent, int[] discovery, int[] low, boolean[] visited, boolean[] isArticulation, int time) {
        visited[u.getId()] = true;
        discovery[u.getId()] = low[u.getId()] = time++;
        int children = 0;

        for (Aresta aresta : arestas) {
            Vertice v = null;

            // Encontrar o vértice adjacente
            if (aresta.getRotuloVertice1().equals(u.getRotulo())) {
                v = encontrarVertice(aresta.getRotuloVertice2());
            } else if (aresta.getRotuloVertice2().equals(u.getRotulo())) {
                v = encontrarVertice(aresta.getRotuloVertice1());
            }

            if (v == null || v.equals(parent)) {
                continue; // Ignorar a aresta de retorno para o pai
            }

            if (!visited[v.getId()]) {
                children++;
                tarjanDFSArticulacao(v, u, discovery, low, visited, isArticulation, time);

                // Atualizar o valor de "low" do vértice atual
                low[u.getId()] = Math.min(low[u.getId()], low[v.getId()]);

                // Caso 1: O vértice raiz da DFS é de articulação se tiver mais de 1 filho
                if (parent == null && children > 1) {
                    isArticulation[u.getId()] = true;
                }

                // Caso 2: Vértice não raiz é de articulação se "low[v] >= discovery[u]"
                if (parent != null && low[v.getId()] >= discovery[u.getId()]) {
                    isArticulation[u.getId()] = true;
                }
            } else {
                // Atualizar "low" para arestas de retorno
                low[u.getId()] = Math.min(low[u.getId()], discovery[v.getId()]);
            }
        }
    }

    public static Grafo gerarGrafoAleatorioConexo(Integer quantidadeVertices, Integer quantidadeArestas) {
        Random random = new Random();

        if (quantidadeArestas <= 0 || quantidadeArestas == null) {
            Integer maxQuantidadeArestas = (quantidadeVertices * (quantidadeVertices - 1)) / 2;
            quantidadeArestas = random.nextInt((quantidadeVertices - 1), maxQuantidadeArestas);
        }

        if (quantidadeArestas < quantidadeVertices - 1) {
            throw new IllegalArgumentException("Para garantir conectividade, o numero minimo de arestas deve ser igual a (numero de vertices - 1).");
        }

        Grafo grafo = new GrafoNaoDirecionado();

        // Passo 1: Criar vértices
        for (int i = 1; i <= quantidadeVertices; i++) {
            grafo.createVertice(String.valueOf(i));
        }

        // Passo 2: Garantir conectividade (árvore geradora)
        for (int i = 2; i <= quantidadeVertices; i++) {
            int v1 = i - 1; // Vértice anterior
            int v2 = i;     // Vértice atual
            grafo.createAresta(String.valueOf(v1), String.valueOf(v2));
        }

        // Passo 3: Adicionar arestas aleatórias adicionais
        int arestasCriadas = quantidadeVertices - 1; // Já criamos n-1 arestas
        while (arestasCriadas < quantidadeArestas) {
            int v1 = random.nextInt(quantidadeVertices) + 1;
            int v2 = random.nextInt(quantidadeVertices) + 1;

            if (v1 != v2 && !grafo.contemAresta(String.valueOf(v1), String.valueOf(v2))) {
                grafo.createAresta(String.valueOf(v1), String.valueOf(v2));
                arestasCriadas++;
            }
        }

        return grafo;
    }

    public Vertice encontrarVertice(String rotuloVertice) {
        return verticies.stream()
                .filter(vertice -> vertice.getRotulo().equals(rotuloVertice))
                .findFirst()
                .orElse(null);
    }

    public Vertice encontrarVertice(Integer IDVertice) {
        return verticies.stream()
                .filter(vertice -> vertice.getId() == IDVertice)
                .findFirst()
                .orElse(null);
    }

    public Aresta encontrarAresta(String rotuloVertice1, String rotuloVertice2) {
        return arestas.stream()
                .filter(aresta
                        -> (aresta.getRotuloVertice1().equals(rotuloVertice1) && aresta.getRotuloVertice2().equals(rotuloVertice2))
                || (aresta.getRotuloVertice1().equals(rotuloVertice2) && aresta.getRotuloVertice2().equals(rotuloVertice1)))
                .findFirst()
                .orElse(null);
    }

    public ArrayList<ArrayList<String>> kosaraju() {
        return null;
    }

    public Boolean verticeImpar(Vertice vertice) {
        int grau = 0;
        for (Aresta aresta : arestas) {
            String rotulo1 = aresta.getRotuloVertice1();
            String rotulo2 = aresta.getRotuloVertice2();

            if (rotulo1.equals(vertice.getRotulo()) || rotulo2.equals(vertice.getRotulo())) {
                grau++; // Incrementar o grau sempre que o vértice aparecer em uma aresta
            }
        }
        return grau % 2 != 0;
    }

    public Boolean imparTresVertice() {
        int impar = 0;
        for (Vertice vertice : verticies) {
            // Encontrar todas as arestas conectadas ao vértice
            if (verticeImpar(vertice)) {
                impar++;
            }
        }

        return (impar > 3);
    }

    public ArrayList<String> fleury() {
        ArrayList<Vertice> verticesImpares = new ArrayList<>();
        for (Vertice vertice : verticies) {
            if (verticeImpar(vertice)) {
                verticesImpares.add(vertice);
            }
        }

        if (verticesImpares.size() >= 3) {
            return null;
        }

        Vertice atual;
        if (!verticesImpares.isEmpty()) {
            atual = verticesImpares.get(0);
        } else {
            atual = verticies.get(0);
        }

        GrafoNaoDirecionado grafoAux = new GrafoNaoDirecionado();
        for (Vertice v : verticies) {
            grafoAux.createVertice(v.getRotulo());
        }
        for (Aresta a : arestas) {
            grafoAux.createAresta(a.getRotuloVertice1(), a.getRotuloVertice2());
        }

        // Começo do caminho:
        ArrayList<String> caminho = new ArrayList<>();

        while (!grafoAux.getArestas().isEmpty()) {
            ArrayList<Aresta> adjacentes = new ArrayList<>();
            for (Aresta a : grafoAux.getArestas()) {
                if (a.getRotuloVertice1().equals(atual.getRotulo()) || a.getRotuloVertice2().equals(atual.getRotulo())) {
                    adjacentes.add(a);
                }
            }

            Aresta escolhida = null;
            for (Aresta aresta : adjacentes) {
                grafoAux.removeAresta(aresta.getRotuloVertice1(), aresta.getRotuloVertice2());
                if (grafoAux.mostrarConectividade().equals("Conexo")) {
                    escolhida = aresta;
                    break;
                }
                grafoAux.createAresta(aresta.getRotuloVertice1(), aresta.getRotuloVertice2());
            }

            if (escolhida == null) {
                escolhida = adjacentes.get(0);
            }

            caminho.add(escolhida.getRotuloVertice1());

            atual = atual.getRotulo().equals(escolhida.getRotuloVertice1())
                    ? encontrarVertice(escolhida.getRotuloVertice2())
                    : encontrarVertice(escolhida.getRotuloVertice1());

            grafoAux.removeAresta(escolhida.getRotuloVertice1(), escolhida.getRotuloVertice2());
        }
        return caminho;
    }
}
