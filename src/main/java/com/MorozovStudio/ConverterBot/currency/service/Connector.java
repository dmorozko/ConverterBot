package com.MorozovStudio.ConverterBot.currency.service;

import com.MorozovStudio.ConverterBot.currency.model.CurrencyModel;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class Connector {
    private static String getData(URL url) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        StringBuilder data = new StringBuilder();
        while (reader.ready()) {
            data.append(reader.readLine());
        }
        reader.close();

        return data.toString();
    }

    public static CurrencyModel getObjectFromJson() throws IOException {
        URL url = new URL("https://www.cbr-xml-daily.ru/latest.js");
        String json = getData(url);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        CurrencyModel currency = null;

        try {
            currency = mapper.readValue(json.toString(), CurrencyModel.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return currency;
    }
}
