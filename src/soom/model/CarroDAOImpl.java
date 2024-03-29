package soom.model;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CarroDAOImpl implements CarroDAO {

    private static String INSERE = "INSERT INTO carros(marca,modelo) VALUES (?,?)";
    private static String LISTA = "SELECT * FROM carros";
    private static String BUSCA_ID = "SELECT * FROM carros where id=?";
    private static String DELETA = "DELETE FROM carros WHERE id=?";
    private static String UPDATE = "UPDATE carros SET marca = ?, modelo = ? WHERE id = ?";

    @Override
    public void insere(Carro ca) throws SQLException {

        Connection con = Conexao.getConnection();
        PreparedStatement stm = con.prepareStatement(INSERE, Statement.RETURN_GENERATED_KEYS);

   //     PreparedStatement stm = con.prepareStatement(INSERE);


        stm.setString(1,ca.getMarca());
        stm.setString(2,ca.getModelo());

        stm.executeUpdate();

        ResultSet rs = stm.getGeneratedKeys();
        rs.next();

        //stm.execute();
        stm.close();
        con.close();

    }

    @Override
    public Carro buscarId(int id) throws SQLException {

        Carro ca = null;

        Connection con = Conexao.getConnection();

        PreparedStatement stm = con.prepareStatement(BUSCA_ID);

        stm.setInt(1,id);

        ResultSet rs = stm.executeQuery();

        while (rs.next()){
            String marca = rs.getString("marca");
            String modelo = rs.getString("modelo");

            ca = new Carro();
            ca.setMarca(marca);
            ca.setModelo(modelo);

        }

        rs.close();
        stm.close();
        con.close();

        return ca;

    }

    @Override
    public List<Carro> lista() throws SQLException {

        List<Carro> lista = new ArrayList<>();

        Connection con = Conexao.getConnection();

        PreparedStatement stm = con.prepareStatement(LISTA);

        ResultSet rs = stm.executeQuery();

        while (rs.next()){

            int id = rs.getInt("id");
            String marca = rs.getString("marca");
            String modelo = rs.getString("modelo");

            Carro ca = new Carro();
            ca.setId(id);
            ca.setMarca(marca);
            ca.setModelo(modelo);

            lista.add(ca);

        }

        rs.close();
        stm.close();
        con.close();

        return lista;

    }

    @Override
    public void update(Carro ca) {

        try {
            Connection con = Conexao.getConnection();

            PreparedStatement stm = con.prepareStatement(UPDATE);

            stm.setString(1,ca.getMarca());
            stm.setString(2,ca.getModelo());
            stm.setInt(3,ca.getId());

            stm.execute();
            stm.close();
            con.close();

        } catch (SQLException e){
            e.getMessage();

        }

    }

    @Override
    public void delete(Carro ca) throws SQLException {

        try {
            Connection con = Conexao.getConnection();

            PreparedStatement stm = con.prepareStatement(DELETA);

            stm.setInt(1, ca.getId());

            stm.execute();
            stm.close();
            con.close();

        }catch (SQLException e){
            e.getMessage();
        }

    }

}
