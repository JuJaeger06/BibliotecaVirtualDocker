package br.csi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectarBancoDados {

    public static Connection conectarBancoPostgres() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        System.out.println("Driver carregado!");

        // 1. Busca as variáveis de ambiente definidas no docker-compose.yml.
        // Se não existirem, usa valores padrão para rodar localmente sem Docker.
        String dbHost = System.getenv("DB_HOST") != null ? System.getenv("DB_HOST") : "localhost";
        String dbName = System.getenv("DB_NAME") != null ? System.getenv("DB_NAME") : "bibliotecaVirtual";
        String dbUser = System.getenv("DB_USER") != null ? System.getenv("DB_USER") : "postgres";
        String dbPass = System.getenv("DB_PASS") != null ? System.getenv("DB_PASS") : "1234";

        // 2. Monta a URL de conexão dinamicamente
        String url = "jdbc:postgresql://" + dbHost + ":5432/" + dbName;

        System.out.println("Conectando a: " + url); // Log para ajudar na depuração

        // 3. Usa as variáveis para criar a conexão
        return DriverManager.getConnection(url, dbUser, dbPass);
    }
}