package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private Connection conexao;
    
    public Conexao() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_dev_web","root","root");
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível efetuar a conexão com o BD", e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Não foi possível encontrar a classe referente", e);
        }
    }

    public Connection getConexao() {
        return this.conexao;
    }

    public void closeConexao() {
        try {
            if (this.conexao != null) {
                this.conexao.close();
            }
        } catch (SQLException e) {
            System.err.println("Erro ao fechar a conexão: " + e.getMessage());
        }
    }
}
