package org.example;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import io.quickchart.QuickChart;

public class MainPanel extends JPanel {

    private JCheckBox jokesCheckBox;
    private JCheckBox countriesCheckBox;
    private JCheckBox changeMoneyCheckBox;
    private JCheckBox quotesCheckBox;
    private JCheckBox numberFactCheckBox;

    private JLabel showOptions;
    private JLabel informationForUser;
    private JLabel lastTenRequestsLabel;

    private Bot bot;

    private Statistics statistics;

    private JTextArea lastTenRequestsTextArea;

    private JButton update;
    public MainPanel(int x, int y, int width, int height){
        this.setBounds(x,y,width,height);
        this.setLayout(null);

        try{
            TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
            this.bot = new Bot(new ArrayList<>());
            api.registerBot(this.bot);
        }catch (Exception exception){
            exception.printStackTrace();
            throw new RuntimeException();
        }

        createObjects();

        this.update.addActionListener((e) -> {
            if (isValidChoose()){
                //כאן הבוט מתחיל לפעול
                this.bot.setTypeApi(getManagerChoose());

            }else {
                this.informationForUser.setText("Not valid choose");
            }
        });

        new Thread(() ->{
           while (true){
               this.lastTenRequestsTextArea.setText(this.bot.getTenLastRequests());

               Utils.sleep(Constants.NEED_TO_WAIT);
           }
        }).start();

        addObjects();
        this.setVisible(true);
    }

    private void createObjects(){

        this.showOptions = Utils.newLabel("Type of bot activities:",
                this.getX() +Constants.MARGIN_FROM_LEFT,
                this.getY() +Constants.MARGIN_FROM_TOP,Constants.BIG_LABEL);
        this.informationForUser = Utils.newLabel("Choose types",
                this.getX() +Constants.MARGIN_FROM_LEFT,
                this.getHeight() - 2*Constants.MARGIN_FROM_TOP - Constants.LABEL_HEIGHT,Constants.BIG_LABEL );

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
        this.numberFactCheckBox = Utils.newCheckBox("Num facts" ,this.showOptions.getX(),
                this.quotesCheckBox.getY() +Constants.CHECK_BOX_HEIGHT +Constants.MARGIN_FROM_TOP);

        this.update = Utils.newButton("Update", this.getX() +Constants.MARGIN_FROM_LEFT,
                this.numberFactCheckBox.getY()+Constants.CHECK_BOX_HEIGHT +Constants.MARGIN_FROM_TOP );

        int xStatistics = this.showOptions.getX() + (Constants.LABEL_WIDTH/4)*3 ;
        this.statistics = new Statistics(this.bot,xStatistics
                ,this.getY() + Constants.MARGIN_FROM_TOP /2,
                this.getWidth()- xStatistics,
                this.getHeight()/4);

        int xTextArea = this.showOptions.getX() + Constants.LABEL_WIDTH + Constants.TEXT_AREA_WIDTH/2;
        this.lastTenRequestsLabel = Utils.newLabel("Last 10 Requests:",
                xTextArea, this.statistics.getHeight() + Constants.MARGIN_FROM_TOP, Constants.BIG_LABEL);
        this.lastTenRequestsTextArea = Utils.newTextArea("You dont have any request"
                ,xTextArea
                ,this.lastTenRequestsLabel.getY() + Constants.LABEL_HEIGHT + Constants.MARGIN_FROM_TOP );

    }

    private void addObjects(){
        this.add(this.jokesCheckBox);
        this.add(this.countriesCheckBox);
        this.add(this.quotesCheckBox);
        this.add(this.numberFactCheckBox);
        this.add(this.changeMoneyCheckBox);
        this.add(this.showOptions);
        this.add(this.informationForUser);
        this.add(this.update);
        this.add(this.statistics);
        this.add(this.lastTenRequestsTextArea);
        this.add(this.lastTenRequestsLabel);
    }

    private List<String> getManagerChoose(){
        List<String> managerChoose = new LinkedList<>();
        if (this.jokesCheckBox.isSelected()){
            managerChoose.add(Constants.JOKE_API);
        }
        if (this.numberFactCheckBox.isSelected()){
            managerChoose.add(Constants.NUM_FACT_API);
        }
        if (this.quotesCheckBox.isSelected()){
            managerChoose.add(Constants.QUOTE_API);
        }
        if (this.changeMoneyCheckBox.isSelected()){
            managerChoose.add(Constants.EXCHANGE_API);
        }
        if (this.countriesCheckBox.isSelected()){
            managerChoose.add(Constants.COUNTRY_API);
        }
        return managerChoose;
    }

    private boolean isValidChoose(){
        boolean result = true;
        int count = 0;
        if (this.jokesCheckBox.isSelected()){
            count++;
        }
        if (this.numberFactCheckBox.isSelected()){
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
