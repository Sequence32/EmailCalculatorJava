package com.example.dustinpc1.email;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.view.ViewGroup.LayoutParams;

public class MainActivity extends AppCompatActivity {

    TableLayout tl;
    TableRow tr;
    String[] buttonText = {"9", "8", "7", "+", "6", "5", "4", "-", "3", "2", "1", "*", "=","0", "/", "clear"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button send = (Button) this.findViewById(R.id.button);


        //Send Email one click, this will send the answer to my Email to another email
        //The final the app will send it to your email if you put in your user-name password and
        //another email address, I may change the way this works moving it to the local email
        //Phone app if I can't get the network threading to work

        send.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    GMailSender sender = new GMailSender();
                    sender.doInBackground(null, null);
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            }
        });

        tl = (TableLayout)findViewById(R.id.tableLayout);
        TableRow.LayoutParams paramsTR = new TableRow.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        TableLayout.LayoutParams params = new TableLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
        params.weight =1;
        int i=0;
        while (i < buttonText.length)
        {
            if(i % 4 == 0)
            {
                tr = new TableRow(this);
                tr.setLayoutParams(params);
                tr.setPadding(0,0,0,0);
                tl.addView(tr);
            }
            final Button button = new Button(this);

            button.setLayoutParams(paramsTR);
            button.setGravity(Gravity.CENTER);
            button.setTextSize(21);
            button.setPadding(0,0,0,0);
            button.setText(buttonText[i]);
            button.setId(i);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    switch(v.getId())
                    {
                        case 0:
                            setText("9");
                            break;
                        case 1:
                            setText("8");
                            break;
                        case 2:
                            setText("7");
                            break;
                        case 3:
                            setText("+");
                            break;
                        case 4:
                            setText("6");
                            break;
                        case 5:
                            setText("5");
                            break;
                        case 6:
                            setText("4");
                            break;
                        case 7:
                            setText("-");
                            break;
                        case 8:
                            setText("3");
                            break;
                        case 9:
                            setText("2");
                            break;
                        case 10:
                            setText("1");
                            break;
                        case 11:
                            setText("*");
                            break;
                        case 12:
                            getAnswer();
                            break;
                        case 13:
                            setText("0");
                            break;
                        case 14:
                            setText("/");
                            break;
                        case 15:
                            clearText();
                            break;
                    }
                }
            });
            tr.addView(button);
            i++;
        }
    }

    private void clearText()
    {
        EditText text = (EditText) findViewById(R.id.editText);
        text.setText("");
    }
    private void setText(String theId)
    {
        EditText text = (EditText) findViewById(R.id.editText);
        EditText answer = (EditText)findViewById(R.id.editText2);
        text.setText(text.getText()+ theId);
        System.out.println("before get answer");
        double theAnswer = getAnswer();
        answer.setText(""+theAnswer);

    }

    private double getAnswer()
    {
        EditText declaration = (EditText) findViewById(R.id.editText);


        try {
            double int1, int2;
            char[] operators = {'+', '-', '*', '/'};


            StringBuilder sb = new StringBuilder();
            sb.append(declaration.toString());
            boolean breaker = false;

            for(int i=0; i<operators.length; i++)
            {
                System.out.println("in "+ i);
                if(declaration.getText().toString().contains(String.valueOf(operators[i])))
                {
                    System.out.println("int1");
                    int1 = Double.parseDouble(declaration.getText().toString().substring(0, declaration.getText().toString().indexOf(operators[i])));
                    System.out.println("int1  " + Integer.parseInt((declaration.getText().toString().substring(0, declaration.getText().toString().indexOf(operators[i])))));
                    System.out.println("int2");

                    int2 = Double.parseDouble(declaration.getText().toString().substring(sb.toString().indexOf(operators[i])-1, declaration.getText().toString().length()));
                    System.out.println("sum ");
                    double sum = int1 + int2;
                    System.out.println(sum);
                    System.out.println("Sum Was returned");
                    return sum;
                }
            }
            if(!breaker) {
                System.out.println("Sum Was not returned");
                return Double.parseDouble(declaration.getText().toString());
            }
            else {
                System.out.println("Sum was not retuened");
                return Double.parseDouble(declaration.getText().toString().replaceAll("[-+*/]", ""));
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return Double.parseDouble(declaration.getText().toString().replaceAll("[-+*/]" ,""));
        }
    }

}


