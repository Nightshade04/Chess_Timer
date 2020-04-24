package com.example.chesstimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button playerOneButton, playerTwoButton;
    CountDownTimer cdTimer;
    EditText timer;
    ToneGenerator toneG;
    // this is done by shruti

    int c = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timer = (EditText) findViewById(R.id.timer);
        timer.setFocusable(false);
        timer.setText("14");

        playerOneButton = findViewById(R.id.player1Button);
        playerTwoButton = findViewById(R.id.player2Button);

        playerTwoButton.setEnabled(false);

        playerOneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClickCallback(playerOneButton);
            }
        });
        playerTwoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClickCallback(playerTwoButton);
            }
        });
    }

    protected void buttonClickCallback(Button button){
        c++;
        resetTimer(button);
        cdTimer.start();
        if(c % 2 == 0)
        {
            playerOneButton.setEnabled(true);
            playerTwoButton.setEnabled(false);
        }
        else{
            playerOneButton.setEnabled(false);
            playerTwoButton.setEnabled(true);
        }
        System.out.println(c);
    }

    private void resetTimer(final Button  button) {
        if(cdTimer == null)
        {
            System.out.println("Timer Not here");
            cdTimer = new CountDownTimer(15000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    timer.setText((millisUntilFinished/1000) + "");
                }

                @Override
                public void onFinish() {
                    newTurn(button);
//                    toneG = new ToneGenerator(AudioManager.STREAM_ALARM, 100);
//                    toneG.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 200);
                    Toast toast =  Toast. makeText(getApplicationContext(),"Player Turn Over",Toast. LENGTH_SHORT);
                    toast.show();
                }
            };
        }
        else
        {
            cdTimer.cancel();
        }
    }

    private void newTurn(Button button) {
        if(button.getText().equals("Player 1")){
            playerOneButton.setEnabled(false);
            buttonClickCallback(playerTwoButton);
        }
        else {
            playerTwoButton.setEnabled(false);
            buttonClickCallback(playerOneButton);
        }
    }


}
