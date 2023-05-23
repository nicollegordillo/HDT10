import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Aplicacion {
    private int[][] distancias;
    private String[][] recorridos;
    private String[] vertices;
    private int size;
    FloydWarshall fw;

    public Aplicacion() {
    }

    public String rutamascorta(String origen, String destino){
        String resultado = "";
        fw = new FloydWarshall(distancias, recorridos, vertices, size + 1);
        fw.calcularRutas();
        for (int i = 0; i < size + 1; i++) {
            for (int j = 0; j < size + 1; j++) {
                if(origen.equals(destino)){
                    resultado+=origen+" -> "+fw.getRecorridos()[i][j];
                    break;
                }
                else if(vertices[i].equals(origen)&&vertices[j].equals(destino)){
                    resultado+=origen+" -> "+fw.getRecorridos()[i][j]+" -> "+destino;
                }
            }
        }
        for (int i = 0; i < size + 1; i++) {
            for (int j = 0; j < size + 1; j++) {
                if(vertices[i].equals(origen)&&vertices[j].equals(destino)){
                    resultado+="\nTiempo Normal: "+fw.getDistancias()[i][j];
                    break;
                }
            }
        }
        return resultado;
    }

    public String Centro(){
        return "";
    }

    public String llenarMatrizDistancias(String filePath) {
        String resultado="";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            size = contarLineas(filePath);
            distancias = new int[size][size];
    
            //String[] cities = new String[size]; // Array to store city names
            ArrayList<String> columnas= new ArrayList<>();
            ArrayList<String> filas= new ArrayList<>();
            ArrayList<String[]> lineas= new ArrayList<>();
            String line;

            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(" ");
                String city1 = tokens[0];
                String city2 = tokens[1];
                lineas.add(tokens);
    
                // Store unique city names in the array
                if (!containsCity(columnas, city1)) {
                    columnas.add(city1);
                    
                }
                if (!containsCity(columnas, city2)) {
                    columnas.add(city2);
                }
            }
            filas=columnas;
            distancias = new int[columnas.size()][columnas.size()];
            for(int i=0; i< columnas.size();i++){
                for(int j=0;j<columnas.size();j++){
                    if(i==j){
                        distancias[i][j]=0;
                    }
                    else{
                        distancias[i][j]=1000;
                    }
                }
            }
            for (String[] tokens : lineas) {
                String city1 = tokens[0];
                String city2 = tokens[1];
                int distance = Integer.parseInt(tokens[2]);
    
                int rowIndex = filas.indexOf(city1);
                int columnIndex = columnas.indexOf(city2);
    
                distancias[rowIndex][columnIndex] = distance;
            }
            
            for(int i=0;i<columnas.size();i++){
                for(int j=0;j<columnas.size();j++){
                    resultado += distancias[i][j]+" ";
                }
                resultado+="\n";
                
            }
            vertices=new String[columnas.size()];
            for(int i=0;i<columnas.size();i++){
                vertices[i]=columnas.get(i);
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultado;
    }

    private boolean containsCity(ArrayList<String> cities, String city) {
        for (int i = 0; i < cities.size(); i++) {
            if (cities.get(i) != null && cities.get(i).equals(city)) {
                return true;
            }
        }
        return false;
    }

    public String llenarMatrizRecorridos(String filePath) {
        String resultado="";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            size = contarLineas(filePath);
            
    
            //String[] cities = new String[size]; // Array to store city names
            ArrayList<String> cities= new ArrayList<>();
            String line;

            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(" ");
                String city1 = tokens[0];
                String city2 = tokens[1];
    
                // Store unique city names in the array
                if (!containsCity(cities, city1)) {
                    cities.add(city1);
                    
                }
                if (!containsCity(cities, city2)) {
                    cities.add(city2);
                }
            }
            recorridos = new String[cities.size()][cities.size()];
            for(int i=0;i<cities.size();i++){
                for(int j=0;j<cities.size();j++){
                    recorridos[i][j]=cities.get(j);
                }
                
            }
           
            for(int i=0;i<cities.size();i++){
                for(int j=0;j<cities.size();j++){
                    resultado += recorridos[i][j]+" ";
                }
                resultado+="\n";
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultado;
    }

    private int contarLineas(String filePath) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            int lines = 0;
            while (br.readLine() != null) {
                lines++;
            }
            return lines;
        }
    }

    public int[][] getDistancias() {
        return distancias;
    }

    public String[][] getRecorridos() {
        return recorridos;
    }

    public int getSize() {
        return size;
    }
}
