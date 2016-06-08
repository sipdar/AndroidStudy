package com.example.sunny.test;

import android.animation.Animator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.sunny.test.AnswerIsTrue";
    private static final String TAG = "QuizActivity";
    private static final String Key_INDEX = "questionIndex";
    private static final String Cheat_INDEXS = "questionCheatIndexs";
    private static final String USER_IS_CHEATER = "cheater";
    public static final int REQUEST_CODE_CHEAT = 0;

    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private Button prevButton;
    private Button cheatButton;
    private TextView questionView;

    private boolean mIsCheater;

    private Question[] mQuestions = new Question[]{
        new Question(R.string.question_One, true),
        new Question(R.string.question_Two, false),
        new Question(R.string.question_three, false),
        new Question(R.string.question_four, true),
        new Question(R.string.question_five, true)
    };

    private boolean[] mCheatIndexs = new boolean[]{
            false,
            false,
            false,
            false,
            false
    };

    private int mCurrentIndex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"on create called");
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(Key_INDEX, 0);
            mIsCheater = savedInstanceState.getBoolean(USER_IS_CHEATER,false);
            mCheatIndexs = savedInstanceState.getBooleanArray(Cheat_INDEXS);
            Log.d(TAG, String.format("get mCurrentIndex %d", mCurrentIndex));

        }


        trueButton = (Button)findViewById(R.id.true_button);
        falseButton = (Button)findViewById(R.id.false_button);
        nextButton = (Button)findViewById(R.id.next_button);
        prevButton = (Button)findViewById(R.id.prev_button);
        questionView = (TextView)findViewById(R.id.question_id);
        cheatButton = (Button)findViewById(R.id.cheatButton);

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
                updateNextQuestionContent();
            }
        });

        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePrevQuestionContent();
            }
        });

        questionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateNextQuestionContent();
            }
        });

        cheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(MainActivity.this, DisplayMessageActivity.class);
                boolean answerIsTrue = mQuestions[mCurrentIndex].isAnswerTrue();
                newIntent.putExtra(EXTRA_MESSAGE, answerIsTrue);
                startActivityForResult(newIntent, REQUEST_CODE_CHEAT);
            }
        });

        updateQuestionContent(mCurrentIndex);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"on onStart called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"on onPause called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }


    private  void updateNextQuestionContent() {
        int index = (mCurrentIndex + 1) % mQuestions.length;
        updateQuestionContent(index);
    }

    private  void updatePrevQuestionContent() {
        int index = (mCurrentIndex - 1) % mQuestions.length;
        if (index < 0 ){
            index += mQuestions.length;
        }
        updateQuestionContent(index);
    }

    private void updateQuestionContent(int nextIndex) {

        Question question = mQuestions[nextIndex];
        mCurrentIndex = nextIndex;
        questionView.setText(question.getTextResId());
    }

    private  void checkQuestionAnswer(boolean answerTrue) {
        boolean questionIsTrue = mQuestions[mCurrentIndex].isAnswerTrue();
        int messageId = 0;
        mIsCheater = mCheatIndexs[mCurrentIndex];
        if (mIsCheater) {
            messageId = R.string.judgment_toast;
        } else if (questionIsTrue == answerTrue) {
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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(Key_INDEX, mCurrentIndex);
        outState.putBoolean(USER_IS_CHEATER, mIsCheater);
        outState.putBooleanArray(Cheat_INDEXS, mCheatIndexs);
        Log.d(TAG, String.format("save mCurrentIndex %d", mCurrentIndex));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode != REQUEST_CODE_CHEAT) {
            return;
        }

        if (resultCode != RESULT_OK) {
            return;
        }

        if (data == null) {
            return;
        }
        mIsCheater = DisplayMessageActivity.wasAnswerShown(data);
        mCheatIndexs[mCurrentIndex] = mIsCheater;
    }

    //    public void sendMessage(View view) {
//        Intent intent = new Intent(this, DisplayMessageActivity.class);
//        EditText editText =  (EditText) findViewById(R.id.edit_message);
//        String message = editText.getText().toString();
//        intent.putExtra(EXTRA_MESSAGE, message);
//        startActivity(intent);
//    }
}
