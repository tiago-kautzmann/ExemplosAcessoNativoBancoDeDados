package org.example;

import java.sql.*;

public class Main {

    public static void main(String[] args) {
        selectData();
    }

    public static void createDatabaseAndTable() {

        // Configurações de conexão
        String url = "jdbc:mysql://localhost:3306";
        String usuario = "root"; // substitua pelo seu usuário do MySQL
        String senha = "ienh"; // substitua pela sua senha do MySQL

        // SQL para criação do banco de dados

        String sqlCriacaoBanco = "CREATE DATABASE IF NOT EXISTS banco_teste";

        // SQL para criação da tabela

        String sqlCriacaoTabela = "CREATE TABLE IF NOT EXISTS aluno ("
                + "id INT AUTO_INCREMENT PRIMARY KEY,"
                + "nome VARCHAR(255) NOT NULL,"
                + "endereco VARCHAR(255) NOT NULL"
                + ")";

        try {
            // Conectando ao banco de dados
            Connection conexao = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conexão estabelecida com sucesso!");

            // Preparando a instrução SQL para criação do banco de dados
            Statement statement = conexao.createStatement();
            statement.execute(sqlCriacaoBanco);
            System.out.println("Banco de dados criado com sucesso!");

            // Preparando a instrução SQL para criação da tabela
            statement.execute("USE banco_teste");
            statement.execute(sqlCriacaoTabela);
            System.out.println("Tabela criada com sucesso!");

            // Fechando a conexão
            statement.close();
            conexao.close();
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ou manipular o banco de dados:");
        }

    }

    public static void insertData() {

        // Configurações de conexão
        String url = "jdbc:mysql://localhost:3306/banco_teste";
        String usuario = "root"; // substitua pelo seu usuário do MySQL
        String senha = "ienh"; // substitua pela sua senha do MySQL

        // SQL para inserção
        String sqlInsercao = "INSERT INTO aluno (nome, endereco) VALUES (?, ?)";

        try {
            // Conectando ao banco de dados
            Connection conexao = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conexão estabelecida com sucesso!");

            // Preparando a instrução SQL
            PreparedStatement statement = conexao.prepareStatement(sqlInsercao);
            statement.setString(1, "Rolando Caio da Rocha"); // Substitua pelo nome desejado
            statement.setString(2, "Rua dos Bobos, número 0"); // Substitua pelo endereço desejado

            // Executando a inserção
            int linhasAfetadas = statement.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Registro inserido com sucesso!");
            }

            // Fechando a conexão
            statement.close();
            conexao.close();
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ou manipular o banco de dados:");
        }

    }

    public static void updateData() {

        // Configurações de conexão
        String url = "jdbc:mysql://localhost:3306/banco_teste";
        String usuario = "root"; // substitua pelo seu usuário do MySQL
        String senha = "ienh"; // substitua pela sua senha do MySQL

        // SQL para atualização
        String sqlAtualizacao = "UPDATE aluno SET endereco = ? WHERE id = ?";

        try {
            // Conectando ao banco de dados
            Connection conexao = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conexão estabelecida com sucesso!");

            // Preparando a instrução SQL
            PreparedStatement statement = conexao.prepareStatement(sqlAtualizacao);
            statement.setString(1, "Rua das Flores, 456"); // Substitua pelo novo endereço
            statement.setInt(2, 2); // Substitua pelo nome do aluno a ser atualizado

            // Executando a atualização
            int linhasAfetadas = statement.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Registro atualizado com sucesso!");
            }

            // Fechando a conexão
            statement.close();
            conexao.close();
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ou manipular o banco de dados:");
        }

    }

    public static void deleteData() {

        // Configurações de conexão
        String url = "jdbc:mysql://localhost:3306/banco_teste";
        String usuario = "root"; // substitua pelo seu usuário do MySQL
        String senha = "ienh"; // substitua pela sua senha do MySQL

        // SQL para exclusão
        String sqlExclusao = "DELETE FROM aluno WHERE id = ?";

        try {
            // Conectando ao banco de dados
            Connection conexao = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conexão estabelecida com sucesso!");

            // Preparando a instrução SQL
            PreparedStatement statement = conexao.prepareStatement(sqlExclusao);
            statement.setInt(1, 3); // Substitua pelo ID do aluno a ser excluído

            // Executando a exclusão
            int linhasAfetadas = statement.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Registro excluído com sucesso!");
            }

            // Fechando a conexão
            statement.close();
            conexao.close();
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ou manipular o banco de dados:");
        }

    }

    public static void selectData() {

        // Configurações de conexão
        String url = "jdbc:mysql://localhost:3306/banco_teste";
        String usuario = "root"; // substitua pelo seu usuário do MySQL
        String senha = "ienh"; // substitua pela sua senha do MySQL

        // SQL para seleção
        String sqlSelecao = "SELECT * FROM aluno";

        try {
            // Conectando ao banco de dados
            Connection conexao = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conexão estabelecida com sucesso!");

            // Preparando a instrução SQL
            PreparedStatement statement = conexao.prepareStatement(sqlSelecao);

            // Executando a seleção
            ResultSet resultado = statement.executeQuery();
            while (resultado.next()) {
                int id = resultado.getInt("id");
                String nome = resultado.getString("nome");
                String endereco = resultado.getString("endereco");
                System.out.println("ID: " + id + ", Nome: " + nome + ", Endereço: " + endereco);
            }

            // Fechando a conexão
            resultado.close();
            statement.close();
            conexao.close();
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ou manipular o banco de dados:");
        }

    }

}