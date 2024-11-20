import java.util.Scanner;

public class Manipulacao {

    private Scanner scanner;

    Manipulacao(Scanner scanner_){
        scanner = scanner_;
    }

    public void criarAresta(Grafo grafo){
        System.out.print("Digite o rotulo 1 da aresta: ");
        String rot1 = scanner.next();
        System.out.print("Digite o rotulo 2 da aresta: ");
        String rot2 = scanner.next();
        Integer op;
        do{
            System.out.println("Deseja por peso? 1=sim | 2=nao");
            op = scanner.nextInt();
            switch (op) {
                case 1:
                    System.out.println("Digite o peso da aresta: ");
                    Float peso = scanner.nextFloat();
                    grafo.createAresta(rot1, rot2, peso);
                    break;
                case 2:
                    grafo.createAresta(rot1, rot2);
                    break;
                default:
                    System.out.println("opcao invalida");
                    break;
            }
        }while (op != 1 || op != 2);
    }

    public void criarVerticie(Grafo grafo){
        System.out.print("Digite o rotulo do vertice: ");
        String Vert = scanner.next();
        System.out.println("Deseja por peso? 1=sim | 2=nao");
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
        System.out.print("Digite o rotulo 1 da aresta: ");
        String rot1 = scanner.next();
        System.out.print("Digite o rotulo 2 da aresta: ");
        String rot2 = scanner.next();
        System.out.print("Digite o novo rotulo 1 da aresta: ");
        String newRot1 = scanner.next();
        System.out.print("Digite o novo rotulo 2 da aresta: ");
        String newRot2 = scanner.next();
        grafo.rotularAresta(rot1, rot2, newRot1, newRot2);
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
}