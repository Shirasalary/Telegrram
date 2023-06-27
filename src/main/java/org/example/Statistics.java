package org.example;

import javax.swing.*;

public class Statistics extends JPanel {

    private JLabel title;
    private JLabel countAllRequestsLabel;
    private JLabel countAllUsersLabel;
    private JLabel mostActiveUser;
    private JLabel mostActiveApi;
    private JLabel countAllRequestsLabel_Ans;
    private JLabel countAllUsersLabel_Ans;
    private JLabel mostActiveUser_Ans;
    private JLabel mostActiveApi_Ans;

    private Bot bot;

    public Statistics(Bot bot,int x, int y, int width, int height){
        this.setBounds(x,y,width,height);
        this.setLayout(null);
        this.bot = bot;
        createObjects();
        new Thread(()->{
            while (true){
                this.countAllRequestsLabel_Ans.setText(Integer.toString(this.bot.countBotRequests()));
                this.countAllUsersLabel_Ans.setText(Integer.toString(this.bot.countUsers()));
                this.mostActiveUser_Ans.setText(this.bot.mostActiveUser());
                this.mostActiveApi_Ans.setText(this.bot.mostActiveApiByUsers());

                Utils.sleep(Constants.NEED_TO_WAIT);
            }
        }).start();

        addObjects();
        this.setVisible(true);
    }

    private void createObjects(){
        this.title = Utils.newLabel("Statistics:",this.getX() + Constants.MARGIN_FROM_LEFT,this.getY(),Constants.BIG_LABEL);
        this.countAllRequestsLabel = Utils.newLabel("All Requests:", this.title.getX(),this.title.getY()+Constants.LABEL_HEIGHT+ Constants.MARGIN_FROM_TOP /2,Constants.BIG_LABEL);
        this.countAllRequestsLabel_Ans = Utils.newLabel("",this.countAllRequestsLabel.getX() + (Constants.LABEL_WIDTH/3)*2 + Constants.MARGIN_FROM_LEFT ,this.countAllRequestsLabel.getY(),Constants.SMALL_LABEL );
        this.countAllUsersLabel = Utils.newLabel("Users num:",this.countAllRequestsLabel.getX(),this.countAllRequestsLabel.getY() + Constants.LABEL_HEIGHT + Constants.MARGIN_FROM_TOP ,Constants.BIG_LABEL);
        this.countAllUsersLabel_Ans = Utils.newLabel("",this.countAllUsersLabel.getX() +(Constants.LABEL_WIDTH/3)*2 + Constants.MARGIN_FROM_LEFT ,this.countAllUsersLabel.getY(),Constants.SMALL_LABEL );
        this.mostActiveUser = Utils.newLabel("Most Active User:",this.countAllRequestsLabel_Ans.getX() + Constants.SMALL_LABEL_WIDTH + Constants.MARGIN_FROM_LEFT , this.countAllRequestsLabel.getY(), Constants.BIG_LABEL  );
        this.mostActiveUser_Ans = Utils.newLabel("", this.mostActiveUser.getX()+ (Constants.LABEL_WIDTH/3)*2 + Constants.MARGIN_FROM_LEFT*2 ,this.mostActiveUser.getY(), Constants.BIG_LABEL);
        this.mostActiveApi = Utils.newLabel("Most Active Api:",this.mostActiveUser.getX(),this.mostActiveUser.getY() + Constants.LABEL_HEIGHT+ Constants.MARGIN_FROM_TOP,Constants.BIG_LABEL );
        this.mostActiveApi_Ans = Utils.newLabel("",this.mostActiveApi.getX()+ (Constants.LABEL_WIDTH/3)*2 + Constants.MARGIN_FROM_LEFT*2,this.mostActiveApi.getY() , Constants.BIG_LABEL  );

    }

    private void addObjects(){
        this.add(this.title);
        this.add(this.countAllRequestsLabel);
        this.add(this.countAllRequestsLabel_Ans);
        this.add(this.countAllUsersLabel);
        this.add(this.countAllUsersLabel_Ans);
        this.add(this.mostActiveUser);
        this.add(this.mostActiveUser_Ans);
        this.add(this.mostActiveApi);
        this.add(this.mostActiveApi_Ans);

    }
}
