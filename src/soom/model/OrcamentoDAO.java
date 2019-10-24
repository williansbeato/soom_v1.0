package soom.model;

import java.sql.SQLException;
import java.util.List;

public interface  OrcamentoDAO {

    void insere(Orcamento o) throws SQLException;

    Orcamento buscaId(int id) throws SQLException;
    List<Orcamento> lista() throws SQLException;
}
