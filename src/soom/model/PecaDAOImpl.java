package soom.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PecaDAOImpl implements PecaDAO {



    private static String INSERE = "INSERT INTO pecas(categoria,nome) VALUES (?,?)";
    private static String LISTA = "SELECT * FROM pecas";
    private static String BUSCA_ID = "SELECT * FROM pecas where id=?";
    private static String DELETA = "DELETE FROM pecas WHERE id=?";
    private static String UPDATE = "UPDATE pecas SET categoria = ?, nome = ? WHERE id = ?";

    @Override
    public void insere(Peca p) throws SQLException {

        Connection con = Conexao.getConnection();

        PreparedStatement stm = con.prepareStatement(INSERE, Statement.RETURN_GENERATED_KEYS);

        stm.setString(1,p.getCategoria());
        stm.setString(2,p.getNome());

        stm.executeUpdate();

        ResultSet rs = stm.getGeneratedKeys();
        rs.next();

        int id = rs.getInt(1);

        p.setId(id);

        rs.close();
        stm.close();
        con.close();


    }

    @Override
    public Peca buscarId(int id) throws SQLException {

        Peca p = null;

        Connection con = Conexao.getConnection();

        PreparedStatement stm = con.prepareStatement(BUSCA_ID);

        stm.setInt(1,id);

        ResultSet rs = stm.executeQuery();

        while (rs.next()){

            String categoria = rs.getString("categoria");
            String nome = rs.getString("nome");

            p = new Peca();
            p.setCategoria(categoria);
            p.setNome(nome);

        }

        rs.close();
        stm.close();
        con.close();

        return p;

    }

    @Override
    public List<Peca> lista() throws SQLException {

        List<Peca> lista = new ArrayList<>();

        Connection con = Conexao.getConnection();

        PreparedStatement stm = con.prepareStatement(LISTA);

        ResultSet rs = stm.executeQuery();

        while (rs.next()){

            int id = rs.getInt("id");
            String categoria = rs.getString("categoria");
            String nome = rs.getString("nome");

            Peca p = new Peca();
            p.setId(id);
            p.setCategoria(categoria);
            p.setNome(nome);

            lista.add(p);

        }

        rs.close();
        stm.close();
        con.close();

        return lista;

    }

    @Override
    public void update(Peca p) throws SQLException {
        try {
            Connection con = Conexao.getConnection();

            PreparedStatement stm = con.prepareStatement(UPDATE);

            stm.setString(1,p.getCategoria());
            stm.setString(2,p.getNome());
            stm.setInt(3,p.getId());

            stm.execute();
            stm.close();
            con.close();

        } catch (SQLException e){
            e.getMessage();

        }
    }

    @Override
    public void delete(Peca p) throws SQLException {

        try {
            Connection con = Conexao.getConnection();

            PreparedStatement stm = con.prepareStatement(DELETA);

            stm.setInt(1, p.getId());

            stm.execute();
            stm.close();
            con.close();

        }catch (SQLException e){
            e.getMessage();
        }

    }

}
