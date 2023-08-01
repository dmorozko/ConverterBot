package com.MorozovStudio.ConverterBot.currency.storage;

import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;

public class CurrencyInfo {
    private final static Map<String, String> map = new HashMap<>();
    static {
        map.put("AUD", "Австралийский доллар"); map.put("AZN", "Азербайджанский манат");
        map.put("GBP", "Фунт стерлингов"); map.put("AMD", "Армянский драм");
        map.put("BYN", "Беларусский рубль"); map.put("BGN", "Болгарскйи лев");
        map.put("BRL", "Бразильский реал"); map.put("HUF", "Венгерский форинт");
        map.put("VND", "Вьетнамский донг"); map.put("HKD", "Гонконгский доллар");
        map.put("GEL", "Грузинский лари"); map.put("DKK", "Датская крона");
        map.put("AED", "Дирхам ОАЭ"); map.put("USD", "Доллар США");
        map.put("EUR", "Евро"); map.put("EGP", "Египетский фунт");
        map.put("INR", "Индийская рупия"); map.put("IDR", "Индонезийская рупия");
        map.put("KZT", "Казахстанский тенге"); map.put("CAD", "Канадский доллар");
        map.put("QAR", "Катарский реал"); map.put("KGS", "Киргизский сом");
        map.put("CNY", "Китайский юань"); map.put("MDL", "Молдавский лей");
        map.put("NZD", "Новозелландский доллар"); map.put("NOK", "Норвежская крона");
        map.put("PLN", "Польский златый"); map.put("RON", "Румынский лей");
        map.put("XDR", "СДР"); map.put("SGD", "Сингапурский доллар");
        map.put("TJS", "Таджитский сомони"); map.put("THB", "Тайсикй бат");
        map.put("TRY", "Турецкая лира"); map.put("TMT", "Туркменский манат");
        map.put("UZS", "Узбекский сум"); map.put("UAH", "Украинская гривна");
        map.put("CZK", "Чешская крона"); map.put("SEK", "Шведская крона");
        map.put("CHF", "Швейцарский франк"); map.put("RSD", "Сербский доллар");
        map.put("ZAR", "Южноамериканский рэн"); map.put("KRW", "Южнокорейская вона");
        map.put("JPY", "Японская йена"); map.put("RUB", "Российский рубль");
    }

    public static String getCodeInfo(String code) {
        return map.get(code);
    }
    public static Map<String, String> getCodesInfo() { return map;}
}
