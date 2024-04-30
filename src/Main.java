import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Agenda agenda = new Agenda();
        boolean salir = false;
        Scanner teclado=new Scanner(System.in);
        do{
            System.out.print("Bienbenido a tu agenda electrponica indica que accion deseas realizar." +
                               "\n 1-Crear contacto." +
                               "\n 2-Eliminar contacto." +
                               "\n 3-Buscar un numero." +
                               "\n 4-Verificar un numero." +
                               "\n 5-Actualizar numero." +
                               "\n 6-Actualizar nombre." +
                               "\n 7-Salir" +
                               "\n >: ");
            try{
                switch (teclado.nextInt()){
                    case 1:
                        agenda.addContac(teclado);
                        break;
                    case 2:
                        System.out.print("Introduce el nombre del contacto: ");
                        if (agenda.removeContac(teclado.next()))System.out.println("Contacto borrado con exito.");
                        else System.out.println("No ha sido posible borrar el contacto.");
                        break;
                    case 3:
                        System.out.print("Introduzca el nombre del contacto: ");
                        String nom=teclado.next();
                        if (agenda.lookforNumber(nom)==null) System.out.println("No existe el registro.");
                        else System.out.println("El numero es: "+agenda.lookforNumber(nom));
                        break;
                    case 4:
                        System.out.print("Introduzca el numero que desea verificar: ");
                        if (agenda.lookHadNumber(teclado.next())) System.out.println("Existe almenos un contacto con ese numero");
                        else System.out.println("No existe ningun contacto con ese numero");
                        break;
                    case 5:
                        agenda.changeNumber(teclado);
                        break;
                    case 6:
                        if (agenda.changeName(teclado)) System.out.println("Cambio realizado con exito");
                        else System.out.println("El nuevo nombre ya existe, debe introducir otro");
                        break;
                    case 7:
                        salir=true;
                        break;
                    default:
                        System.out.println("Numero sin accion asignada");
                }
            }catch (InputMismatchException ime){
                System.out.println("Caracter no valido, vuelva a intentarlo.");
                teclado=new Scanner(System.in);
            }
        }while(!salir);
    }
}
