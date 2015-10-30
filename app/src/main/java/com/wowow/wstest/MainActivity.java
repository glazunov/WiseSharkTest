package com.wowow.wstest;


import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    final String nameOfArrayInJson = "img";
    final int defaultSizeOfImages = 182;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
             .build();
        ImageLoader.getInstance().init(config);

        InputStream jsonStream = this.getResources().openRawResource(R.raw.json_in_resources);
        ArrayList<String> urls = jsonArrayParser(inputStringToStringConverter(jsonStream));

        GridView resultGridView = (GridView)findViewById (R.id.gridView);
        MyGridAdapter adapter = new MyGridAdapter(this, urls, defaultSizeOfImages);
        resultGridView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    String inputStringToStringConverter(InputStream jsonStream){
        String jsonString = "";
        try {
            jsonString = IOUtils.toString(jsonStream);
            IOUtils.closeQuietly(jsonStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    ArrayList<String> jsonArrayParser(String jsonString) {
        ArrayList<String> urls = new ArrayList<String>();
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(nameOfArrayInJson);
            for (int i = 0; i < jsonArray.length(); i++) {
                urls.add(jsonArray.get(i).toString());
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        return urls;
    }

}

