package com.example.me.vsap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.MenuInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;


public class HomeActivity extends AppCompatActivity {

    String[] vsapMenu = {"TRY CART","YOUR ACCOUNT","GET STARTED"};
    Animation fade_in, fade_out;
    ViewFlipper viewFlipper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ImageView imageView= (ImageView) findViewById(R.id.vsap_scan);

        ListView listView = (ListView) findViewById(R.id.vsap_menu);
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.list_item,vsapMenu);


        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int itemPosition = position;

                switch (itemPosition) {

                    case 0:
                        startActivity(new Intent(HomeActivity.this, Try_Cart.class));
                        break;

                    case 1:
                        startActivity(new Intent(HomeActivity.this, SignupActivity.class));
                        break;

                    case 2:
                        startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                        break;
                }

            }
        });
        final ImageView contactUs = (ImageView) findViewById(R.id.contact_us);
        contactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(HomeActivity.this, contactUs);
                MenuInflater inflater = popupMenu.getMenuInflater();
                inflater.inflate(R.menu.activity_main_drawer, popupMenu.getMenu());
                popupMenu.show();
            }
        });

        viewFlipper = (ViewFlipper) this.findViewById(R.id.bckgrndViewFlipper1);
        fade_in = AnimationUtils.loadAnimation(this,android.R.anim.fade_in);
        fade_out = AnimationUtils.loadAnimation(this,android.R.anim.fade_out);
        viewFlipper.setInAnimation(fade_in);
        viewFlipper.setOutAnimation(fade_out);
        //sets auto flipping
        viewFlipper.setAutoStart(true);
        viewFlipper.setFlipInterval(3000);
        viewFlipper.startFlipping();
    }
}