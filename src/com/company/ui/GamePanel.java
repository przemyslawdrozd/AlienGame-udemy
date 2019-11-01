package com.company.ui;

import com.company.callbacks.GameEventListener;
import com.company.image.*;
import com.company.model.SpaceShip;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

import static com.company.constants.Constants.*;
import static com.company.image.Image.BACKGROUND;

public class GamePanel extends JPanel {

    private ImageIcon backgroundImage;
    private Timer timer;
    private SpaceShip spaceShip;
    private boolean inGame = true;

    public GamePanel(){
        initVariables();
        initLayout();
    }

    private void initVariables() {
        this.spaceShip = new SpaceShip();
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
    }

    public void keyReleased(KeyEvent e) {
        this.spaceShip.keyReleased(e);
    }

    public void keyPressed(KeyEvent e) {
        this.spaceShip.keyPressed(e);
    }
}
