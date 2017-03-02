package com.devncoffee.sportstrivia;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    //Player Attributes
    int playerScore;
    String playerRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Called when submit button is clicked
    void submitScore(View view){
        calculateScore();
        calculateRating();
        displayMessage();
        sendMail();
    }

    //Calculates the score based on given answers
    void calculateScore(){
        playerScore = 0;
        for(int i = 1; i<=10; i++) {
            scoreQuestion(i);
        }
    }

    //Adds points to the score according to the question
    void scoreQuestion(int questionNumber){
        switch(questionNumber){
            case 1:
                RadioButton radioButtonQ1 = (RadioButton) findViewById(R.id.soccer_question_1_correct_answer);
                if (radioButtonQ1.isChecked()){
                    playerScore += 10;
                }
                break;
            case 2:
                CheckBox checkBoxQ2 = (CheckBox) findViewById(R.id.soccer_question_2_wrong_answer_1);
                if(checkBoxQ2.isChecked()){
                    return;
                }
                checkBoxQ2 = (CheckBox) findViewById(R.id.soccer_question_2_wrong_answer_2);
                if(checkBoxQ2.isChecked()){
                    return;
                }
                checkBoxQ2 = (CheckBox) findViewById(R.id.soccer_question_2_correct_answer_1);
                if(checkBoxQ2.isChecked()){
                    playerScore += 5;
                }
                checkBoxQ2 = (CheckBox) findViewById(R.id.soccer_question_2_correct_answer_2);
                if(checkBoxQ2.isChecked()){
                    playerScore += 5;
                }
                break;
            case 3:
                RadioButton radioButtonQ3 = (RadioButton) findViewById(R.id.soccer_question_3_correct_answer);
                if (radioButtonQ3.isChecked()){
                    playerScore += 10;
                }
                break;
            case 4:
                RadioButton radioButtonQ4 = (RadioButton) findViewById(R.id.basketball_question_1_correct_answer);
                if (radioButtonQ4.isChecked()){
                    playerScore += 10;
                }
                break;
            case 5:
                RadioButton radioButtonQ5 = (RadioButton) findViewById(R.id.basketball_question_2_correct_answer);
                if (radioButtonQ5.isChecked()){
                    playerScore += 10;
                }
                break;
            case 6:
                EditText editTextQ6 = (EditText) findViewById(R.id.basketball_question_3);
                String answerQ6 = getString(R.string.basketball_question_3_correct_answer);
                if(answerQ6.equalsIgnoreCase(editTextQ6.getText().toString())){
                    playerScore += 10;
                }
                break;
            case 7:
                RadioButton radioButtonQ7 = (RadioButton) findViewById(R.id.volleyball_question_1_correct_answer);
                if (radioButtonQ7.isChecked()){
                    playerScore += 10;
                }
                break;
            case 8:
                RadioButton radioButtonQ8 = (RadioButton) findViewById(R.id.volleyball_question_2_correct_answer);
                if (radioButtonQ8.isChecked()){
                    playerScore += 10;
                }
                break;
            case 9:
                RadioButton radioButtonQ9 = (RadioButton) findViewById(R.id.tenis_question_1_correct_answer);
                if (radioButtonQ9.isChecked()){
                    playerScore += 10;
                }
                break;
            case 10:
                EditText editTextQ10 = (EditText) findViewById(R.id.boxe_question_1);
                String answerQ10 = getString(R.string.boxe_question_1_correct_answer);
                if(answerQ10.equalsIgnoreCase(editTextQ10.getText().toString())){
                    playerScore += 10;
                }
                break;
            default:
                break;
        }
    }

    //Calculates the player's rating based on his score
    void calculateRating(){
        if(playerScore <= 30){
            playerRating = getString(R.string.rating_message_bad);
        }
        else if(playerScore <= 70){
            playerRating = getString(R.string.rating_message_regular);
        }
        else if(playerScore <= 90){
            playerRating = getString(R.string.rating_message_good);
        }
        else{
            playerRating = getString(R.string.rating_message_excelent);
        }
    }

    //Displays toast message with player score
    void displayMessage(){
        Toast toast = Toast.makeText(this, getString(R.string.congratz_message, String.valueOf(playerScore)), Toast.LENGTH_SHORT);
        toast.show();
    }

    //Sends to the user an e-mail with the score
    void sendMail(){
        String mailBody = getString(R.string.congratz_message, String.valueOf(playerScore));
        mailBody += " " + playerRating;

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); //only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Sports Trivia score for " + getPlayerName());
        intent.putExtra(intent.EXTRA_TEXT, mailBody);

        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }
    }

    //Get method to retrieve player's name
    String getPlayerName() {
        EditText editText = (EditText) findViewById(R.id.name_edit_text_view);
        return editText.getText().toString();
    }

    //Get method to retrieve player's e-mail
    String getPlayerMail() {
        EditText editText = (EditText) findViewById(R.id.mail_edit_text_view);
        return editText.getText().toString();
    }
}
