package com.company.model;

import com.company.constants.Constants;
import com.company.image.Image;
import com.company.image.ImageFactory;

import javax.swing.*;

import static com.company.constants.Constants.LASER_HORIZONTAL_TRANS;
import static com.company.constants.Constants.SPACESHIP_WIDTH;
import static com.company.image.Image.LASER;

public class Laser extends Sprite {

    public Laser(int x, int y) {
        this.x = x;
        this.y = y;
        init();
    }

    public Laser() {
    }

    private void init() {
        ImageIcon imageIcon = ImageFactory.createImage(LASER);
        setImage(imageIcon.getImage());

        setX(x + (SPACESHIP_WIDTH / 2));
        setY(y);
    }

    @Override
    public void move() {
        this.y -= LASER_HORIZONTAL_TRANS;

        if (this.y < 0) {
            this.die();
        }
    }
}
