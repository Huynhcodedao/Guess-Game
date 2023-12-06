package com.example.applicationgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.LayoutInflater;

import com.example.applicationgame.databinding.ActivityMainActivityBinding;
import com.example.applicationgame.databinding.ActivityMainBinding;
import android.view.View;

public class MainActivityA extends AppCompatActivity {
private ActivityMainActivityBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainActivityBinding.inflate(getLayoutInflater());
        View viewview = binding.getRoot();
        setContentView(viewview);
        binding.btnStart.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivityA.this, MainActivity.class );
            startActivity(intent);
        });

    }
}