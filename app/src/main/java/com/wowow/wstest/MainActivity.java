package com.wowow.wstest;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
               // .imageDownloader(new BaseImageDownloader(this))
         .build();
        ImageLoader.getInstance().init(config);

        InputStream is = this.getResources().openRawResource(R.raw.ws);
        ArrayList<String> items = jsonArrayParser(inputStringToStringConverter(is));

        MyGridAdapter adapter = new MyGridAdapter(this, items);
        GridView gv = (GridView)findViewById (R.id.gridView);
        gv.setAdapter(adapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    String inputStringToStringConverter(InputStream is){
        String s = "";
        try {
            s = IOUtils.toString(is);
            IOUtils.closeQuietly(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    ArrayList<String> jsonArrayParser(String json) {
        ArrayList<String> imgUrl = new ArrayList<String>();
        String[] urls;
        if (!json.isEmpty()) {
            json = StringUtils.substringBetween(json, "[", "]");
            urls = json.split(",");
            for (String url : urls) {
                if (url.length() > 5) {
                    url = url.replaceAll("\\s", "");
                    url = url.replace("\"", "");
                    url = url.replace(",", "");
                    url = url.replace(" ", "");
                    imgUrl.add(url);
                }
            }
        }
        return imgUrl;
    }



}

