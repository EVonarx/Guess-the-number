package com.example.guessthenumber;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText idInputNumber;
    private Button idButtonCompare;
    private TextView idMessage;
    private ProgressBar idProgressBar;
    private TextView idHistory;

    private int searchedValue;
    private int score;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        idInputNumber = (EditText) findViewById(R.id.idInputNumber);
        idButtonCompare = (Button) findViewById(R.id.idButtonCompare);
        idMessage = (TextView) findViewById(R.id.idMessage);
        idProgressBar = (ProgressBar) findViewById(R.id.idProgressBar);
        idHistory = (TextView) findViewById(R.id.idHistory);

        idButtonCompare.setOnClickListener(btnCompareListener);

        init();


    }

    private void init() {
     score = 0;
     searchedValue = 1 +(int) (Math.random() *100);
     String s = String.valueOf(searchedValue);
     Log.i("Searched value : " , s);

        idMessage.setText("");
        idHistory.setText("");

        idProgressBar.setProgress(score);

        idInputNumber.setText("");
        idInputNumber.requestFocus();
    }


    private View.OnClickListener btnCompareListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            Log.i("INFORMATION", "Button clicked" );
            String InputNumber = idInputNumber.getText().toString();
            if (InputNumber.equals("")) return;

            score++;
            idInputNumber.setText("");
            idInputNumber.requestFocus();
            idProgressBar.incrementProgressBy(1);
            idHistory.append(InputNumber + "\n");

            int intInputNumber = Integer.parseInt(InputNumber);
            if (intInputNumber == searchedValue) {
                    congratulations();
            } else {
                if (intInputNumber> searchedValue)
                    idMessage.setText(R.string.strSmaller);
                else
                    idMessage.setText(R.string.strBigger);


            }

        }
    };

    private void congratulations() {
        //idMessage.setText(getString(R.string.strCongratulations, score));
        AlertDialog aD = new AlertDialog.Builder(this).create();
        aD.setTitle(R.string.app_name);

        aD.setMessage(getString(R.string.strCongratulations, score) + "\n" + "Would you like to play again ?");

        aD.setButton(AlertDialog.BUTTON_POSITIVE, getString(R.string.strYes), new AlertDialog.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    init();
                }
        });
        aD.setButton(AlertDialog.BUTTON_NEGATIVE, getString(R.string.strNo), new AlertDialog.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        aD.show();
    }
}
