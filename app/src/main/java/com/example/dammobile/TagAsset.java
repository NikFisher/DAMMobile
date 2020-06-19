package com.example.dammobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Switch;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

public class TagAsset extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{

    Asset asset;
    TextView assetType;
    ArrayList<String> productArray;
    ArrayAdapter adapter;
    ListView productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tag_asset_layout);

        Intent intent = getIntent();
        this.asset = (Asset) intent.getSerializableExtra(MainActivity.EXTRA_ASSET);

        ImageLoader imageLoader = ImageLoader.getInstance();
        ImageView img = (ImageView) findViewById(R.id.imageView2);
        TextView assetName = (TextView) findViewById(R.id.tagAssetName);
        assetType = (TextView) findViewById(R.id.assetTypeTxt);
        assetType.setText(asset.getType());
        assetName.setText(asset.getName());
        imageLoader.displayImage(asset.getImageURL(), img);

        productList = (ListView) findViewById(R.id.productList);
        productList.setOnTouchListener(new View.OnTouchListener() {
            // Setting on Touch Listener for handling the touch inside ScrollView
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Disallow the touch request for parent scroll on touch of child view
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        productList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                SparseBooleanArray positionChecker = productList.getCheckedItemPositions();
                int count = productList.getCount();
                for(int item = count-1; item>=0; item-- ){
                    if (positionChecker.get(item)){
                        adapter.remove(productArray.get(item));
                    }
                }

                positionChecker.clear();
                adapter.notifyDataSetChanged();
                return false;
            }
        });
        productArray = new ArrayList<String>();

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, productArray);
        productList.setAdapter(adapter);
    }


    public void typeBtnClicked(View v){
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.asset_type_menu);
        popup.show();
    }

    public void productBtnClicked(View v){
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.asset_product_menu);
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item){
        switch(item.getItemId()){
            case R.id.product_image:
                asset.setType("Product Image");
                assetType.setText(asset.getType());
                return true;
            case R.id.packaging:
                asset.setType("Packaging");
                assetType.setText(asset.getType());
                return true;
            case R.id.demonstration:
                asset.setType("Demonstration");
                assetType.setText(asset.getType());
                return true;
            case R.id.illustration:
                asset.setType("Illustration");
                assetType.setText(asset.getType());
                return true;
            case R.id.hammer:
                if(!productArray.contains("Hammer")){
                    productArray.add("Hammer");
                    adapter.notifyDataSetChanged();
                }
                return true;
            case R.id.screwdriver:
                if(!productArray.contains("Screwdriver")) {
                    productArray.add("Screwdriver");
                    adapter.notifyDataSetChanged();
                }
                return true;
            case R.id.saw:
                if(!productArray.contains("Saw")) {
                    productArray.add("Saw");
                    adapter.notifyDataSetChanged();
                }
                return true;
            case R.id.blowtorch:
                if(!productArray.contains("Blowtorch")) {
                    productArray.add("Blowtorch");
                    adapter.notifyDataSetChanged();
                }
                return true;
            case R.id.wrench:
                if(!productArray.contains("Wrench")) {
                    productArray.add("Wrench");
                    adapter.notifyDataSetChanged();
                }
                return true;
            case R.id.drill:
                if(!productArray.contains("Drill")){
                    productArray.add("Drill");
                    adapter.notifyDataSetChanged();
                }
                return true;
            case R.id.pliers:
                if(!productArray.contains("Pliers")){
                    productArray.add("Pliers");
                    adapter.notifyDataSetChanged();
                }
                return true;
            default:
                return false;
        }
    }
}


