package my.telegrambot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public class BashBot extends TelegramLongPollingBot {
    Sender sender = new Sender(this);

    @Override
    public void onUpdateReceived(Update update) {
        String text = update.getMessage().getText();
        MessageType type = MessageType.getButton(text);
        long chatId = update.getMessage().getChatId();

        switch (type) {
            case QUOTE -> sender.sendRandomQuote(chatId);
            case IMAGE -> sender.sendStrip(chatId);
            case EMPTY -> sender.sendText(chatId, "Команда не распознана");
        }
    }

    @Override
    public String getBotUsername() {
        return System.getenv("telegrambotusername");
    }

    @Override
    public String getBotToken() {
        return System.getenv("telegrambottoken");
    }
}
