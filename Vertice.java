public class Vertice {
    private String rotulo;
    private Float peso;
    private int id; 

    private static int contadorID = 0;

    Vertice(String rotulo, Float peso) {
        this.rotulo = rotulo;
        this.peso = peso;
        this.id = contadorID++;
    }

    Vertice(String rotulo) {
        this.rotulo = rotulo;
        this.id = contadorID++;
    }

    public int getId() {
        return id;
    }

    public Float getPeso() {
        return peso;
    }

    public void setPeso(Float peso) {
        this.peso = peso;
    }

    public String getRotulo() {
        return rotulo;
    }

    public void setRotulo(String rotulo) {
        this.rotulo = rotulo;
    }
}
