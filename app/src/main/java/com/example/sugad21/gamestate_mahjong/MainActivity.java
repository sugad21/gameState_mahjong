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

        firstInstance.discardTile(firstInstance.getGamePlayers().get(0).getHand().get(0),0);
        gameStatus.append("Player at seating East has discarded a tile\n");
        firstInstance.drawFromWall(firstInstance.getWall().get(0),1);
        firstInstance.discardTile(firstInstance.getGamePlayers().get(1).getHand().get(0),1);
        gameStatus.append("Player at seating North has drawn a tile and then discarded it\n");
        firstInstance.drawDiscardTile(firstInstance.getRecentDiscard(),3);
        gameStatus.append("Player 3 has picked up the recently discarded card\n\n");
        gameStatus.append(firstInstance.toString());
        gameStatus.append("First Instance Game Status:\n\n");
        gameStatus.append(secondInstance.toString());
        gameStatus.append("Third Instance Game Status\n\n");
        gameStatus.append(fourthInstance.toString());

    }


}
