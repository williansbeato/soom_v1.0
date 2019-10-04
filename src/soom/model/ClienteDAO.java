package soom.model;

import java.sql.SQLException;
import java.util.List;

public interface ClienteDAO {

    void insere(Cliente c) throws SQLException;

    Cliente buscaID(int id) throws SQLException;
    List<Cliente> lista() throws SQLException;

    void atualizar(Cliente c) throws SQLException;

    void delete(Cliente c) throws SQLException;

}
