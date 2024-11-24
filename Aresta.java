
public class Aresta {

    private String rotuloAresta;
    private String rotuloVertice1;
    private String rotuloVertice2;
    private Float peso;
    private int id;

    private static int contadorID = 0;

    Aresta(String rotuloVertice1, String rotuloVertice2, Float peso) {
        this.rotuloVertice1 = rotuloVertice1;
        this.rotuloVertice2 = rotuloVertice2;
        this.peso = peso;
        this.id = contadorID++;
        this.rotuloAresta = ("e" + contadorID);
    }

    Aresta(String rotuloVertice1, String rotuloVertice2) {
        this.rotuloVertice1 = rotuloVertice1;
        this.rotuloVertice2 = rotuloVertice2;
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

    public void setRotuloVertice1(String rotuloVertice1) {
        this.rotuloVertice1 = rotuloVertice1;
    }

    public void setRotuloVertice2(String rotuloVertice2) {
        this.rotuloVertice2 = rotuloVertice2;
    }

    public Float getPeso() {
        return peso;
    }

    public void setPeso(Float peso) {
        this.peso = peso;
    }
}
