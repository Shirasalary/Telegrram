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
        this.countRequest[Constants.COUNTRY_API_CODE]++;
        String json =getJson(Constants.COUNTRY_API_PATH + endPath);
        String result = getMessage(json , Constants.COUNTRY_API);

        return result;
    }

    public String getJokeByLanguage(String language){
        this.countRequest[Constants.JOKE_API_CODE]++;
        String json =getJson(Constants.JOKE_API_PATH + language);
        String result = getMessage(json , Constants.JOKE_API);

        return result;
    }

    public String getQuoteByTag(String tag){
        this.countRequest[Constants.QUOTE_API_CODE]++;
        String json =getJson(Constants.QUOTE_API_PATH_START + tag + Constants.QUOTE_API_PATH_END);
        String result = getMessage(json , Constants.QUOTE_API);

        return result;

    }
    public String getExchange(String coins){
        this.countRequest[Constants.EXCHANGE_API_CODE]++;
        String json =getJson(Constants.EXCHANGE_API_PATH + coins);
        String result = getMessage(json , Constants.EXCHANGE_API);

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
                        message+="borders: ";
                        List<String> borders = countries.get(i).getBorders();
                        for (int j = 0; j< borders.size();j++ ){
                            String jsonBorder = getJson(Constants.COUNTRY_CODE_API_PATH+ borders.get(j));
                            Country border = objectMapper.readValue(jsonBorder, new TypeReference<>() {});;
                            message += border.getName() ;
                            if (i != borders.size()-2){
                                message+= ", ";
                            }
                        }
                        message+="\n";
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


//    private List<Country> startApi(String path , int typeResult ){
//        AtomicReference<List<Country>> result = new AtomicReference<>();
//        AtomicBoolean isDone = new AtomicBoolean(false);
//        new Thread(() -> {
//
//            try {
//                GetRequest getRequest = Unirest.get(path);
//                HttpResponse<String> response = getRequest.asString(); //איך לקבל את המידע כמחרוזת
//                System.out.println("Code:");
//                System.out.println(response.getStatus());
//                //200-OK מעולה
//                //404 - NOT FOUND הבעיה אצלנו
//                //500 - SERVER ISSUE אנחנו עשינו הכל טוב, הבעיה היא אצל הספק
//                String json = response.getBody();
//                ObjectMapper objectMapper = new ObjectMapper();//הופך את המחרוזת לאובייקט
//
//                result.set(objectMapper.readValue(json, new TypeReference<>() {}));
//                isDone.set(true);
//
//            }catch (Exception e){
//
//                e.printStackTrace();
//            }
//        }).start();
//
//        while (!isDone.get()){
//            System.out.print("");
//        }
//        return result.get();
//    }


    //TODO לשים בטרד
//    private <T> List<T> startApi (String path , int typeResult , T ob ){
//
//        List<T> result = List.of(ob);
//        try {
//            GetRequest getRequest = Unirest.get(path);
//            HttpResponse<String> response = getRequest.asString(); //איך לקבל את המידע כמחרוזת
//            System.out.println("Code:");
//            System.out.println(response.getStatus());
//            //200-OK מעולה
//            //404 - NOT FOUND הבעיה אצלנו
//            //500 - SERVER ISSUE אנחנו עשינו הכל טוב, הבעיה היא אצל הספק
//            String json = response.getBody();
//            ObjectMapper objectMapper = new ObjectMapper();//הופך את המחרוזת לאובייקט
//
//            if (typeResult == Constants.LIST_TYPE){
//                result =  objectMapper.readValue(json, new TypeReference<>() {});
//            } else if (typeResult == Constants.SINGLE_TYPE) {
//                T apiSingleResult = objectMapper.readValue(json, new TypeReference<>() {});
//                result.add(apiSingleResult);
//            }
//        }catch (Exception e){
//
//            e.printStackTrace();
//        }
//       return result;
//    }
}
