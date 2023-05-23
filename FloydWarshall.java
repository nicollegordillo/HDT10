public class FloydWarshall {
    private int[][] distancias;
    private String[][] recorridos;
    private String[] vertices;
    private int size;

    public FloydWarshall(int[][] distancias, String[][] recorridos, String[] vertices,int size) {
        this.distancias = distancias;
        this.recorridos = recorridos;
        this.vertices=vertices;
        this.size = size;
    }

    public int[][] getDistancias() {
        return distancias;
    }

    public String[][] getRecorridos() {
        return recorridos;
    }

    public void calcularRutas() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < size; k++) {
                    if ((i != j) && (i != k)) {
                        int suma = distancias[j][i] + distancias[i][k];
                        if (suma < distancias[j][k]) {
                            distancias[j][k] = suma;
                            recorridos[j][k] = vertices[i];
                        }
                    }
                }
            }
        }
    }
}