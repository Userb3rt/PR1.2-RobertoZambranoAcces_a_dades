import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class PR134gestioestudiants {


    private static final int ID_SIZE = 4;
    private static final int NAME_SIZE = 20;
    private static final int CHAR_SIZE = 2;
    private static final int NOTA_SIZE = 4; 


    private static final int TAMANY_REGISTRE = 28; // 4 + 20 + 4 bytes per estudiant

    public static void main(String[] args) {
        try{
            File fitxerEstudiants = new File("Codi/data/estudiants.dat");
            RandomAccessFile raf = new RandomAccessFile(fitxerEstudiants, "rw");

            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("1. Afegir estudiant");
                System.out.println("2. Actualitzar nota d'estudiant");
                System.out.println("3. Consultar nota d'estudiant");
                System.out.println("4. Sortir");
                System.out.print("Selecciona una opció: ");

                int opcio = scanner.nextInt();
                scanner.nextLine(); // Consumir la nova línia després de llegir l'enter
                int numRegistre;

                switch (opcio) {
                    case 1:
                        afegirEstudiant(raf, scanner);
                        break;
                    case 2:
                        System.out.print("Introdueix el número de registre de l'estudiant: ");
                        numRegistre = scanner.nextInt();
                        actualitzarNota(raf, scanner, numRegistre);
                        break;
                    case 3:
                        System.out.print("Introdueix el número de registre de l'estudiant: ");
                        numRegistre = scanner.nextInt();
                        consultarNota(raf, scanner,numRegistre);
                        break;
                    case 4:
                        raf.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Opció no vàlida.");
                }
            }
            
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void afegirEstudiant(RandomAccessFile raf, Scanner scanner) throws IOException {
        try {
            System.out.print("Introdueix el número de registre de l'estudiant: ");
            int numRegistre = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Introdueix el nom de l'estudiant: ");
            String nom = scanner.nextLine();

            System.out.print("Introdueix la nota de l'estudiant: ");
            float nota = scanner.nextFloat();
            scanner.nextLine();

            raf.seek(raf.length());

            // Escriure les dades a l'arxiu
            raf.writeInt(numRegistre);
            raf.writeChars(getPaddedName(nom));
            raf.writeFloat(nota);

            System.out.println("Estudiant afegit amb èxit.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    private static void actualitzarNota(RandomAccessFile raf, Scanner scanner, int id) throws IOException {

        long posicio = buscarPosicioEstudiant(id);

        if (posicio != -1) {
            // Moure el punter a la posició de l'estudiant
            raf.seek(posicio);

            System.out.print("Introdueix la nova nota de l'estudiant: ");
            float novaNota = scanner.nextFloat();

            // Escriure la nova nota
            raf.skipBytes(24); // Saltar els bytes corresponents a l'identificador i el nom
            raf.writeFloat(novaNota);

            System.out.println("Nota actualitzada amb èxit.");
        } else {
            System.out.println("L'estudiant no existeix.");
        }
    }

    private static void consultarNota(RandomAccessFile raf, Scanner scanner, int id) throws IOException {

        long posicio = buscarPosicioEstudiant(id);

        System.out.println(posicio);

        if (posicio != -1) {
            // Moure el punter a la posició de l'estudiant
            raf.seek(posicio);

            // Llegir la nota i mostrar-la
            raf.skipBytes(24); // Saltar els bytes corresponents a l'identificador i el nom
            float nota = raf.readFloat();
            System.out.println("La nota de l'estudiant és: " + Integer.parseInt(nota+""));
        } else {
            System.out.println("L'estudiant no existeix.");
        }
    }

    
    private static long buscarPosicioEstudiant(int id) {
            return (id - 1) * (ID_SIZE + NOTA_SIZE + NAME_SIZE * CHAR_SIZE);
        }
    

    private static String getPaddedName(String name) {
        if (name.length() > NAME_SIZE) {
            return name.substring(0, NAME_SIZE);
        }
        return String.format("%1$-" + NAME_SIZE + "s", name);
    }
}
