package com.jianhaoweb.play;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class DinoGame {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Dino Game");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(new GamePanel());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);
            frame.setVisible(true);
        });
    }

    static class GamePanel extends JPanel implements ActionListener, KeyListener {

        private int dinoX = 50;
        private int dinoY = 100;
        private int dinoWidth = 20;
        private int dinoHeight = 20;
        private int obstacleX = 250;
        private int obstacleY = 100;
        private int obstacleWidth = 20;
        private int obstacleHeight = 30;
        private int score = 0;
        private boolean isJumping = false;
        private int jumpHeight = 50;
        private int jumpStep = 0;
        private Timer timer;

        public GamePanel() {
            setPreferredSize(new Dimension(300, 150));
            setBackground(Color.WHITE);
            setFocusable(true);
            addKeyListener(this);
            timer = new Timer(20, this);
            timer.start();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // 绘制恐龙
            g.setColor(Color.LIGHT_GRAY);

            g.fillRect(dinoX, dinoY, dinoWidth, dinoHeight);

            // 绘制障碍物
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(obstacleX, obstacleY, obstacleWidth, obstacleHeight);

            // 绘制得分
            g.setColor(Color.LIGHT_GRAY);
            g.drawString("Score: " + score, 10, 20);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // 更新障碍物位置
            obstacleX -= 3;
            if (obstacleX < -obstacleWidth) {
                obstacleX = 250;
                score++; // 恐龙跳过障碍物时加一分
            }

            // 更新恐龙跳跃
            if (isJumping) {
                if (jumpStep <= jumpHeight) {
                    dinoY -= 2;
                    jumpStep += 2;
                } else if (jumpStep < jumpHeight * 2) {
                    dinoY += 2;
                    jumpStep += 2;
                } else {
                    isJumping = false;
                    jumpStep = 0;
                }
            }

            // 检测碰撞
            if (new Rectangle(dinoX, dinoY, dinoWidth, dinoHeight).intersects(new Rectangle(obstacleX, obstacleY, obstacleWidth, obstacleHeight))) {
                timer.stop(); // 恐龙碰到障碍物，游戏结束
            }

            repaint();
        }

        @Override
        public void keyTyped(KeyEvent e) {}

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_SPACE && !isJumping) {
                isJumping = true;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {}

    }
}
