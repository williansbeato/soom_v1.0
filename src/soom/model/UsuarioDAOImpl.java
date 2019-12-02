package soom.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOImpl implements UsuarioDAO {



    private static String INSERE = "INSERT INTO usuarios(login,senha) VALUES (?,?)";
    private static String LISTA = "SELECT * FROM usuarios";
    private static String BUSCA_ID = "SELECT * FROM usuarios where id=?";

    private static final String LOGIN = "SELECT * FROM usuarios WHERE login = ? and senha = ?";




    public boolean validate(String login, String senha) throws SQLException {


        try {
            Connection con = Conexao.getConnection();

            PreparedStatement stm = con.prepareStatement(LOGIN);

            stm.setString(1, login);
            stm.setString(2, senha);

            System.out.println(stm);

            ResultSet resultSet = stm.executeQuery();
            if (resultSet.next()) {
                return true;
            }


        } catch (SQLException e){
            printSQLException(e);

        }





//        // Step 1: Establishing a Connection and
//        // try-with-resource statement will auto close the connection.
//        try (Connection connection = DriverManager
//                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
//
//             // Step 2:Create a statement using connection object
//             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY)) {
//            preparedStatement.setString(1, emailId);
//            preparedStatement.setString(2, password);
//
//            System.out.println(preparedStatement);
//
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if (resultSet.next()) {
//                return true;
//            }
//
//
//        } catch (SQLException e) {
//            // print SQL exception information
//            printSQLException(e);
//        }
        return false;
    }



    public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }


    @Override
    public  void verificaLogin(Usuario u) {
        try {
            Connection c = Conexao.getConnection();
            Statement stm = c.createStatement();
            ResultSet rs = stm.executeQuery("select * from usuarios where login = '"+u.getLogin()+
                    "' and senha ='"+u.getSenha()+"';");
//            if(rs.next()){
//                Funcionario funcionario = montaFuncionario(rs);
//                rs.close();
//                stm.close();
//                c.close();
//                funcio=JDBCFuncionarioDAO.getInstance().search(funcionario.getMatricula());
//                return funcionario;
//            }
            rs.close();
            stm.close();
            c.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void insere(Usuario u) throws SQLException {

        Connection con = Conexao.getConnection();

        PreparedStatement stm = con.prepareStatement(INSERE);

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
