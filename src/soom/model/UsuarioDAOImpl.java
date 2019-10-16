package soom.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOImpl implements UsuarioDAO {



    private static String INSERE = "INSERT INTO usuarios(login,senha) VALUES (?,?)";
    private static String LISTA = "SELECT * FROM usuarios";
    private static String BUSCA_ID = "SELECT * FROM usuarios where id=?";


    @Override
    public void insere(Usuario u) throws SQLException {

        Connection con = Conexao.getConnection();

        PreparedStatement stm = con.prepareStatement(INSERE, Statement.RETURN_GENERATED_KEYS);

        stm.setString(1,u.getLogin());
        stm.setString(2,u.getSenha());

        stm.executeUpdate();

        ResultSet rs = stm.getGeneratedKeys();
        rs.next();

        int id = rs.getInt(1);

        u.setId(id);

        rs.close();
        stm.close();
        con.close();


    }

    @Override
    public Usuario buscarId(int id) throws SQLException {

        Usuario u = null;

        Connection con = Conexao.getConnection();

        PreparedStatement stm = con.prepareStatement(BUSCA_ID);

        stm.setInt(1,id);

        ResultSet rs = stm.executeQuery();

        while (rs.next()){

            String login = rs.getString("login");
            String senha = rs.getString("senha");

            u = new Usuario();
            u.setLogin(login);
            u.setSenha(senha);


        }

        rs.close();
        stm.close();
        con.close();

        return u;


    }

    @Override
    public List<Usuario> lista() throws SQLException {

        List<Usuario> lista = new ArrayList<>();

        Connection con = Conexao.getConnection();

        PreparedStatement stm = con.prepareStatement(LISTA);

        ResultSet rs = stm.executeQuery();

        while (rs.next()){

            int id = rs.getInt("id");
            String login = rs.getString("login");
            String senha = rs.getString("senha");

            Usuario u = new Usuario();
            u.setId(id);
            u.setLogin(login);
            u.setSenha(senha);

            lista.add(u);

        }

        rs.close();
        stm.close();
        con.close();

        return lista;

    }

    @Override
    public void update(Usuario u) throws SQLException {



    }

    @Override
    public void delete(Usuario u) throws SQLException {

    }



}
