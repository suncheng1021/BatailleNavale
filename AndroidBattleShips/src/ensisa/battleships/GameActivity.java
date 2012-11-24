package ensisa.battleships;

import android.os.Bundle;
import android.app.TabActivity;
import android.content.Intent;
import android.util.Log;
import android.widget.TabHost;

public class GameActivity extends TabActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        
        Log.d("BattleShips - GameActivity", "onCreate");
        
		TabHost tabHost = getTabHost();
		TabHost.TabSpec spec; // reusable tabspec for each tab
		Intent intent;

		// On crée toutes les activity liées aux onglets
		intent = new Intent().setClass(this, BattleGridActivity.class);
		spec = tabHost.newTabSpec("My battlegrid").setIndicator("My battlegrid").setContent(intent);
		tabHost.addTab(spec);

		intent = new Intent().setClass(this, BattleGridActivity.class);
		spec = tabHost.newTabSpec("Adverse battlegrid").setIndicator("Adverse battlegrid").setContent(intent);
		tabHost.addTab(spec);

		// Le premier onglet est actif
		tabHost.setCurrentTab(0);
    }

}
