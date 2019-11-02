package com.company.model;

import com.company.image.ImageFactory;

import javax.swing.*;

import static com.company.constants.Constants.*;
import static com.company.image.Image.BOMB;

public class Bomb extends Sprite {

    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;
        init();
    }

    private void init() {
        ImageIcon imageIcon = ImageFactory.createImage(BOMB);
        setImage(imageIcon.getImage());
    }

    @Override
    public void move() {
        this.y++; // one px every iteration

        if (y >= BOARD_HEIGHT - BOMB_HEIGHT) {
            die();
        }
    }
}
