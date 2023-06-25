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

        this.title = Utils.newLabel("Statistics:",this.getX() + Constants.MARGIN_FROM_LEFT,this.getY());
        this.countAllRequestsLabel = Utils.newLabel("All Requests:", this.title.getX(),this.title.getY()+Constants.LABEL_HEIGHT+ Constants.MARGIN_FROM_TOP /2);
        this.countAllRequestsLabel_Ans = Utils.newLabel("",this.countAllRequestsLabel.getX() + Constants.LABEL_WIDTH + Constants.MARGIN_FROM_LEFT ,this.countAllRequestsLabel.getY() );

        addObjects();
        this.setVisible(true);
    }

    private void addObjects(){
        this.add(this.title);
        this.add(this.countAllRequestsLabel);
        this.add(this.countAllRequestsLabel_Ans);

    }
}
