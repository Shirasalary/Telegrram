package org.example;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainPanel extends JPanel {

    private JCheckBox jokesCheckBox;
    private JCheckBox countriesCheckBox;
    private JCheckBox changeMoneyCheckBox;
    private JCheckBox quotesCheckBox;
    private JCheckBox weatherCheckBox;

    private JLabel showOptions;
    private JLabel informationForUser;

    private JButton update;
    public MainPanel(int x, int y, int width, int height){
        this.setBounds(x,y,width,height);
        this.setLayout(null);
        createObjects();

        this.update.addActionListener((e) -> {
            if (isValidChoose()){
                //כאן הבוט מתחיל לפעול
            try{
            TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
            api.registerBot(new Bot(getManagerChoose()));
             }catch (Exception exception){
            throw new RuntimeException();
            }

            }else {
                this.informationForUser.setText("Not valid choose");
            }
        });

        addObjects();
        this.setVisible(true);
    }

    private void createObjects(){

        this.showOptions = Utils.newLabel("Type of bot activities:",
                this.getX() +Constants.MARGIN_FROM_LEFT,
                this.getY() +Constants.MARGIN_FROM_TOP);
        this.informationForUser = Utils.newLabel("Choose types",
                this.getX() +Constants.MARGIN_FROM_LEFT,
                this.getHeight() - 2*Constants.MARGIN_FROM_TOP - Constants.LABEL_HEIGHT );

        // this.jokesCheckBox.isSelected(); בודק האם השדה סומן או לא
        this.jokesCheckBox = Utils.newCheckBox("Jokes"
                ,this.showOptions.getX(),
                this.showOptions.getY() +Constants.CHECK_BOX_HEIGHT +Constants.MARGIN_FROM_TOP);
        this.countriesCheckBox = Utils.newCheckBox("Countries" ,this.showOptions.getX(),
                this.jokesCheckBox.getY() +Constants.CHECK_BOX_HEIGHT +Constants.MARGIN_FROM_TOP);
        this.changeMoneyCheckBox = Utils.newCheckBox("Currency exchange" ,this.showOptions.getX(),
                this.countriesCheckBox.getY() +Constants.CHECK_BOX_HEIGHT +Constants.MARGIN_FROM_TOP);
        this.quotesCheckBox = Utils.newCheckBox("Quotes" ,this.showOptions.getX(),
                this.changeMoneyCheckBox.getY() +Constants.CHECK_BOX_HEIGHT +Constants.MARGIN_FROM_TOP);
        this.weatherCheckBox = Utils.newCheckBox("Weather" ,this.showOptions.getX(),
                this.quotesCheckBox.getY() +Constants.CHECK_BOX_HEIGHT +Constants.MARGIN_FROM_TOP);

        this.update = Utils.newButton("Update", this.getX() +Constants.MARGIN_FROM_LEFT,
                this.weatherCheckBox.getY()+Constants.CHECK_BOX_HEIGHT +Constants.MARGIN_FROM_TOP );

    }

    private void addObjects(){
        this.add(this.jokesCheckBox);
        this.add(this.countriesCheckBox);
        this.add(this.quotesCheckBox);
        this.add(this.weatherCheckBox);
        this.add(this.changeMoneyCheckBox);
        this.add(this.showOptions);
        this.add(this.informationForUser);
        this.add(this.update);
    }

    private List<String> getManagerChoose(){
        List<String> managerChoose = new LinkedList<>();
        if (this.jokesCheckBox.isSelected()){
            managerChoose.add("Joke");
        }
        if (this.weatherCheckBox.isSelected()){
            managerChoose.add("Weather");
        }
        if (this.quotesCheckBox.isSelected()){
            managerChoose.add("Quote");
        }
        if (this.changeMoneyCheckBox.isSelected()){
            managerChoose.add("Currency exchange");
        }
        if (this.countriesCheckBox.isSelected()){
            managerChoose.add("country");
        }
        return managerChoose;
    }

    private boolean isValidChoose(){
        boolean result = true;
        int count = 0;
        if (this.jokesCheckBox.isSelected()){
            count++;
        }
        if (this.weatherCheckBox.isSelected()){
            count++;
        }
        if (this.quotesCheckBox.isSelected()){
            count++;
        }
        if (this.changeMoneyCheckBox.isSelected()){
            count++;
        }
        if (this.countriesCheckBox.isSelected()){
            count++;
        }

        if ((count == 0) || (count>Constants.MAX_OPTIONS)){
            result =false;
        }
        return result;
    }
}
