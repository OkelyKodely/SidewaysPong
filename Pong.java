

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Pong implements KeyListener {

    private Thread t = null;
    
    private JFrame j = new JFrame();
    
    private JPanel p = new JPanel();
    
    private Ball ball, ball2;
    
    private int lives = 0;
    
    private Paddle paddle = new Paddle();

    private Paddle enemyPaddle = new Paddle();

    private int hits = 0;
    
    private long time = 0;
    private long timet = 0;
    
    private String vk = "";
    
    private boolean menu = true;
    private int a = 1;
    private int b = 1;

    private Date date = new Date();
    
    private int level = 1;
    
    private int lives2 = 0;
    
    private Thread tt = null;
    
    private Image ballimg = null;
    private Image bgimg = null;
    private Image paddleimg1 = null;
    private Image paddleimg2 = null;
    
    public class Square {
        int x, y;
        int width = 80;
        int height = 45;
        Color color = Color.WHITE;
        public boolean isHit() {
            if(ball.y >= y && ball.y <= y + height && ball.x >= x && ball.x <= x + width) {
                return true;
            }
            return false;
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(menu)
        {
            if(e.getKeyCode() == KeyEvent.VK_DOWN) {
                if(a == 1) {
                    a = 2;
                }
                else if(a == 2)
                    a = 3;
                else if(a == 3)
                    a = 1;
            }
            if(e.getKeyCode() == KeyEvent.VK_UP) {
                if(b == 1) {
                    b = 2;
                }
                else if(b == 2)
                    b = 3;
                else if(b == 3)
                    b = 4;
                else if(b == 4)
                    b = 1;
            }
            if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                menu = false;
                lives = lives2 = 0;
                t = new Thread() {
                    public void run() {
                        while(true) {
                            playfill();
                        }
                    }
                };
                t.start();
            }
        }
        if(!menu)
        {
            if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                if(JOptionPane.showConfirmDialog(null, "End current game?") == JOptionPane.YES_OPTION) {
                    menu = true;
                    t.stop();
                                    lives = lives2 = 0;

                    tt = new Thread() {
                        public void run() {
                            while(true) {
                                if(!menu)
                                    break;
                                Graphics g = p.getGraphics();
                                try {
                                    if(bgimg == null)
                                        bgimg = ImageIO.read(getClass().getResourceAsStream("bg.jpg"));
                                    g.drawImage(bgimg, 0, 0, 1000, 700, null);
                                } catch(Exception e) {
                                    e.printStackTrace();
                                }
                                g.setFont(new Font("arial",Font.BOLD,40));g.setColor(Color.PINK);g.drawString("PONG", 10, 50);g.setFont(new Font("arial",Font.BOLD,20));
                                if(a == 1) {
                                    g.drawString("> Easy", 10, 100);
                                    g.drawString("Medium", 10, 150);
                                    g.drawString("Hard", 10, 200);
                                    if(b == 1) {
                                        g.drawString("> 5 point", 10, 300);
                                        g.drawString("10 point", 10, 350);
                                        g.drawString("15 point", 10, 400);
                                        g.drawString("free play", 10, 450);
                                    } else if(b == 2) {
                                        g.drawString("5 point", 10, 300);
                                        g.drawString("> 10 point", 10, 350);
                                        g.drawString("15 point", 10, 400);
                                        g.drawString("free play", 10, 450);
                                    } else if(b == 3) {
                                        g.drawString("5 point", 10, 300);
                                        g.drawString("10 point", 10, 350);
                                        g.drawString("> 15 point", 10, 400);
                                        g.drawString("free play", 10, 450);
                                    } else if(b == 4) {
                                        g.drawString("5 point", 10, 300);
                                        g.drawString("10 point", 10, 350);
                                        g.drawString("15 point", 10, 400);
                                        g.drawString("> free play", 10, 450);
                                    }
                                } else if(a == 2) {
                                    g.drawString("Easy", 10, 100);
                                    g.drawString("> Medium", 10, 150);
                                    g.drawString("Hard", 10, 200);
                                    if(b == 1) {
                                        g.drawString("> 5 point", 10, 300);
                                        g.drawString("10 point", 10, 350);
                                        g.drawString("15 point", 10, 400);
                                        g.drawString("free play", 10, 450);
                                    } else if(b == 2) {
                                        g.drawString("5 point", 10, 300);
                                        g.drawString("> 10 point", 10, 350);
                                        g.drawString("15 point", 10, 400);
                                        g.drawString("free play", 10, 450);
                                    } else if(b == 3) {
                                        g.drawString("5 point", 10, 300);
                                        g.drawString("10 point", 10, 350);
                                        g.drawString("> 15 point", 10, 400);
                                        g.drawString("free play", 10, 450);
                                    } else if(b == 4) {
                                        g.drawString("5 point", 10, 300);
                                        g.drawString("10 point", 10, 350);
                                        g.drawString("15 point", 10, 400);
                                        g.drawString("> free play", 10, 450);
                                    }
                                } else if(a == 3) {
                                    g.drawString("Easy", 10, 100);
                                    g.drawString("Medium", 10, 150);
                                    g.drawString("> Hard", 10, 200);
                                    if(b == 1) {
                                        g.drawString("> 5 point", 10, 300);
                                        g.drawString("10 point", 10, 350);
                                        g.drawString("15 point", 10, 400);
                                        g.drawString("free play", 10, 450);
                                    } else if(b == 2) {
                                        g.drawString("5 point", 10, 300);
                                        g.drawString("> 10 point", 10, 350);
                                        g.drawString("15 point", 10, 400);
                                        g.drawString("free play", 10, 450);
                                    } else if(b == 3) {
                                        g.drawString("5 point", 10, 300);
                                        g.drawString("10 point", 10, 350);
                                        g.drawString("> 15 point", 10, 400);
                                        g.drawString("free play", 10, 450);
                                    } else if(b == 4) {
                                        g.drawString("5 point", 10, 300);
                                        g.drawString("10 point", 10, 350);
                                        g.drawString("15 point", 10, 400);
                                        g.drawString("> free play", 10, 450);
                                    }
                                }
                                g.dispose();
                                try {
                                    Thread.sleep(1000);
                                } catch(InterruptedException ie) {

                                }
                            }
                        }
                    };
                    tt.start();
                }
            }
            if(e.getKeyCode() == KeyEvent.VK_UP) {
                vk = "left";
                paddle.move_up();
                timet = new Date().getTime();
                if(Math.abs(time - timet) < 2000 && Math.abs(time - timet) > 1000) {
                    if(ball.y >= paddle.y && ball.y <= paddle.y + paddle.height && ball.x >= paddle.x-10 && ball.x <= paddle.x+10 + 15) {
                        paddle.accel_up();
                        ball.move();
                    }
                } else if (Math.abs(time - timet) < 1000) {
                    if(ball.y >= paddle.y && ball.y <= paddle.y + paddle.height && ball.x >= paddle.x-10 && ball.x <= paddle.x+10 + 15) {
                        paddle.accel_up();
                        paddle.accel_up();
                        ball.move();
                    }
                }
                time = new Date().getTime();
            }
            if(e.getKeyCode() == KeyEvent.VK_DOWN) {
                vk = "right";
                paddle.move_down();
                timet = new Date().getTime();
                if(Math.abs(time - timet) < 2000 && Math.abs(time - timet) > 1000) {
                    if(ball.y >= paddle.y && ball.y <= paddle.y + paddle.height && ball.x >= paddle.x-10 && ball.x <= paddle.x+10 + 15) {
                        paddle.accel_up();
                        ball.move();
                    }
                } else if (Math.abs(time - timet) < 1000) {
                    if(ball.y >= paddle.y && ball.y <= paddle.y + paddle.height && ball.x >= paddle.x-10 && ball.x <= paddle.x+10 + 15) {
                        paddle.accel_down();
                        paddle.accel_down();
                        ball.move();
                    }
                }
                time = new Date().getTime();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public Pong() {
        j.requestFocus();
        j.setLayout(null);
        j.setBounds(0, 0, 1000, 700);
        p.setBounds(j.getBounds());
        j.add(p);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setVisible(true);
        j.addKeyListener(this);
        draw();
        setBall();
        play();
    }
    
    public class Paddle {
        int x, y;
        int height = 140;
        int accel = 0;
        public Paddle() {
            x = 50;
            y = 130;
        }
        public void draw() {
            Graphics g = p.getGraphics();
            try {
                if(x==50) {
                    for(int i=0; i<14; i++) {
                        g.setColor(new Color(i*20, 0, 0));
                        g.fillRect(x, y+i*10, 22, 10);
                    }
                }
                else {
                    for(int i=0; i<14; i++) {
                        g.setColor(new Color(0, i*20, 0));
                        g.fillRect(x, y+i*10, 22, 10);
                    }
                    g.fillRect(x, y, 22, paddle.height);
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
            g.dispose();
            }
        public void accel_up() {
            accel = -4;
            ball.plusy -= 6;
        }
        public void accel_down() {
            accel = 4;
            ball.plusy += 6;
        }
        public void move_up() {
            y-=20;
        }
        public void move_down() {
            y+=20;
        }
        public void move_up_mid() {
            y-=30;
        }
        public void move_down_mid() {
            y+=30;
        }
    }
    
    public class Ball {
        int x, y;
        int xx = -1;
        int plusx, plusy;
        
        public Ball() {
            plusx = 1;
            plusy = 4;
        }
        
        public void move() {
            if(y <= 10) {
                plusy = -plusy;
            }
            if(y >= 650) {
                plusy = -plusy;
            }
            if(y >= paddle.y && y <= paddle.y + paddle.height && x >= paddle.x-10 && x <= paddle.x+10+14) {
                plusx = -plusx;
                hits++;
            }
            if(y >= enemyPaddle.y && y <= enemyPaddle.y + enemyPaddle.height && x >= enemyPaddle.x-10 && x <= enemyPaddle.x+10+20) {
                plusx = -plusx;
                hits++;
            }
            x+=plusx;
            y+=plusy;
        }
    }
    
    public void setBall() {
        ball = new Ball();
        
        ball.x = 500;
        ball.y = 150;
        
        ball.plusx = 7;
        ball.plusy = 12;

        ball2 = new Ball();
        
        ball2.x = 400;
        ball2.y = 250;
        ball2.xx = 1;
        
        ball2.plusx = 7;
        ball2.plusy = 12;
    }
    
    private void makeSound(String file) throws Exception {

        File audioFile = new File(file);
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

        AudioFormat format = audioStream.getFormat();

        DataLine.Info info = new DataLine.Info(Clip.class, format);
        Clip audioClip = (Clip) AudioSystem.getLine(info);

        audioClip.open(audioStream);
        audioClip.start();
        audioStream.close();
    }

    public void play() {
        enemyPaddle.x = 900;
        enemyPaddle.y = 50;
        lives = 0;
        tt = new Thread() {
            public void run() {
                while(true) {
                    if(!menu)
                        break;
                    Graphics g = p.getGraphics();
                    try {
                        Image img = ImageIO.read(getClass().getResourceAsStream("bg.jpg"));
                        g.drawImage(img, 0, 0, 1000, 700, null);
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                    g.setFont(new Font("arial",Font.BOLD,40));g.setColor(Color.PINK);g.drawString("PONG", 10, 50);g.setFont(new Font("arial",Font.BOLD,20));
                    if(a == 1) {
                        g.drawString("> Easy", 10, 100);
                        g.drawString("Medium", 10, 150);
                        g.drawString("Hard", 10, 200);
                        if(b == 1) {
                            g.drawString("> 5 point", 10, 300);
                            g.drawString("10 point", 10, 350);
                            g.drawString("15 point", 10, 400);
                            g.drawString("free play", 10, 450);
                        } else if(b == 2) {
                            g.drawString("5 point", 10, 300);
                            g.drawString("> 10 point", 10, 350);
                            g.drawString("15 point", 10, 400);
                            g.drawString("free play", 10, 450);
                        } else if(b == 3) {
                            g.drawString("5 point", 10, 300);
                            g.drawString("10 point", 10, 350);
                            g.drawString("> 15 point", 10, 400);
                            g.drawString("free play", 10, 450);
                        } else if(b == 4) {
                            g.drawString("5 point", 10, 300);
                            g.drawString("10 point", 10, 350);
                            g.drawString("15 point", 10, 400);
                            g.drawString("> free play", 10, 450);
                        }
                    } else if(a == 2) {
                        g.drawString("Easy", 10, 100);
                        g.drawString("> Medium", 10, 150);
                        g.drawString("Hard", 10, 200);
                        if(b == 1) {
                            g.drawString("> 5 point", 10, 300);
                            g.drawString("10 point", 10, 350);
                            g.drawString("15 point", 10, 400);
                            g.drawString("free play", 10, 450);
                        } else if(b == 2) {
                            g.drawString("5 point", 10, 300);
                            g.drawString("> 10 point", 10, 350);
                            g.drawString("15 point", 10, 400);
                            g.drawString("free play", 10, 450);
                        } else if(b == 3) {
                            g.drawString("5 point", 10, 300);
                            g.drawString("10 point", 10, 350);
                            g.drawString("> 15 point", 10, 400);
                            g.drawString("free play", 10, 450);
                        } else if(b == 4) {
                            g.drawString("5 point", 10, 300);
                            g.drawString("10 point", 10, 350);
                            g.drawString("15 point", 10, 400);
                            g.drawString("> free play", 10, 450);
                        }
                    } else if(a == 3) {
                        g.drawString("Easy", 10, 100);
                        g.drawString("Medium", 10, 150);
                        g.drawString("> Hard", 10, 200);
                        if(b == 1) {
                            g.drawString("> 5 point", 10, 300);
                            g.drawString("10 point", 10, 350);
                            g.drawString("15 point", 10, 400);
                            g.drawString("free play", 10, 450);
                        } else if(b == 2) {
                            g.drawString("5 point", 10, 300);
                            g.drawString("> 10 point", 10, 350);
                            g.drawString("15 point", 10, 400);
                            g.drawString("free play", 10, 450);
                        } else if(b == 3) {
                            g.drawString("5 point", 10, 300);
                            g.drawString("10 point", 10, 350);
                            g.drawString("> 15 point", 10, 400);
                            g.drawString("free play", 10, 450);
                        } else if(b == 4) {
                            g.drawString("5 point", 10, 300);
                            g.drawString("10 point", 10, 350);
                            g.drawString("15 point", 10, 400);
                            g.drawString("> free play", 10, 450);
                        }
                    }
                    g.dispose();
                    try {
                        Thread.sleep(1000);
                    } catch(InterruptedException ie) {
                        
                    }
                }
            }
        };
        tt.start();
        t = new Thread() {
            public void run() {
                while(true) {
                    playfill();
                }
            }
        };
    }
    
    private void playfill() {
        if(ball.x >= ball2.x) {
        if(a==1) {
            if(ball.y < enemyPaddle.y+enemyPaddle.height/2) {
                enemyPaddle.move_up();
            }
            if(ball.y > enemyPaddle.y+enemyPaddle.height/2) {
                enemyPaddle.move_down();
            }
        } else if(a==2) {
            if(ball.y < enemyPaddle.y+enemyPaddle.height/2) {
                enemyPaddle.move_up_mid();
            }
            if(ball.y > enemyPaddle.y+enemyPaddle.height/2) {
                enemyPaddle.move_down_mid();
            }
        } else if(a==3) {
            if(ball.y < enemyPaddle.y+enemyPaddle.height/2) {
                enemyPaddle.move_up();
                enemyPaddle.move_up();
            }
            if(ball.y > enemyPaddle.y+enemyPaddle.height/2) {
                enemyPaddle.move_down();
                enemyPaddle.move_down();
            }
        }
        } else {
        if(a==1) {
            if(ball2.y < enemyPaddle.y+enemyPaddle.height/2) {
                enemyPaddle.move_up();
            }
            if(ball2.y > enemyPaddle.y+enemyPaddle.height/2) {
                enemyPaddle.move_down();
            }
        } else if(a==2) {
            if(ball2.y < enemyPaddle.y+enemyPaddle.height/2) {
                enemyPaddle.move_up_mid();
            }
            if(ball2.y > enemyPaddle.y+enemyPaddle.height/2) {
                enemyPaddle.move_down_mid();
            }
        } else if(a==3) {
            if(ball2.y < enemyPaddle.y+enemyPaddle.height/2) {
                enemyPaddle.move_up();
                enemyPaddle.move_up();
            }
            if(ball2.y > enemyPaddle.y+enemyPaddle.height/2) {
                enemyPaddle.move_down();
                enemyPaddle.move_down();
            }
        }
        }
        if(vk.equals("left")) {
            vk = "";
            if(Math.abs(time - timet) < 2000) {
                if(ball.y >= paddle.y && ball.y <= paddle.y + paddle.height && ball.x >= paddle.x-10 && ball.x <= paddle.x+10 + 15) {
                    paddle.accel_up();
                    ball.move();
                }
            }
        }
        if(vk.equals("right")) {
            vk = "";
            if(Math.abs(time - timet) < 2000) {
                if(ball.y >= paddle.y && ball.y <= paddle.y + paddle.height && ball.x >= paddle.x-10 && ball.x <= paddle.x+10 + 15) {
                    paddle.accel_down();
                    ball.move();
                }
            }
        }
        if(b == 4)
            j.setTitle("pong. {Free Points} Human: " + lives2 + " v. Computer: " + lives);
        else
            j.setTitle("pong. {Points} Human: " + lives2 + " v. Computer: " + lives);
        try {
            if(b == 1) {
                if(lives == 5) {
                    menu = true;
                    JOptionPane.showMessageDialog(null, "You win");
                }
                if(lives2 == 5) {
                    menu = true;
                    JOptionPane.showMessageDialog(null, "You lose");
                }
            }
            if(b == 2) {
                if(lives == 10) {
                    menu = true;
                    JOptionPane.showMessageDialog(null, "You win");
                }
                if(lives2 == 10) {
                    menu = true;
                    JOptionPane.showMessageDialog(null, "You lose");
                }
            }
            if(b == 3) {
                if(lives == 15) {
                    menu = true;
                    JOptionPane.showMessageDialog(null, "You win");
                }
                if(lives2 == 15) {
                    menu = true;
                    JOptionPane.showMessageDialog(null, "You lose");
                }
            }
            if(menu) {
                lives = lives2 = 0;

                tt = new Thread() {
                    public void run() {
                        while(true) {
                            if(!menu)
                                break;
                            Graphics g = p.getGraphics();
                            try {
                                if(bgimg == null)
                                    bgimg = ImageIO.read(getClass().getResourceAsStream("bg.jpg"));
                                g.drawImage(bgimg, 0, 0, 1000, 700, null);
                            } catch(Exception e) {
                                e.printStackTrace();
                            }
                            g.setFont(new Font("arial",Font.BOLD,40));g.setColor(Color.PINK);g.drawString("PONG", 10, 50);g.setFont(new Font("arial",Font.BOLD,20));
                            if(a == 1) {
                                g.drawString("> Easy", 10, 100);
                                g.drawString("Medium", 10, 150);
                                g.drawString("Hard", 10, 200);
                                if(b == 1) {
                                    g.drawString("> 5 point", 10, 300);
                                    g.drawString("10 point", 10, 350);
                                    g.drawString("15 point", 10, 400);
                                    g.drawString("free play", 10, 450);
                                } else if(b == 2) {
                                    g.drawString("5 point", 10, 300);
                                    g.drawString("> 10 point", 10, 350);
                                    g.drawString("15 point", 10, 400);
                                    g.drawString("free play", 10, 450);
                                } else if(b == 3) {
                                    g.drawString("5 point", 10, 300);
                                    g.drawString("10 point", 10, 350);
                                    g.drawString("> 15 point", 10, 400);
                                    g.drawString("free play", 10, 450);
                                } else if(b == 4) {
                                    g.drawString("5 point", 10, 300);
                                    g.drawString("10 point", 10, 350);
                                    g.drawString("15 point", 10, 400);
                                    g.drawString("> free play", 10, 450);
                                }
                            } else if(a == 2) {
                                g.drawString("Easy", 10, 100);
                                g.drawString("> Medium", 10, 150);
                                g.drawString("Hard", 10, 200);
                                if(b == 1) {
                                    g.drawString("> 5 point", 10, 300);
                                    g.drawString("10 point", 10, 350);
                                    g.drawString("15 point", 10, 400);
                                    g.drawString("free play", 10, 450);
                                } else if(b == 2) {
                                    g.drawString("5 point", 10, 300);
                                    g.drawString("> 10 point", 10, 350);
                                    g.drawString("15 point", 10, 400);
                                    g.drawString("free play", 10, 450);
                                } else if(b == 3) {
                                    g.drawString("5 point", 10, 300);
                                    g.drawString("10 point", 10, 350);
                                    g.drawString("> 15 point", 10, 400);
                                    g.drawString("free play", 10, 450);
                                } else if(b == 4) {
                                    g.drawString("5 point", 10, 300);
                                    g.drawString("10 point", 10, 350);
                                    g.drawString("15 point", 10, 400);
                                    g.drawString("> free play", 10, 450);
                                }
                            } else if(a == 3) {
                                g.drawString("Easy", 10, 100);
                                g.drawString("Medium", 10, 150);
                                g.drawString("> Hard", 10, 200);
                                if(b == 1) {
                                    g.drawString("> 5 point", 10, 300);
                                    g.drawString("10 point", 10, 350);
                                    g.drawString("15 point", 10, 400);
                                    g.drawString("free play", 10, 450);
                                } else if(b == 2) {
                                    g.drawString("5 point", 10, 300);
                                    g.drawString("> 10 point", 10, 350);
                                    g.drawString("15 point", 10, 400);
                                    g.drawString("free play", 10, 450);
                                } else if(b == 3) {
                                    g.drawString("5 point", 10, 300);
                                    g.drawString("10 point", 10, 350);
                                    g.drawString("> 15 point", 10, 400);
                                    g.drawString("free play", 10, 450);
                                } else if(b == 4) {
                                    g.drawString("5 point", 10, 300);
                                    g.drawString("10 point", 10, 350);
                                    g.drawString("15 point", 10, 400);
                                    g.drawString("> free play", 10, 450);
                                }
                            }
                            g.dispose();
                            try {
                                Thread.sleep(1000);
                            } catch(InterruptedException ie) {

                            }
                        }
                    }
                };
                tt.start();
                return;
            }
            Thread.sleep(50);
            draw();
            ball.move();
            ball2.move();
            if(ball.x > 1000 && ball2.x > 1000)
                lives2++;
            if(ball.x <= 20 && ball2.x <= 20)
                lives++;
            if(ball.x > 1000 && ball2.x <= 20)
                lives2++;
            if(ball.x <= 20 && ball2.x > 1000)
                lives++;
            if(ball.x > 1000 && ball2.x <= 20) {
                try {
                    Thread.sleep(3000);
                } catch(InterruptedException ie) {

                }
                ball.x = 100;
                ball2.x = 300;
                ball.y = 200;
                ball2.y = 100;
                ball.plusx = 7;
                ball.plusy = 12;
                ball2.plusx = 7;
                ball2.plusy = 12;
            }
            if(ball2.x > 1000 && ball.x <= 20) {
                try {
                    Thread.sleep(3000);
                } catch(InterruptedException ie) {

                }
                ball.x = 100;
                ball2.x = 300;
                ball.y = 200;
                ball2.y = 100;
                ball.plusx = 7;
                ball.plusy = 12;
                ball2.plusx = 7;
                ball2.plusy = 12;
            }
            if(ball.x <= 20 && ball2.x <= 20) {
                try {
                    Thread.sleep(3000);
                } catch(InterruptedException ie) {

                }
                ball.x = 100;
                ball2.x = 300;
                ball.y = 200;
                ball2.y = 100;
                ball.plusx = 7;
                ball.plusy = 12;
                ball2.plusx = 7;
                ball2.plusy = 12;
            }
            if(ball.x > 1000 && ball2.x > 1000) {
                try {
                    Thread.sleep(3000);
                } catch(InterruptedException ie) {

                }
                ball.x = 100;
                ball2.x = 300;
                ball.y = 200;
                ball2.y = 100;
                ball.plusx = 7;
                ball.plusy = 12;
                ball2.plusx = 7;
                ball2.plusy = 12;
            }
            paddle.draw();
            enemyPaddle.draw();
            drawBall();
            drawBall2();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    private void drawBall() {
        Graphics g = p.getGraphics();
        g.setColor(Color.white);
        g.fillOval(ball.x,ball.y,20,20);
        g.setColor(Color.red);
        g.drawOval(ball.x,ball.y,20,20);
        g.dispose();
    }
    
    private void drawBall2() {
        Graphics g = p.getGraphics();
        g.setColor(Color.white);
        g.fillOval(ball2.x,ball2.y,20,20);
        g.setColor(Color.red);
        g.drawOval(ball2.x,ball2.y,20,20);
        g.dispose();
    }

    public void draw() {
        Graphics g = p.getGraphics();
        g.setColor(new Color(44,118,140));
        g.fillRect(0,0,1000,700);
        g.dispose();
    }
    
    public static void main(String[] args) {
        new Pong();
    }
}