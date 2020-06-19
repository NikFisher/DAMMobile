package com.example.dammobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class ViewAsset extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_asset_layout);

        Intent intent = getIntent();
        String text = intent.getStringExtra(MainActivity.EXTRA_TEXT);
        String imgURL = intent.getStringExtra(MainActivity.EXTRA_IMAGE);


        ImageLoader imageLoader = ImageLoader.getInstance();

        TextView name = (TextView) findViewById(R.id.assetName);
        ImageView imgView = (ImageView) findViewById(R.id.assetImg);

        imageLoader.displayImage(imgURL,imgView);
        name.setText(text);


    }

}
