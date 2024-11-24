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
            System.out.println("Um dos vertices nao existem");
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
                if(grafo.createVertice(Vert)){
                    System.out.println("Vertice criado com sucesso");
                }else{
                    System.out.println("Vertice ja existe");
                }
                break;
            case 1:
                System.out.print("Digite o peso do vertice: ");
                Float peso = scanner.nextFloat();
                if(grafo.createVertice(Vert, peso)){
                    System.out.println("Vertice com peso criado com sucesso");
                }else{
                    System.out.println("Vertice ja existe");
                }
                break;
            default:
                System.out.println("Comando invalido");
                break;
        }
    }

    public void removerAresta(Grafo grafo) {
        System.out.print("Digite o rotulo 1 da aresta: ");
        String rot1 = scanner.next();
        System.out.print("Digite o rotulo 2 da aresta: ");
        String rot2 = scanner.next();
        if(grafo.removeAresta(rot1, rot2)){
            System.out.println("Aresta removida com sucesso");
        }else{
            System.out.println("Aresta solicitada para remocao nao encontrada");
        }
    }

    public void rotularAresta(Grafo grafo) {
        System.out.print("Digite o rotulo da aresta: ");
        String rot = scanner.next();
        System.out.print("Digite o novo rotulo da aresta: ");
        String newRot = scanner.next();
        if(grafo.rotularAresta(rot, newRot)){
            System.out.println("Aresta rotulada com sucesso");
        }else{
            System.out.println("Aresta nao encontrada");
        }
    }

    public void ponderarAresta(Grafo grafo) {
        System.out.print("Digite o rotulo 1 da aresta: ");
        String rot1 = scanner.next();
        System.out.print("Digite o rotulo 2 da aresta: ");
        String rot2 = scanner.next();
        System.out.println("Digite o novo peso da aresta: ");
        Float peso = scanner.nextFloat();
        if(grafo.ponderarAresta(rot1, rot2, peso)){
            System.out.println("Peso da aresta alterado com sucesso");
        }else{
            System.out.println("Aresta nao encontrada");
        }
    }

    public void rotularVertice(Grafo grafo) {
        System.out.print("Digite o rotulo do vertice: ");
        String vert = scanner.next();
        System.out.print("Digite o novo rotulo do vertice: ");
        String newVert = scanner.next();
        if(grafo.rotularVertice(vert, newVert)){
            System.out.println("Vertice rotulado");
        }else{
            System.out.println("Vertice nao encontrado");
        }
    }

    public void ponderarVertice(Grafo grafo) {
        System.out.print("Digite o rotulo do vertice: ");
        String vert = scanner.next();
        System.out.print("Digite o novo peso do vertice: ");
        Float newPeso = scanner.nextFloat();
        if(grafo.ponderarVertice(vert, newPeso)){
            System.out.println("Peso do vertice alterado");
        }else{
            System.out.println("Vertice nao encontrado");
        }
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
        Integer quantidadeVertices, comandoInterno;
        Grafo grafo = null;
        do {
            switch (comando) {
                case 1:
                    System.out.println("Insira a quantidade de vertices:");
                    quantidadeVertices = scanner.nextInt();
                    System.out.println("Deseja criar um grafo direcionado?");
                    System.out.println("1 - Sim (criar grafo direcionado)");
                    System.out.println("2 - Nao (criar grafo nao direcionado)");
                    comandoInterno = scanner.nextInt();
                    if(comandoInterno == 1){
                        System.out.println("Deseja criar um grafo conexo?");
                        System.out.println("1 - Sim");
                        System.out.println("2 - Nao");
                        comandoInterno = scanner.nextInt();
                        if(comandoInterno == 1){
                            grafo = GrafoDirecionado.gerarGrafoAleatorioConexo(quantidadeVertices, 0);
                        }else{
                            grafo = GrafoDirecionado.gerarGrafoAleatorio(quantidadeVertices);
                        }
                    }else if(comandoInterno == 2){
                        System.out.println("Deseja criar um grafo conexo?");
                        System.out.println("1 - Sim");
                        System.out.println("2 - Nao");
                        comandoInterno = scanner.nextInt();
                        if(comandoInterno == 1){
                            grafo = GrafoNaoDirecionado.gerarGrafoAleatorioConexo(quantidadeVertices, 0);
                        }else{
                            grafo = GrafoNaoDirecionado.gerarGrafoAleatorio(quantidadeVertices);
                        }
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
                    comandoInterno = scanner.nextInt();
                    if(comandoInterno == 1){
                        System.out.println("Deseja criar um grafo conexo?");
                        System.out.println("1 - Sim");
                        System.out.println("2 - Nao");
                        comandoInterno = scanner.nextInt();
                        if(comandoInterno == 1){
                            grafo = GrafoDirecionado.gerarGrafoAleatorioConexo(quantidadeVertices, quantidadeArestas);
                        }else{
                            grafo = GrafoDirecionado.gerarGrafoAleatorio(quantidadeVertices, quantidadeArestas);
                        }
                    }else if(comandoInterno == 2){
                        System.out.println("Deseja criar um grafo conexo?");
                        System.out.println("1 - Sim");
                        System.out.println("2 - Nao");
                        comandoInterno = scanner.nextInt();
                        if(comandoInterno == 1){
                            grafo = GrafoNaoDirecionado.gerarGrafoAleatorioConexo(quantidadeVertices, quantidadeArestas);
                        }else{
                            grafo = GrafoNaoDirecionado.gerarGrafoAleatorio(quantidadeVertices, quantidadeArestas);
                        }
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
        System.out.println("1 - Checagem ponte via Tarjan");
        System.out.println("2 - Checagem ponte via Naive");
        System.out.println("3 - Checagem Articulacao");
        Integer comando = scanner.nextInt();

        switch (comando) {
            case 1:
                var pontesTarjan = ((GrafoNaoDirecionado) grafo).encontrarArestasPontesTarjan();
                if (pontesTarjan.isEmpty()) {
                    System.out.println("Nao ha arestas pontes no grafo.");
                } else {
                    System.out.println("Arestas pontes:");
                    for (Aresta ponte : pontesTarjan) {
                        System.out.println(ponte.getRotuloVertice1() + " - " + ponte.getRotuloVertice2());
                    }
                }
                break;
            case 2:
                var arestas = new ArrayList<>(grafo.getArestas());
                ArrayList<Aresta> pontesNaive = new ArrayList<>();
                for(Aresta aresta: arestas){
                    grafo.removeAresta(aresta.getRotuloVertice1(), aresta.getRotuloVertice2());
                    if(grafo.mostrarConectividade().equals("Grafo e desconexo")){
                        pontesNaive.add(aresta);
                    }
                    grafo.createAresta(aresta.getRotuloVertice1(), aresta.getRotuloVertice2(), aresta.getPeso());
                }
                if(pontesNaive.isEmpty()){
                    System.out.println("Nao ha arestas pontes no grafo.");
                }else{
                    System.out.println("Arestas pontes:");
                    for (Aresta ponte : pontesNaive) {
                        System.out.println(ponte.getRotuloVertice1() + " - " + ponte.getRotuloVertice2());
                    }
                }
                break;
            case 3:
                var articulacoes = grafo.encontrarVerticesArticulacao();
                if (articulacoes.isEmpty()) {
                    System.out.println("Nao ha vertices de articulação no grafo.");
                } else {
                    System.out.println("Vertices de articulação:");
                    for (Vertice vertice : articulacoes) {
                        System.out.println(vertice.getRotulo());
                    }
                }
                break;
            default:
                break;
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

    public void kosaraju(Grafo grafo){
        if(grafo.mostrarKosaraju() == null){
            System.out.println("Funciona somente para grafos Direcionados");
        } else {
            var cfcs = grafo.mostrarKosaraju();

            System.out.println("Componenetes Fortementes Conexos:");
            for (ArrayList<String> componente : cfcs) {
                System.out.println(componente);
            }
        }
    }
}