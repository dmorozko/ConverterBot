package com.MorozovStudio.ConverterBot.currency.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateEquals {
    public static boolean isEqualsWithoutTime(Date date1, Date date2) {
        DateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
        String str_date1 = formatter.format(date1);
        String str_date2 = formatter.format(date2);
        return str_date1.equals(str_date2);
    }
}
