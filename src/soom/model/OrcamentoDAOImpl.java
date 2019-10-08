package soom.model;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrcamentoDAOImpl implements OrcamentoDAO {

    private static String INSERE = "INSERT INTO orcamentos(valor,data,idCliente) VALUES(?,?,?)";
    private static String INSERE_ORCAMENTO_SERVICO = "INSERT INTO orcamentoservicos(idOrcamento,idServico,valor) VALUES(?,?,?)";

    private static String LISTA = "SELECT * FROM orcamentos";
    private static String LISTA_ORCAMENTOS_SERVICOS = "SELECT * FROM orcamentoservicos WHERE idOrcamento=?";

    @Override
    public void insere(Orcamento o) throws SQLException {

        Connection con = Conexao.getConnection();

        PreparedStatement stm = con.prepareStatement(INSERE, Statement.RETURN_GENERATED_KEYS);

        stm.setDouble(1,o.getValor());
        stm.setTimestamp(2, Timestamp.valueOf(o.getData()));
        stm.setInt(3,o.getCliente().getId());

        int rows = stm.executeUpdate();

        if (rows == 0){
            throw new SQLException("Erro ao inserir Orçamento!");

        }
            ResultSet rs = stm.getGeneratedKeys();

            rs.next();

            int id = rs.getInt(1);

            o.setId(id);

            rs.close();

            stm = con.prepareStatement(INSERE_ORCAMENTO_SERVICO);


            for(Servico servico:o.getServicos()){
                stm.setInt(1,id);
                stm.setInt(2,servico.getId());
                stm.setDouble(3,servico.getValor());

                stm.executeUpdate();
            }

            stm.close();
            con.close();


    }

    @Override
    public Orcamento buscaId(int id) throws SQLException {

        Orcamento orcamento = null;

        Connection con = Conexao.getConnection();

        PreparedStatement stm = con.prepareStatement(LISTA);
        PreparedStatement itensStm = con.prepareStatement(LISTA_ORCAMENTOS_SERVICOS);



        ResultSet rs = stm.executeQuery();

        ClienteDAO clienteDAO = new ClienteDAOImpl();
        ServicoDAO servicoDAO = new ServicoDAOImpl();

        while (rs.next()){

            int idOrcamento = rs.getInt("id");
            int idCliente = rs.getInt("idCliente");

            double valor = rs.getDouble("valor");
            LocalDateTime data = rs.getTimestamp("data").toLocalDateTime();

            Cliente cliente = clienteDAO.buscaID(idCliente);

            orcamento = new Orcamento();
            orcamento.setId(idOrcamento);
            orcamento.setCliente(cliente);
            orcamento.setData(data);
            orcamento.setValor(valor);

            itensStm.setInt(1,idOrcamento);

            ResultSet rsItens = itensStm.executeQuery();

            while (rsItens.next()){
                int idServico = rsItens.getInt("idServico");
                double valorServico = rsItens.getDouble("valor");

                Servico servico = servicoDAO.buscarID(idServico);
                servico.setValor(valorServico);

                orcamento.adicionaServico(servico);

            }
            rsItens.close();
            itensStm.close();


        }
        rs.close();
        stm.close();
        con.close();


        return orcamento;


    }

    @Override
    public List<Orcamento> lista() throws SQLException {

        List<Orcamento> lista = new ArrayList<>();

        Connection con = Conexao.getConnection();

        PreparedStatement stm = con.prepareStatement(LISTA);

        ResultSet rs = stm.executeQuery();

        ClienteDAO clienteDAO = new ClienteDAOImpl();
        ServicoDAO servicoDAO = new ServicoDAOImpl();

        while (rs.next()){

            int idOrcamento = rs.getInt("id");
            int idCliente = rs.getInt("idCliente");

            double valor = rs.getDouble("valor");
            LocalDateTime data = rs.getTimestamp("data").toLocalDateTime();

            Cliente cliente = clienteDAO.buscaID(idCliente);

            Orcamento orcamento = new Orcamento();
            orcamento.setId(idOrcamento);
            orcamento.setCliente(cliente);
            orcamento.setData(data);
            orcamento.setValor(valor);

            PreparedStatement itensStm = con.prepareStatement(LISTA_ORCAMENTOS_SERVICOS);

            itensStm.setInt(1,idOrcamento);

            ResultSet rsItens = itensStm.executeQuery();

            while (rsItens.next()){
                int idServico = rsItens.getInt("idServico");
                double valorServico = rsItens.getDouble("valor");

                Servico servico = servicoDAO.buscarID(idServico);
                servico.setValor(valorServico);

                orcamento.adicionaServico(servico);

            }
            rsItens.close();
            itensStm.close();


            lista.add(orcamento);
        }
        rs.close();
        stm.close();
        con.close();

        return lista;
    }
}


