package com.jianhaoweb.play;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class DinoGm {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame(" ");
            frame.setUndecorated(true);
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
        private boolean isSecondJumping = false;
        private int temp = 0;
        private boolean isFalling = false;

        public GamePanel() {
            setPreferredSize(new Dimension(300, 150));
            setBackground(Color.WHITE);
            setBorder(new LineBorder(Color.WHITE, 2));
            setFocusable(true);
            addKeyListener(this);
            timer = new Timer(20, this);
            timer.start();
           /* restartButton = new JButton(".");
            restartButton.addActionListener(e -> restartGame());
            restartButton.setVisible(false);
            add(restartButton);*/
            random = new Random();

            // 添加鼠标事件监听器
            MouseAdapter mouseAdapter = new MouseAdapter() {
                private Point initialClick;

                @Override
                public void mousePressed(MouseEvent e) {
                    initialClick = e.getPoint();
                    getComponentAt(initialClick);
                }

                @Override
                public void mouseDragged(MouseEvent e) {
                    // 当前位置
                    int thisX = getTopLevelAncestor().getLocation().x;
                    int thisY = getTopLevelAncestor().getLocation().y;

                    // 移动距离
                    int xMoved = e.getX() - initialClick.x;
                    int yMoved = e.getY() - initialClick.y;

                    // 新位置
                    int newX = thisX + xMoved;
                    int newY = thisY + yMoved;

                    // 移动窗口
                    getTopLevelAncestor().setLocation(newX, newY);
                }
            };

            addMouseListener(mouseAdapter);
            addMouseMotionListener(mouseAdapter);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g.create();

            // 将坐标系上下翻转，使得y轴正方向朝上
            g2d.scale(1, -1);
            // 平移坐标系，使原点位于左下角
            g2d.translate(0, -getHeight());

            // 绘制恐龙
            g2d.setColor(new Color(250, 250, 250));
            g2d.fillRect(dinoX, dinoY, dinoWidth, dinoHeight);
            // 绘制障碍物

            g2d.setColor(new Color(250, 250, 250));
            g2d.fillRect(obstacleX, obstacleY, obstacleWidth, obstacleHeight);
            // 绘制得分

            g.setColor(new Color(220, 220, 220));
            g.drawString("S: " + score, 10, 20);
            g2d.dispose();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // 更新障碍物位置
            obstacleX -= obstacleSpeed;
            if (obstacleX < -obstacleWidth) {
                obstacleX = 250;
                obstacleY = 6;
                obstacleHeight = 10 + random.nextInt(20);
                score++; // 恐龙跳过障碍物时加一分
                // 每过5分，增加障碍物速度.当速度==一定数后，不加速了
                if (score % 5 == 0 && obstacleSpeed < 6) {
                    obstacleSpeed++;
                }
            }

            // 更新跳跃
            if (isJumping) {
                if (isSecondJumping) {
                    if (jumpStep < temp + jumpHeight) {
                        dinoY += obstacleSpeed - 1;
                        jumpStep += obstacleSpeed - 1;
                    } else {
                        dinoY -= obstacleSpeed - 1;
                        jumpStep += obstacleSpeed - 1;
                        if (dinoY <= 6) {
                            dinoY = 6;
                            isSecondJumping = false;
                            isJumping = false;
                            jumpStep = 0;
                            isFalling = false;
                        }
                    }
                } else {
                    if (jumpStep < jumpHeight) {
                        dinoY += obstacleSpeed - 1;
                        jumpStep += obstacleSpeed - 1;
                    } else {
                        dinoY -= obstacleSpeed - 1;
                        jumpStep += obstacleSpeed - 1;
                        if (dinoY <= 6) {
                            dinoY = 6;
                            isJumping = false;
                            jumpStep = 0;
                            isFalling = true;
                        }
                    }
                }
            }

            // 检测碰撞
            if (new Rectangle(dinoX, dinoY, dinoWidth, dinoHeight).intersects(new Rectangle(obstacleX, obstacleY, obstacleWidth, obstacleHeight))) {
                timer.stop(); // 恐龙碰到障碍物，游戏结束
//                restartButton.setVisible(true);
            }

            repaint();


        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if ((e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) && !isJumping && !isFalling && dinoY == 6) {
                isJumping = true;
                isFalling = false;
            } else if ((e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) && isJumping && !isSecondJumping && !isFalling) {
                isSecondJumping = true;
                temp = dinoY;
                isFalling = false;
            }

            if ((e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) && !timer.isRunning()) {
                restartGame();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

        private void restartGame() {
            dinoY = 6;
            obstacleX = 250;
            obstacleY = 6;
            score = 0;
            isJumping = false;
            jumpStep = 0;
            obstacleSpeed = 3;
            isSecondJumping = false;
            temp = 0;
            isFalling = false; // 重置 isFalling 状态
            //doubleJumpStep = 0;
            //restartButton.setVisible(false);
            timer.restart();
        }
    }
}
