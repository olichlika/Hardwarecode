package com.lixin.hardwarecode.Tools;

import java.util.Random;

/**
 * Created by kingcrum on 2018/10/16.
 */

public class RandomDevice {

    public static String makeRandomIMEI(){
        String imei;

        StringBuilder str = new StringBuilder();//定义变长字符串
        Random random = new Random();
        for (int i = 0; i < 14; i++) {
            str.append(random.nextInt(10));
        }
        imei = str.toString();

        char[] imeiChar = imei.toCharArray();
        int resultInt=0;
        for (int i = 0; i < imeiChar.length; i++) {
            int a = Integer.parseInt(String.valueOf(imeiChar[i]));
            i++;
            final int temp = Integer.parseInt(String.valueOf(imeiChar[i]))*2;
            final int b = temp<10?temp:temp-9;
            resultInt += a + b;
        }
        resultInt %= 10;
        resultInt=resultInt==0?0:10-resultInt;

        imei = imei + Integer.toString(resultInt);

        return imei;
    }
}
