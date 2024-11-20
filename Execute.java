import java.util.Scanner;

public class Execute {

    private static Grafo grafo;
    private static Manipulacao manipulacao;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        manipulacao = new Manipulacao(scanner);
        Integer comando;
        do {
            System.out.println("\nInsira um comando de acordo com as seguintes opcoes:");

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
                    System.out.println("10 - Checagem de existencia de Aresta");
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
            case 1:
                grafo.mostrarMatrizAdjacencia();
                break;
            case 2:
                grafo.mostrarMatrizIncidencia();
                break;
            case 3:
                grafo.mostrarListaAdjacencia();
                break;
            default:
                System.out.println("Error op Representacao");
                break;
        }
    }

    private static void operacoesManipulacao(Integer comando, Scanner scanner){
        switch (comando){
            case 0:
                break;
            case 1:
                manipulacao.criarAresta(grafo);
                break;
            case 2:
                manipulacao.criarVerticie(grafo);
                break;
            case 3:
                manipulacao.removerAresta(grafo);
                break;
            case 4:
                manipulacao.rotularAresta(grafo);
                break;
            case 5:
                manipulacao.ponderarAresta(grafo);
                break;
            case 6:
                manipulacao.rotularVertice(grafo);
                break;
            case 7:
                manipulacao.ponderarVertice(grafo);
                break;
            case 8:
                manipulacao.checagemAdjacenciaVertice(grafo);
                break;
            case 9:
                manipulacao.checagemAdjacenciaAresta(grafo);
                break;
            case 10:
                manipulacao.existeAresta(grafo);
                break;
            case 11:
                manipulacao.quantideV_E(grafo);
                break;
            case 12:
                manipulacao.grafoVazio(grafo);
                break;
            case 13:
                manipulacao.grafoCompleto(grafo);
                break; 
            case 14:
                manipulacao.conectividade(grafo);
                break;
            case 15:
                break;
            case 16:
                break;
            default:
                System.out.println("Error opManipulacao");
                break;
        }
    }
}