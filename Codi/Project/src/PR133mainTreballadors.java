import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class PR133mainTreballadors {

    static int longituddecelda = 10;
    private static String[] titulodelascelas = {"Id","Nom","Cognom","Departament","Salari"};
    
    List<Treballador> treballadors = new ArrayList<Treballador>();

    public static void main(String[] args) {
        String filePath = "Codi/Project/src/PR133treballadors.csv"; // Reemplaza con la ruta de tu archivo

        List<List<String>> data = readCSV(filePath);
        mostrarArray(data);
        editarvalor(data);
        subirarchivo(data);
        
    }

    static void subirarchivo(List<List<String>> data){
        String text = "";
        for (List<String> record : data) {
            for (int i = 0;i<record.size();i++) {
                if (record.size()-1 == i) {
                    text += record.get(i)+"\n";
                }else{
                    text += record.get(i)+",";
                }
            }
        }
        System.out.println(text);
        try {
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e){

        }
    }

    static void mostrarArray(List<List<String>> data){
        
        // Imprime el contenido de la lista de listas (ArrayList dentro de otra ArrayList)
        for (List<String> record : data) {
            for (String field : record) {
                for (int i = 0; i<longituddecelda ;i++){
                     if (i>=field.length()) {
                        System.out.print(" ");
                    }else{
                        System.out.print(field.charAt(i));
                    }
                }
            }
            System.out.println(); // Nueva línea para cada registro
        }
    }

    static public void editarvalor(List<List<String>> data){
        int newint;
        Object Value;
        String parametro;
        int idbuscar;
        Scanner sc = new Scanner(System.in);
        System.out.println("ID:");


        try {
            idbuscar = sc.nextInt();
            for (List<String> record : data){
                if (record.get(0).equals(idbuscar+"")) {
                    System.out.println("Que quieres cambiar?\n");
                    for (int i = 0; i<titulodelascelas.length; i++) {
                        System.out.println(i+")"+titulodelascelas[i]);
                    }
                        System.out.println(titulodelascelas.length+")Salir\n-->");
                        sc.next();
                        int opcion = sc.nextInt();
                        System.out.println("Valor de la variable:");
                        String valor = sc.nextLine();
                        record.set(opcion, valor);
                }
            }
        } catch (InputMismatchException e){
            System.out.println("Ha de ser un ID con 3 numeros.");
        }

        
    }

    public static List<List<String>> readCSV(String filePath) {
        List<List<String>> records = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Divide la línea en campos usando la coma como separador
                String[] fields = line.split(",");
                
                // Convierte el array de campos a una lista y agrégalo a la lista principal
                List<String> record = new ArrayList<>();
                for (String field : fields) {
                    record.add(field.trim()); // trim() elimina espacios en blanco alrededor del campo
                }
                records.add(record);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return records;
    }
}

class Treballador implements Serializable{

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


}