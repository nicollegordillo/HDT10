import java.io.FileNotFoundException;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws FileNotFoundException {
        Boolean continuar=true;
        int op1=0;
        Scanner teclado = new Scanner(System.in);
        System.out.println("Bienvenido al programa que calcula la ruta más corta entre dos ciudades");
        while(continuar){
            System.out.println("Elija la opción que desea");
            System.out.println("1. Ruta más corta entre dos ciudades \n2. Mostrar el centro \n3. Modificar \n4. Salir");
            op1=teclado.nextInt();
            teclado.nextLine();
            if(op1==1){

            }
            else if(op1==2){

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
