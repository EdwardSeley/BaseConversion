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
                
		if (endBaseNum == 16 && !finalHexLetters.isEmpty())
        		Toast.makeText(ConversionActivity.this, String.valueOf(finalHexLetters), Toast.LENGTH_SHORT).show();
        	else
                	JOptiToast.makeText(ConversionActivity.this, String.valueOf(result), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public int fromBaseTen(int inputNum, int startBaseNum, int endBaseNum, int inputLength) {
     int incrementNum = 0;
        int result = 0;
        int finalValue = 0;
        int inputNum = Integer.parseInt(inputValue);
        do {
            result = inputNum / ((int) Math.pow(endBaseNum, incrementNum));
            incrementNum++;
        }
        while (result != 0);
        
        int decrementNum = incrementNum - 2;
        String [] lettersArray = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};
        String hexLetters = "";
        while (decrementNum > -1) {
        	
            result = inputNum / ((int) Math.pow(endBaseNum, decrementNum));
            hexLetters = hexLetters.concat(String.valueOf(lettersArray[result]));
            System.out.println(hexLetters);
            finalValue = finalValue + result * (int) Math.pow(10, decrementNum);
            inputNum = inputNum % ((int) Math.pow(endBaseNum, decrementNum));
            decrementNum--;
        }
        if (endBaseNum == 16)
        	finalHexLetters = hexLetters;
        return finalValue;
    }


    public int toBaseTen (Editable inputValue, int startBaseNum, int endBaseNum, int inputLength){
          int incrementNum = 0, decrementNum = inputLength - 1, total = 0;
        int tempNum = 0;
        String [] lettersArray = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};
        if (startBaseNum == 16)
        for (int x = 0; x < inputLength; x++){
        tempNum= Arrays.asList(lettersArray).indexOf(inputValue.substring(x, x + 1));
        total =  total + (tempNum * (int) Math.pow(startBaseNum, decrementNum));
        decrementNum--;
        }
        else
        while(incrementNum != inputLength){
        	total =  total + Integer.parseInt(inputValue.subSequence(decrementNum - 1, decrementNum).toString()) * (int) Math.pow(startBaseNum, incrementNum);
            incrementNum++;
            decrementNum--;}
        return total;

    }

static String finalHexLetters = null;

}
