
import java.util.ArrayList;

public interface Grafo {

    public ArrayList<Aresta> getArestas();

    public ArrayList<Vertice> getVerticies();

    public Boolean createAresta(String rotuloVertice1, String rotuloVertice2);

    public Boolean createAresta(String rotuloVertice1, String rotuloVertice2, Float peso);

    public Boolean removeAresta(String rotuloVertice1, String rotuloVertice2);

    public Boolean contemAresta(String rotuloVertice1, String rotuloVertice2);

    public Boolean createVertice(String rotuloVertice);

    public Boolean createVertice(String rotuloVertice, Float peso);

    public Boolean contemVertice(String rotuloVertice);

    public Boolean rotularVertice(String rotuloAtual, String novoRotulo);

    public Boolean ponderarVertice(String rotuloVertice, Float novoPeso);

    public Boolean rotularAresta(String rotuloAresta, String novoRotulo);

    public Boolean ponderarAresta(String rotuloAresta1, String rotuloAresta2, Float novoPeso);

    public Aresta encontrarArestaPorRotulo(String rotulo);

    public Aresta encontrarAresta(String rotuloVertice1, String rotuloVertice2);

    public void mostrarMatrizAdjacencia();

    public void mostrarMatrizIncidencia();

    public void mostrarListaAdjacencia(Boolean aux);

    public String mostrarConectividade();

    public ArrayList<Aresta> encontrarArestasPontesTarjan();
    public ArrayList<Aresta> encontrarArestasPontesNaive();
    public ArrayList<Vertice> encontrarVerticesArticulacao();

    public ArrayList<ArrayList<String>> kosaraju();
    public ArrayList<String> fleury(Boolean method);
}
