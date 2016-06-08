package com.example.sunny.test;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DisplayMessageActivity extends AppCompatActivity {

    private Button mShowAnswerButton;
    private static final String mShownAnswer = "shownAnswer";
    private static final String EXTRA_ANSWER_SHOWN = "com.example.sunny.test.AnswerShown";
    private static final String ANSWER_isTrue = "com.example.sunny.test.AnswerIsTrue";

    private TextView mAnswerTextView;
    private TextView mVersionTextView;
    private boolean mAnswerShown;
    private boolean mAnswerIsTrue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
        mShowAnswerButton = (Button)findViewById(R.id.showAnswerButton);
        mAnswerTextView = (TextView)findViewById(R.id.answerTextView);
        mVersionTextView = (TextView)findViewById(R.id.versionTextView);

        mVersionTextView.setText(String.format("API %s",Build.VERSION.SDK_INT));

        if (savedInstanceState != null) {
            mAnswerShown = savedInstanceState.getBoolean(EXTRA_ANSWER_SHOWN, false);
            mAnswerIsTrue = savedInstanceState.getBoolean(ANSWER_isTrue, false);

            sendResultAndUpdateAnswerTextView();

        } else {

            mAnswerIsTrue = getIntent().getBooleanExtra(MainActivity.EXTRA_MESSAGE, false);
            mShowAnswerButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mAnswerShown = true;
                    sendResultAndUpdateAnswerTextView();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        int cx = mShowAnswerButton.getWidth() / 2 ;
                        int cy = mShowAnswerButton.getHeight() / 2;

                        float radius = mShowAnswerButton.getWidth();

                        Animator anim = ViewAnimationUtils.createCircularReveal(mShowAnswerButton, cx, cy, radius, 0);
                        anim.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                mAnswerTextView.setVisibility(View.VISIBLE);
                                mShowAnswerButton.setVisibility(View.VISIBLE);
                            }
                        });
                        anim.start();

                    }
                }
            });
        }
    }

    public  void sendResultAndUpdateAnswerTextView() {
        if (mAnswerIsTrue) {
            mAnswerTextView.setText(R.string.true_button);
        } else {
            mAnswerTextView.setText(R.string.false_button);
        }

        Intent result = new Intent();
        result.putExtra(EXTRA_ANSWER_SHOWN, mAnswerShown);
        setResult(RESULT_OK, result);
    }

    public static boolean wasAnswerShown(Intent result) {
        return result.getBooleanExtra(EXTRA_ANSWER_SHOWN, false);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(EXTRA_ANSWER_SHOWN,mAnswerShown);
        outState.putBoolean(ANSWER_isTrue,mAnswerIsTrue);
    }
}
