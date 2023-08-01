package com.MorozovStudio.ConverterBot.bot;

import com.MorozovStudio.ConverterBot.conf.BotConfig;
import com.MorozovStudio.ConverterBot.currency.storage.CurrencyInfo;
import com.MorozovStudio.ConverterBot.currency.storage.UsersCodes;
import com.MorozovStudio.ConverterBot.currency.service.CurrencyService;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Map;

@Component
@AllArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {
    private final BotConfig botConfig;

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        String currency = "";

        if(update.hasMessage() && update.getMessage().hasText()){
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            if (!hasCodeForChatId(chatId)) {
                UsersCodes.setCodeForChatId(chatId, "RUB");
            }
            String currentCode = UsersCodes.getCodeByChatId(chatId);

            switch (messageText){
                case "/start":
                    startCommandReceived(chatId, update.getMessage().getChat().getFirstName(), currentCode);
                    break;
                case "/codes":
                    sendMessage(chatId, getCurrencyInfo());
                    break;
                case "/help":
                    sendHelpText(chatId);
                    break;
                case "/change":
                    changeCurrentCode(chatId);
                    break;
                default:
                    parseMessageText(chatId, messageText, currentCode);
            }
        }

    }

    private void startCommandReceived(Long chatId, String name, String currentCode) {
        String answer = "Привет, " + name + ", рад встрече!" + "\n" +
                "На данный момент, итоговая валюта - " + CurrencyInfo.getCodeInfo(currentCode) + "\n" +
                "Введи код валюты, курс которой ты хочешь узнать." + "\n" +
                "Например: USD" + "\n\n" +
                "Введите /help, чтобы узнать список доступных команд.";
        sendMessage(chatId, answer);
    }

    private void sendHelpText(Long chatId) {
        String answer = "Введите /start,чтобы я снова вас поприветствовал." + "\n" +
                "Введите /codes,чтобы узнать коды валют." + "\n" +
                "Введите /help, чтобы узнать список доступных команд.";
        sendMessage(chatId, answer);
    }

    private void sendErrorText(Long chatId) {
        String answer = "Некорректный ввод или код валюты";
        sendMessage(chatId, answer);
    }

    private void parseMessageText(Long chatId, String messageText, String currentCode) {
        double count = 1;
        String code = messageText;

        if (messageText.split(" ").length > 1) {
            try {
                count = Double.parseDouble(messageText.split(" ")[0]);
                code = messageText.split(" ")[1];

                sendCalculateResult(chatId, count, code, currentCode);
            } catch (Exception e) {
                sendErrorText(chatId);
            }
        } else {
            sendCalculateResult(chatId, 1, messageText, currentCode);
        }
    }

    private void sendCalculateResult(long chatId,double count, String code, String currentCode) {
        double result = CurrencyService.calculate(count, code, currentCode);
        if (result == -1.0) {
            sendMessage(chatId, "Ошибка, некорректно введены данные!");
        } else {
            sendMessage(chatId, String.format("%.2f %s = %.2f %s", count, code.toUpperCase(), result, currentCode));
        }
    }

    private void changeCurrentCode(Long chatId) {
        String code;
        if (UsersCodes.getCodeByChatId(chatId) == "USD") {
            code = "RUB";
        } else {
            code = "USD";
        }
        UsersCodes.setCodeForChatId(chatId, code);
        String message = "На данный момент, итоговая валюта - " + code;
        sendMessage(chatId, message);
    }

    private boolean hasCodeForChatId(Long chatId) {
        if (UsersCodes.getCodeByChatId(chatId) != null) {
            return true;
        }
        return false;
    }

    private String getCurrencyInfo() {
        Map<String, String> info = CurrencyInfo.getCodesInfo();
        String result = "";

        for (Map.Entry<String, String> pair : info.entrySet())
        {
            result += pair.getKey() + " : " + pair.getValue() + "\n";
        }
        return result;
    }

    private void sendMessage(Long chatId, String textToSend){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(textToSend);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {

        }
    }
}
