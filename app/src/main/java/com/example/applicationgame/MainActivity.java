package com.example.applicationgame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.applicationgame.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class MainActivity extends AppCompatActivity {

    public String[] wordArray = {"PASSWORD","ONANA","RONALDO", "MESSI"};
    private String wordSolution;
   private void chooseRandomWord() {
        Random random = new Random();
        int randomIndex = random.nextInt(wordArray.length);
        wordSolution = wordArray[randomIndex];
    }

    public static final String wordSolution_story = "wordSolution_story_VALUE";
    public static final String remainCount_story = "remainCount_story_VALUE";
    public static final String guesses_story = "guesses_story_VALUE";
    public static final String notice_story = "notice_story_VALUE";
    public static final String count_story = "count_story_VALUE";

    private int remainCount = 4;
    private ActivityMainBinding binding;

    private String guesses="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View viewview = binding.getRoot();
        setContentView(viewview);
        binding.btnReplay.setVisibility(View.GONE);
        binding.btnReplay.setOnClickListener(view->{
            Intent intent = new Intent(MainActivity.this, MainActivityA.class);
            startActivity(intent);
        });
        if (savedInstanceState != null) {
            wordSolution = savedInstanceState.getString("wordSolution_story");
            remainCount = savedInstanceState.getInt(remainCount_story);
            guesses = savedInstanceState.getString("guesses_story");
            binding.notice.setText(savedInstanceState.getString(notice_story));
            binding.count.setText(savedInstanceState.getString(count_story));
            updateDisplay();
        } else {
            chooseRandomWord();
        }
        updateDisplay();

        binding.btnCheck.setOnClickListener(view -> {
            checkGuess();

        });
        binding.btnAgree.setOnClickListener(view -> {
            checkAswer();
            binding.btnReplay.setVisibility(View.VISIBLE);
        });
    }
    private void updateDisplay(){
        StringBuilder display = new StringBuilder();
        for (int i =0; i<wordSolution.length(); i++){
            char w = wordSolution.charAt(i);
            if(guesses.contains(String.valueOf(w))) {
                display.append(w);
            }else{
                display.append("_");
            }
                display.append(" ");
        }
       binding.solution.setText(display.toString());
    }
    private void checkGuess(){
        String doan = binding.guess.getText().toString().toUpperCase();
        if(doan.length()!=1||doan.length()==0){
            binding.count.setText("Hãy nhập một từ!");
        }else{
            char w = doan.charAt(0);
            if(wordSolution.contains(String.valueOf(w))){
                guesses = guesses+w;
                remainCount--;
                binding.count.setText("Số lượt đoán còn lại là "+ remainCount);
                if(remainCount<=0) {
                    binding.guess.setEnabled(false);
                   binding.btnCheck.setEnabled(false);
                  binding.count.setText("Bạn đã hết lượt đoán");
                }
                updateDisplay();
            }else{
                remainCount--;
                binding.count.setText("Số lượt đoán còn lại là "+ remainCount);
                if(remainCount==0){
                    binding.guess.setEnabled(false);
                    binding.btnCheck.setEnabled(false);
                    binding.count.setText("Bạn đã hết lượt đoán");
                }
            }
        }
    }
    private void checkAswer(){
        String ans = binding.answer.getText().toString().toLowerCase();
        if(ans.equalsIgnoreCase(wordSolution)){
            binding.notice.setText("Đúng!");
            binding.count.setText("");
        }else {
           binding.notice.setText("Sai!");
            if(remainCount>0){
               binding.count.setText("Hãy dùng dự đoán \n Số lượt đoán còn lại là "+ remainCount);
            }else{
                binding.count.setText("Số lượt đoán còn lại là "+ remainCount);
            }

        }
        }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("wordSolution_story", wordSolution);
        outState.putInt(remainCount_story, remainCount);
        outState.putString("guesses_story", guesses);
        outState.putString(notice_story, String.valueOf(binding.notice.getText()));
        outState.putString(count_story, String.valueOf(binding.count.getText()));
    }
    }
