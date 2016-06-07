package com.example.sunny.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.example.sunny.test.MESSAGE";
    private Button trueButton;
    private Button falseButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        trueButton = (Button)findViewById(R.id.true_button);
        falseButton= (Button)findViewById(R.id.false_button);
        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, R.string.correct_toast,Toast.LENGTH_SHORT).show();
            }
        });

        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, R.string.inCorrect_toast,Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void trueButtonPressed(View view) {
        Toast.makeText(MainActivity.this, R.string.correct_toast,Toast.LENGTH_SHORT).show();

    }

    public void  falseButtonPressed(View view) {
        Toast.makeText(MainActivity.this, R.string.inCorrect_toast,Toast.LENGTH_SHORT).show();

    }
//    public void sendMessage(View view) {
//        Intent intent = new Intent(this, DisplayMessageActivity.class);
//        EditText editText =  (EditText) findViewById(R.id.edit_message);
//        String message = editText.getText().toString();
//        intent.putExtra(EXTRA_MESSAGE, message);
//        startActivity(intent);
//    }
}
