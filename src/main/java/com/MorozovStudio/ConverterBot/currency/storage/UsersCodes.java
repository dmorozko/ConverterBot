package com.MorozovStudio.ConverterBot.currency.storage;

import java.util.HashMap;
import java.util.Map;

public class UsersCodes {
    private static Map<Long, String> usersCodes = new HashMap<>();

    public static String getCodeByChatId(Long chatId) {
        return usersCodes.get(chatId);
    }

    public static void setCodeForChatId(Long chatId, String code) {
        usersCodes.put(chatId, code);
    }
}
