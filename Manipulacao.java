import java.util.ArrayList;
import java.util.Scanner;

public class Manipulacao {

    private Scanner scanner;

    Manipulacao(Scanner scanner_) {
        scanner = scanner_;
    }

    public void criarAresta(Grafo grafo) {
        System.out.print("Digite o vertice 1 da aresta: ");
        String rot1 = scanner.next();
        System.out.print("Digite o vertice 2 da aresta: ");
        String rot2 = scanner.next();

        if(!(grafo.contemVertice(rot1) && grafo.contemVertice(rot2))){
            System.out.println("Um dos vértices não existem");
        }

        Integer op;
        do {
            System.out.println("Deseja por peso?  \n 1 - Sim \n 2 - Nao");
            op = scanner.nextInt();
            switch (op) {
                case 1:
                    System.out.println("Digite o peso da aresta: ");
                    Float peso = scanner.nextFloat();
                    if(grafo.createAresta(rot1, rot2, peso)){
                        System.out.println("Aresta criada com sucesso");
                    }else{
                        System.out.println("Aresta já existe");
                    }
                    break;
                case 2:
                    if(grafo.createAresta(rot1, rot2)){
                        System.out.println("Aresta criada com sucesso");
                    }else{
                        System.out.println("Aresta já existe");
                    }
                    break;
                default:
                    System.out.println("opcao invalida");
                    break;
            }
        }while (!(op == 1 || op == 2));
    }

    public void criarVerticie(Grafo grafo) {
        System.out.print("Digite o rotulo do vertice: ");
        String Vert = scanner.next();
        System.out.println("Deseja por peso?  \n1 - Sim \n2 - Nao");
        int op = scanner.nextInt();
        switch (op) {
            case 2:
                grafo.createVertice(Vert);
                break;
            case 1:
                System.out.print("Digite o peso do vertice: ");
                Float peso = scanner.nextFloat();
                grafo.createVertice(Vert, peso);
                break;
            default:
                System.out.println("Error case2");
                break;
        }
    }

    public void removerAresta(Grafo grafo) {
        System.out.print("Digite o rotulo 1 da aresta: ");
        String rot1 = scanner.next();
        System.out.print("Digite o rotulo 2 da aresta: ");
        String rot2 = scanner.next();
        grafo.removeAresta(rot1, rot2);
    }

    public void rotularAresta(Grafo grafo) {
        System.out.print("Digite o rotulo da aresta: ");
        String rot = scanner.next();
        System.out.print("Digite o novo rotulo da aresta: ");
        String newRot = scanner.next();
        grafo.rotularAresta(rot, newRot);
    }

    public void ponderarAresta(Grafo grafo) {
        System.out.print("Digite o rotulo 1 da aresta: ");
        String rot1 = scanner.next();
        System.out.print("Digite o rotulo 2 da aresta: ");
        String rot2 = scanner.next();
        System.out.println("Digite o novo peso da aresta: ");
        Float peso = scanner.nextFloat();
        grafo.ponderarAresta(rot1, rot2, peso);
    }

    public void rotularVertice(Grafo grafo) {
        System.out.print("Digite o rotulo do vertice: ");
        String vert = scanner.next();
        System.out.print("Digite o novo rotulo do vertice: ");
        String newVert = scanner.next();
        grafo.rotularVertice(vert, newVert);
    }

    public void ponderarVertice(Grafo grafo) {
        System.out.print("Digite o rotulo do vertice: ");
        String vert = scanner.next();
        System.out.print("Digite o novo peso do vertice: ");
        Float newPeso = scanner.nextFloat();
        grafo.ponderarVertice(vert, newPeso);
    }

    public void existeAresta(Grafo grafo){
        String rot1, rot2;
        System.out.print("Digite o 1° rotulo: ");
        rot1 = scanner.next();
        System.out.print("Digite o 2° rotulo: ");
        rot2 = scanner.next();

        if(grafo.contemAresta(rot1, rot2)){
            System.out.println("Areste existe");
        } else {
            System.out.println("Aresta nao existe");
        }
    }

    public void checagemAdjacenciaVertice(Grafo grafo){
        String v1, v2;
        System.out.print("Digite o 1° vertice: ");
        v1 = scanner.next();
        System.out.print("Digite o 1° vertice: ");
        v2 = scanner.next();
        
        if(grafo.contemAresta(v1, v2)){
            System.out.println("Sao adjacentes");
        } else {
            System.out.println("Nao sao adjacentes");
        }
    }


    public void checagemAdjacenciaAresta(Grafo grafo) {
        System.out.println("Escolha um metodo de busca:  \n 1 - Por rotulo \n 2 - Por Vertices");
        String op = scanner.next();

        if (!op.equals("1") && !op.equals("2")) {
            System.out.println("Comando inválido! Por favor, escolha 1 ou 2.");
            return;
        }
        if (op.equals("1")) {
            System.out.print("Digite o primeiro Rotulo: ");
            String rotulo1 = scanner.next();
            Aresta aresta1 = grafo.encontrarArestaPorRotulo(rotulo1);
            if (aresta1 == null) {
                System.out.println("Aresta nao encontrada: ");
            }
            System.out.print("Digite o segundo Rotulo: ");
            String rotulo2 = scanner.next();
            Aresta aresta2 = grafo.encontrarArestaPorRotulo(rotulo2);
            if (aresta2 == null) {
                System.out.println("Aresta nao encontrada");
            }

            if (aresta1.getRotuloVertice1().equals(aresta2.getRotuloVertice1())
                    || aresta1.getRotuloVertice1().equals(aresta2.getRotuloVertice2())
                    || aresta1.getRotuloVertice2().equals(aresta2.getRotuloVertice1())
                    || aresta1.getRotuloVertice2().equals(aresta2.getRotuloVertice2())) {
                System.out.println("As arestas existem e possuem adjacencia no grafo.");
            } else {
                System.out.println("As arestas nao possuem adjacencia no grafo.");
            }
        }
        if (op.equals("2")) {
            System.out.print("Digite o primeiro vertice da primeira aresta: ");
            String v1 = scanner.next();
            System.out.print("Digite o segundo vertice da primeira aresta: ");
            String v2 = scanner.next();
            if (!grafo.contemAresta(v1, v2)) {
                System.out.println("Aresta nao encontrada");
                return;
            }

            System.out.print("Digite o primeiro vertice da segunda aresta: ");
            String v3 = scanner.next();
            System.out.print("Digite o segundo vertice da segunda aresta: ");
            String v4 = scanner.next();
            if (!grafo.contemAresta(v3, v4)) {
                System.out.println("Aresta nao encontrada");
                return;
            }

            if (v1.equals(v3) || v1.equals(v4) || v2.equals(v3) || v2.equals(v4)) {
                System.out.println("As arestas existem e possuem adjacencia no grafo.");
            } else {
                System.out.println("As arestas nao possuem adjacencia no grafo.");
            }
        }
    }

    public void quantideV_E(Grafo grafo){
        System.out.println("Quantidade de Vertices:" + grafo.getVerticies().size());
        System.out.println("Quantidade de Arestas:" + grafo.getArestas().size()); 
    }

    public void grafoVazio(Grafo grafo){
        if(grafo == null){
            System.out.println("Grafo Vazio");
        } else {
            System.out.println("Grafo nao e vazio");
        }
    }

    public void grafoCompleto(Grafo grafo){
        int V = grafo.getVerticies().size();
        if( (V*(V-1)/2) == grafo.getArestas().size()){
            System.out.println("Grafo completo");
        } else {
            System.out.println("Grafo nao e completo");
        }
    }

    public void conectividade(Grafo grafo)  {
        System.out.println(grafo.mostrarConectividade());
    }



    public Grafo criarGrafoAleatorio() {
        System.out.println("1 - Inserir quantidade de vertices");
        System.out.println("2 - inserir quantidade de vertices e arestas");
        Integer comando = scanner.nextInt();
        Integer quantidadeVertices, tipoGrafo;
        Grafo grafo = null;
        do {
            switch (comando) {
                case 1:
                    System.out.println("Insira a quantidade de vertices:");
                    quantidadeVertices = scanner.nextInt();
                    System.out.println("Deseja criar um grafo direcionado?");
                    System.out.println("1 - Sim (criar grafo direcionado)");
                    System.out.println("2 - Nao (criar grafo nao direcionado)");
                    tipoGrafo = scanner.nextInt();
                    if(tipoGrafo == 1){
                        grafo = GrafoDirecionado.gerarGrafoAleatorio(quantidadeVertices);
                    }else if(tipoGrafo == 2){
                        grafo = GrafoNaoDirecionado.gerarGrafoAleatorio(quantidadeVertices);
                    }
                    break;
                case 2:
                    System.out.println("Insira a quantidade de vertices:");
                    quantidadeVertices = scanner.nextInt();
                    System.out.println("Insira a quantidade de arestas:");
                    Integer quantidadeArestas = scanner.nextInt();
                    System.out.println("Deseja criar um grafo direcionado?");
                    System.out.println("1 - Sim (criar grafo direcionado)");
                    System.out.println("2 - Nao (criar grafo nao direcionado)");
                    tipoGrafo = scanner.nextInt();
                    if(tipoGrafo == 1){
                        grafo = GrafoDirecionado.gerarGrafoAleatorio(quantidadeVertices, quantidadeArestas);
                    }else if(tipoGrafo == 2){
                        grafo = GrafoNaoDirecionado.gerarGrafoAleatorio(quantidadeVertices, quantidadeArestas);
                    }
                    break;
                default:
                    System.out.println("comando invalido");
                    break;
            }
        } while (grafo == null);
        return grafo;
    }

    public void checagemPonteArticulacao(Grafo grafo) {
        ArrayList<Aresta> pontesTarjan = ((GrafoNaoDirecionado) grafo).encontrarArestasPontesTarjan();
        if (pontesTarjan.isEmpty()) {
            System.out.println("Não há arestas pontes no grafo.");
        } else {
            System.out.println("Arestas pontes:");
            for (Aresta ponte : pontesTarjan) {
                System.out.println(ponte.getRotuloVertice1() + " - " + ponte.getRotuloVertice2());
            }
        }
        ArrayList<Vertice> articulacoes = ((GrafoNaoDirecionado) grafo).encontrarVerticesArticulacao();
        if (articulacoes.isEmpty()) {
            System.out.println("Não há vértices de articulação no grafo.");
        } else {
            System.out.println("Vértices de articulação:");
            for (Vertice vertice : articulacoes) {
                System.out.println(vertice.getRotulo());
            }
        }
    }

    public void mostrarListaAdjacencia(Grafo grafo) {
        if (grafo instanceof GrafoDirecionado){
            System.out.println("1 - Lista por Sucessor");
            System.out.println("2 - Lista por Antecessor");
            Integer comando = scanner.nextInt();
            grafo.mostrarListaAdjacencia(comando == 1 ? true : false);
        }else {
            grafo.mostrarListaAdjacencia(null);
        }
    }
}