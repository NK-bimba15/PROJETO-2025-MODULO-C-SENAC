package jogo;

import banco.PontuacaoDAO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PongGame extends JPanel implements KeyListener, ActionListener {

    private final int WIDTH = 800, HEIGHT = 600;
    private final int PADDLE_WIDTH = 20, PADDLE_HEIGHT = 100;
    private int paddle1Y = 250, paddle2Y = 250;
    private final int PADDLE_SPEED = 12; 
    private int ballX = WIDTH / 2, ballY = HEIGHT / 2;
    private int ballSize = 20, ballDX = 7, ballDY = 7; 
    private Timer timer;
    private boolean wPressed = false, sPressed = false, upPressed = false, downPressed = false;
    private int score1 = 0, score2 = 0;
    private int tempoRestante = 120; 
    private Timer timerRelogio; 
    
    


    public PongGame() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);
        timer = new Timer(10, this);
        timer.start();
        timerRelogio = new Timer(1000, e -> {
        tempoRestante--;
         if (tempoRestante <= 0) {
        timer.stop();
        timerRelogio.stop();
        salvarResultado();
        String resultado;
        if (score1 > score2) {
            resultado = "Jogador 1 venceu!";
        } else if (score2 > score1) {
            resultado = "Jogador 2 venceu!";
        } else {
            resultado = "Empate!";
        }
        JOptionPane.showMessageDialog(this,
            "Tempo esgotado!\n" + resultado +
            "\nPlacar:\nJogador 1: " + score1 + "\nJogador 2: " + score2);
        System.exit(0);
    }
    });
    timerRelogio.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Raquete 1 - Rosa
        g.setColor(Color.PINK);
        g.fillRect(50, paddle1Y, PADDLE_WIDTH, PADDLE_HEIGHT);

        // Raquete 2 - Azul
        g.setColor(Color.CYAN);
        g.fillRect(WIDTH - 50 - PADDLE_WIDTH, paddle2Y, PADDLE_WIDTH, PADDLE_HEIGHT);

        // Bola - Branca
        g.setColor(Color.WHITE);
        g.fillOval(ballX, ballY, ballSize, ballSize);

        // Placar
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString(String.valueOf(score1), WIDTH / 4, 50);
        g.drawString(String.valueOf(score2), WIDTH * 3 / 4, 50);
        
        int minutos = tempoRestante / 60;
        int segundos = tempoRestante % 60;
        String tempoFormatado = String.format("%02d:%02d", minutos, segundos);

        g.setFont(new Font("Arial", Font.PLAIN, 24));
        g.setColor(Color.WHITE);
        g.drawString("Tempo: " + tempoFormatado, WIDTH / 2 - 60, 30);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (wPressed && paddle1Y > 0) paddle1Y -= PADDLE_SPEED;
        if (sPressed && paddle1Y < HEIGHT - PADDLE_HEIGHT) paddle1Y += PADDLE_SPEED;
        if (upPressed && paddle2Y > 0) paddle2Y -= PADDLE_SPEED;
        if (downPressed && paddle2Y < HEIGHT - PADDLE_HEIGHT) paddle2Y += PADDLE_SPEED;

        ballX += ballDX;
        ballY += ballDY;

        if (ballY <= 0 || ballY >= HEIGHT - ballSize) ballDY *= -1;

        if (ballX <= 50 + PADDLE_WIDTH && ballY + ballSize >= paddle1Y && ballY <= paddle1Y + PADDLE_HEIGHT) {
            ballDX *= -1;
            ballX = 50 + PADDLE_WIDTH;
        }

        if (ballX + ballSize >= WIDTH - 50 - PADDLE_WIDTH && ballY + ballSize >= paddle2Y && ballY <= paddle2Y + PADDLE_HEIGHT) {
            ballDX *= -1;
            ballX = WIDTH - 50 - PADDLE_WIDTH - ballSize;
        }

        if (ballX < 0) {
            score2++;
            resetBall();
        } else if (ballX > WIDTH) {
            score1++;
            resetBall();
        }

        if (score1 == 18 || score2 == 18) {
            timer.stop();
            salvarResultado();
            JOptionPane.showMessageDialog(this,
                "Fim de jogo!\nJogador 1: " + score1 + "\nJogador 2: " + score2);
            System.exit(0);
        }

        repaint();
    }

    private void resetBall() {
        ballX = WIDTH / 2;
        ballY = HEIGHT / 2;
        ballDX *= -1;
    }
    

    private void salvarResultado() {
        PontuacaoDAO dao = new PontuacaoDAO();
        dao.salvarPontuacao(score1, score2);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> wPressed = true;
            case KeyEvent.VK_S -> sPressed = true;
            case KeyEvent.VK_UP -> upPressed = true;
            case KeyEvent.VK_DOWN -> downPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> wPressed = false;
            case KeyEvent.VK_S -> sPressed = false;
            case KeyEvent.VK_UP -> upPressed = false;
            case KeyEvent.VK_DOWN -> downPressed = false;
        }
    } 

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
   
    
}
