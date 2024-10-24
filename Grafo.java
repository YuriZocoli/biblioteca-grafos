import java.util.ArrayList;

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

        for(Integer i = 0; i> quantidadeVerticies; i++){
            verticies.add(new Vertice(i.toString()));
        }
    }
    
    public ArrayList<Aresta> getArestas() {
        return arestas;
    }

    public ArrayList<Vertice> getVerticies() {
        return verticies;
    }
    //Temo que fazer alguma coisa para verificar se existe os rotulos da aresta pra criar ela 
    public void createAresta(String rotuloAresta1, String rotuloAresta2){
        if(contemAresta(rotuloAresta1, rotuloAresta2)){
            System.out.println("Aresta já existe");
        }else{
            arestas.add(new Aresta(rotuloAresta1, rotuloAresta2));
            System.out.println("Aresta criada com sucesso");
        }
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
        var isAnyRemoved = arestas.removeIf((aresta) -> aresta.getRotuloAresta1().equals(rotuloAresta1) && aresta.getRotuloAresta2().equals(rotuloAresta2));
        if(isAnyRemoved){
            System.out.println("Aresta removida com sucesso");
        }else{
            System.out.println("Aresta solicitada para remocao nao encontrada");
        }
    }

    public Boolean contemAresta(String rotuloAresta1, String rotuloAresta2){
        return arestas.stream().anyMatch(aresta -> aresta.getRotuloAresta1().equals(rotuloAresta1) && aresta.getRotuloAresta2().equals(rotuloAresta2));
    }
}


class Vertice{
    private String rotulo;
    private Float peso;

    Vertice(String rotulo, Float peso){
        this.rotulo = rotulo;
        this.peso = peso;
    }

    Vertice(String rotulo){
        this.rotulo = rotulo;
    }

    public Float getPeso() {
        return peso;
    }

    public String getRotulo() {
        return rotulo;
    }

    public void setPeso(Float peso) {
        this.peso = peso;
    }

    public void setRotulo(String rotulo) {
        this.rotulo = rotulo;
    }
}

class Aresta{
    private String rotuloAresta;
    private String rotuloAresta1;
    private String rotuloAresta2;
    private Float peso;

    Aresta(String rotuloAresta1, String rotuloAresta2, Float peso){
        this.rotuloAresta1 = rotuloAresta1;
        this.rotuloAresta2 = rotuloAresta2;
        this.peso = peso;
    }

    Aresta(String rotuloAresta1, String rotuloAresta2){
        this.rotuloAresta1 = rotuloAresta1;
        this.rotuloAresta2 = rotuloAresta2;
    }

    public String getRotuloAresta() {
        return rotuloAresta;
    }

    public String getRotuloAresta1() {
        return rotuloAresta1;
    }

    public String getRotuloAresta2() {
        return rotuloAresta2;
    }

    public void setRotuloAresta(String rotuloAresta) {
        this.rotuloAresta = rotuloAresta;
    }

    public Float getPeso() {
        return peso;
    }

    public void setPeso(Float peso) {
        this.peso = peso;
    }
}
