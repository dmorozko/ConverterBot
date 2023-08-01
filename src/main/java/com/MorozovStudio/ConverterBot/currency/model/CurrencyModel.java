package com.MorozovStudio.ConverterBot.currency.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CurrencyModel {
    private Date date;
    private HashMap<String,Double> rates;

    public CurrencyModel() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(HashMap<String, Double> rates) {
        this.rates = rates;
    }

    public Double getRate(String code) {
        return rates.get(code);
    }
}
