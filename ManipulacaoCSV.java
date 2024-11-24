import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ManipulacaoCSV {

    public static String[] listarArquivos(){
        String folderPath = System.getProperty("user.dir");

        // Criando um objeto File para a pasta
        File folder = new File(folderPath);

        // Verifica se é uma pasta válida
        if (folder.isDirectory()) {
            // Filtra os arquivos CSV usando um FilenameFilter
            File[] csvFiles = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".csv"));

            // Verifica se encontrou arquivos
            if (csvFiles != null && csvFiles.length > 0) {
                String[] filesNames = new String[csvFiles.length];
                Integer counter = 0;
                for (File file : csvFiles) {
                    filesNames[counter] = file.getName();
                    counter++;
                }
                return filesNames;
            } else {
                System.out.println("Nenhum arquivo CSV encontrado na pasta.");
                return null;
            }
        } else {
            System.out.println("O caminho fornecido não é uma pasta.");
            return null;
        }
    }

    public static Grafo buscarGrafo(String caminhoArquivo, Integer tipoGrafo){
        String filePath = caminhoArquivo;
        
        Grafo grafo;
        if(tipoGrafo == 1){
            grafo = new GrafoDirecionado();
        }else {
            grafo = new GrafoNaoDirecionado();
        }

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                Boolean isFirst = true;
                String vertice = "";
                for (String value : values) {
                    if(isFirst){
                        vertice = value;
                        grafo.createVertice(value);
                        isFirst = false;
                    }else{
                        grafo.createAresta(vertice, value);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return grafo;
    }

    public void salvarGrafoEmArquivo(Grafo grafo, String arquivoNome) {
        // Nome do arquivo CSV
        String fileName = arquivoNome+".csv";

        // Dados a serem salvos no arquivo (como exemplo)
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        for(Vertice vertice: grafo.getVerticies()){
            ArrayList<String> adjList = new ArrayList<>();
            adjList.add(vertice.getRotulo());
            data.add(adjList);
        }
        for(Aresta aresta: grafo.getArestas()){
            var actualList = data.stream().filter(adjList -> adjList.get(0).equals(aresta.getRotuloVertice1())).findFirst().orElse(null);
            actualList.add(aresta.getRotuloVertice2());
        }

        try (FileWriter writer = new FileWriter(fileName)) {
            // Escrevendo os dados
            for (ArrayList<String> row : data) {
                writer.append(String.join(";", row));
                writer.append("\n");
            }

            System.out.println("Arquivo CSV criado com sucesso: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
