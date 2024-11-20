import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.Random;

public class Grafo {
    private ArrayList<Vertice> verticies;
    private ArrayList<Aresta> arestas;

    Grafo(){
        verticies = new ArrayList<Vertice>();
        arestas = new ArrayList<Aresta>();
    }

    Grafo(Integer quantidadeVerticies){
        verticies = new ArrayList<Vertice>();
        arestas = new ArrayList<Aresta>();

        for(Integer i = 1; i <= quantidadeVerticies; i++){
            verticies.add(new Vertice(i.toString()));
        }
    }
    
    public ArrayList<Aresta> getArestas() {
        return arestas;
    }

    public ArrayList<Vertice> getVerticies() {
        return verticies;
    }
    
    public void createAresta(String rotuloAresta1, String rotuloAresta2){
        if(contemAresta(rotuloAresta1, rotuloAresta2)){
            System.out.println("Aresta ja existe");
        }else{
            arestas.add(new Aresta(rotuloAresta1, rotuloAresta2));
            System.out.println("Aresta criada com sucesso");
        }
        System.out.println("");
    }

    public void createAresta(String rotuloAresta1, String rotuloAresta2, Float peso){
        if(contemAresta(rotuloAresta1, rotuloAresta2)){
            System.out.println("Aresta já existe");
        }else{
            arestas.add(new Aresta(rotuloAresta1, rotuloAresta2, peso));
            System.out.println("Aresta criada com sucesso");
        }
    }

    public void removeAresta(String rotuloAresta1, String rotuloAresta2){
        var isAnyRemoved = arestas.removeIf((aresta) -> aresta.getRotuloVertice1().equals(rotuloAresta1) && aresta.getRotuloVertice2().equals(rotuloAresta2));
        if(isAnyRemoved){
            System.out.println("Aresta removida com sucesso");
        }else{
            System.out.println("Aresta solicitada para remocao nao encontrada");
        }
    }

    public Boolean contemAresta(String rotuloVertice1, String rotuloVertice2){
        return arestas.stream().anyMatch(aresta -> aresta.getRotuloVertice1().equals(rotuloVertice1) && aresta.getRotuloVertice2().equals(rotuloVertice2));
    }

    public void createVertice(String rotuloVertice) {
        if (contemVertice(rotuloVertice)) {
            System.out.println("Vértice já existe");
        } else {
            verticies.add(new Vertice(rotuloVertice));
            System.out.println("Vértice criado com sucesso");
        }
    }

    public void createVertice(String rotuloVertice, Float peso) {
        if (contemVertice(rotuloVertice)) {
            System.out.println("Vértice já existe");
        } else {
            verticies.add(new Vertice(rotuloVertice, peso));
            System.out.println("Vértice com peso criado com sucesso");
        }
    }

    public Boolean contemVertice(String rotuloVertice) {
        return verticies.stream().anyMatch(vertice -> vertice.getRotulo().equals(rotuloVertice));
    }

    public void rotularVertice(String rotuloAtual, String novoRotulo) {
        Vertice vertice = encontrarVertice(rotuloAtual);
        if (vertice != null) {
            vertice.setRotulo(novoRotulo);
            System.out.println("Vértice rotulado");
        } else {
            System.out.println("Vértice não encontrado");
        }
    }

    public void ponderarVertice(String rotuloVertice, Float novoPeso) {
        Vertice vertice = encontrarVertice(rotuloVertice);
        if (vertice != null) {
            vertice.setPeso(novoPeso);
            System.out.println("Peso do vértice alterado");
        } else {
            System.out.println("Vértice não encontrado");
        }
    }

    private Vertice encontrarVertice(String rotuloVertice) {
        return verticies.stream()
                       .filter(vertice -> vertice.getRotulo().equals(rotuloVertice))
                       .findFirst()
                       .orElse(null);
    }

    public void rotularAresta(String rotuloAresta1, String rotuloAresta2, String novoRotulo1, String novoRotulo2) {
        Aresta aresta = encontrarAresta(rotuloAresta1, rotuloAresta2);
    public void rotularAresta(String rotuloAresta, String novoRotulo) {
        Aresta aresta = encontrarArestaPorRotulo(rotuloAresta);
        if (aresta != null) {
            aresta.setRotuloVertice1(novoRotulo1);
            aresta.setRotuloVertice2(novoRotulo2);
            aresta.setRotuloAresta(novoRotulo);
            System.out.println("Aresta rotulada com sucesso");
        } else {
            System.out.println("Aresta não encontrada");
        }
    }

    public void ponderarAresta(String rotuloAresta1, String rotuloAresta2, Float novoPeso) {
        Aresta aresta = encontrarAresta(rotuloAresta1, rotuloAresta2);
        if (aresta != null) {
            aresta.setPeso(novoPeso);
            System.out.println("Peso da aresta alterado com sucesso");
        } else {
            System.out.println("Aresta não encontrada");
        }
    }

    private Aresta encontrarAresta(String rotuloAresta1, String rotuloAresta2) {
        return arestas.stream()
                      .filter(aresta -> 
                    aresta.getRotuloVertice1().equals(rotuloAresta1) && aresta.getRotuloVertice2().equals(rotuloAresta2))
                      .findFirst()
                      .orElse(null);
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
    
        System.out.println("Matriz de Adjacência:");
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

    public void mostrarListaAdjacencia() {
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
    

    public Boolean conectividadeNaoDirecionado() {
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

}

class Vertice {
    private String rotulo;
    private Float peso;
    private int id; 

    private static int contadorID = 0;

    Vertice(String rotulo, Float peso) {
        this.rotulo = rotulo;
        this.peso = peso;
        this.id = contadorID++;
    }

    Vertice(String rotulo) {
        this.rotulo = rotulo;
        this.id = contadorID++;
    }

    public int getId() {
        return id;
    }

    public Float getPeso() {
        return peso;
    }

    public void setPeso(Float peso) {
        this.peso = peso;
    }

    public String getRotulo() {
        return rotulo;
    }

    public void setRotulo(String rotulo) {
        this.rotulo = rotulo;
    }
}


class Aresta{
    private String rotuloAresta;
    private String rotuloVertice1;
    private String rotuloVertice2;
    private Float peso;
    private int id; 

    private static int contadorID = 0;

    Aresta(String rotuloAresta1, String rotuloAresta2, Float peso){
        this.rotuloVertice1 = rotuloAresta1;
        this.rotuloVertice2 = rotuloAresta2;
        this.peso = peso;
        this.id = contadorID++;
        this.rotuloAresta = ("e" + contadorID);
    }

    Aresta(String rotuloAresta1, String rotuloAresta2){
        this.rotuloVertice1 = rotuloAresta1;
        this.rotuloVertice2 = rotuloAresta2;
        this.id = contadorID++;
        this.rotuloAresta = ("e" + contadorID);
    }

    public int getId() {
        return id;
    }

    public String getRotuloAresta() {
        return rotuloAresta;
    }

    public String getRotuloVertice1() {
        return rotuloVertice1;
    }

    public String getRotuloVertice2() {
        return rotuloVertice2;
    }

    public void setRotuloAresta(String rotuloAresta) {
        this.rotuloAresta = rotuloAresta;
    }

    public void setRotuloVertice1(String rotuloAresta1) {
        this.rotuloVertice1 = rotuloAresta1;
    }

    public void setRotuloVertice2(String rotuloAresta2) {
        this.rotuloVertice2 = rotuloAresta2;
    }

    public Float getPeso() {
        return peso;
    }

    public void setPeso(Float peso) {
        this.peso = peso;
    }
}