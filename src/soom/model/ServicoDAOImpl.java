package soom.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicoDAOImpl implements ServicoDAO {

    private static String INSERE = "INSERT INTO servicos(categoria,nome,valor) VALUES (?,?,?)";
    private static String LISTA = "SELECT * FROM servicos";
    private static String BUSCA_ID = "SELECT * FROM servicos where id=?";
    private static String DELETA = "DELETE FROM servicos WHERE id=?";
    private static String UPDATE = "UPDATE servicos SET categoria = ?, nome = ?, valor = ? WHERE id = ?";


    @Override
    public void insere(Servico s) throws SQLException {

        Connection con = Conexao.getConnection();
        //PreparedStatement stm = con.prepareStatement(INSERE, Statement.RETURN_GENERATED_KEYS);
        PreparedStatement stm = con.prepareStatement(INSERE);

        stm.setString(1,s.getCategoria());
        stm.setString(2,s.getNome());
        stm.setDouble(3,s.getValor());

        stm.execute();
        stm.close();
        con.close();

    }

    @Override
    public Servico buscarId(int id) throws SQLException {

        Servico s = null;

        Connection con = Conexao.getConnection();

        PreparedStatement stm = con.prepareStatement(BUSCA_ID);

        stm.setInt(1,id);

        ResultSet rs = stm.executeQuery();

        while (rs.next()){
            String categoria = rs.getString("categoria");
            String nome = rs.getString("nome");
            Double valor = rs.getDouble("valor");

            s = new Servico();
            s.setCategoria(categoria);
            s.setNome(nome);
            s.setValor(valor);

        }

        rs.close();
        stm.close();
        con.close();

        return s;
    }

    @Override
    public List<Servico> lista() throws SQLException {

        List<Servico> lista = new ArrayList<>();

        Connection con = Conexao.getConnection();

        PreparedStatement stm = con.prepareStatement(LISTA);

        ResultSet rs = stm.executeQuery();

        while (rs.next()){

            int id = rs.getInt("id");
            String categoria = rs.getString("categoria");
            String nome = rs.getString("nome");
            Double valor = rs.getDouble("valor");

            Servico s = new Servico();
            s.setId(id);
            s.setCategoria(categoria);
            s.setNome(nome);
            s.setValor(valor);

            lista.add(s);

        }

        rs.close();
        stm.close();
        con.close();

        return lista;

    }

    @Override
    public void update(Servico s) throws SQLException {

        try {
            Connection con = Conexao.getConnection();

            PreparedStatement stm = con.prepareStatement(UPDATE);

            stm.setString(1,s.getCategoria());
            stm.setString(2,s.getNome());
            stm.setDouble(3,s.getValor());
            stm.setInt(4,s.getId());

            stm.execute();
            stm.close();
            con.close();

        } catch (SQLException e){
            e.getMessage();

        }

    }

    @Override
    public void delete(Servico s) throws SQLException {

        try {
            Connection con = Conexao.getConnection();

            PreparedStatement stm = con.prepareStatement(DELETA);

            stm.setInt(1, s.getId());

            stm.execute();
            stm.close();
            con.close();

        }catch (SQLException e){
            e.getMessage();
        }

    }

}
