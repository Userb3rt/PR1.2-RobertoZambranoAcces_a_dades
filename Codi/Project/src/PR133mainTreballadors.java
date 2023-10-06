import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.List;

public class PR133mainTreballadors {

    static Treballador t1 = new Treballador(123,"Nicolás","Rana",2,1000.00);
    static Treballador t2 = new Treballador(435,"Xavi","Gil",2,1800.50);
    static Treballador t3 = new Treballador(876,"Daniel","Ramos",6,700.30);
    static Treballador t4 = new Treballador(285,"Pedro","Drake",4,2500.00);
    static Treballador t5 = new Treballador(224,"Joan","Potter",6,1000.00);
    

    public static void main(String[] args) {
        creaciontreballadors();
        editarvalor("nom");


    }

    public static void creaciontreballadors() throws IOException{
        try {
            FileOutputStream fos = new FileOutputStream("/myFiles/PR133treballadors.csv");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(t1);
            oos.writeObject(t2);
            oos.writeObject(t3);
            oos.writeObject(t4);
            oos.writeObject(t5);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            return ;
        }
        
        
    } 

    public static void editarvalor(String nombreatributo){
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("/myFiles/PR133treballadors.csv"))) {
            List<Treballador> listaObjetos = (List<Treballador>) ois.readObject();
            System.out.println(listaObjetos);
        }catch(Exception e){
            e.printStackTrace();
        }   
}





class Treballador{
    private int id;
    private String nom;
    private String cognom;
    private int departament;
    private double salari;

    public Treballador(int id, String nom, String cognom,int departament,double salari){
        this.id = id;
        this.nom = nom;
        this.cognom = cognom;
        this.departament = departament;
        this.salari = salari;
    }
    
    @Override
    public String toString() {
        return id+"         "+nom+"         "+cognom+"          "+departament+"         "+salari;
    }



}