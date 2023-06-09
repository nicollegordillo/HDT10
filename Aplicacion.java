import java.util.ArrayList;

public class Aplicacion {
    private int[][] distancias;
    private String[][] recorridos;
    private String[] vertices;
    private int size;
    FloydWarshall fw;
    private ArrayList<String[]> lineas;
    private ArrayList<String> lineas1;

    public Aplicacion() {
        lineas= new ArrayList<>();
    }

    public String rutamascorta(String origen, String destino){
        String resultado = "";
        fw = new FloydWarshall(distancias, recorridos, vertices, vertices.length);
        fw.calcularRutas();
        for (int i = 0; i < vertices.length; i++) {
            for (int j = 0; j < vertices.length; j++) {
                if(origen.equals(destino)){
                    resultado+=origen+" -> "+fw.getRecorridos()[i][j];
                    break;
                }
                else if(vertices[i].equals(origen)&&vertices[j].equals(destino)){
                    resultado+=origen+" -> "+fw.getRecorridos()[i][j]+" -> "+destino;
                }
            }
        }
        for (int i = 0; i < vertices.length; i++) {
            for (int j = 0; j < vertices.length; j++) {
                if(vertices[i].equals(origen)&&vertices[j].equals(destino)){
                    resultado+="\nTiempo Normal: "+fw.getDistancias()[i][j];
                    break;
                }
            }
        }
        return resultado;
    }

    public String Centro(){
        fw = new FloydWarshall(distancias, recorridos, vertices, vertices.length);
        fw.calcularRutas();
        int[][] distances = fw.getDistancias();
        String resultado="";
        ArrayList<Integer> eccentricities=new ArrayList<>();
        for (int col = 0; col < distances[0].length; col++) {
            int max = Integer.MIN_VALUE; 
            boolean hasZero = false;
            boolean hasNonZero = false;

            for (int row = 0; row < distances.length; row++) {
                if (distances[row][col] == 0) {
                    hasZero = true;
                } else if (distances[row][col] != 1000 && distances[row][col] > max) {
                    max = distances[row][col]; 
                    hasNonZero = true;
                }
            }

            if (hasZero && !hasNonZero) {
                max = 1000;
            }

            eccentricities.add(max); // Agrega el máximo al arraylist
        }
        int min = eccentricities.get(0); 
        
        for (int i = 1; i < eccentricities.size(); i++) {
            int currentNumber = eccentricities.get(i);
            if (currentNumber < min) {
                min = currentNumber; // Actualiza el minimo si encuentra uno menor
            }
        }
        for (int i = 0; i < eccentricities.size(); i++) {
            if (eccentricities.get(i) == min) {
                resultado+= "\n"+vertices[i];
            }
        }
        

        // Devuelve el vertice
        return resultado;
    }

    public String llenarMatrizDistancias( ArrayList<String> lineas1 ) {
        lineas= new ArrayList<>();
        String resultado="";
            this.lineas1=lineas1;
            size = lineas1.size();
            distancias = new int[size][size];
    
            ArrayList<String> columnas= new ArrayList<>();
            ArrayList<String> filas= new ArrayList<>();
            int k=size;
            int cont=0;
            while (k!= 0) {
                String[] tokens = lineas1.get(cont).split(" ");
                String city1 = tokens[0];
                String city2 = tokens[1];
                lineas.add(tokens);
    
                // Guarda los nombres
                if (!containsCity(columnas, city1)) {
                    columnas.add(city1);
                    
                }
                if (!containsCity(columnas, city2)) {
                    columnas.add(city2);
                }
                k=k-1;
                cont=cont+1;
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

    public String llenarMatrizRecorridos(ArrayList<String> lineas1) {
        String resultado="";
            size = lineas1.size();
            this.lineas1=lineas1;
            ArrayList<String> cities= new ArrayList<>();
            int k=size;
            System.out.println(size);
            int cont=0;
            while (k!= 0) {
                String[] tokens = lineas1.get(cont).split(" ");
                String city1 = tokens[0];
                String city2 = tokens[1];
    
                // Guarda los nombres
                if (!containsCity(cities, city1)) {
                    cities.add(city1);
                    
                }
                if (!containsCity(cities, city2)) {
                    cities.add(city2);
                }
                k=k-1;
                cont=cont+1;
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
        return resultado;
    }

    public ArrayList<String> get_lineas()  
    {
       return this.lineas1;
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
    public void modificarDistancia(String ciudad1, String ciudad2, int posicion) {
        int indiceCiudad1 = -1;
        int indiceCiudad2 = -1;
        String[] tokens =new String[1];
        // Buscar los índices de las ciudades en el arreglo de vértices
        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i].equals(ciudad1)) {
                indiceCiudad1 = i;
            } else if (vertices[i].equals(ciudad2)) {
                indiceCiudad2 = i;
            }
        }
    
        // Verificar si se encontraron las ciudades en el arreglo de vértices
        if (indiceCiudad1 != -1 && indiceCiudad2 != -1) {
            // Obtener el arreglo de cadenas correspondiente al par de ciudades en el ArrayList
            for(int i =0;i<lineas.size();i++){
                if(lineas.get(i)[0].equals(ciudad1)&&lineas.get(i)[1].equals(ciudad2)){
                     tokens = lineas.get(i);
                }
            }
    
            // Modificar el valor de la distancia en el arreglo de cadenas
            int nuevaDistancia = Integer.parseInt(tokens[posicion]);
            tokens[posicion] = Integer.toString(nuevaDistancia);
    
            // Actualizar la lista de líneas con el arreglo modificado
            for(int i =0;i<lineas.size();i++){
                if(lineas.get(i)[0].equals(ciudad1)&&lineas.get(i)[1].equals(ciudad2)){
                    lineas.set(i, tokens);
                }
            }
    
            // Modificar el valor de la distancia en la matriz de distancias
            distancias[indiceCiudad1][indiceCiudad2] = nuevaDistancia;
            distancias[indiceCiudad2][indiceCiudad1] = nuevaDistancia;
    
            // Actualizar la matriz de recorridos si es necesario
            if (nuevaDistancia < 1000) {
                recorridos[indiceCiudad1][indiceCiudad2] = ciudad2;
                recorridos[indiceCiudad2][indiceCiudad1] = ciudad1;
            }
        }
    }
    public void eliminar_conexion(String valor) {
        for (String elemento : lineas1) {
            if (elemento.contains(valor)) {
                this.lineas1.remove(elemento);
                System.out.println("Se eliminó la conexión: " + elemento);
                break;
            }
        }
    }
    
}