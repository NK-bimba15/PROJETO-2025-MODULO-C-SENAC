package jogo;

import banco.PontuacaoDAO;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Ranking extends JFrame {
    
    public Ranking() {
        setTitle("Ranking de Pontuações");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        panel.setLayout(new BorderLayout());
        
        JLabel titulo = new JLabel("TOP 10 PONTUAÇÕES", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setForeground(Color.WHITE);
        panel.add(titulo, BorderLayout.NORTH);
        
        JTextArea rankingArea = new JTextArea();
        rankingArea.setEditable(false);
        rankingArea.setBackground(Color.BLACK);
        rankingArea.setForeground(Color.GREEN);
        rankingArea.setFont(new Font("Courier New", Font.PLAIN, 16));
        
       
        
        PontuacaoDAO dao = new PontuacaoDAO();
          List<String> pontuacoes = null;
             try {
        pontuacoes = dao.getTopPontuacoes(10);
        
        if (pontuacoes.isEmpty()) {
            rankingArea.setText("Nenhuma pontuação registrada ainda!");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("   TOP 10 PARTIDAS REGISTRADAS   \n");
            sb.append("--------------------------------\n");
            sb.append("Pos. Jogador 1 | Jogador 2 | Vencedor | Data\n");
            sb.append("--------------------------------\n");
            for (String pontuacao : pontuacoes) {
                sb.append(pontuacao).append("\n");
            }
            rankingArea.setText(sb.toString());
        }
    } catch (Exception e) {
        rankingArea.setText("Erro ao carregar o ranking!\n" + e.getMessage());
        e.printStackTrace();
    }
    
        
       if (pontuacoes.isEmpty()) {
    rankingArea.setText("Nenhuma pontuação registrada ainda!");
    } else {
    StringBuilder sb = new StringBuilder();
    sb.append("   TOP 10 PARTIDAS REGISTRADAS   \n");
    sb.append("--------------------------------\n");
    sb.append("Pos. Jogador 1 | Jogador 2 | Data\n");
    sb.append("--------------------------------\n");
    for (String pontuacao : pontuacoes) {
        sb.append(pontuacao).append("\n");
    }
    rankingArea.setText(sb.toString());
}
       
        
        JScrollPane scrollPane = new JScrollPane(rankingArea);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        add(panel);
    }
}