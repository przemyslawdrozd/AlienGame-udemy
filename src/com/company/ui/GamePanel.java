package com.company.ui;

import com.company.callbacks.GameEventListener;
import com.company.image.*;
import com.company.model.Bomb;
import com.company.model.EnemyShip;
import com.company.model.Laser;
import com.company.model.SpaceShip;
import com.company.sound.SoundFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import static com.company.constants.Constants.*;
import static com.company.image.Image.BACKGROUND;

public class GamePanel extends JPanel {

    private ImageIcon backgroundImage;
    private SoundFactory soundFactory;
    private Timer clockTimer;
    private SpaceShip spaceShip;
    private boolean inGame = true;
    private Laser laser;
    private int direction = -1;
    private java.util.List<EnemyShip> enemyShips;
    private java.util.List<Bomb> bombs;
    private Random generator;
    private String message;
    private int deaths = 0;

    public GamePanel() {
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
        this.soundFactory = new SoundFactory();
        this.generator = new Random();
        this.spaceShip = new SpaceShip();
        this.laser = new Laser();
        this.enemyShips = new ArrayList<>();
        this.bombs = new ArrayList<>();
        this.backgroundImage = ImageFactory.createImage(BACKGROUND);
        this.clockTimer = new Timer(GAME_SPEED, new GameLoop(this)); // needed to make animations
        this.clockTimer.start();
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
        for (EnemyShip enemyShip : this.enemyShips) {
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
                0, 0, // starting point of image
                null // fulfill Panel with the image
        );

        doDrawing(g);
    }

    private void doDrawing(Graphics g) {
        if (inGame) {
            drawPlayer(g);
            drawLaser(g);
            drawAlien(g);
            drawBombs(g);
        } else {
            if (clockTimer.isRunning()) {
                clockTimer.stop();
            }

            drawGameOver(g);
        }
        Toolkit.getDefaultToolkit().sync();
    }

    private void drawGameOver(Graphics g) {
        g.drawImage(backgroundImage.getImage(),0, 0, null);

        Font font = new Font("Helvetica", Font.BOLD, 50);
        FontMetrics fontMetrics = this.getFontMetrics(font);

        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString(message,
                (BOARD_WIDTH / 2 - fontMetrics.stringWidth(message) / 2),
                (BOARD_HEIGHT / 2 - 100));

    }

    private void drawBombs(Graphics g) {
        for (Bomb bomb: this.bombs) {
            if (!bomb.isDead()) {
                g.drawImage(bomb.getImage(), bomb.getX(), bomb.getY(), this);
            }
        }
    }

    // call every 10 millisecond
    public void doOneLoop() {
        update();
        repaint(); // paintComponent will be called
    }

    private void update() {

        if (spaceShip.isDead()) {
            inGame = false;
            message = GAME_OVER + " killed: " + deaths;
        }

        if (deaths == this.enemyShips.size()) {
            inGame = false;
            message = WIN + " killed: " + deaths;
        }
        this.spaceShip.move();

        int shotX = laser.getX();
        int shotY = laser.getY();

        if (!laser.isDead()) {
            for (EnemyShip alien: this.enemyShips) {
                int alienX = alien.getX();
                int alienY = alien.getY();

                if (!alien.isVisible()) {
                    continue;
                }

                // this is collision detection algorithm
                if (shotX >= alienX && shotX <= (alienX + ENEMY_SHIP_WIDTH) &&
                     shotY <= (alienY) && shotY <= (alienY + ENEMY_SHIP_HEIGHT)) {
                    deaths++;
                    soundFactory.explosion();
                    alien.setVisible(false);
                    laser.die();
                }
            }
            this.laser.move();
        }

        this.enemyShips.forEach(enemyShip -> {
            if (enemyShip.getX() >= BOARD_WIDTH - 2 * BORDER_PADDING && direction != -1
                    || enemyShip.getX() <= BORDER_PADDING && direction != 1) {
                direction *= -1;

                enemyShips.forEach(ufo -> ufo.setY(ufo.getY() + GO_DOWN));
            }
            if (enemyShip.isVisible()) {

                // if enemy ships reach bottom
                if (enemyShip.getY() > BOARD_HEIGHT - 100 - SPACESHIP_HEIGHT) {
                    spaceShip.die();
                }

                enemyShip.move(direction);
            }
        });

        // Update for bombs
        for (EnemyShip enemyShip: this.enemyShips) {
            if (enemyShip.isVisible() && generator.nextDouble() < BOMB_DROPPING_PROBABILITY) {
                Bomb bomb = new Bomb(enemyShip.getX(), enemyShip.getY());
                this.bombs.add(bomb);
            }
        }

        // moving the bombs
        for (Bomb bomb: bombs) {

            int bombX = bomb.getX();
            int bombY = bomb.getY();
            int spaceShipX = spaceShip.getX();
            int spaceShipY = spaceShip.getY();

            if (!bomb.isDead() && !spaceShip.isDead()) {

                if (bombX >= spaceShipX && bombX <= (spaceShipX + SPACESHIP_WIDTH) &&
                    bombY >= spaceShipY && bombY <= (spaceShipY + SPACESHIP_HEIGHT)) {

                    bomb.die();
                    spaceShip.die();
                }
            }

            if (!bomb.isDead()) {
                bomb.move();
            }
        }
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
                soundFactory.laser();
                laser = new Laser(laserX, laserY);
            }
        }
    }
}
