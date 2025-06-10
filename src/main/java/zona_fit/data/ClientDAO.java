/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zona_fit.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//import java.util.Scanner;

import zona_fit.domain.Client;
import zona_fit.connection.ConnectionDB;

/**
 *
 * @author m_fer
 */
public class ClientDao implements IClientDao {

    @Override
    public List<Client> listClients() {
        List<Client> clients = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        Connection conn = ConnectionDB.getConnection();

        var sql = "SELECT * FROM clientes ORDER BY id";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                var client = new Client();
                client.setId(rs.getInt("id"));
                client.setNombre(rs.getString("nombre"));
                client.setApellido(rs.getString("apellido"));
                client.setCorreo(rs.getString("correo"));
                client.setFecha_contratacion(rs.getString("fecha_contratacion"));
                client.setRecargo(rs.getFloat("recargo"));
                client.setActivo(rs.getBoolean("activo"));
                clients.add(client);

            }

        } catch (SQLException e) {
            System.out.println("Error al listar los clientes: " + e.getMessage());
        } finally {
            try {
                conn.close();

            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }

        return clients;
    }

    @Override
    public Client getClientById(int id) {
        Client client = new Client();
        PreparedStatement ps;
        ResultSet rs;
        Connection conn = ConnectionDB.getConnection();
        var sql = "SELECT * FROM clientes WHERE id= ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                client.setId(id);
                client.setNombre(rs.getString("nombre"));
                client.setApellido(rs.getString("apellido"));
                client.setCorreo(rs.getString("correo"));
                client.setFecha_contratacion(rs.getString("fecha_contratacion"));
                client.setRecargo(rs.getFloat("recargo"));
                client.setActivo(rs.getBoolean("activo"));

            }

        } catch (SQLException e) {
            System.out.println("Error al recupa<rar el cliente por id: " + e.getMessage());
        } finally {
            try {
                conn.close();

            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }

        return client;

    }

    @Override
    public boolean addClient(Client client) {
        PreparedStatement ps;
        Connection conn = ConnectionDB.getConnection();
        var sql = "INSERT INTO clientes(nombre, apellido, correo, fecha_contratacion, recargo, activo)" + " VALUES(?, ?, ?, ?, ?, ?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, client.getNombre());
            ps.setString(2, client.getApellido());
            ps.setString(3, client.getCorreo());
            ps.setString(4, client.getFecha_contratacion());
            ps.setDouble(5, client.getRecargo());
            ps.setBoolean(6, client.isActivo());
            ps.execute();
            return true;

        } catch (SQLException e) {
            System.out.println("");
        } finally {
            try {
                conn.close();

            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }

        return false;

    }

    @Override
    public boolean updateClient(int id, int activo) {

        PreparedStatement ps;
        Connection conn = ConnectionDB.getConnection();
        var sql = "UPDATE clientes SET activo = ?" + " WHERE id = ?";

        try {
            ps = conn.prepareStatement(sql);
            if (activo == 1) {
                ps.setBoolean(1, true);
            } else {
                ps.setBoolean(1, false);
            }
            ps.setInt(2, id);
            ps.execute();
            return true;

        } catch (SQLException e) {
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexiòn: " + e.getMessage());
            }

        }
        return false;
    }

    @Override
    public boolean deleteCliente(int id) {

        PreparedStatement ps;
        Connection conn = ConnectionDB.getConnection();
        var sql = "DELETE FROM clientes WHERE id = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;

        } catch (SQLException e) {
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexiòn: " + e.getMessage());
            }

        }
        return false;
    }


    /*public static void main(String[] args) {
        //IClientDao clientDAO = new ClientDAO();
        //Listar Clientes

        /*System.out.println("*** Listado de Clientes ***");
        var clients = clientDAO.listClients();
        clients.forEach(System.out::println);*/
            

        //Buscar Cliente por id
        /*System.out.println("*** Buscar cliente por ID ***");
        Scanner console = new Scanner(System.in); // Crear objeto scanner
        System.out.print("Ingresa el ID del cliente para buscar: ");
        int id = console.nextInt();
        System.out.println("Buscando en la base de datos el cliente con el id: " + id);
        Client client = clientDAO.getClientById(id);
        System.out.println(client);*/
    //Crear cliente
    /* System.out.println("*** Crear nuevo cliente ***");
        try (Scanner console = new Scanner(System.in)) {
            System.out.println("\nIntroduce los datos del nuevo cliente");
            System.out.print("Nombre: ");
            String nombre = console.next();
            System.out.print("Apellido: ");
            String apellido = console.next();
            System.out.print("Correo: ");
            String correo = console.next();
            System.out.print("Fecha de contrato: ");
            String fecha_contratacion = console.next();
            System.out.print("Recargo: ");
            float recargo = console.nextFloat();
            
            var newClient = new Client(nombre, apellido, correo, fecha_contratacion, recargo, true);
            var agregado = clientDAO.addClient(newClient);
            System.out.println("Cliente agregado: " + agregado);
        }*/
    //Actualizar el estado de un usuario
    /*System.out.println("*** Actualizar el estado de un cliente ***");
        try (Scanner console = new Scanner(System.in)) {
            System.out.println("\nConsultado la base de datos de cliente...");
            System.out.println("\n*** Listado de Clientes ***");
            var clients = clientDAO.listClients();
            clients.forEach(System.out::println);
            System.out.print("\nSelecciona un cliente por su id del listado: ");
            int id = console.nextInt();
            System.out.print("\nDigite: 1-> activo, 2-> inactivo: ");
            int estado = console.nextInt();
            
            var updatedClient = clientDAO.updateClient(id, estado);
            System.out.println("\nCliente actualizado: " + updatedClient);
        }*/
    //Eliminar cliente
    /*System.out.println("***Eliminar cliente ***");
        try (Scanner console = new Scanner(System.in)) {
            System.out.println("\nConsultado la base de datos de cliente...");
            System.out.println("\n*** Listado de Clientes ***");
            var clients = clientDAO.listClients();
            clients.forEach(System.out::println);
            System.out.print("\nSelecciona un cliente por su id para eliminar: ");
            int id = console.nextInt();
            
            var deletedClient = clientDAO.deleteCliente(id);
            System.out.println("\nCliente eliminado: " + deletedClient);
        }
    }
     */
}
