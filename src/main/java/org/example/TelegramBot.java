package org.example;

import javax.swing.*;

public class TelegramBot extends JFrame {


    public TelegramBot(){
        this.setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setTitle("Telegram Bot by Shira & Hila & Sara");
        MainPanel mainPanel = new MainPanel(Constants.X_WINDOW, Constants.Y_WINDOW, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        this.add(mainPanel);
        this.setVisible(true);
    }
}
