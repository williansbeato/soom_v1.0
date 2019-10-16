package soom.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAOImpl implements ClienteDAO {

    private static String INSERE = "INSERT INTO clientes(nome,telefone) VALUES(?,?)";

    private static String LISTA = "SELECT * FROM clientes";

    private static String BUSCA_ID = "SELECT * FROM clientes WHERE id=?";

    private static String DELETE = "DELETE FROM clientes WHERE id =? ";


    @Override
    public void insere(Cliente c) throws SQLException {

        Connection con = Conexao.getConnection();

        PreparedStatement stm = con.prepareStatement(INSERE, Statement.RETURN_GENERATED_KEYS);
        stm.setString(1,c.getNome());
        stm.setString(2,c.getTelefone());

        stm.executeUpdate();

        ResultSet rs = stm.getGeneratedKeys();

        rs.next();

        int id=rs.getInt(1);

        c.setId(id);

        rs.close();
        stm.close();
        con.close();


    }

    @Override
    public Cliente buscaID(int id) throws SQLException {

        Cliente c = null;

        Connection con = Conexao.getConnection();

        PreparedStatement stm = con.prepareStatement(BUSCA_ID);

        stm.setInt(1,id);

        ResultSet rs = stm.executeQuery();

        while (rs.next()){


            String nome = rs.getString("nome");
            String telefone = rs.getString("telefone");

            c = new Cliente();
            c.setId(id);
            c.setNome(nome);
            c.setTelefone(telefone);


        }

        rs.close();
        stm.close();
        con.close();

        return c;
    }

    @Override
    public List<Cliente> lista() throws SQLException {

        List<Cliente> lista = new ArrayList<>();

        Connection con = Conexao.getConnection();
        PreparedStatement stm = con.prepareStatement(LISTA);

        ResultSet rs = stm.executeQuery();

        while (rs.next()){

            int id =rs.getInt("id");
            String nome = rs.getString("nome");
            String telefone = rs.getString("telefone");

            Cliente c = new Cliente();
            c.setId(id);
            c.setNome(nome);
            c.setTelefone(telefone);

            lista.add(c);

        }

        rs.close();
        stm.close();
        con.close();


        return lista;
    }

    @Override
    public void atualizar(Cliente c) throws SQLException {

    }

    @Override
    public void delete(Cliente c) throws SQLException {

        try {

            Connection con = Conexao.getConnection();

            PreparedStatement stm = con.prepareStatement(DELETE);

            stm.setInt(1,c.getId());
            stm.execute();
            stm.close();
            con.close();

        }catch (SQLException e){
            e.getMessage();
        }
    }

}

