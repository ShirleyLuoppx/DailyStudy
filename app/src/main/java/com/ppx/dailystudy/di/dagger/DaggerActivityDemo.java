package com.ppx.dailystudy.di.dagger;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ppx.dailystudy.R;

import javax.inject.Inject;

public class DaggerActivityDemo extends AppCompatActivity {

    @Inject
    Chef chef;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        findViewById(R.id.ben_show_toast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DaggerActivityDemo.this, chef.cook(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
