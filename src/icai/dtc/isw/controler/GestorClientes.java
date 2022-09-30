package icai.dtc.isw.controler;


import icai.dtc.isw.dao.BDUsuarios;
import icai.dtc.isw.domain.Cliente;

import java.util.ArrayList;

public class GestorClientes {

    private ArrayList<Cliente> clientes = new ArrayList<>();
    private BDUsuarios bdUsuarios;

    public GestorClientes(BDUsuarios bdUsuarios){
        this.bdUsuarios = bdUsuarios;
        clientes = bdUsuarios.fetchClientes();
    }

    public BDUsuarios getBdUsuarios(){
        return bdUsuarios;
    }

    public ArrayList<Cliente> getClientes(){
        return bdUsuarios.fetchClientes();
    }

    public void addCliente(Cliente cliente){
        ArrayList<String> usuarios = new ArrayList<>();
        clientes.forEach(c -> usuarios.add(c.getNombre()));

        if (!usuarios.contains(cliente.getNombre())){
            bdUsuarios.addCliente(cliente);
            clientes.add(cliente);
        }else{
            System.out.println("---ERROR---\nUsuario ya existente");
        }
    }
}
