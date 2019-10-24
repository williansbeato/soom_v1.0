package soom.model;

import java.sql.SQLException;
import java.util.List;

public interface PecaDAO {


    void insere(Peca p) throws SQLException;

    Peca buscarId(int id) throws SQLException;
    List<Peca> lista() throws SQLException;

    void update(Peca p) throws SQLException;
    void delete(Peca p) throws SQLException;
}
