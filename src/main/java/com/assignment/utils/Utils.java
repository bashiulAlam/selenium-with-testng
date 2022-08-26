package com.assignment.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Utils {
    public static String getCustomizeDate(int day) {
        String futureDate = getFutureDate("yyyy-MM-dd", day);
        return futureDate;
    }

    public static String getFutureDate(String format, int days) {
        String result = null;
        try {
            String currentDate = getCurrentDate(format);
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(sdf.parse(currentDate));
            calendar.add(Calendar.DATE, days);
            result = sdf.format(calendar.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String getCurrentDate(String format) {
        String result = null;
        try {
            java.text.DateFormat dateFormat = new SimpleDateFormat(format);
            result = dateFormat.format(new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String generateEmail() {
        long time = System.currentTimeMillis();
        String str = String.valueOf(time);
        //String subStr = str.substring(0, 6);
        return "it" + str + "@gmail.com";
    }

    public static String generateUniqueMobileNumber() {
        long nanoTime = System.nanoTime();
        return "015" + String.valueOf(nanoTime).substring(7);
    }

    public static String generateNidNumber(int length) {
        long nanoTime = System.nanoTime();
        return String.valueOf(nanoTime).substring(0, length);
    }

    public static int getMatchedAccountNumberIndex(List<String> accountNumbers, String accountNo) {
        for (int i = 0; i < accountNumbers.size(); i++) {
            if (accountNumbers.get(i).equals(accountNo)) {
                System.out.println("Account no matched at index : " + i);
                return i + 1;
            }
        }
        return -1;
    }
}
