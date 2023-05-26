import junit.framework.TestCase;
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
        lineas.add("BuenosAires SaoPaulo 10 50 20 50");
        lineas.add("BuenosAires Lima 15 20 30 70");
        lineas.add("Lima Quito 10 12 15 20");
    }
    public void test_llenarMatrizDistancias()
    {
        pruebita();
        String resultado= ap.llenarMatrizDistancias(lineas);
        String esperado="0 10 15 1000 "+"\n"+"1000 0 1000 1000 "+"\n"+"1000 1000 0 10 "+"\n"+"1000 1000 1000 0 "+"\n";
        assertEquals(resultado,esperado);
    
    }
    public void test_llenarMatrizRecorridos()
    {
        pruebita();
        System.out.println(ap.llenarMatrizDistancias(lineas));
        String resultado= ap.llenarMatrizRecorridos(lineas);
        String esperado="BuenosAires SaoPaulo Lima Quito "+"\n"+"BuenosAires SaoPaulo Lima Quito "+"\n"+"BuenosAires SaoPaulo Lima Quito "+"\n"+"BuenosAires SaoPaulo Lima Quito "+"\n";
        assertEquals(resultado,esperado);
    }
    public void test_clima()
    {
        pruebita();
        System.out.println(ap.llenarMatrizDistancias(lineas));
        System.out.println(ap.llenarMatrizRecorridos(lineas));
        ap.modificarDistancia("BuenosAires" ,"Lima", 4);
        String resultado=ap.rutamascorta("BuenosAires", "Lima");
        String esperado="BuenosAires -> Lima -> Lima\nTiempo Normal: 30";
        assertEquals(resultado,esperado);
    }
    public void test_Insertar()
    {
        pruebita();
        System.out.println(ap.llenarMatrizDistancias(lineas));
        System.out.println(ap.llenarMatrizRecorridos(lineas));
        lineas.add("Santiago Lima 10 30 40 50");
        ap.llenarMatrizDistancias(lineas);
        String resultado=ap.llenarMatrizRecorridos(lineas);
        String esperado="BuenosAires SaoPaulo Lima Quito Santiago "+"\n"+"BuenosAires SaoPaulo Lima Quito Santiago "+"\n"+"BuenosAires SaoPaulo Lima Quito Santiago "+"\n"+"BuenosAires SaoPaulo Lima Quito Santiago "+"\n"+"BuenosAires SaoPaulo Lima Quito Santiago "+"\n";
        assertEquals(resultado,esperado);
    }
    public void test_Eliminar()
    {
        pruebita();
        System.out.println(ap.llenarMatrizDistancias(lineas));
        System.out.println(ap.llenarMatrizRecorridos(lineas));
        lineas.add("Santiago Lima 10 30 40 50");
        ap.llenarMatrizDistancias(lineas);
        ap.llenarMatrizRecorridos(lineas);
        ap.eliminar_conexion("Santiago Lima");
        ArrayList<String> lineas2 = ap.get_lineas();
        System.out.println(ap.llenarMatrizDistancias(lineas2));
        String resultado=ap.llenarMatrizRecorridos(lineas2);
        String esperado="BuenosAires SaoPaulo Lima Quito "+"\n"+"BuenosAires SaoPaulo Lima Quito "+"\n"+"BuenosAires SaoPaulo Lima Quito "+"\n"+"BuenosAires SaoPaulo Lima Quito "+"\n";
        assertEquals(resultado,esperado);
    }
    public void test_centro()
    {
        pruebita();
        System.out.println(ap.llenarMatrizDistancias(lineas));
        ap.llenarMatrizRecorridos(lineas);
        String resultado= ap.Centro();
        String esperado="\nSaoPaulo";
        assertEquals(resultado,esperado);
    }

}
