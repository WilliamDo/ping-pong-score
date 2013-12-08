package mog.net.tabletennisumpire;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class MatchSettingsActivity extends Activity {

	public static final String PLAYER_HOME = "mog.net.tabletennisumpire.PLAYER_HOME";
	public static final String PLAYER_AWAY = "mog.net.tabletennisumpire.PLAYER_AWAY";
	public static final String MAX_GAMES = "mog.net.tabletennisumpire.MAX_GAMES";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_match_settings);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_match_settings, menu);
		return true;
	}
	
	public void startMatch(View view) {
		Intent intent = new Intent(this, ScoreBoardActivity.class);
		
		EditText playerHome = (EditText) findViewById(R.id.editText1);
		EditText playerAway = (EditText) findViewById(R.id.editText2);
		
		String playerHomeName = playerHome.getText().toString();
		String playerAwayName = playerAway.getText().toString();
		
		intent.putExtra(PLAYER_HOME, playerHomeName);
		intent.putExtra(PLAYER_AWAY, playerAwayName);
		
		Spinner spinner = (Spinner) findViewById(R.id.spinner_max_games);
		String maxGames = spinner.getSelectedItem().toString();
		intent.putExtra(MAX_GAMES, Integer.parseInt(maxGames));
		
		startActivity(intent);
	}

}
