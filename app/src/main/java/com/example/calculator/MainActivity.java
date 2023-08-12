package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView restv, soltv;
    MaterialButton buttonc, buttonparaopen, buttonparaclose;
    MaterialButton buittondivide, buttonprod, buttonequals, buttonminus, buttonplus;
    MaterialButton buttonac, buttondot, button1, button2, button3, button4, button5, button6, button7, button8, button9, button0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        restv = findViewById(R.id.resulttv);
        soltv = findViewById(R.id.solutiontv);
        assignId(button0, R.id.button0);
        assignId(button1, R.id.button1);
        assignId(button2, R.id.button2);
        assignId(button3, R.id.button3);
        assignId(button4, R.id.button4);
        assignId(button5, R.id.button5);
        assignId(button6, R.id.button6);
        assignId(button7, R.id.button7);
        assignId(button8, R.id.button8);
        assignId(button9, R.id.button9);
        assignId(buttonac, R.id.buttonac);
        assignId(buttondot, R.id.buttondot);
        assignId(buttonplus, R.id.addbutton);
        assignId(buttonminus, R.id.minusbutton);
        assignId(buittondivide, R.id.dividebutton);
        assignId(buttonprod, R.id.buttonmultiply);
        assignId(buttonequals, R.id.equalsbutton);
        assignId(buttonc, R.id.cbutton);
        assignId(buttonparaclose, R.id.closeParenthesisbutton);
        assignId(buttonparaopen, R.id.openParenthesisbutton);
    }


    void assignId(MaterialButton btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button =(MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = soltv.getText().toString();

        if(buttonText.equals("AC")){
            soltv.setText("");
            restv.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            soltv.setText(restv.getText());
            return;
        }
        if(buttonText.equals("C")){
            dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);
        }else{
            dataToCalculate = dataToCalculate+buttonText;
        }
        soltv.setText(dataToCalculate);

        String finalResult = getResult(dataToCalculate);

        if(!finalResult.equals("Err")){
            restv.setText(finalResult);
        }

    }
    String getResult(String data){
        try{
            Context context  = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult =  context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        }catch (Exception e){
            return "Err";
        }
    }

}




