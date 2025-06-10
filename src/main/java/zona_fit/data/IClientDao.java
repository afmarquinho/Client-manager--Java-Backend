/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package zona_fit.data;

import java.util.List;
import zona_fit.domain.Client;

/**
 *
 * @author m_fer
 */
public interface IClientDao {

    List<Client> listClients();

    Client getClientById(int id);

    boolean addClient(Client client);

    boolean updateClient(int id, int activo);

    boolean deleteCliente(int id);
}
