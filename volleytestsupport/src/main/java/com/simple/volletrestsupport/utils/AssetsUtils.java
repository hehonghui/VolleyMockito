package com.simple.volletrestsupport.utils;

import android.content.Context;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by mrsimple on 20/1/17.
 */
public class AssetsUtils {

    public static String parseString(Context context, String fileName) {
        String content = "";
        try {
            content = stream2String(context.getAssets().open(fileName)) ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }


    private static String stream2String(InputStream stream) {
        StringBuilder stringBuilder = new StringBuilder() ;
        BufferedReader reader = null ;
        try {
            reader = new BufferedReader(new InputStreamReader(stream));
            String line;
            while ( (line = reader.readLine() ) != null ) {
                stringBuilder.append(line) ;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ( reader != null ) {
                try {
                    reader.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return stringBuilder.toString();
    }
}
