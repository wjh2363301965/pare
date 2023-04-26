package com.jianhaoweb.play;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class DinoGame {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame(" ");
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
        private int dinoY = 6;
        private int dinoWidth = 5;
        private int dinoHeight = 20;
        private int obstacleX = 250;
        private int obstacleY = 6;
        private int obstacleWidth = 5;
        private int obstacleHeight = 30;
        private int score = 0;
        private boolean isJumping = false;
        private int jumpHeight = 50;
        private int jumpStep = 0;
        private Timer timer;
        private JButton restartButton;
        private Random random;
        private int obstacleSpeed = 3;


        public GamePanel() {
            setPreferredSize(new Dimension(300, 150));
            setBackground(Color.WHITE);
            setFocusable(true);
            addKeyListener(this);
            timer = new Timer(20, this);
            timer.start();
            restartButton = new JButton(".");
            restartButton.addActionListener(e -> restartGame());
            restartButton.setVisible(false);
            add(restartButton);

            random = new Random();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g.create();

            // 将坐标系上下翻转，使得y轴正方向朝上
            g2d.scale(1, -1);
            // 平移坐标系，使原点位于左下角
            g2d.translate(0, -getHeight());

            // 在这里添加你的绘制代码，使用g2d对象
            // 例如：g2d.drawRect(10, 10, 50, 50);



            // 绘制恐龙
            /*g.setColor(Color.LIGHT_GRAY);

            g.fillRect(dinoX, dinoY, dinoWidth, dinoHeight);*/
            g2d.setColor(Color.LIGHT_GRAY);
            g2d.fillRect(dinoX, dinoY, dinoWidth, dinoHeight);
            // 绘制障碍物
          /*  g.setColor(Color.LIGHT_GRAY);
            g.fillRect(obstacleX, obstacleY, obstacleWidth, obstacleHeight);*/
            g2d.setColor(Color.LIGHT_GRAY);
            g2d.fillRect(obstacleX, obstacleY, obstacleWidth, obstacleHeight);
            // 绘制得分
          /*  g.setColor(Color.LIGHT_GRAY);
            g.drawString("Score: " + score, 10, 20);*/

            g.setColor(Color.LIGHT_GRAY);
            g.drawString("Score: " + score, 10, 20);
            g2d.dispose();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // 更新障碍物位置
            obstacleX -=  obstacleSpeed;
            if (obstacleX < -obstacleWidth) {
                obstacleX = 250;
                obstacleY = 6;
                obstacleHeight = 30 + random.nextInt(30);
                score++; // 恐龙跳过障碍物时加一分
                // 每过5分，增加障碍物速度.当速度==一定数后，不加速了
                if (score % 5 == 0 && obstacleSpeed <30) {
                    obstacleSpeed++;
                }
            }

            // 更新恐龙跳跃
            if (isJumping) {
                if (jumpStep <= jumpHeight) {
                    dinoY += 2;
                    jumpStep += 2;
                } else if (jumpStep < jumpHeight * 2) {
                    dinoY -= 2;
                    jumpStep += 2;
                } else {
                    isJumping = false;
                    jumpStep = 0;
                }
                // 如果跳跃结束后，恐龙的位置低于起始高度，将其设置为起始高度
                if (!isJumping && dinoY > 6) {
                    dinoY = 6;
                }
            }

            // 检测碰撞
            if (new Rectangle(dinoX, dinoY, dinoWidth, dinoHeight).intersects(new Rectangle(obstacleX, obstacleY, obstacleWidth, obstacleHeight))) {
                timer.stop(); // 恐龙碰到障碍物，游戏结束
                restartButton.setVisible(true);
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

        private void restartGame() {
            dinoY = 6;
            obstacleX = 250;
            obstacleY = 6 ;
            score = 0;
            isJumping = false;
            jumpStep = 0;
            obstacleSpeed = 3;
            restartButton.setVisible(false);
            timer.restart();
        }
    }
}
