package org.example;

import io.quickchart.QuickChart;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Graph extends JPanel {

    private Bot bot;
    private ImageIcon imageGraph;

    public Graph(int x, int y, int width, int height , Bot bot)  {

        //"https://quickchart.io/chart?c={type:'bar',data:{labels:['Joke','Quote','Country','Currency exchange','Num fact'], datasets:[{label:'Requests num',data:[100,200,300,400]}]}}"

        this.setBounds(x,y,width,height);
        this.setLayout(null);
        this.bot = bot;
        QuickChart chart = new QuickChart();
        chart.setWidth(this.getWidth());
        chart.setHeight(this.getHeight());
        //chart.setBackgroundColor("white");
        createImageGraph(chart);

        new Thread(()-> {
            while (true){

                createImageGraph(chart);
                Utils.sleep(Constants.NEED_TO_WAIT);
            }
        }).start();


        this.setVisible(true);
    }

    private void createImageGraph(QuickChart chart){
        try {
            chart.setConfig(Constants.START_GRAPH_PATH+ this.bot.getCountApiByTape() + Constants.END_GRAPH_PATH);
            String pathToGraph = chart.getUrl();
            this.imageGraph = new ImageIcon(new URL(pathToGraph));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        //graphics.drawImage(imageGraph.getImage(), this.getX(), this.getY(), this);
        graphics.drawImage(this.imageGraph.getImage(),this.getX(),this.getY(),this.getWidth(), this.getHeight(),null );

    }

}
