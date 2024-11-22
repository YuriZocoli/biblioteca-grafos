
import java.util.ArrayList;

public interface Grafo {
    public ArrayList<Aresta> getArestas();
    public ArrayList<Vertice> getVerticies();
    public void createAresta(String rotuloVertice1, String rotuloVertice2);
    public void createAresta(String rotuloVertice1, String rotuloVertice2, Float peso);
    public void removeAresta(String rotuloVertice1, String rotuloVertice2);
    public Boolean contemAresta(String rotuloVertice1, String rotuloVertice2);
    public void createVertice(String rotuloVertice);
    public void createVertice(String rotuloVertice, Float peso);
    public Boolean contemVertice(String rotuloVertice);
    public void rotularVertice(String rotuloAtual, String novoRotulo);
    public void ponderarVertice(String rotuloVertice, Float novoPeso);
    public void rotularAresta(String rotuloAresta, String novoRotulo);
    public void ponderarAresta(String rotuloAresta1, String rotuloAresta2, Float novoPeso);
    public Aresta encontrarArestaPorRotulo(String rotulo);
    public void mostrarMatrizAdjacencia();
    public void mostrarMatrizIncidencia();
    public void mostrarListaAdjacencia();
    public String mostrarConectividade();
}
