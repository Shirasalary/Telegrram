package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.GetRequest;

import java.util.List;

public class Api {

    private String[] apiTypes;
    private int[] countRequest;


    public Api(){
        this.countRequest = new int[Constants.API_NUM];
        this.apiTypes = Constants.API_TYPES_NAME;
    }

    public String getCountriesByeName(String endPath){
        return getApiTable(Constants.COUNTRY_API_CODE,
                Constants.COUNTRY_API_PATH + endPath,
                Constants.COUNTRY_API );
    }



    public String getJokeByLanguage(String language){

        return getApiTable(Constants.JOKE_API_CODE
                ,Constants.JOKE_API_PATH + language
                ,Constants.JOKE_API );
    }

    public String getQuoteByTag(String tag){

        return getApiTable(Constants.QUOTE_API_CODE
                ,Constants.QUOTE_API_PATH_START + tag + Constants.QUOTE_API_PATH_END
                , Constants.QUOTE_API);

    }
    public String getExchange(String coins){

        return getApiTable(Constants.EXCHANGE_API_CODE,
                Constants.EXCHANGE_API_PATH + coins,
                Constants.EXCHANGE_API );

    }

    public String getNumFact(){

        return getApiTable(Constants.NUM_FACT_API_CODE
                ,Constants.NUM_FACT_API_PATH ,
                Constants.NUM_FACT_API );

    }

    private String getApiTable(int apiCode, String allPath, String apiName){
        this.countRequest[apiCode]++;
        String json =getJson(allPath);
        String result = getMessage(json , apiName);
        return result;
    }

    private String getMessage(String json , String apiType){
        String message ="";
        try {

            if (!json.equals("")){
                ObjectMapper objectMapper = new ObjectMapper();
                if (apiType.equals(Constants.COUNTRY_API)){
                    List<Country> countries = objectMapper.readValue(json, new TypeReference<>() {});
                    for (int i =0;i< countries.size();i++){
                        message +=countries.get(i).toString();
                        message+="Borders: ";
                        List<String> borders = countries.get(i).getBorders();
                        for (int j = 0; j< borders.size();j++ ){
                            String jsonBorder = getJson(Constants.COUNTRY_CODE_API_PATH+ borders.get(j));
                            Country border = objectMapper.readValue(jsonBorder, new TypeReference<>() {});;
                            message += border.getName() ;
                            if (i != borders.size()-3){
                                message+= " ,";
                            }
                        }
                        message+=".\n";
                    }

                } else if (apiType.equals(Constants.JOKE_API)) {
                    Joke joke = objectMapper.readValue(json, new TypeReference<>() {});
                    message+= joke;
                } else if (apiType.equals(Constants.QUOTE_API)) {

                    List<Quote> quotes = objectMapper.readValue(json, new TypeReference<>() {});
                    message+=quotes.get(0).toString();
                } else if (apiType.equals(Constants.EXCHANGE_API)){

                    Exchange exchange = objectMapper.readValue(json, new TypeReference<>() {});
                    message+= exchange.toString();
                } else if (apiType.equals(Constants.NUM_FACT_API)) {
                    NumberFacts numberFact =objectMapper.readValue(json, new TypeReference<>() {});
                    message+= numberFact.toString();
                }
            }else {
                message +="misinformation :(";
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return message;
    }

    private String getJson (String path ){
        String json ="";
        try {
            GetRequest getRequest = Unirest.get(path);
            HttpResponse<String> response = getRequest.asString(); //איך לקבל את המידע כמחרוזת
            System.out.println("Code:");
            int code = response.getStatus();
            System.out.println(code);
            //200-OK מעולה
            //404 - NOT FOUND הבעיה אצלנו
            //500 - SERVER ISSUE אנחנו עשינו הכל טוב, הבעיה היא אצל הספק
            if (code == Constants.API_REQUEST_OK){
                json += response.getBody();
            }

        }catch (Exception e){

            e.printStackTrace();
        }
        return json;
    }

    public String mostActiveApi(){
        String result = "";
        int max = 0;
        for (int i =0; i<this.countRequest.length;i++){
            if (this.countRequest[i]>max){
                max = this.countRequest[i];
                result = this.apiTypes[i];
            }
        }
        return result;
    }

    public String getCountApi(){
        String result = "";
        for (int i =0; i<this.countRequest.length;i++){
            result+=this.countRequest[i];
            if (i<= this.countRequest.length-2){
                result+=",";
            }
        }
        return result;
    }


}
