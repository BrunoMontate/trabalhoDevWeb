package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ContaDAO {
    private Connection conexao;

    public ContaDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public Map<String, Double> verificarSaldo(int id_conta) {
        try {
            // Lógica para verificar saldo no banco de dados
            String sql = "SELECT saldo, valInvestido FROM conta WHERE id_conta = ?";
            
            try (PreparedStatement statement = conexao.prepareStatement(sql)) {
                statement.setInt(1, id_conta);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        double saldo = resultSet.getDouble("saldo");
                        double valInvestido = resultSet.getDouble("valInvestido");

                        // Criar um mapa com as informações
                        Map<String, Double> contaInfo = new HashMap<>();
                        contaInfo.put("saldo", saldo);
                        contaInfo.put("valInvestido", valInvestido);
                        return contaInfo;
                    } else {
                        // Se a conta não for encontrada, retorna null ou lança uma exceção, dependendo do seu requisito
                        return null;
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar saldo", e);
        }
    }
    

    // Outros métodos para depósito, transferência, etc., podem ser adicionados aqui
}
