
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
        Integer op;
        do {
            System.out.println("Deseja por peso?  \n 1 - Sim \n 2 - Nao");
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
        } while (!(op == 1 || op == 2));
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
        System.out.print("Digite o rotulo 1 da aresta: ");
        String rot1 = scanner.next();
        System.out.print("Digite o rotulo 2 da aresta: ");
        String rot2 = scanner.next();
        System.out.print("Digite o novo rotulo 1 da aresta: ");
        String newRot = scanner.next();
        grafo.rotularAresta(rot1, rot2, newRot);
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

    public void existenciaAresta(Grafo grafo) {
        System.out.print("Digite o primeiro vertice da aresta: ");
        String rotulo1 = scanner.next();
        System.out.print("Digite o segundo vertice da aresta: ");
        String rotulo2 = scanner.next();

        if (grafo.contemAresta(rotulo1, rotulo2)) {
            System.out.println("A aresta existe no grafo.");
        } else {
            System.out.println("A aresta NAO existe no grafo.");
        }
    }

    public void arestaAdjacente(Grafo grafo) {
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

    public void grafoVazio(Grafo grafo) {
        if (grafo.grafoVazio()) {
            System.out.println("O grafo está vazio.");
        } else {
            System.out.println("O grafo NÃO está vazio.");
        }
    }

    public void grafoCompleto(Grafo grafo) {
        if (grafo.grafoCompleto()) {
            System.out.println("O grafo é completo.");
        } else {
            System.out.println("O grafo NÃO é completo.");
        }
    }
}
