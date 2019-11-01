package com.company;

import com.company.ui.GameMainFrame;
import java.awt.*;

public class Main {

    public static void main(String[] args) {

        // Create a new thread to not block a main thread
        EventQueue.invokeLater(GameMainFrame::new);
    }
}
