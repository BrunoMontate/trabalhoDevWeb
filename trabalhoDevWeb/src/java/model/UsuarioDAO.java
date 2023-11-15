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

public Map<String, Double> obterInformacoesConta(int idConta) {
    try {
        // Adapte a consulta conforme a sua estrutura de banco de dados
        String sql = "SELECT saldo FROM conta WHERE id_conta = ?";
        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setInt(1, idConta);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // Recupere o saldo da conta do resultado da consulta
                    double saldo = resultSet.getDouble("saldo");

                    // Retorne as informações da conta em um Map
                    Map<String, Double> informacoesConta = new HashMap<>();
                    informacoesConta.put("saldo", saldo);
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
}
