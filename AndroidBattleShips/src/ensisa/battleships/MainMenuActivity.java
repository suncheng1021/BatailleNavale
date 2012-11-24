package ensisa.battleships;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainMenuActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        
        Log.d("BattleShips - MainMenuActivity", "onCreate");
        
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		String nickname = preferences.getString("nickname", null);
        if (nickname != null)
        	((EditText) findViewById(R.id.editText1)).setText(nickname);
    
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_menu, menu);
        return true;
    }
    
    
    public void singlePlayerGame(View v) {
    	Log.d("BattleShips - MainMenuActivity", "singlePlayerGame");
    	if (!this.checkAndRecordNickname())
    		return;
    	
		Intent myIntent = new Intent(this, GameActivity.class);
		startActivity(myIntent);
    	
    }
    
    public void createMultiplayerGame(View v) {
    	Log.d("BattleShips - MainMenuActivity", "createMultiplayerGame");
    	if (!this.checkAndRecordNickname())
    		return;
    	
		Intent myIntent = new Intent(this, GameActivity.class);
		startActivity(myIntent);	
    }
    
    
    public void joinMultiplayerGame(View v) {
    	Log.d("BattleShips - MainMenuActivity", "joinMultiplayerGame");
    	if (!this.checkAndRecordNickname())
    		return;
 
		Intent myIntent = new Intent(this, JoinServerActivity.class);
		startActivity(myIntent);	
    }
    
    public boolean checkAndRecordNickname() {
		String nickname = ((EditText) findViewById(R.id.editText1)).getText().toString();
		if (nickname.equals("")) {
			Toast.makeText(getApplicationContext(), "Le champ pseudonyme n'est pas renseigné.", Toast.LENGTH_LONG).show();
			return false;
		}
			
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		SharedPreferences.Editor editor = preferences.edit();
		editor.putString("nickname", nickname);
    	editor.commit();
    	
    	return true;
    }
    
}
