package com.example.sunny.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.example.sunny.test.MESSAGE";
    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private TextView questionView;
    private Question[] mQuestions = new Question[]{
        new Question(R.string.question_One, true),
        new Question(R.string.question_Two, false),
        new Question(R.string.question_three, false),
        new Question(R.string.question_four, true),
        new Question(R.string.question_five, true)
    };

    private int currentQuestionIndex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        trueButton = (Button)findViewById(R.id.true_button);
        falseButton = (Button)findViewById(R.id.false_button);
        nextButton = (Button)findViewById(R.id.next_button);
        questionView = (TextView)findViewById(R.id.question_id);

        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkQuestionAnswer(true);
            }
        });

        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkQuestionAnswer(false);
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateQuestionContent();
            }
        });


        updateQuestionContent();
    }

    private void updateQuestionContent() {
        int index = (currentQuestionIndex+ 1) % mQuestions.length;
        Question question = mQuestions[index];
        currentQuestionIndex = index;
        questionView.setText(question.getTextResId());
    }

    private  void checkQuestionAnswer(boolean answerTrue) {
        boolean questionIsTrue = mQuestions[currentQuestionIndex].isAnswerTrue();
        int messageId = 0;
        if (questionIsTrue == answerTrue) {
            messageId = R.string.correct_toast;
        } else {
            messageId = R.string.inCorrect_toast;
        }

        Toast.makeText(MainActivity.this, messageId,Toast.LENGTH_SHORT).show();
    }

    public void trueButtonPressed(View view) {


    }

    public void  falseButtonPressed(View view) {

    }



//    public void sendMessage(View view) {
//        Intent intent = new Intent(this, DisplayMessageActivity.class);
//        EditText editText =  (EditText) findViewById(R.id.edit_message);
//        String message = editText.getText().toString();
//        intent.putExtra(EXTRA_MESSAGE, message);
//        startActivity(intent);
//    }
}
