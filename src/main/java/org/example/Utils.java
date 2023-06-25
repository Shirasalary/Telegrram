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

    public static JLabel newLabel (String text, int x, int y)
    {
        JLabel label = new JLabel(text);
        label.setBounds(x,y,Constants.LABEL_WIDTH,Constants.LABEL_HEIGHT);
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
}
