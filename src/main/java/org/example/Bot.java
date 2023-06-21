package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bot extends TelegramLongPollingBot {

    private List<User> users;
    private List<String> typeApi;

    public Bot(List<String> typeApi){
        this.users = new ArrayList<>();
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

            user.addRequest();

        }else if (user.getStatusRequest() == Constants.REQUEST_IN_PROCESS) {

            if (update.getCallbackQuery().getData().equals(Constants.COUNTRY_API)){
                message.setText("Please enter country name");
                user.setStatusRequest(Constants.WAIT_TO_RESPOND);
                user.setApiTypeRequest(Constants.COUNTRY_API);

            } else if (update.getCallbackQuery().getData().equals(Constants.JOKE_API)) {
                message.setReplyMarkup(getKeyboard(Utils.convertArrayToList(Constants.LANGUAGE),Utils.convertArrayToList(Constants.LANGUAGE_CODE)));
                message.setText("Please choose language for joke");
                user.setStatusRequest(Constants.WAIT_TO_RESPOND);
                user.setApiTypeRequest(Constants.JOKE_API);

            } else if (update.getCallbackQuery().getData().equals(Constants.QUOTE_API)) {
                message.setReplyMarkup(getKeyboard(Utils.convertArrayToList(Constants.QUOTE_TAG),Utils.convertArrayToList(Constants.QUOTE_TAG_CODE)));
                message.setText("Please choose tag for quote");
                user.setStatusRequest(Constants.WAIT_TO_RESPOND);
                user.setApiTypeRequest(Constants.QUOTE_API);
            }

        } else if (user.getStatusRequest() == Constants.WAIT_TO_RESPOND) {

            if (user.isEqualApiTypeRequest(Constants.COUNTRY_API)){
                //להפעיל API ןלשלוח לו את התשובה + לשנות את הסטטוס בקשה לסוף + לאפס את סוג הבקשה לריק
            }else if (user.isEqualApiTypeRequest(Constants.JOKE_API)){
                //להפעיל API ןלשלוח לו את התשובה + לשנות את הסטטוס בקשה לסוף + לאפס את סוג הבקשה לריק
            } else if (user.isEqualApiTypeRequest(Constants.QUOTE_API)) {
                //להפעיל API ןלשלוח לו את התשובה + לשנות את הסטטוס בקשה לסוף + לאפס את סוג הבקשה לריק
            }
        } else if (user.getStatusRequest() == Constants.END_REQUEST) {
            message.setText("Tank you for using as! send message for another request :)");
            user.setStatusRequest(Constants.START_REQUEST);
        }


        try {
            System.out.println("send");
            execute(message);

        } catch (TelegramApiException e) {
            System.out.println("no send");
            throw new RuntimeException(e);

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


}
