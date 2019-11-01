package com.company.ui;

import com.company.constants.Constants;
import javax.swing.*;

public class GameMainFrame extends JFrame {

    public GameMainFrame() {
        initLayout();
    }

    private void initLayout() {
        add(new GamePanel());
        setTitle(Constants.TITLE);
        setResizable(false);
        setVisible(true);
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
//        setIconImage(ImageFactory.createImage(Image.SPACESHIP).getImage());
    }
}
