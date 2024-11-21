import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.Stack;

public class GrafoDirecionado extends Grafo {
    public GrafoDirecionado() {
        super(); 
    }
    public GrafoDirecionado(int quantidadeVerticies) {
        super(quantidadeVerticies); 
    }

    
    @Override
    public void createAresta(String rotuloAresta1, String rotuloAresta2) {
        if (contemAresta(rotuloAresta1, rotuloAresta2)) {
            System.out.println("Aresta já existe");
        } else {
            getArestas().add(new Aresta(rotuloAresta1, rotuloAresta2));
            System.out.println("Aresta direcionada criada com sucesso");
        }
    }

    @Override
    public void createAresta(String rotuloAresta1, String rotuloAresta2, Float peso) {
        if (contemAresta(rotuloAresta1, rotuloAresta2)) {
            System.out.println("Aresta já existe");
        } else {
            getArestas().add(new Aresta(rotuloAresta1, rotuloAresta2, peso));
            System.out.println("Aresta direcionada com peso criada com sucesso");
        }
    }

    @Override
    public Boolean contemAresta(String rotuloVertice1, String rotuloVertice2) {
        return getArestas().stream().anyMatch(aresta -> 
            aresta.getRotuloVertice1().equals(rotuloVertice1) &&
            aresta.getRotuloVertice2().equals(rotuloVertice2));
    }

    @Override
    public void mostrarMatrizAdjacencia() {
        int n = getVerticies().size();
        int[][] matrizAdjacencia = new int[n][n];

        for (Aresta aresta : getArestas()) {
            String rotulo1 = aresta.getRotuloVertice1();
            String rotulo2 = aresta.getRotuloVertice2();
            Vertice i = encontrarVertice(rotulo1);
            Vertice j = encontrarVertice(rotulo2);
            if (i != null && j != null) {
                matrizAdjacencia[i.getId()][j.getId()] = 1; // Apenas na direção da aresta
            }
        }

        System.out.println("Matriz de Adjacência (Direcionado):");
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

    @Override
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
    
            
            // matrizIncidencia[vertice1.getId()][k] = 1;   // origem
            // matrizIncidencia[vertice2.getId()][k] = -1;  // destino
        }
    
        System.out.println("Matriz de Incidencia:");
        System.out.print("   ");
        for (int i = 0; i < arestas.size(); i++) {
            System.out.print( "e" + arestas.get(i).getId() + "  ");
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

    public void mostrarListaAdjacenciaSucessores() {
        System.out.println("Lista de Adjacência de Sucessores:");
    
    // Para cada vértice, encontrar os sucessores
        for (Vertice vertice : verticies) {
            System.out.print(vertice.getRotulo() + "-> "); 
            
            boolean primeiro = true;
            for (Aresta aresta : arestas) {
                if (aresta.getRotuloVertice1().equals(vertice.getRotulo())) {
                    Vertice sucessor = encontrarVertice(aresta.getRotuloVertice2());
                    if (sucessor != null) {
                        if (!primeiro) {
                            System.out.print("-> ");
                        }
                        System.out.print(sucessor.getRotulo());
                        primeiro = false;
                    }
                }
            }
            System.out.println();  // Avança para o próximo vértice
        }
    }

    public void mostrarListaAdjacenciaAntecessores() {
        System.out.println("Lista de Adjacência de Antecessores:");
        
        // Para cada vértice, encontrar os antecessores
        for (Vertice vertice : verticies) {
            System.out.print(vertice.getRotulo() + "-> ");  // Exibe o rótulo do vértice atual
            
            boolean primeiro = true;
            for (Aresta aresta : arestas) {
                if (aresta.getRotuloVertice2().equals(vertice.getRotulo())) {
                    Vertice antecessor = encontrarVertice(aresta.getRotuloVertice1());
                    if (antecessor != null) {
                        if (!primeiro) {
                            System.out.print("-> ");
                        }
                        System.out.print(antecessor.getRotulo());
                        primeiro = false;
                    }
                }
            }
            System.out.println();  // Avança para o próximo vértice
        }
    }

    public boolean fortementeConexo() {
        for (Vertice vertice : getVerticies()) {
            if (!alcançavelAPartirDeTodos(vertice)) {
                return false;
            }
        }
        return true;
    }

    public boolean semifortementeConexo() {
    for (Vertice vertice : getVerticies()) {
        if (!alcançavelDeOuParaTodos(vertice)) {
            return false;
        }
    }
    return true;
}

    public Boolean simplesmenteConexo() {
        Random random = new Random(); 
        int n = random.nextInt(0, verticies.size()-1);
        
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
        return visitados.size() < verticies.size();
    }

    private boolean alcançavelDeOuParaTodos(Vertice vertice) {
        // Conjuntos para rastrear acessibilidade
        Set<Vertice> visitadosDe = new HashSet<>(); // Vértices alcançáveis a partir de "vertice"
        Set<Vertice> visitadosPara = new HashSet<>(); // Vértices que alcançam "vertice"

        // DFS para encontrar todos os vértices alcançáveis a partir de "vertice"
        buscaProfundidadeDirecionado(vertice, visitadosDe, true);

        // buscaProfundidadeDirecionado para encontrar todos os vértices que podem alcançar "vertice"
        buscaProfundidadeDirecionado(vertice, visitadosPara, false);

        // Se a união de ambos os conjuntos cobre todos os vértices, é válido
        return visitadosDe.size() + visitadosPara.size() - 1 == getVerticies().size();
    }

    private boolean alcançavelAPartirDeTodos(Vertice inicial) {
        Set<Vertice> visitados = new HashSet<>();
        Stack<Vertice> stack = new Stack<>();
        stack.push(inicial);

        while (!stack.isEmpty()) {
            Vertice atual = stack.pop();
            if (!visitados.contains(atual)) {
                visitados.add(atual);

                for (Aresta aresta : getArestas()) {
                    if (aresta.getRotuloVertice1().equals(atual.getRotulo())) {
                        Vertice adj = encontrarVertice(aresta.getRotuloVertice2());
                        if (adj != null && !visitados.contains(adj)) {
                            stack.push(adj);
                        }
                    }
                }
            }
        }
        return visitados.size() == getVerticies().size();
    }

    private void buscaProfundidadeDirecionado(Vertice inicial, Set<Vertice> visitados, boolean direcao) {
    Stack<Vertice> stack = new Stack<>();
    stack.push(inicial);

    while (!stack.isEmpty()) {
        Vertice atual = stack.pop();
        if (!visitados.contains(atual)) {
            visitados.add(atual);

            // Explora as arestas, considerando a direção
            for (Aresta aresta : getArestas()) {
                Vertice adjacente = null;
                if (direcao && aresta.getRotuloVertice1().equals(atual.getRotulo())) {
                    adjacente = encontrarVertice(aresta.getRotuloVertice2());
                } else if (!direcao && aresta.getRotuloVertice2().equals(atual.getRotulo())) {
                    adjacente = encontrarVertice(aresta.getRotuloVertice1());
                }

                if (adjacente != null && !visitados.contains(adjacente)) {
                    stack.push(adjacente);
                }
            }
        }
    }
}

    public static Grafo gerarGrafoAleatorio(int quantidadeVertices) {
        // Criar vértices
        Grafo grafo = new Grafo();
        for (int i = 1; i <= quantidadeVertices; i++) {
            grafo.createVertice(String.valueOf(i));
        }

        Random random = new Random();
        int maximoArestas = (quantidadeVertices * (quantidadeVertices - 1)); // Máximo de arestas possíveis
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
        int maximoArestas = (quantidadeVertices * (quantidadeVertices - 1)); // Máximo de arestas possíveis
        if(quantidadeArestas>maximoArestas){
            System.out.println("quantidade de arestas maior que o limite permitido, Limite: "+maximoArestas+", quantidadeArestas: "+quantidadeArestas);
            return null;
        }

        Grafo grafo = new Grafo();
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

}