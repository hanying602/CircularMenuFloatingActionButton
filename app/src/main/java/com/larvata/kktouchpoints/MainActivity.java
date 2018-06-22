package com.larvata.kktouchpoints;

import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.larvata.library.CircularMenuFloatingActionButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CircularMenuFloatingActionButton mFab = findViewById(R.id.main_activity_mfab);

        FloatingActionButton fab1 = new FloatingActionButton(MainActivity.this);
        fab1.setImageResource(android.R.drawable.ic_dialog_dialer);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"fab1 click",Toast.LENGTH_SHORT).show();
            }
        });

        FloatingActionButton fab2 = new FloatingActionButton(MainActivity.this);
        fab2.setImageResource(android.R.drawable.ic_dialog_alert);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"fab2 click",Toast.LENGTH_SHORT).show();
            }
        });

        FloatingActionButton fab3 = new FloatingActionButton(MainActivity.this);
        fab3.setImageResource(android.R.drawable.ic_dialog_email);
        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"fab3 click",Toast.LENGTH_SHORT).show();
            }
        });

        FloatingActionButton fab4 = new FloatingActionButton(MainActivity.this);
        fab4.setImageResource(android.R.drawable.ic_dialog_info);
        fab4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"fab4 click",Toast.LENGTH_SHORT).show();
            }
        });

        FloatingActionButton fab5 = new FloatingActionButton(MainActivity.this);
        fab5.setImageResource(android.R.drawable.ic_dialog_map);
        fab5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"fab5 click",Toast.LENGTH_SHORT).show();
            }
        });



        ConstraintLayout mainCLayout = findViewById(R.id.main_activity_layout);
        mainCLayout.addView(fab1);
        mainCLayout.addView(fab2);
        mainCLayout.addView(fab3);
        mainCLayout.addView(fab4);
        mainCLayout.addView(fab5);

        mFab.setParentView(mainCLayout);
        mFab.setMenuButtons(fab1,fab2,fab3,fab4,fab5);
        mFab.setCustomSize(200);
        mFab.setMenuButtonsSize(100);
        mFab.setBtnDistance(500);

    }
}
