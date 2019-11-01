package com.company.model;

import com.company.image.ImageFactory;

import javax.swing.*;

import java.awt.event.KeyEvent;

import static com.company.constants.Constants.*;
import static com.company.image.Image.SPACESHIP;

public class SpaceShip extends Sprite {

    public SpaceShip() {
        init();
    }

    private void init() {
        ImageIcon imageIcon = ImageFactory.createImage(SPACESHIP);
        setImage(imageIcon.getImage());

        int start_x = BOARD_WIDTH / 2 - SPACESHIP_WIDTH / 2;
        int start_y = BOARD_HEIGHT - 100;

        setX(start_x);
        setY(start_y);
    }

    @Override
    public void move() {
        x += dx;

        if (x < SPACESHIP_WIDTH) {
            x = SPACESHIP_WIDTH;
        }

        if (x > (BOARD_WIDTH - SPACESHIP_WIDTH * 2)) {
            x = (BOARD_WIDTH - SPACESHIP_WIDTH * 2);
        }
    }


    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx  = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx  = -2;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 2;
        }

    }
}
