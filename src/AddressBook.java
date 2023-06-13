/*Se importan las librerias necesarias para el programa*/
import java.io.*;
import java.util.*;


public class AddressBook {

    /*ATRIBUTOS HashMap almacena los contactos
                String  almacena nombre del archivo*/
    private HashMap<String, String> contactos;
    private String fileName;

    /*INICIALIZAR ATRIBUTOS*/
    public AddressBook(String fileName) {
        this.contactos = new HashMap<>();
        this.fileName = fileName;
    }

    /*METODO load: cargará los contactos del archivo.*/
    public void load() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String number = parts[0].trim();
                    String name = parts[1].trim();
                    contactos.put(number, name);
                }
            }
            reader.close();
            System.out.println("Agenda cargada exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al cargar la agenda.");
        }
    }

    /*METODO save: guardará los cambios en el archivo*/
    public void save() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            for (Map.Entry<String, String> entry : contactos.entrySet()) {
                String number = entry.getKey();
                String name = entry.getValue();
                writer.write(number + " : " + name);
                writer.newLine();
            }
            writer.close();
            System.out.println("Cambios guardados exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar los cambios.");
        }
    }

    /*METODO list: mostrar los contactos de la agenda*/
    public void list() {
        System.out.println("Contactos:");
        for (Map.Entry<String, String> entry : contactos.entrySet()) {
            String number = entry.getKey();
            String name = entry.getValue();
            System.out.println(number + " : " + name);
        }
    }

    /*METODO create: crear un nuevo contacto*/
    public void create(String number, String name) {
        contactos.put(number, name);
        System.out.println("Contacto creado: " + number + " : " + name);
    }

    /*METODO delete: borrar un contacto*/
    public void delete(String number) {
        if (contactos.containsKey(number)) {
            String name = contactos.get(number);
            contactos.remove(number);
            System.out.println("Contacto eliminado: " + number + " : " + name);
        } else {
            System.out.println("El contacto no existe.");
        }
    }

    /*Se instancian las variables
      Se despliega el menu
        el menu se integra en un do while y un switch */
    public static void main(String[] args) {
        AddressBook addressBook = new AddressBook("agenda.txt");
        addressBook.load();

        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("Menu:");
            System.out.println("1. Listar contactos");
            System.out.println("2. Crear contacto");
            System.out.println("3. Eliminar contacto");
            System.out.println("4. Guardar cambios");
            System.out.println("5. Salir");
            System.out.print("Elija una opción: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addressBook.list();
                    break;
                case 2:
                    System.out.print("Ingrese el número de contacto: ");
                    String number = scanner.nextLine();
                    System.out.print("Ingrese el nombre de contacto: ");
                    String name = scanner.nextLine();
                    addressBook.create(number, name);
                    break;
                case 3:
                    System.out.print("Ingrese el número de contacto a eliminar: ");
                    String numberToDelete = scanner.nextLine();
                    addressBook.delete(numberToDelete);
                    break;
                case 4:
                    addressBook.save();
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida.");
                    break;
            }
        } while (choice != 5);

        scanner.close();
    }
}
