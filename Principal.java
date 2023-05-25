import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
public class Principal {
    public static void main(String[] args) throws FileNotFoundException {
        String filePath = "logistica.txt";
        Boolean continuar=true;
        int op1=0;
        String origen="";
        String destino="";
        Scanner teclado = new Scanner(System.in);
        Aplicacion ap = new Aplicacion();
        //lineas
        ArrayList<String> lineas= new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {;
            String line;

            while ((line = br.readLine()) != null) 
            {
                lineas.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
       // System.out.println("Bienvenido al programa que calcula la ruta más corta entre dos ciudades");
      //  System.out.println("--------Matrices iniciales---------");
        System.out.println(ap.llenarMatrizDistancias(lineas));
        System.out.println(ap.llenarMatrizRecorridos(lineas));
        while(continuar){
            System.out.println("Elija la opción que desea");
            System.out.println("1. Ruta más corta entre dos ciudades \n2. Mostrar el centro \n3. Modificar \n4. Salir");
            op1=teclado.nextInt();
            teclado.nextLine();
            if(op1==1){
                System.out.println("Ingrese el origen: ");
                origen=teclado.nextLine();
                System.out.println("Ingrese el destino: ");
                destino=teclado.nextLine();
                System.out.println(ap.rutamascorta(origen, destino));
            }
            else if(op1==2){
                System.out.println("Centro del grafo: "+ap.Centro());

            }
            else if(op1==3){
                System.out.println("Ingrese la modificación que desea realizar: \n1. Clima \n3. Conexión nueva \n4. Eliminar una conexión");
                int c=teclado.nextInt();
                if (c==1)
                {
                    System.out.println("Ingrese el origen: ");
                    origen=teclado.nextLine();
                    origen=teclado.nextLine();
                    System.out.println("Ingrese el destino: ");
                    destino=teclado.nextLine();
                    System.out.println("Condición climática: \n1. Normal \n2. lluvia \n3. Nieve \n4. Tormenta     ");
                    int clim=teclado.nextInt();
                    ap.modificarDistancia(origen, destino, clim+1);
                }
                else if(c==2)
                {
                   // System.out.println("Ingrese el origen: ");
                   // origen=teclado.nextLine();
                   // System.out.println("Ingrese el destino: ");
                   // destino=teclado.nextLine();
                   // System.out.println("Ingrese las distancias (normal, lluvia, nieve y tormenta): ");
                   // String dist=teclado.nextLine();
                   ArrayList<String> lineas1= ap.get_lineas();
                   lineas1.add("Lima Santiago 15 18 30 50");
                   System.out.println(ap.llenarMatrizDistancias(lineas1));
                   System.out.println(ap.llenarMatrizRecorridos(lineas1));
                   
                }
                else if (c==3)
                {
                   // System.out.println("Ingrese ciudad 1: ");
                  //  origen=teclado.nextLine();
                  //  origen=teclado.nextLine();
                  //  System.out.println("Ingrese ciudad 2: ");
                  //  destino=teclado.nextLine();
                    String value="Lima Santiago";
                    ap.eliminar_conexion(value);
                    ArrayList<String> lineas2 = ap.get_lineas();
                    System.out.println(lineas2);
                    System.out.println(ap.llenarMatrizDistancias(lineas2));
                    System.out.println(ap.llenarMatrizRecorridos(lineas2));
                }
                else
                {
                    System.out.println("Opción ingresada no válida ");
                }
            }
            else if(op1==4)
            {
                continuar=false;
            }
            else{
                System.out.println("Ingrese una opción válida");
            }

        }
        
    }
}