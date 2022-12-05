package br.ufrn.imd.Locadora.service;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

public class UtilsService {
    public static Date strToDate(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return formatter.parse(date);
        } catch (ParseException e) {
            return new Date(Long.MIN_VALUE);
        }
    }

    public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
    }

    public static float roundValue(float value){
        float roundOff = (float) (Math.round(value * 100.0) / 100.0);
        return roundOff;
    }
}
