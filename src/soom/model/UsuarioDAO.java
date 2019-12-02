package soom.model;

import java.sql.SQLException;
import java.util.List;

public interface UsuarioDAO {
    void insere(Usuario u) throws SQLException;

    Usuario buscarId(int id) throws SQLException;
    List<Usuario> lista() throws SQLException;

    void update(Usuario u) throws SQLException;
    void delete(Usuario u) throws SQLException;
    void verificaLogin(Usuario u) throws  SQLException;

}
