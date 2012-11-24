package ensisa.battleships;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class JoinServerActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_server);
        
        Log.d("BattleShips - JoinServerActivity", "onCreate");
        
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		String ipAddress = preferences.getString("ip_address", null);
        if (ipAddress != null)
        	((EditText) findViewById(R.id.editText1)).setText(ipAddress);
    }
    
    public void connexion(View v) {
    	Log.d("BattleShips - JoinServerActivity", "joinMultiplayerGame");
    	if (!this.checkAndRecordIpAddress())
    		return;
 
		Intent myIntent = new Intent(this, GameActivity.class);
		startActivity(myIntent);
    	
    }
    
    public boolean checkAndRecordIpAddress() {
		String ipAddress = ((EditText) findViewById(R.id.editText1)).getText().toString();
		if (ipAddress.equals("")) {
			Toast.makeText(getApplicationContext(), "Le champ addresse IP n'est pas renseigné.", Toast.LENGTH_LONG).show();
			return false;
		}
			
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		SharedPreferences.Editor editor = preferences.edit();
		editor.putString("ip_address", ipAddress);
    	editor.commit();
    	
    	return true;
    }

}
