package com.example.quizapp;

import android.graphics.Color;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView questionTextView;
    TextView totalQuestionTextView;
    Button ansA,ansB,ansC,ansD;
    Button btn_submit;

    int score=0;
    int totalQuestion = QuestionAnswer.question.length;
    int currentquestionIndex = 0;
    String selectAnswer = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        totalQuestionTextView = findViewById(R.id.total_question);
        questionTextView = findViewById(R.id.question);
        ansA=findViewById(R.id.ans_a);
        ansB=findViewById(R.id.ans_b);
        ansC=findViewById(R.id.ans_c);
        ansD=findViewById(R.id.ans_d);
        btn_submit=findViewById(R.id.btn_submit);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        btn_submit.setOnClickListener(this);

        totalQuestionTextView.setText("Total Question: "+totalQuestion);

        loadnewQuestion();
    }
    private void loadnewQuestion() {
        if(currentquestionIndex == totalQuestion){
            finishQuiz();
            return;
        }
        questionTextView.setText(QuestionAnswer.question[currentquestionIndex]);
        ansA.setText(QuestionAnswer.choices[currentquestionIndex][0]);
        ansB.setText(QuestionAnswer.choices[currentquestionIndex][1]);
        ansC.setText(QuestionAnswer.choices[currentquestionIndex][2]);
        ansD.setText(QuestionAnswer.choices[currentquestionIndex][3]);

        selectAnswer="";

    }
    private void finishQuiz(){
        String passStatus;
        if(score>=totalQuestion*0.6){
            passStatus="passed";
        }else{
            passStatus="failed";
        }
        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Your score is "+score+" out of "+totalQuestion)
                .setPositiveButton("Restart ",((dialog, i) -> restartQuiz() ))
                .setCancelable(false)
                .show();

    }
    private void restartQuiz(){
        score=0;
        currentquestionIndex=0;
        loadnewQuestion();
    }

    @Override
    public void onClick(View view){
        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);

        Button clickedButton = (Button) view;

        if (clickedButton.getId() == R.id.btn_submit){
            if (!selectAnswer.isEmpty()){
                if (selectAnswer.equals((QuestionAnswer.correctAnswers[currentquestionIndex]))){
                    score++;
                }else{
                    clickedButton.setBackgroundColor(Color.MAGENTA);
                }
                currentquestionIndex++;
                loadnewQuestion();
            }else{

            }
        }else{
            selectAnswer=clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.YELLOW);
        }
    }
}
