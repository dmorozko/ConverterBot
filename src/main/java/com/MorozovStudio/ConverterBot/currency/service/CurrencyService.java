package com.MorozovStudio.ConverterBot.currency.service;

import com.MorozovStudio.ConverterBot.currency.model.CurrencyModel;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.Date;
import java.util.Map;

public class CurrencyService {
    private static CurrencyModel currencyModel;

    public static double calculate(double count, String codeIn, String codeOut) {
        checkActualRates();
        codeIn = codeIn.toUpperCase();
        codeOut = codeOut.toUpperCase();
        double result = -1.0;
        if ((currencyModel.getRate(codeIn) == null  || codeIn.equals("RUB")) &&
                (currencyModel.getRate(codeOut) == null  || codeOut.equals("RUB")) ||
                count < 0) {
            return result;
        } else {
            if (codeIn.equals("RUB")) {
                result = count * currencyModel.getRate(codeOut);
            } else if (codeOut.equals("RUB")) {
                result = count / currencyModel.getRate(codeIn);
            } else {
                result = (currencyModel.getRate(codeOut) / currencyModel.getRate(codeIn))*count;
            }
            return result;
        }
    }

    private static void checkActualRates() {
        if (currencyModel == null || !DateEquals.isEqualsWithoutTime(new Date(),
                currencyModel.getDate()) && !new Date().toString().split(" ")[0].equals("Sun")) {
            try {
                currencyModel = Connector.getObjectFromJson();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
