package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //0:yellow 1:red 2: empty state
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[] [] winningPositons = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int activePlayer = 0;
    boolean gameActive = true;
    public void dropin(View view){
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if (gameState[tappedCounter] == 2 && gameActive) {
            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1500);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1500).rotation(1800).setDuration(1000);
            for (int[] winningpositon : winningPositons) {
                if (gameState[winningpositon[0]] == gameState[winningpositon[1]] && gameState[winningpositon[1]] == gameState[winningpositon[2]] && gameState[winningpositon[0]] != 2) {
                    //some one has won
                    gameActive = false;
                    String winner = "";
                    if (activePlayer == 1) {
                        winner = "yellow";
                    } else {
                        winner = "red";
                    }

                    Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
                    TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
                    winnerTextView.setText(winner + " has won!");
                    playAgainButton.setVisibility(View.VISIBLE);
                    winnerTextView.setVisibility(View.VISIBLE);
                }
            }
        }
    }
     public void playAgain(View view){
         Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
         TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
         playAgainButton.setVisibility(View.INVISIBLE);
         winnerTextView.setVisibility(View.INVISIBLE);
         GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
         for(int i=0; i<gridLayout.getChildCount();i++){
             ImageView counter = (ImageView) gridLayout.getChildAt(1);
             counter.setImageDrawable(null);
         }
         for(int i=0;i<gameState.length;i++){
             gameState[i] = 2;
         }
         activePlayer = 0;
         gameActive = true;
     }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}