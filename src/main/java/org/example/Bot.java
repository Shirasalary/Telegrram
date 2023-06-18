package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
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
        long chatId = getChatID(update);
        User user = isUserExist(chatId);
        //אם היוזר קיים נוסיף לו בקשה
        if ( user!= null){
            user.addRequest();
            //  יצירת כפתור במקלדת אפשריות, צריך לשנות את השמות רק שמתי את הקוד שתדעי איך עושים
            InlineKeyboardButton sundayButton = new InlineKeyboardButton("sunday");
            sundayButton.setCallbackData("1");
            InlineKeyboardButton mondayButton = new InlineKeyboardButton("monday");
            mondayButton.setCallbackData("2");
            InlineKeyboardButton tuesdayButton = new InlineKeyboardButton("tuesday");
            tuesdayButton.setCallbackData("3");



        }else { //אם היוזר לא קיים נוסיף אותו לרשימה
            user = new User(chatId);
            this.users.add(user);
        }
        System.out.println(update.getMessage().getText());//מדפיס את תוכן ההודעה הנשלחת
        System.out.println("List size" + this.users.size());
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
        if (update.getMessage()!=null){
            result=update.getMessage().getChatId();
        }else {
            result=update.getCallbackQuery().getMessage().getChatId();
        }
        return result;
    }
}
