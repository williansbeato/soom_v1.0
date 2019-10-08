package soom.model;

import java.sql.SQLException;
import java.util.List;

public interface ServicoDAO {

    void insere(Servico s) throws SQLException;

    Servico buscarID(int id) throws SQLException;
    List<Servico> lista() throws SQLException;

    void update(Servico s) throws SQLException;
    void delete(Servico s) throws SQLException;
}
