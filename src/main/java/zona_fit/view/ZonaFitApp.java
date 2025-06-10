/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zona_fit.view;

import java.util.Scanner;
import zona_fit.data.ClientDao;
import zona_fit.domain.Client;

/**
 *
 * @author m_fer
 */
public class ZonaFitApp {

    public static void main(String[] args) {
        zonaFitApp();
    }

    private static void zonaFitApp() {
        var exit = false;
        // Creamos objeto de la clase clentDAO para llamar sus métodos.
        try (Scanner console = new Scanner(System.in)) {
            // Creamos objeto de la clase clentDAO para llamar sus métodos.
            var clientDao = new ClientDao();

            while (!exit) {
                try {
                    var option = checkMenu(console);
                    exit = executeOptions(console, option, clientDao);

                } catch (Exception e) {
                    System.out.println("Error al ejecutar opciones: " + e.getMessage());

                }
                System.out.println();
            }
        }

    }

    private static int checkMenu(Scanner console) {
        System.out.print("""
                         *** Zona Fit - Gimnasios ***
                         1. Listar Clientes
                         2. Buscar Cliente
                         3. Agregar Cliente
                         4. Actulizar el estado de un Cliente
                         5. Eliminar Cliente
                         6. Salir
                         Elige una de estas opciones:\s""");
        var option = Integer.parseInt(console.nextLine());
        return option;
    }

    private static boolean executeOptions(Scanner console, int option, ClientDao clientDao) {
        var exit = false;
        switch (option) {
            case 1 -> { //1. Listar Clientes
                System.out.println("\n--- Listado de clientes ---");
                System.out.println("\nConsultando base de datos ...");
                var clients = clientDao.listClients();
                clients.forEach(System.out::println);
            }

            case 2 -> { //2. Listar Clientes
                System.out.println("\n--- Buscar cliente ---");
                System.out.print("\nIngresa el ID del cliente para buscar: ");
                int id = Integer.parseInt(console.nextLine());
                System.out.println("Buscando en la base de datos el cliente con el id: " + id);
                Client client = clientDao.getClientById(id);
                if (client.getId() != 0) {
                    System.out.println("Datos del cliente: " + client);
                } else {
                    System.out.println("Cliente no encontrado");
                }

            }
            case 3 -> { //3. Agregar cliente
                System.out.println("\n--- Agregar cliente ---");
                System.out.println("\nIngresa los datos del cliente a agregar");
                System.out.print("\nNombre: ");
                String nombre = console.nextLine();
                System.out.print("Apellido: ");
                String apellido = console.nextLine();
                System.out.print("Correo: ");
                String correo = console.nextLine();
                System.out.print("Fecha de contrato: ");
                String fecha_contratacion = console.nextLine();
                System.out.print("Recargo: ");
                float recargo = Float.parseFloat(console.nextLine());
                Client newClient = new Client(nombre, apellido, correo, fecha_contratacion, recargo, true);
                System.out.println("Agregando cliente");
                var added = clientDao.addClient(newClient);

                if (added) {
                    System.out.println("Cliente agregado");
                } else {
                    System.out.println("Cliente no agregado");
                }

            }
            case 4 -> { //4. Modificar estado del cliente
                System.out.println("\n--- Modificar el estado del cliente ---");
                System.out.println("\nConsultado la base de datos de cliente...");
                System.out.println("*** Listado de Clientes ***");
                var clients = clientDao.listClients();
                clients.forEach(System.out::println);
                System.out.print("\nSelecciona un cliente por su id del listado: ");
                int id = Integer.parseInt(console.nextLine());
                System.out.print("\nDigite: 1-> activo, 2-> inactivo: ");
                int estado = Integer.parseInt(console.nextLine());
                var updatedClient = clientDao.updateClient(id, estado);
                System.out.println("\nCliente actualizado: " + updatedClient);

            }

            case 5 -> { //5. Eliminar cliente
                System.out.println("\n--- Eliminar cliente ---");
                System.out.println("\nConsultado la base de datos de cliente...");
                System.out.println("*** Listado de Clientes ***");
                var clients = clientDao.listClients();
                clients.forEach(System.out::println);
                System.out.print("\nSelecciona un cliente por su id para eliminar: ");
                int id = Integer.parseInt(console.nextLine());
                var deletedClient = clientDao.deleteCliente(id);
                if (deletedClient) {
                    System.out.println("\nCliente eliminado: ");

                } else {
                    System.out.println("\nCliente no eliminado: ");
                }
            }
            case 6 -> { //6. Salir
                System.out.println("\n--- Hasta pronto ---");
                exit = true;
            }
            default ->
                System.out.println("Opción no reconocida");

        }
        return exit;
    }
}
