package com.wowow.wstest;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

/**
 * Created by wowow on 28.10.15.
 */
public class ImageFactory {
    public ArrayList<String> imgUrl = new ArrayList<String>();
    String[] urls;

    ImageFactory(Context context){
        InputStream is = context.getResources().openRawResource(R.raw.ws);
        String s = "";
        try {
            s = IOUtils.toString(is);
            IOUtils.closeQuietly(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(!s.isEmpty()){

            s = StringUtils.substringBetween(s, "[", "]");
            urls = s.split(",");
            for(String url: urls) {
                if(url.length() > 5){
                    url = url.replaceAll("\\s","");
                   url = url.replace("\"", "");
                   url = url.replace(",", "");
                   url = url.replace(" ", "");
                    String df = "http://a1.dspncdn.com/media/206x/0c/03/cb/0c03cb25b692947a9bbea12a02086d17.jpg";
                    imgUrl.add(url);
                    Log.d("URL", url + url.length());
                    Log.d("URL", df + df.length());

                }
            }

        }

    }



}
