package com.company.model;

import com.company.image.ImageFactory;
import javax.swing.*;

import static com.company.image.Image.UFO;

public class EnemyShip extends Sprite{

    private boolean visible = true;

    public EnemyShip(int x, int y) {
        this.x = x;
        this.y = y;
        init();
    }

    private void init() {
        ImageIcon imageIcon = ImageFactory.createImage(UFO);
        setImage(imageIcon.getImage());
    }

    public void move(int direction) {
        this.x += direction;
    }

    @Override
    public void move() {

    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
