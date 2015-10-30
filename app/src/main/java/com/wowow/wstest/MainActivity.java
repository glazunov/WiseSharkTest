package com.wowow.wstest;

import android.app.ActionBar;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
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


        ImageFactory imageFactory = new ImageFactory(this);
        ArrayList<String> items = imageFactory.imgUrl;
        MyGridAdapter adapter = new MyGridAdapter(this, items);
        GridView gv = (GridView)findViewById (R.id.gridView);
        gv.setAdapter(adapter);

/*
        ImageFactory imageFactory = new ImageFactory(this);

        TextView tv = (TextView)findViewById (R.id.textView);
        tv.setText(imageFactory.urls[0] + " dsfdsf");

        ImageView imageView = new ImageView(this);
        ImageView iv = (ImageView)findViewById(R.id.imageView);

       // iv.setImageBitmap(getBitmapFromUrl("http://a1.dspncdn.com/media/206x/0c/03/cb/0c03cb25b692947a9bbea12a02086d17.jpg"));
        Picasso.with(this).load("http://a1.dspncdn.com/media/206x/0c/03/cb/0c03cb25b692947a9bbea12a02086d17.jpg").into(imageView);
        Picasso.with(this).load("http://a1.dspncdn.com/media/206x/0c/03/cb/0c03cb25b692947a9bbea12a02086d17.jpg").into(iv);

//        imageView.setImageBitmap(getBitmapFromUrl(imageFactory.urls[0]));
//http://a1.dspncdn.com/media/206x/0c/03/cb/0c03cb25b692947a9bbea12a02086d17.jpg

*/
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




}

