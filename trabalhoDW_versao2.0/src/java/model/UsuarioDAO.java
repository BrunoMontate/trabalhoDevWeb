package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class UsuarioDAO {
    private final Connection conexao;

    public UsuarioDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public Map<String, Object> verificarCredenciais(String usuario, String senha) {
        try {
            String sql = "SELECT tipo_usuario,id_usuario FROM usuarios WHERE cpf = ? AND senha = SHA2(?, 256)";
            try (PreparedStatement statement = conexao.prepareStatement(sql)) {
                statement.setString(1, usuario);
                statement.setString(2, senha);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        String tipoUsuario = resultSet.getString("tipo_usuario");
                        String id_usuario = resultSet.getString("id_usuario");
                        Map<String, Object> result = new HashMap<>();
                        result.put("tipo_usuario", tipoUsuario);

                        if ("2".equals(tipoUsuario)) {
                            // Se o tipo de usuário for 2, faça uma nova consulta para obter mais informações
                            int idConta = obterIdConta(id_usuario); // Adapte para sua lógica
                            result.put("id_conta", idConta);
                        }

                        return result;
                    } else {
                        return null;
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar credenciais", e);
        }
    }
    
    int obterIdConta(String id_usuario) {
        try {
            // Adapte a consulta conforme a sua estrutura de banco de dados
            String sql = "SELECT id_conta FROM conta WHERE id_usuario = ?";
            try (PreparedStatement statement = conexao.prepareStatement(sql)) {
                statement.setString(1, id_usuario);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt("id_conta");
                    } else {
                        return 0; // Valor padrão se não encontrar o ID da conta
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter ID da conta", e);
        }
    }

static Map<String, Double> verificarSaldo(int idConta) {
    try {
        // Adapte a consulta conforme a sua estrutura de banco de dados
        Connection conexao = new Conexao().getConexao();
        String sql = "SELECT saldo,valInvestido FROM conta WHERE id_conta = ?";
        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setInt(1, idConta);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // Recupere o saldo da conta do resultado da consulta
                    double saldo = resultSet.getDouble("saldo");
                    double valInvestido = resultSet.getDouble("valInvestido");
                    // Retorne as informações da conta em um Map
                    Map<String, Double> informacoesConta = new HashMap<>();
                    informacoesConta.put("saldo", saldo);
                    informacoesConta.put("valInvestido", valInvestido);
                    // Adicione outros atributos se necessário
                    return informacoesConta;
                } else {
                    // Retorne um Map vazio se a conta não for encontrada
                    return new HashMap<>();
                }
            }
        }
    } catch (SQLException e) {
        throw new RuntimeException("Erro ao obter informações da conta", e);
    }
}

static int realizarSaque(String id_conta, String valor) {
    Connection conexao = new Conexao().getConexao();
    String sql1 = "UPDATE conta SET saldo = saldo - ? WHERE id_conta = ?";
    try (PreparedStatement statement = conexao.prepareStatement(sql1)) {
        statement.setString(1, valor);
        statement.setString(2, id_conta);
        int affectedRows = statement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Falha na operação financeira, nenhuma linha afetada.");
        }
    } catch (SQLException e) {
        throw new RuntimeException("Erro ao realizar o Depósito", e);
    
    }
    String sql = "INSERT INTO transacoes (id_conta_origem, tipo_transacao, valor) VALUES (?, 1, ?)";
    try (PreparedStatement statement = conexao.prepareStatement(sql)) {
        statement.setString(1, id_conta);
        statement.setString(2, valor);
        int affectedRows = statement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Falha na operação financeira, nenhuma linha afetada.");
        }
    } catch (SQLException e) {
        throw new RuntimeException("Erro ao realizar o Depósito", e);
    
    }
    return 0;
}

static int realizarDeposito(String id_conta, Double valor) {
    Connection conexao = new Conexao().getConexao();
    String sql1 = "UPDATE conta SET saldo = saldo + ? WHERE id_conta = ?";
    try (PreparedStatement statement = conexao.prepareStatement(sql1)) {
        statement.setDouble(1, valor);
        statement.setString(2, id_conta);
        int affectedRows = statement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Falha na operação financeira, nenhuma linha afetada.");
        }
    } catch (SQLException e) {
        throw new RuntimeException("Erro ao realizar o Depósito", e);
    
    }
    String sql = "INSERT INTO transacoes (id_conta_origem, tipo_transacao, valor) VALUES (?, 2, ?)";
    try (PreparedStatement statement = conexao.prepareStatement(sql)) {
        statement.setString(1, id_conta);
        statement.setDouble(2, valor);
        int affectedRows = statement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Falha na operação financeira, nenhuma linha afetada.");
        }
    } catch (SQLException e) {
        throw new RuntimeException("Erro ao realizar o Depósito", e);
    
    }
    return 0;
}
static int realizarInvestimento(String id_conta, String valor) {
    Connection conexao = new Conexao().getConexao();
    String sql1 = "UPDATE conta SET saldo = saldo - ? WHERE id_conta = ?";
    try (PreparedStatement statement = conexao.prepareStatement(sql1)) {
        statement.setString(1, valor);
        statement.setString(2, id_conta);
        int affectedRows = statement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Falha na operação financeira, nenhuma linha afetada.");
        }
    } catch (SQLException e) {
        throw new RuntimeException("Erro ao realizar o Depósito", e);
    
    }
    String sql2 = "UPDATE conta SET valInvestido = valInvestido + ? WHERE id_conta = ?";
    try (PreparedStatement statement = conexao.prepareStatement(sql2)) {
        statement.setString(1, valor);
        statement.setString(2, id_conta);
        int affectedRows = statement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Falha na operação financeira, nenhuma linha afetada.");
        }
    } catch (SQLException e) {
        throw new RuntimeException("Erro ao realizar o Depósito", e);
    
    }
    String sql3 = "INSERT INTO transacoes (id_conta_origem, tipo_transacao, valor) VALUES (?, 4, ?)";
    try (PreparedStatement statement = conexao.prepareStatement(sql3)) {
        statement.setString(1, id_conta);
        statement.setString(2, valor);
        int affectedRows = statement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Falha na operação financeira, nenhuma linha afetada.");
        }
    } catch (SQLException e) {
        throw new RuntimeException("Erro ao realizar o Depósito", e);
    
    }
    return 0;
}

static int realizarTransferencia(String id_conta_origem,String id_conta_destino, String valor){
    Connection conexao = new Conexao().getConexao();
    String sql1 = "UPDATE conta SET saldo = saldo - ? WHERE id_conta = ?";
    try (PreparedStatement statement = conexao.prepareStatement(sql1)) {
        statement.setString(1, valor);
        statement.setString(2, id_conta_origem);
        int affectedRows = statement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Falha na operação financeira, nenhuma linha afetada.");
        }
    } catch (SQLException e) {
        throw new RuntimeException("Erro ao realizar o Depósito", e);
    
    }
    String sql2 = "UPDATE conta SET saldo = saldo + ? WHERE id_conta = ?";
    try (PreparedStatement statement = conexao.prepareStatement(sql2)) {
        statement.setString(1, valor);
        statement.setString(2, id_conta_destino);
        int affectedRows = statement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Falha na operação financeira, nenhuma linha afetada.");
        }
    } catch (SQLException e) {
        throw new RuntimeException("Erro ao realizar o Depósito", e);
    
    }
    String sql = "INSERT INTO transacoes (id_conta_origem,id_conta_destino,tipo_transacao, valor) VALUES (?,?,3, ?)";
    try (PreparedStatement statement = conexao.prepareStatement(sql)) {
        statement.setString(1, id_conta_origem);
        statement.setString(2, id_conta_destino);
        statement.setString(3, valor);
        int affectedRows = statement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Falha na operação financeira, nenhuma linha afetada.");
        }
    } catch (SQLException e) {
        throw new RuntimeException("Erro ao realizar o Depósito", e);
    
    }
        return 0;
}

static int verificarExistenciaConta(String idConta) {
    Connection conexao = new Conexao().getConexao();
    String sql = "SELECT COUNT(*) AS total FROM conta WHERE id_conta = ?";
    
    try (PreparedStatement statement = conexao.prepareStatement(sql)) {
        statement.setString(1, idConta);

        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                int total = resultSet.getInt("total");
                return total;
            }
        }
    } catch (SQLException e) {
        // Adicione algum log para registro de erro, se necessário
        e.printStackTrace();
    }
    
    return 0;
}
}