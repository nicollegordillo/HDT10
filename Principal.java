import java.io.FileNotFoundException;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws FileNotFoundException {
        String filePath = "logistica.txt";
        Boolean continuar=true;
        int op1=0;
        String origen="";
        String destino="";
        Scanner teclado = new Scanner(System.in);
        Aplicacion ap = new Aplicacion();
        
        System.out.println("Bienvenido al programa que calcula la ruta más corta entre dos ciudades");
        System.out.println("--------Matrices iniciales---------");
        System.out.println(ap.llenarMatrizDistancias(filePath));
        System.out.println(ap.llenarMatrizRecorridos(filePath));
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