import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class PR132main {
    public static void main(String[] args) {
        PR132persona persona1 = new PR132persona("Maria","Lopez",36);
        PR132persona persona2 = new PR132persona("Gustavo","Ponts",63);
        PR132persona persona3 = new PR132persona("Irene","Sales",54);
        String path = "D:\\Roberto\\Documents\\AMS2-MP06 Accés a dades 23-24\\PR1.2-RobertoZambrano\\PR1.2-RobertoZambranoAcces_a_dades\\Codi\\Project\\src\\myFiles\\PR132people.dat";
        try {
            
            FileOutputStream fos = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(persona1);
            oos.writeObject(persona2);
            oos.writeObject(persona3);
            oos.close();
            fos.close();
        } catch (Exception e){
            e.printStackTrace();
        }

        try {
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis);
            while (true) {
                PR132persona leido = (PR132persona) ois.readObject();
                System.out.println(leido.toString());
            }
        }catch(java.io.EOFException e){
            System.out.println("\nExportado con exito!\n ");
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        
    }
}
