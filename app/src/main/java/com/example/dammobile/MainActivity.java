package com.example.dammobile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.inputmethodservice.Keyboard;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    public static final String EXTRA_TEXT = ".com.example.dammobile.EXTRA_TEXT";
    public static final String EXTRA_IMAGE = ".com.example.dammobile.EXTRA_IMAGE";
    public static final String EXTRA_ASSET = ".com.example.dammobile.EXTRA_ASSET";

    public static final int PICK_IMAGE = 1;
    ImageView img;
    Uri imageUri;
    ListView assetList;
    ArrayList <Asset> assetsArrayList;
    AssetListAdapter adapter;
    int position = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assetList = (ListView) findViewById(R.id.assetList);
        assetsArrayList = new ArrayList<Asset>();

        adapter = new AssetListAdapter(this,R.layout.activity_view_layout,assetsArrayList);
        assetList.setAdapter(adapter);


        Button uploadBtn = (Button) findViewById(R.id.uploadBtn);
        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent gallery = new Intent();
              gallery.setType("image/*");
              gallery.setAction(Intent.ACTION_GET_CONTENT);

              startActivityForResult(Intent.createChooser(gallery,"Select Picture"), PICK_IMAGE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==PICK_IMAGE && resultCode == RESULT_OK){
            imageUri = data.getData();
            try{
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),imageUri);
                Asset newAsset = new Asset(imageUri.getLastPathSegment(), imageUri.toString());
                assetsArrayList.add(newAsset);
                adapter.notifyDataSetChanged();

            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    public void optionsBtnClicked(View v){
        PopupMenu popup = new PopupMenu(this,v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.popup_menu);
        popup.show();
        position = (Integer)v.getTag();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch(item.getItemId()){
            case R.id.view:
                //Toast.makeText(this, "View clicked", Toast.LENGTH_SHORT).show();
                openViewAsset();
                return true;
            case R.id.tag:
               // Toast.makeText(this, "Tag clicked", Toast.LENGTH_SHORT).show();
                openTagAsset();
                return true;
            default:
                return false;
        }
    }

    public void openViewAsset(){

        Asset asset = assetsArrayList.get(position);

        Intent intent = new Intent(this, ViewAsset.class);
        intent.putExtra(EXTRA_TEXT, asset.getName());
        intent.putExtra(EXTRA_IMAGE, asset.getImageURL());

        startActivity(intent);
    }

    public void openTagAsset(){
        Asset asset = assetsArrayList.get(position);
        Intent intent = new Intent(this, TagAsset.class);
        intent.putExtra(EXTRA_ASSET, asset);

        startActivity(intent);
    }

}
