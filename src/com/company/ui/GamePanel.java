package com.company.ui;

import com.company.callbacks.GameEventListener;
import com.company.image.*;
import com.company.model.EnemyShip;
import com.company.model.Laser;
import com.company.model.SpaceShip;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import static com.company.constants.Constants.*;
import static com.company.image.Image.BACKGROUND;

public class GamePanel extends JPanel {

    private ImageIcon backgroundImage;
    private Timer timer;
    private SpaceShip spaceShip;
    private boolean inGame = true;
    private Laser laser;
    private int direction = -1;
    private java.util.List<EnemyShip> enemyShips;

    public GamePanel(){
        initVariables();
        initLayout();
        initGame();
    }

    private void initGame() {

        for (int i = 0; i < ENEMY_SHIP_ROW; i++) {
            for (int j = 0; j < ENEMY_SHIP_COLUMN; j++) {
                EnemyShip enemyShip = new EnemyShip(ENEMY_SHIP_INIT_X + 50 * j, ENEMY_SHIP_INIT_Y + 50 * i);
                this.enemyShips.add(enemyShip);
            }
        }
    }

    private void initVariables() {
        this.spaceShip = new SpaceShip();
        this.laser = new Laser();
        this.enemyShips = new ArrayList<>();
        this.backgroundImage = ImageFactory.createImage(BACKGROUND);
        this.timer = new Timer(GAME_SPEED, new GameLoop(this)); // needed to make animations
        this.timer.start();
    }

    private void initLayout() {
        addKeyListener(new GameEventListener(this));
        setFocusable(true);
        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
    }

    private void drawPlayer(Graphics g) {
        g.drawImage(spaceShip.getImage(), spaceShip.getX(), spaceShip.getY(), this);
    }

    private void drawLaser(Graphics g) {
        if (!laser.isDead())
            g.drawImage(laser.getImage(), laser.getX(), laser.getY(), this);
    }

    private void drawAlien(Graphics g) {
        for (EnemyShip enemyShip: this.enemyShips) {
            if (enemyShip.isVisible()) {
                g.drawImage(enemyShip.getImage(), enemyShip.getX(), enemyShip.getY(), this);
            }
        }
    }

    // set Background
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(backgroundImage.getImage(), // image
                0,0, // starting point of image
                null // fulfill Panel with the image
        );

        doDrawing(g);
    }

    private void doDrawing(Graphics g) {
        if (inGame) {
            drawPlayer(g);
            drawLaser(g);
            drawAlien(g);
        } else {
            if (timer.isRunning()){
                timer.stop();
            }
        }
        Toolkit.getDefaultToolkit().sync();
    }

    // call every 10 millisecond
    public void doOneLoop() {
        update();
        repaint(); // paintComponent will be called
    }

    private void update() {
        this.spaceShip.move();
        this.laser.move();
    }

    public void keyReleased(KeyEvent e) {
        this.spaceShip.keyReleased(e);
    }

    public void keyPressed(KeyEvent e) {
        this.spaceShip.keyPressed(e);

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE) {
            int laserX = this.spaceShip.getX();
            int laserY = this.spaceShip.getY();

            if (inGame && laser.isDead()) {
                laser = new Laser(laserX, laserY);
            }
        }
    }
}
