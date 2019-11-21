package soom.model;

import java.sql.SQLException;
import java.util.List;

public interface CarroDAO {

    void insere(Carro c) throws SQLException;
    Carro buscarId(int id) throws SQLException;
    List<Carro> lista() throws SQLException;
    void update(Carro c) throws SQLException;
    void delete(Carro c) throws SQLException;

}

