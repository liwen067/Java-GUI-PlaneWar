package ui;

import com.sun.glass.ui.Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Panel extends JPanel {
    private BufferedImage bg;
    private Plane plane;
//    EnemyPlane enemyPlane = new EnemyPlane();
    List<EnemyPlane> enemyPlanes = new ArrayList<EnemyPlane>();
    List<Fire> fires = new ArrayList<Fire>();

    public void begin(){
        new Thread(){
            public void run(){
                while(true){
                    epCreate();
                    epMove();
                    hit();
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    repaint();
                }
            }
        }.start();
    }
    int score = 0;
    private void hit() {
        for (int i = 0; i <enemyPlanes.size(); i++){
            EnemyPlane enemyPlane = enemyPlanes.get(i);
            for (int j = 0; j < fires.size();j++){
                Fire fire = fires.get(j);
                if (fire.getX() + fire.image.getWidth()/4>enemyPlane.getX() && fire.getX()<enemyPlane.getX()+enemyPlane.image.getWidth() && fire.getY()+fire.image.getHeight()/4>enemyPlane.getY()&&fire.getY()<enemyPlane.getY()+enemyPlane.image.getHeight()){
                    enemyPlanes.remove(i);
                    fires.remove(j);
                    score++;
                }
            }
        }
    }

    int index = 0;
    private void epCreate() {
        index++;
        if (index >= 20){
            EnemyPlane enemyPlane = new EnemyPlane();
            enemyPlanes.add(enemyPlane);
            index = 0;
        }
    }
    private void epMove() {
        for(int i = 0; i < enemyPlanes.size(); i++){
            EnemyPlane enemyPlane = enemyPlanes.get(i);
            enemyPlane.setY(enemyPlane.getY() + 5);

        }
    }
    //子弹的线程
    public void fireBegin(){
        new Thread(){
            public void run(){
                while (true){
                    fireCreate();
                    fireMove();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    repaint();
                }

            }
        }.start();
    }
    int index1 = 0;
    private void fireCreate() {
    index1++;
    if (index>=20){
        Fire fire = new Fire(plane);
        fires.add(fire);
        index1=0;

    }
    }
    private void fireMove() {
        for (int i = 0; i < fires.size();i++){
            Fire fire = fires.get(i);
            fire.setY(fire.getY() - 5);
        }
    }
    public Panel(Frame frame) {
        bg = GetImg.getImg("bg1.jpg");
        plane = new Plane();
        //鼠标操控飞机
        mouse();
        //键盘操控飞机
        keyControl(frame);}


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(bg,0,0,null);

        for(int i = 0; i < enemyPlanes.size(); i++){
            EnemyPlane enemyPlane = enemyPlanes.get(i);
            g.drawImage(enemyPlane.image,enemyPlane.getX(),enemyPlane.getY(),null);
        }

        for (int i = 0; i < fires.size(); i++){
            Fire fire = fires.get(i);
            g.drawImage(fire.image,fire.getX(),fire.getY(),fire.image.getWidth()/4,fire.image.getHeight()/4,null);
        }

        g.drawImage(plane.image,plane.getX(),plane.getY(),null);
//        g.drawImage(enemyPlane.image,enemyPlane.getX(),enemyPlane.getY(),null);
        g.setColor(Color.red);
        g.drawString("分数"+score,10,30);

    }


    private void keyControl(Frame frame) {
        KeyAdapter keyAdapter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                int i = e.getKeyCode();
                if(i==KeyEvent.VK_A||i==KeyEvent.VK_LEFT){
                    plane.setX(plane.getX()-30);
                    if(plane.getX() <= 0){
                        plane.setX(0);
                    }
                }else if(i==KeyEvent.VK_D||i==KeyEvent.VK_RIGHT){
                    plane.setX(plane.getX()+30);
                    if(plane.getX() >= 512){
                        plane.setX(512);
                    }
                }else if(i==KeyEvent.VK_W||i==KeyEvent.VK_UP){
                    plane.setY(plane.getY()-30);
                    if(plane.getY() <= 0){
                        plane.setY(0);
                    }
                }else if(i==KeyEvent.VK_S||i==KeyEvent.VK_DOWN){
                    plane.setY(plane.getY()+30);
                    if(plane.getY() >= 768){
                        plane.setY(768);
                    }
                }
                repaint();
            }
        };
    frame.addKeyListener(keyAdapter);

    }

    private void mouse() {
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                plane.setX(e.getX()-plane.image.getWidth()/2);
                plane.setY(e.getY()-plane.image.getHeight()/2);
                repaint();
            }
        };

        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);

    }
    }

