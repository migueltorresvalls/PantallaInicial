package icai.dtc.isw.dao;


import icai.dtc.isw.domain.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BDUsuarios {

    private Connection con;

    public BDUsuarios(Connection con){
        this.con = con;
    }

    public void refreshConnection(){
        con = ConnectionDAO.getInstance().getConnection();
    }

    public void addCliente(Cliente cliente) {
        try {
            con.prepareStatement("INSERT INTO usuarios VALUES ('" + cliente.getNombre() + "', '" + cliente.getContra() + "', '" + cliente.getCorreo() + "')").executeQuery();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public Cliente getCliente(String usuario){
        Cliente cliente = null;
        try (PreparedStatement pst = con.prepareStatement("SELECT * FROM USUARIOS WHERE nombre LIKE '" +
                usuario + "'");
            ResultSet rs = pst.executeQuery()){

            if(rs.next()){
                cliente = new Cliente(rs.getString(1), rs.getString(2), rs.getString(3));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return cliente;
    }

    public ArrayList<Cliente> fetchClientes(){
        ArrayList<Cliente> clientes = new ArrayList<>();
        try (PreparedStatement pst = con.prepareStatement("SELECT * FROM usuarios");
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                clientes.add(new Cliente(rs.getString(1), rs.getString(2), rs.getString(3)));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return clientes;
    }
}
