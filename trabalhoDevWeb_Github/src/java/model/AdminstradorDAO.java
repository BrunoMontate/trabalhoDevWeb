/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.Timestamp;

/**
 *
 * @author Usuário
 */
public class AdminstradorDAO {
     private final Connection conexao;
     
     public AdminstradorDAO(Connection conexao) {
        this.conexao = conexao;
    }
static int criarUsuario(Connection conexao,String cpf, String nome, String tipo, String senha) {
    String sql = "INSERT INTO usuarios (nome, cpf, tipo_usuario, senha) VALUES (?, ?, ?, SHA2(?, 256))";

    try (PreparedStatement statement = conexao.prepareStatement(sql)) {
        statement.setString(1, nome);
        statement.setString(2, cpf);
        statement.setString(3, tipo);
        statement.setString(4, senha);
        int affectedRows = statement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Falha na criação do usuário, nenhuma linha afetada.");
        }
    } catch (SQLException e) {
        throw new RuntimeException("Erro ao criar usuário", e);
    }

    int idUsuario = -1;
    String query = "SELECT id_usuario FROM usuarios WHERE cpf = ?";
    try (PreparedStatement statement = conexao.prepareStatement(query)) {
        statement.setString(1, cpf);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            idUsuario = resultSet.getInt("id_usuario");
        }
    } catch (SQLException e) {
        throw new RuntimeException("Erro ao buscar usuário", e);
    }

    return idUsuario;
}

static int criarConta(Connection conexao,String id_usuario, String saldo, String valInvestido) {
    String sql = "INSERT INTO conta (id_usuario, saldo, valInvestido) VALUES (?, ?, ?)";

    try (PreparedStatement statement = conexao.prepareStatement(sql)) {
        statement.setString(1, id_usuario);
        statement.setString(2, saldo);
        statement.setString(3, valInvestido);
        int affectedRows = statement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Falha na criação da conta, nenhuma linha afetada.");
        }
    } catch (SQLException e) {
        throw new RuntimeException("Erro ao criar conta", e);
    }

    int idconta = -1;
    String query = "SELECT id_conta FROM conta WHERE id_usuario = ?";
    try (PreparedStatement statement = conexao.prepareStatement(query)) {
        statement.setString(1, id_usuario);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            idconta = resultSet.getInt("id_conta");
        }
    } catch (SQLException e) {
        throw new RuntimeException("Erro ao buscar Conta", e);
    }

    return idconta;
}

static Map<String, Double> consultaSimples(Connection conexao, int id_conta) {
    String consultaSQL = "SELECT saldo, valInvestido FROM conta WHERE id_conta = ?";

    try (PreparedStatement statement = conexao.prepareStatement(consultaSQL)) {
        statement.setInt(1, id_conta);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            double saldo = resultSet.getDouble("saldo");
            double valorInvestido = resultSet.getDouble("valInvestido");

            // Retornar os valores em um mapa
            Map<String, Double> resultados = new HashMap<>();
            resultados.put("saldo", saldo);
            resultados.put("valorInvestido", valorInvestido);

            return resultados;
        } else {
            // Retornar null se a conta não for encontrada
            return null;
        }
    } catch (SQLException e) {
        throw new RuntimeException("Erro ao consultar conta", e);
    }
}

    public static List<Map<String, Object>> consultarTransacoes(Connection conexao, int id_conta) {
        String consultaSQL = "SELECT tipo_transacao, valor, data_hora FROM transacoes WHERE id_conta_origem = ?";
        List<Map<String, Object>> transacoes = new ArrayList<>();

        try (PreparedStatement statement = conexao.prepareStatement(consultaSQL)) {
            statement.setInt(1, id_conta);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // Ler os dados da transação do ResultSet
                int tipo_transacao = resultSet.getInt("tipo_transacao");
                Timestamp dataTransacao = resultSet.getTimestamp("data_hora");
                double valorTransacao = resultSet.getDouble("valor");

                // Criar um mapa para armazenar os dados da transação
                Map<String, Object> transacaoMap = new HashMap<>();
                transacaoMap.put("tipo_transacao", tipo_transacao);
                transacaoMap.put("data_transacao", dataTransacao);
                transacaoMap.put("valor_transacao", valorTransacao);

                // Adicionar o mapa à lista
                transacoes.add(transacaoMap);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar transações", e);
        }

        return transacoes;
    }
    
        public static List<Map<String, Object>> consultarClientes(Connection conexao) {
        String consultaSQL = "select u.nome, u.cpf, u.id_usuario,c.id_conta from usuarios u ,conta c where u.id_usuario = c.id_usuario AND u.tipo_usuario = 2";
        List<Map<String, Object>> transacoes = new ArrayList<>();

        try (PreparedStatement statement = conexao.prepareStatement(consultaSQL)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // Ler os dados da transação do ResultSet
                String nomeCliente = resultSet.getString("u.nome");
                String cpfCliente = resultSet.getString("u.cpf");
                int idUserCliente = resultSet.getInt("u.id_usuario");
                int idContaCliente = resultSet.getInt("c.id_conta");

                // Criar um mapa para armazenar os dados da transação
                Map<String, Object> clienteMap = new HashMap<>();
                clienteMap.put("nome", nomeCliente);
                clienteMap.put("cpf", cpfCliente);
                clienteMap.put("idUsuario", idUserCliente);
                clienteMap.put("idConta", idContaCliente);
                

                // Adicionar o mapa à lista
                transacoes.add(clienteMap);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar Clientes", e);
        }

        return transacoes;
    }
    
}
   
