package com.example.edward237.baseconversion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.edward237.baseconversion.R;

public class ConversionActivity extends AppCompatActivity {

    private Button mEnterButton;
    private EditText mInputNumber;
    private EditText mStartBase;
    private EditText mEndBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion);

        mInputNumber = (EditText) findViewById(R.id.input_number);
        mStartBase = (EditText) findViewById(R.id.starting_base);
        mEndBase = (EditText) findViewById(R.id.end_base);


        mEnterButton = (Button) findViewById(R.id.enter_button);
        mEnterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int result = 0;
                int inputNum = Integer.parseInt(mInputNumber.getText().toString());
                int startBaseNum = Integer.parseInt(mStartBase.getText().toString());
                int endBaseNum = Integer.parseInt(mEndBase.getText().toString());
                int inputLength = mInputNumber.length();

                if (endBaseNum == 10 || startBaseNum != 10)
                    result = toBaseTen(mInputNumber.getText(), startBaseNum, endBaseNum, inputLength);
                else {
                    result = fromBaseTen(inputNum, startBaseNum, endBaseNum, inputLength);
                }

                Toast.makeText(ConversionActivity.this, String.valueOf(result), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public int fromBaseTen(int inputNum, int startBaseNum, int endBaseNum, int inputLength) {
        int incrementNum = 0;
        int result = 0;
        int finalValue = 0;
        do {
            result = inputNum / ((int) Math.pow(endBaseNum, incrementNum));
            incrementNum++;
        }
        while (result != 0);
        int decrementNum = incrementNum - 2;


        while (decrementNum > -1) {

            result = inputNum / ((int) Math.pow(endBaseNum, decrementNum));
            finalValue = finalValue + result * (int) Math.pow(10, decrementNum);
            inputNum = inputNum % ((int) Math.pow(endBaseNum, decrementNum));
            decrementNum--;
        }

        return finalValue;
    }


    public int toBaseTen (Editable inputValue, int startBaseNum, int endBaseNum, int inputLength){
        int incrementNum = 0, decrementNum = inputLength, total = 0;
        while(incrementNum != inputLength){
            total =  total + Integer.parseInt(inputValue.subSequence(decrementNum - 1, decrementNum).toString()) * (int) Math.pow(startBaseNum, incrementNum);
            incrementNum++;
            decrementNum--;}
        return total;

    }

}
