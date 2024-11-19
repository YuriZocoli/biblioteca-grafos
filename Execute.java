import java.util.Scanner;

public class Execute {

    private static Grafo grafo;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Integer comando;
        do {
            System.out.println("Insira um comando de acordo com as seguintes opcoes:");

            if(grafo != null){
                System.out.println("1 - Mostrar opcoes de representacao de grafo");
                System.out.println("2 - Mostrar opcoes de manipulacao de grafos");
                comando = scanner.nextInt();
                if(comando == 1){
                    System.out.println("Insira um comando de acordo com a representacao desejada:");
                    System.out.println("1 - Matriz de Adjacencia");
                    System.out.println("2 - Matriz de Incidencia");
                    System.out.println("3 - Lista de Adjacencia");
                    comando = scanner.nextInt();
                    operacoesRepresentacao(comando, scanner);
                }else if(comando == 2){
                    System.out.println("Insira um comando de acordo com a manipulacao desejada:");
                    System.out.println("1 - Criar Aresta");
                    System.out.println("2 - Criar Vertice");
                    System.out.println("3 - Remover Aresta");
                    System.out.println("4 - Rotular Aresta");
                    System.out.println("5 - Ponderar Aresta");
                    System.out.println("6 - Rotular Vertice");
                    System.out.println("7 - Ponderar Vertice");
                    System.out.println("8 - Checar adjacencia entre Verticies");
                    System.out.println("9 - Checar adjacencia entre Arestas");
                    System.out.println("10 - Checagem de existemcia de Aresta");
                    System.out.println("11 - Checagem da quantidade de Verticies e Arestas");
                    System.out.println("12 - Checagem de grafo vazio");
                    System.out.println("13 - Checagem de grafo completo");
                    System.out.println("14 - Checagem de conectividade");
                    System.out.println("15 - Checagem de quantidade de componentes fortemente conexos");
                    System.out.println("16 - Checagem de ponte e articulacao");
                    comando = scanner.nextInt();
                    operacoesManipulacao(comando, scanner);
                }
            }else{
                System.out.println("1 - Criar Grafo vazio");
                System.out.println("2 - Criar Grafo com N verticies");
                comando = scanner.nextInt();
                operacoesGrafoVazio(comando, scanner);
            }
            
        } while (comando != 0);
        scanner.close();
    }

    private static void operacoesGrafoVazio(Integer comando, Scanner scanner){
        if(comando == 0){
            return;
        }
        else if(comando == 1){
            grafo = new Grafo();
        }else if(comando == 2){
            System.out.println("Insira a quantidade de verticies desejada:");
            Integer quantidadeVerticies;
            quantidadeVerticies = scanner.nextInt();
            grafo = new Grafo(quantidadeVerticies);
        }else{
            System.out.println("Comando invalido!");
        }
    }

    private static void operacoesRepresentacao(Integer comando, Scanner scanner){
        switch (comando) {
            case 1 -> {

            }
            case 2 -> {

            }
            case 3 -> {

            }
            default -> System.out.println("Error opRepresentacao");
        }
    }

    private static void operacoesManipulacao(Integer comando, Scanner scanner){
        switch (comando){
            case 1 -> {
                System.out.print("Digite o rotulo 1 da aresta: ");
                String Rot1 = scanner.next();
                System.out.print("Digite o rotulo 2 da aresta: ");
                String Rot2 = scanner.next();
                System.out.println("Deseja por peso? 1=sim | 2=nao");
                int op = scanner.nextInt();
                switch (op) {
                    case 2 -> grafo.createAresta(Rot1, Rot2);
                    case 1 -> {
                        System.out.println("Digite o peso da aresta: ");
                        Float peso = scanner.nextFloat();
                        grafo.createAresta(Rot1, Rot2, peso);
                    }
                    default -> System.out.println("Error case1");
                }
            }
            case 2 -> {
                System.out.print("Digite o rotulo do vertice: ");
                String Vert = scanner.next();
                System.out.println("Deseja por peso? 1=sim | 2=nao");
                int op = scanner.nextInt();
                switch (op) {
                    case 2 -> grafo.createVertice(Vert);
                    case 1 -> {
                    System.out.print("Digite o peso do vertice: ");
                    Float peso = scanner.nextFloat();
                    grafo.createVertice(Vert, peso);
                    }
                    default -> System.out.println("Error case2");
                }
            }
            case 3 -> {
                System.out.print("Digite o rotulo 1 da aresta: ");
                String Rot1 = scanner.next();
                System.out.print("Digite o rotulo 2 da aresta: ");
                String Rot2 = scanner.next();
                grafo.removeAresta(Rot1, Rot2);
            }
            case 4 -> {
                System.out.print("Digite o rotulo 1 da aresta: ");
                String Rot1 = scanner.next();
                System.out.print("Digite o rotulo 2 da aresta: ");
                String Rot2 = scanner.next();
                System.out.print("Digite o novo rotulo 1 da aresta: ");
                String newRot1 = scanner.next();
                System.out.print("Digite o novo rotulo 2 da aresta: ");
                String newRot2 = scanner.next();
                grafo.rotularAresta(Rot1, Rot2, newRot1, newRot2);
            }
            case 5 -> {
                System.out.print("Digite o rotulo 1 da aresta: ");
                String Rot1 = scanner.next();
                System.out.print("Digite o rotulo 2 da aresta: ");
                String Rot2 = scanner.next();
                System.out.println("Digite o novo peso da aresta: ");
                Float peso = scanner.nextFloat();
                grafo.ponderarAresta(Rot1, Rot2, peso);
            }
            case 6 -> {
                System.out.print("Digite o rotulo do vertice: ");
                String Vert = scanner.next();
                System.out.print("Digite o novo rotulo do vertice: ");
                String newVert = scanner.next();
                grafo.rotularVertice(Vert, newVert);
            }
            case 7 -> {
                System.out.print("Digite o rotulo do vertice: ");
                String Vert = scanner.next();
                System.out.print("Digite o novo peso do vertice: ");
                Float newPeso = scanner.nextFloat();
                grafo.ponderarVertice(Vert, newPeso);
            }
            case 8 -> {
            }
            case 9 -> {
            }
            case 10 -> {
            }
            case 11 -> {
            }
            case 12 -> {
            }
            case 13 -> {
            }
            case 14 -> {
            }
            case 15 -> {
            }
            case 16 -> {
            }
            default -> System.out.println("Error opManipulacao");
        }
    }
}
