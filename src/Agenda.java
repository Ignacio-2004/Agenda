import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class Agenda {
    private HashMap<String,String> agenda;
    private File agendaFile;
    public Agenda() {
        this.agenda = new HashMap<String,String>();
        agendaFile =new File("AgendaFile.txt");
    }
    public void addContac (Scanner teclado){
        boolean hecho=true;
        do{
            System.out.print("Introduzca el nombre del contacto y el numero separado por \"-\" :");
            try{
                String[]datos=teclado.next().split("-");
                String comprobar=agenda.putIfAbsent(datos[0],datos[1]);
                if (comprobar!=null){System.out.println("El contacto ya existe");}
                hecho=true;
            }catch (Exception e){
                System.out.println("Valores introducidos no validos, vuelva a intentarlo");
                hecho=false;
            }
        }while (!hecho);
    }
    private void addContact(String key, String content){
        agenda.put(key, content);
    }
    public void loadAgendaFromFiles(){
        try{
            FileReader leer = new FileReader(agendaFile);
            BufferedReader lector = new BufferedReader(leer);
            String linea =lector.readLine();
            while(linea!=null){
                String[]lineaCadena=linea.split("-");
                agenda.put(lineaCadena[0],lineaCadena[1]);
                linea =lector.readLine();
            }
        }catch(IOException ioe){
            System.out.println("Error al leer el archivo");
        }
    }
    public void saveAgenda(){
        try{
            agendaFile.createNewFile();
            FileWriter escritura = new FileWriter(agendaFile,true);
            for (String clave:agenda.keySet()){
                escritura.write(clave+"-"+agenda.get(clave)+"\n");
            }
            escritura.close();
        }catch (IOException oie){
            System.out.println("Error al guardar en el archivo");
        }
    }
    public void changeNumber(Scanner teclado){
        System.out.print("Introduce el nombre de quien quieres cambiar el numero: ");
        String antiguaClave= teclado.next();
        System.out.print("Introduce el nuevo numero: ");
        agenda.replace(antiguaClave, teclado.next());
        System.out.println("Cambio realizado con exito");
    }
    public boolean changeName(Scanner teclado){
        System.out.print("Introduce el antiguo nombre: ");
        String antiguaClave= teclado.next();
        System.out.print("Introduce el nuevo nombre: ");
        String nuevaClave= teclado.next();
        if (agenda.containsKey(nuevaClave)){return false;}
        agenda.put(nuevaClave,agenda.get(antiguaClave));
        agenda.remove(antiguaClave);
        return true;
    }
    public boolean removeContac (String clave){return (agenda.remove(clave)==null)?false:true;}
    public String lookforNumber(String clave){return agenda.get(clave);}
    public boolean lookHadNumber(String clave){return agenda.containsValue(clave);}

}
