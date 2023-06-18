package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.*;

//מחלקה שבנינו בתרגול המחלקה של העבודה היא Bot
//המחלקה של הבוט עצמו והפעיליות שלו
public class ShiraBot extends TelegramLongPollingBot {

    private Map<Long,Integer> chats;

    private List<Long> chatsId;

    public ShiraBot(){

        this.chats = new HashMap<>();
        this.chatsId = new ArrayList<>();
    }

    @Override
    public String getBotUsername() {
        return "shira222Bot";
    }

    @Override
    public String getBotToken() {
        return "6206852627:AAHBpFSbpgDyX2H8K9ZiQFFh62TnJArCyqY";
    }

    @Override
    public void onUpdateReceived(Update update) {

        long chatId = getChatID(update);

        SendMessage message = new SendMessage();


        if (!this.chatsId.contains(chatId))
        {
            this.chatsId.add(chatId);
            message.setChatId(chatId);
            //יצירת כפתור במקלדת אפשריות
            InlineKeyboardButton sundayButton = new InlineKeyboardButton("sunday");
            sundayButton.setCallbackData("1");
            InlineKeyboardButton mondayButton = new InlineKeyboardButton("monday");
            mondayButton.setCallbackData("2");
            InlineKeyboardButton tuesdayButton = new InlineKeyboardButton("tuesday");
            tuesdayButton.setCallbackData("3");
            List<InlineKeyboardButton> topRow = Arrays.asList(sundayButton, mondayButton,tuesdayButton);
            List<List<InlineKeyboardButton>> keyboard = Arrays.asList(topRow);
            //יצירת המקלדת עם כל אפשריות
            InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
            inlineKeyboardMarkup.setKeyboard(keyboard);
            message.setReplyMarkup(inlineKeyboardMarkup);
            message.setText("which day you choose?");
        }else {
            message.setChatId(chatId);
           if ( update.getCallbackQuery().getMessage().getText().equals("1"))
           {
               message.setText("the meeting on sunday");
           }
        }

        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }


//        if (this.times ==1)
//        {
//            message.setText("Hi, when remind you?");
//        }else {
//            int second = Integer.parseInt(update.getMessage().getText());
//            try {
//                Thread.sleep(second*1000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            message.setText("remind you");
//        }


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