package banco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PontuacaoDAO {
    private final String URL = "jdbc:mysql://localhost:3306/pong";
    private final String USER = "root";
    private final String PASS = "root";

    public void salvarPontuacao(int jogador1, int jogador2) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "INSERT INTO pontuacoes (jogador1, jogador2, data_partida) VALUES (?, ?, NOW())";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, jogador1);
            stmt.setInt(2, jogador2);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   public List<String> getTopPontuacoes(int limite) {
    List<String> pontuacoes = new ArrayList<>();
    try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
        String sql = "SELECT jogador1, jogador2, data_partida FROM pontuacoes " +
                     "ORDER BY GREATEST(jogador1, jogador2) DESC, " +
                     "LEAST(jogador1, jogador2) DESC LIMIT ?";
        
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, limite);
        ResultSet rs = stmt.executeQuery();
        
        int posicao = 1;
        while (rs.next()) {
            int j1 = rs.getInt("jogador1");
            int j2 = rs.getInt("jogador2");
            String data = rs.getTimestamp("data_partida").toString();
            String vencedor = (j1 > j2) ? "J1" : (j2 > j1) ? "J2" : "Empate";
            pontuacoes.add(String.format("%2d. J1: %d | J2: %d (%s) | %s", 
                posicao++, j1, j2, vencedor, data.substring(0, 10)));
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Erro ao acessar o banco de dados: " + e.getMessage());
    }
    return pontuacoes;
}
    
    public List<String> getTopJogadores(int limite) {
    List<String> pontuacoes = new ArrayList<>();
    try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
        // Top Jogador 1
        String sqlJ1 = "SELECT jogador1, data_partida FROM pontuacoes " +
                      "ORDER BY jogador1 DESC LIMIT ?";
        
        // Top Jogador 2
        String sqlJ2 = "SELECT jogador2, data_partida FROM pontuacoes " +
                      "ORDER BY jogador2 DESC LIMIT ?";
        
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return pontuacoes;
}
}