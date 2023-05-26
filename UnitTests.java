import junit.framework.TestCase;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
public class UnitTests extends TestCase {
    Aplicacion ap = new Aplicacion();
    ArrayList<String> lineas;
    private void pruebita()
    {
        lineas= new ArrayList<>();
    }
    public void testllenarMatrizDistancias()
    {
        pruebita();
        try (BufferedReader br = new BufferedReader(new FileReader("logistica.txt"))) {;
            String line;

            while ((line = br.readLine()) != null) 
            {
                lineas.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(ap.llenarMatrizDistancias(lineas));
        System.out.println(ap.llenarMatrizRecorridos(lineas));
       // assertEquals(ap.Centro(),"\nSaoPaulo");
    
    }
}
