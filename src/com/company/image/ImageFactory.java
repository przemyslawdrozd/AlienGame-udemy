package com.company.image;

import javax.swing.*;
import static com.company.constants.Constants.*;

public class ImageFactory {

    public static ImageIcon createImage(Image image) {
        ImageIcon imageIcon;

        switch (image) {
            case UFO:
                imageIcon = new ImageIcon(UFO_IMAGE_URL);
                break;
            case BOMB:
                imageIcon = new ImageIcon(BOMB_IMAGE_URL);
                break;
            case LASER:
                imageIcon = new ImageIcon(LASER_IMAGE_URL);
                break;
            case SPACESHIP:
                imageIcon = new ImageIcon(SPACESHIP_IMAGE_URL);
                break;
            case BACKGROUND:
                imageIcon = new ImageIcon(BACKGROUND_IMAGE_URL);
                break;
            default:
                return null;
        }

        return imageIcon;
    }
}
