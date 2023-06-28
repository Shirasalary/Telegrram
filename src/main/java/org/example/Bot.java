package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.*;

public class Bot extends TelegramLongPollingBot {

    private List<User> users;
    private List<String> typeApi;

    private Api apiProvider;

    private Queue<String> lastTenRequests;

    public Bot(List<String> typeApi){
        this.users = new ArrayList<>();
        this.typeApi = typeApi;
        this.apiProvider = new Api();
        this.lastTenRequests = new LinkedList<>();
    }

    public void setTypeApi(List<String> typeApi) {
        this.typeApi = typeApi;
    }

    @Override
    public String getBotUsername() {
        return "shira222Bot";
    }

    @Override
    public String getBotToken() {
        return "6206852627:AAHBpFSbpgDyX2H8K9ZiQFFh62TnJArCyqY";
    }

    //מה קורה בעת יצירת קשר עם הבוט זאת הפונקציה העיקרית
    @Override
    public void onUpdateReceived(Update update) {

        if (!this.typeApi.isEmpty()){
            //מדפיס את תוכן ההודעה הנשלחת
            System.out.println("List size" + this.users.size());

            long chatId = getChatID(update);
            User user = isUserExist(chatId);
            SendMessage message = new SendMessage();
            message.setChatId(chatId);

            if ( user == null){ //אם היוזר לא קיים נוסיף אותו לרשימה
                user = new User(chatId);
                this.users.add(user);
            }

            if (user.getStatusRequest() == Constants.START_REQUEST){

                message.setReplyMarkup(getKeyboard(this.typeApi,this.typeApi));
                message.setText("which service you choose?");
                user.setStatusRequest(Constants.REQUEST_IN_PROCESS);

                user.setDate();
                user.addRequest();

            }else if (user.getStatusRequest() == Constants.REQUEST_IN_PROCESS) {

                if (update.getCallbackQuery().getData().equals(Constants.COUNTRY_API)){
                    message.setText("Please enter country name");
                    user.setApiTypeRequest(Constants.COUNTRY_API);

                } else if (update.getCallbackQuery().getData().equals(Constants.JOKE_API)) {
                    message.setReplyMarkup(getKeyboard(Utils.convertArrayToList(Constants.LANGUAGE),Utils.convertArrayToList(Constants.LANGUAGE_CODE)));
                    message.setText("Please choose language for joke");
                    user.setApiTypeRequest(Constants.JOKE_API);

                } else if (update.getCallbackQuery().getData().equals(Constants.QUOTE_API)) {
                    message.setReplyMarkup(getKeyboard(Utils.convertArrayToList(Constants.QUOTE_TAG),Utils.convertArrayToList(Constants.QUOTE_TAG_CODE)));
                    message.setText("Please choose tag for quote");

                    user.setApiTypeRequest(Constants.QUOTE_API);
                } else if (update.getCallbackQuery().getData().equals(Constants.EXCHANGE_API)) {
                    message.setReplyMarkup(getKeyboard(Utils.convertArrayToList(Constants.EXCHANGE),Utils.convertArrayToList(Constants.EXCHANGE_CODE)));
                    message.setText("Please choose a currency exchange");

                    user.setApiTypeRequest(Constants.EXCHANGE_API);
                } else if (update.getCallbackQuery().getData().equals(Constants.NUM_FACT_API)) {

                    user.setApiTypeRequest(Constants.NUM_FACT_API);
                    getApiAndSetMessage(user,message,update);
                }

                if (update.getCallbackQuery().getData().equals(Constants.NUM_FACT_API)){
                    user.setStatusRequest(Constants.START_REQUEST);
                }else {
                    user.setStatusRequest(Constants.WAIT_TO_RESPOND);
                }
                addToLastRequests(user.summaryOfRequest());

            } else if (user.getStatusRequest() == Constants.WAIT_TO_RESPOND) {

                getApiAndSetMessage(user,message,update);

            }


            try {
                System.out.println("send");
                execute(message);

            } catch (TelegramApiException e) {
                System.out.println("no send");
                throw new RuntimeException(e);

            }
        }

    }

    private InlineKeyboardMarkup getKeyboard(List<String> options ,List<String> optionsCode ){
        List<InlineKeyboardButton> buttons = new ArrayList<>();
        for (int i =0; i<options.size(); i++){
            InlineKeyboardButton newButton = new InlineKeyboardButton(options.get(i));
            newButton.setCallbackData(optionsCode.get(i));
            buttons.add(newButton);
        }
        List<List<InlineKeyboardButton>> keyboard = Arrays.asList(buttons);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(keyboard);
        return inlineKeyboardMarkup;
    }

    private void getApiAndSetMessage(User user, SendMessage message , Update update){
        String response = "";
        if (user.isEqualApiTypeRequest(Constants.COUNTRY_API)){

            response = this.apiProvider.getCountriesByeName(update.getMessage().getText());

        }else if (user.isEqualApiTypeRequest(Constants.JOKE_API)){

            response = this.apiProvider.getJokeByLanguage(update.getCallbackQuery().getData());

        } else if (user.isEqualApiTypeRequest(Constants.QUOTE_API)) {

            response = this.apiProvider.getQuoteByTag(update.getCallbackQuery().getData());
        }else if (user.isEqualApiTypeRequest(Constants.EXCHANGE_API)){
            //להפעיל API ןלשלוח לו את התשובה + לשנות את הסטטוס בקשה לסוף + לאפס את סוג הבקשה לריק
            response = this.apiProvider.getExchange(update.getCallbackQuery().getData());
        }else if (user.isEqualApiTypeRequest(Constants.NUM_FACT_API)){

            response = this.apiProvider.getNumFact();
        }

        response+= "\n Thank you for using us! Send another message for a request :)";
        user.setStatusRequest(Constants.START_REQUEST);
        user.setApiTypeRequest("");
        message.setText(response);
    }

    private User isUserExist(long chatId){
        User result = null;
        for (User user:this.users){
            if (user.isEqualChatId(chatId)){
                result = user;
                break;
            }
        }
        return result;
    }
    private long getChatID(Update update){
        long result=0;
        if (!update.hasCallbackQuery()){
            result=update.getMessage().getChatId();
        }else {
            result=update.getCallbackQuery().getMessage().getChatId();
        }
        return result;
    }

    public int countBotRequests(){
        int count = 0;
        for (int i =0 ;i<this.users.size();i++){
            count+= this.users.get(i).getCountRequest();
        }
        return count;
    }

    public int countUsers(){
        return this.users.size();
    }

    public String mostActiveUser(){
        String result ="";
        int max = 0;
        User user = null;
        for (int i =0 ;i<this.users.size();i++){
           if (this.users.get(i).getCountRequest() > max){
               max = this.users.get(i).getCountRequest();
               user = this.users.get(i);
           }
        }

        if (user == null){
            result +="NOT have active user";
        }else {
            result = user.toString();
        }
        return result;
    }

    public String mostActiveApiByUsers(){
        return this.apiProvider.mostActiveApi();
    }

    public String getCountApiByTape(){
        return this.apiProvider.getCountApi();
    }
    private void addToLastRequests(String last){

        if (this.lastTenRequests.size() == Constants.MAX_LAST){
            this.lastTenRequests.remove();
        }
        this.lastTenRequests.add(last);
    }

    public String getTenLastRequests(){
        String result = "";
        for (int i =0;i<this.lastTenRequests.size();i++){
            String request = this.lastTenRequests.remove();
            result+= request + "\n";
            this.lastTenRequests.add(request);
        }
        return result;
    }

}
