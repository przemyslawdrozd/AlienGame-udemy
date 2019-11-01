package com.company.constants;

import java.util.Collections;

public class Constants {

    private Constants(){
    }

    public static final String TITLE = "Space Invasion";
    public static final int BOARD_WIDTH = 900;
    public static final int BOARD_HEIGHT = 750;

    // UFO related constants
    public static final int ENEMY_SHIP_WIDTH = 32;
    public static final int ENEMY_SHIP_HEIGHT = 24;
    public static final int ENEMY_SHIP_INIT_X = 280;
    public static final int ENEMY_SHIP_INIT_Y = 100;
    public static final int ENEMY_SHIP_ROW = 4;
    public static final int ENEMY_SHIP_COLUMN = 8;

    // speed of the application
    public static final int GAME_SPEED = 10;
    public static final int SPACESHIP_WIDTH = 34;
    public static final int SPACESHIP_HEIGHT = 28;

    // Speed of the laser
    public static final int LASER_HORIZONTAL_TRANS = 4;

    // images for the objects
    public static final String UFO_IMAGE_URL = "/Users/przemo06133/Documents/aliengame/src/images/ufo.png";
    public static final String LASER_IMAGE_URL = "/Users/przemo06133/Documents/aliengame/src/images/laser.png";
    public static final String BOMB_IMAGE_URL = "/Users/przemo06133/Documents/aliengame/src/images/bomb.png";
    public static final String BACKGROUND_IMAGE_URL = "/Users/przemo06133/Documents/aliengame/src/images/background.jpg";
    public static final String SPACESHIP_IMAGE_URL = "/Users/przemo06133/Documents/aliengame/src/images/spaceship.png";
}
