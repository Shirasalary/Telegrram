package org.example;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Constants {

    public static final int WINDOW_WIDTH = 1000, WINDOW_HEIGHT =650, X_WINDOW = 0, Y_WINDOW = 0 ;
    public static final Font myFont = new Font("Gisha", Font.BOLD,24);

    public static final int CHECK_BOX_WIDTH = 250, CHECK_BOX_HEIGHT = 50;
    public static final int LABEL_WIDTH = 250, LABEL_HEIGHT = 40;

    public static final int MARGIN_FROM_TOP = 25 , MARGIN_FROM_LEFT = 15;

    public static final int BUTTON_WIDTH = 150, BUTTON_HEIGHT = 30;

    public static final int MAX_OPTIONS = 3;

    public static String[] LANGUAGE = {"English", "Spanish","French"} , LANGUAGE_CODE = {"en", "es","fr"};

    public static String[] QUOTE_TAG = {"History", "Love","Famous quotes"}, QUOTE_TAG_CODE = {"history", "love","famous-quotes"};
    public static final int END_REQUEST = 4, REQUEST_IN_PROCESS =2 , START_REQUEST = 1 ,WAIT_TO_RESPOND = 3;
    public static final String JOKE_API ="Joke", QUOTE_API = "Quote", COUNTRY_API = "Country";


}
