package ensisa.battleships;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;
import android.view.View;

public class BattleGridActivity extends Activity  {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_grid);
        
        Log.d("BattleShips - BattleGridActivity", "onCreate");
        
        BattleGridView battleGridView = (BattleGridView) findViewById(R.id.battleGridView1);

       // battleGridView.setOnClickListener()
        

        
    }

}
