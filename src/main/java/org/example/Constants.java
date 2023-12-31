package org.example;

import org.glassfish.jersey.server.internal.scanning.PackageNamesScanner;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Constants {

    public static final int WINDOW_WIDTH = 1000, WINDOW_HEIGHT =650, X_WINDOW = 0, Y_WINDOW = 0 ;
    public static final Font myFont = new Font("Gisha", Font.BOLD,20);

    public static final int CHECK_BOX_WIDTH = 250, CHECK_BOX_HEIGHT = 50;
    public static final int LABEL_WIDTH = 215, LABEL_HEIGHT = 35 , SMALL_LABEL_WIDTH = 50, SMALL_LABEL_HEIGHT = 35,
    BIG_LABEL =1, SMALL_LABEL =2;

    public static final int MARGIN_FROM_TOP = 25 , MARGIN_FROM_LEFT = 15;

    public static final int BUTTON_WIDTH = 150, BUTTON_HEIGHT = 30;

    public static final int MAX_OPTIONS = 3 ,API_NUM = 5 , API_REQUEST_OK = 200 ;

    public static final int NEED_TO_WAIT = 6000;

    public static String[] API_TYPES_NAME = {"Country","Joke","Quote" ,"Currency exchange" ,"Num fact"};

    public static String[] LANGUAGE = {"English", "Spanish","French"} , LANGUAGE_CODE = {"en", "es","fr"};

    public static String[] QUOTE_TAG = {"History", "Love","Famous quotes"}, QUOTE_TAG_CODE = {"history", "love","famous-quotes"};

    public static String[] EXCHANGE = {"ILS to USD", "ILS to EUR","USD to EUR"}, EXCHANGE_CODE = {"USD_ILS", "EUR_ILS","EUR_USD"};
    public static final int REQUEST_IN_PROCESS =2 , START_REQUEST = 1 ,WAIT_TO_RESPOND = 3;
    public static final String JOKE_API ="Joke", QUOTE_API = "Quote", COUNTRY_API = "Country" ,EXCHANGE_API ="Currency exchange",
    NUM_FACT_API = "Num fact";


//https://quickchart.io/chart?c={type:'bar',data:{labels:['Joke','Quote','Country','Currency exchange','Num fact'], datasets:[{label:'Requests num',data:[100,200,300,400]}]}}

    public static final int COUNTRY_API_CODE = 0 ,JOKE_API_CODE =1, QUOTE_API_CODE = 2 , EXCHANGE_API_CODE =3,
            NUM_FACT_API_CODE =4;

    public static final int MAX_LAST = 10;

    public static final int TEXT_AREA_WIDTH = 200, TEXT_AREA_HEIGHT = 300;

    public static final String TWO_PARTS_TYPE ="twopart" , SINGLE_TYPE ="single";

    public static final String COUNTRY_API_PATH = "https://restcountries.com/v2/name/",
            COUNTRY_CODE_API_PATH ="https://restcountries.com/v2/alpha/" ,
    JOKE_API_PATH = "https://v2.jokeapi.dev/joke/Any?lang=" ,
    QUOTE_API_PATH_START = "https://api.quotable.io/quotes/random?tags=" , QUOTE_API_PATH_END = "&&maxLength=100",
    EXCHANGE_API_PATH = "https://api.api-ninjas.com/v1/exchangerate?pair=" ,
    NUM_FACT_API_PATH = "http://numbersapi.com/random/math?json" ,
    START_GRAPH_PATH = "https://quickchart.io/chart?c={type:'bar',data:{labels:['Joke','Quote','Country','Currency exchange','Num fact'], datasets:[{label:'Requests num',data:[",
    END_GRAPH_PATH ="]}]}}";


}
