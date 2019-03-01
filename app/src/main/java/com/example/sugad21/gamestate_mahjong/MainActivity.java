package com.example.sugad21.gamestate_mahjong;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button runButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        runButton = (Button)findViewById(R.id.testButton);
        runButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        GameState firstInstance = new GameState();
        GameState secondInstance = new GameState(firstInstance);
        GameState thirdInstance = new GameState();
        GameState fourthInstance = new GameState(thirdInstance);
    }


}
