package com.codeavenge.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.View;
import org.mariuszgromada.math.*;
import org.mariuszgromada.math.mxparser.Expression;

import com.codeavenge.calculator.databinding.ActivityMainBinding;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Disable android default keyboard when click edit text
        binding.display.setShowSoftInputOnFocus(false);
        Objects.requireNonNull(getSupportActionBar()).hide();

        binding.display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getString(R.string.edt).equals(binding.display.getText().toString())) {
                    binding.display.setText("");
                }
            }
        });


        binding.zeroBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateText("0");
            }
        });
        binding.oneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateText("1");
            }
        });
        binding.twoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateText("2");
            }
        });
        binding.threeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateText("3");
            }
        });
        binding.fourBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateText("4");
            }
        });
        binding.fiveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateText("5");
            }
        });
        binding.sixBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateText("6");
            }
        });
        binding.sevenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateText("7");
            }
        });
        binding.eightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateText("8");
            }
        });
        binding.nineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateText("9");
            }
        });
        binding.divisionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateText("÷");
            }
        });
        binding.multiplyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateText("×");
            }
        });
        binding.subtractBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateText("-");
            }
        });
        binding.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateText("+");
            }
        });
        binding.pointBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateText(".");
            }
        });
        binding.exponentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateText("^");
            }
        });
//        binding.clearBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                updateText("");
//            }
//        });

        binding.equalsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userExp=binding.display.getText().toString();
                userExp=userExp.replaceAll("÷","/");
                userExp=userExp.replaceAll("×","*");

                Expression expression=new Expression(userExp);
                String result=String.valueOf(expression.calculate());
                binding.display.setText(result);
                binding.display.setSelection(result.length());
            }
        });

        binding.backspaceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Find the cursor position
                int cursorPosi = binding.display.getSelectionStart();
                int length = binding.display.getText().length();
                //Backspace button logic
                if (cursorPosi != 0 && length != 0) {
                    SpannableStringBuilder spannableStringBuilder = (SpannableStringBuilder) binding.display.getText();
                    spannableStringBuilder.replace(cursorPosi - 1, cursorPosi, "");
                    binding.display.setText(spannableStringBuilder);
                    binding.display.setSelection(cursorPosi - 1);
                }
            }
        });
        binding.parenthesisBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cursorPosi=binding.display.getSelectionStart();
                int openPar=0;
                int closePar=0;
                int textLength=binding.display.getText().length();

                for (int i=0;i<cursorPosi;i++){
                    if (binding.display.getText().toString().substring(i,i+1).equals("(")){
                        openPar += 1;
                    }else if (binding.display.getText().toString().substring(i,i+1).equals(")")){
                        closePar += 1;
                    }
                }
                if (openPar==closePar||binding.display.getText().toString().substring(textLength-1,textLength).equals("")){
                    updateText("(");
                }else if (closePar<openPar && !binding.display.getText().toString().substring(textLength-1,textLength).equals("")){
                    updateText(")");
                }
                binding.display.setSelection(cursorPosi+1);
            }
        });

    }

    private void updateText(String stringToAdd) {
        String oldStr = binding.display.getText().toString();
        //Find the cursor position
        int cursorPosi = binding.display.getSelectionStart();
        //Separate input string to different string based on current cursor position
        String leftStr = oldStr.substring(0, cursorPosi);
        String rightStr = oldStr.substring(cursorPosi);
        if (getString(R.string.edt).equals(binding.display.getText().toString())) {
            binding.display.setText(stringToAdd);
        } else {
            binding.display.setText(String.format("%s%s%s", leftStr, stringToAdd, rightStr));
        }
        binding.display.setSelection(cursorPosi + 1);

    }
    public void onClearButton(View v) {
        binding.display.getText().clear();
    }

}