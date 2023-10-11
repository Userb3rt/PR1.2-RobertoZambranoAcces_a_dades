import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PR133mainTreballadors {
    
    List<Treballador> treballadors = new ArrayList<Treballador>();

    public static void main(String[] args) {
        String filePath = "Codi/Project/src/PR133treballadors.csv"; // Reemplaza con la ruta de tu archivo

        List<List<String>> data = readCSV(filePath);

        // Imprime el contenido de la lista de listas (ArrayList dentro de otra ArrayList)
        for (List<String> record : data) {
            for (String field : record) {
                System.out.print(field + " ");
            }
            System.out.println(); // Nueva línea para cada registro
        }
    }

    public void editarvalor(){

        String filePath = "Codi/Project/src/PR133treballadors.csv"; // Reemplaza con la ruta de tu archivo
        List<List<String>> data = readCSV(filePath);
        
        int id;
        Object Value;
        String parametro;
        Scanner sc = new Scanner(System.in);
        System.out.println("ID:");
        try {
            int idcambiar = sc.nextInt();
            for (List<String> record : data) {
            for (String field : record) {
                if (Integer.parseInt(record.get(0))== id) {
                    
                }
            }
            System.out.println(); // Nueva línea para cada registro
        }
                
            
        } catch (Exception e) {
            System.out.println("No es un Id valido.");
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