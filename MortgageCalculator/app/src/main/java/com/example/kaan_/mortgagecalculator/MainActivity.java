package com.example.kaan_.mortgagecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.text.TextUtils;
import android.widget.SeekBar;
import android.widget.TextView;
import android.text.TextWatcher;

public class MainActivity extends AppCompatActivity {



    private static final String LOAN_AMOUNT = "LOAN_AMOUNT";
    private static final String CUSTOM_INTEREST_RATE = "CUSTOM_INTEREST_RATE";

    private double currentLoanAmount ;
    private EditText loanEditText;

    private double currentCustomRate;
    private TextView customRateTextView;




    Button calculateButton1;


    private TextView tenYearText;
    private TextView twentyYearText;
    private TextView thirtyYearText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




    {

        currentLoanAmount = savedInstanceState.getDouble(LOAN_AMOUNT);

        currentCustomRate =
                savedInstanceState.getDouble(CUSTOM_INTEREST_RATE);
    }



        final EditText Price = (EditText) findViewById(R.id.pursPriceEdit);
        final EditText Rate = (EditText)findViewById(R.id.rateEdit);


        final SeekBar customSeekBar = (SeekBar) findViewById(R.id.seekBar);
        customSeekBar.setOnSeekBarChangeListener(customSeekBarListener);


        tenYearText = (TextView) findViewById(R.id.tenEdit);
        twentyYearText = (TextView) findViewById(R.id.twentyEdit);
        thirtyYearText = (TextView) findViewById(R.id.thirtyEdit);


        final EditText DownPay = (EditText)findViewById(R.id.downPayEdit);




        final EditText result = (EditText)findViewById(R.id.ResultEdit) ;


        calculateButton1 = (Button) findViewById(R.id.calculateButton);

        calculateButton1.setOnClickListener(new View.OnClickListener() {

            private double formula(double Price, double Rate, int term)
            {
                double c = Rate/100/12.;
                double n = term *12 ;
                return Price * (c * Math.pow(1 + c, n )) / ( Math.pow(1 + c,n)-1);
            }


            private void updateMonthlyPayment()
            {

                double tenYearMonthly =
                        formula(currentLoanAmount,currentCustomRate, 10 );
                double twentyYearMonthly =
                        formula(currentLoanAmount,currentCustomRate, 20 );
                double thirtyYearMonthly =
                        formula(currentLoanAmount,currentCustomRate, 30 );


                tenYearText.setText("$" + String.format("%.0f", tenYearMonthly));
                twentyYearText.setText("$" + String.format("%.0f", twentyYearMonthly));
                thirtyYearText.setText("$" + String.format("%.0f", thirtyYearMonthly));
            }


            private void updateCustomRate()
            {

                customRateTextView.setText(String.format("%.02f", currentCustomRate) + "%");
                updateMonthlyPayment();

            }



            @Override
            public void onClick(View v) {

                String st1 = Price.getText().toString();
                String st2 = Rate.getText().toString();


                if (TextUtils.isEmpty(st1)) {
                    Price.setError(" Enter Purchase Price : ");
                    Price.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(st2)) {
                    Rate.setError(" Enter Interest Rate : ");
                    Rate.requestFocus();
                    return;
                }






                result.setText(String.valueOf(Result));




                DownPay.setText(String.valueOf(ti));

            }
        });
    }


}
