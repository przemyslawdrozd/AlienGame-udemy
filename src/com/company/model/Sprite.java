package com.company.model;

import java.awt.*;

public abstract class Sprite {

    private Image image;
    private boolean isDead;

    protected int x;
    protected int y;
    protected int dx;

    public abstract void move();

    public Sprite() {
        this.isDead = false;
    }

    public void die() {
        this.isDead = true;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }
}
