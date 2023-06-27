package org.example;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utils {

    public static JCheckBox newCheckBox(String text, int x, int y){
        JCheckBox jCheckBox = new JCheckBox(text);
        jCheckBox.setBounds(x,y,Constants.CHECK_BOX_WIDTH,Constants.CHECK_BOX_HEIGHT);
        jCheckBox.setFont(Constants.myFont);
        jCheckBox.setVisible(true);
        return jCheckBox;
    }

    public static JLabel newLabel (String text, int x, int y , int type)
    {
        JLabel label = new JLabel(text);
        if (type == Constants.BIG_LABEL){
            label.setBounds(x,y,Constants.LABEL_WIDTH,Constants.LABEL_HEIGHT);
        } else if (type == Constants.SMALL_LABEL) {
            label.setBounds(x,y,Constants.SMALL_LABEL_WIDTH,Constants.SMALL_LABEL_HEIGHT);
        }
        label.setFont(Constants.myFont);
        label.setVisible(true);
        return label;
    }

    public static JButton newButton (String text, int x, int y)
    {
        JButton button = new JButton(text);
        button.setBounds(x,y,Constants.BUTTON_WIDTH,Constants.BUTTON_HEIGHT);
        button.setFocusable(false);
        button.setFont(Constants.myFont);
        return button;
    }

    public static    List<String> convertArrayToList(String[] array){
        List<String> result = new ArrayList<>();
        result.addAll(Arrays.asList(array));
        return result;
    }

    public static void sleep(int seconds){
        try {
            Thread.sleep(seconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public static JTextArea newTextArea (String text, int x, int y){

        JTextArea textArea = new JTextArea(text);
        textArea.setBounds(x, y,Constants.TEXT_AREA_WIDTH,Constants.TEXT_AREA_HEIGHT);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        return textArea;
    }
}
