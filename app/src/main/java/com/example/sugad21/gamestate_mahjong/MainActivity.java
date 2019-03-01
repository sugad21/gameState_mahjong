package com.example.sugad21.gamestate_mahjong;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button runButton;
    private EditText gameStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        runButton = (Button)findViewById(R.id.runButton);
        runButton.setOnClickListener(this);

        gameStatus = (EditText)findViewById(R.id.gameStatus);

    }

    @Override
    public void onClick(View v){
        GameState firstInstance = new GameState();
        GameState secondInstance = new GameState(firstInstance);
        GameState thirdInstance = new GameState();
        GameState fourthInstance = new GameState(thirdInstance);
        gameStatus.setText("");

        firstInstance.discardTile(new mTiles(3,"dots"),0);
        gameStatus.append("Player at seating East has discarded a tile (suit: dot; value: 3");


    }


}
