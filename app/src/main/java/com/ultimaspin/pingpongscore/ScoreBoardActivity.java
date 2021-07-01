package com.ultimaspin.pingpongscore;

import mog.net.tabletennisumpire.model.Game;
import mog.net.tabletennisumpire.model.Match;
import mog.net.tabletennisumpire.model.Match.Player;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TableLayout;
import android.widget.TextView;

public class ScoreBoardActivity extends Activity {

	private Match currentMatch;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_score_board);

		TextView playerHomeTv = (TextView) findViewById(R.id.player_home);
		playerHomeTv.setText(getIntent().getStringExtra(
				MatchSettingsActivity.PLAYER_HOME));

		TextView playerAwayTv = (TextView) findViewById(R.id.player_away);
		playerAwayTv.setText(getIntent().getStringExtra(
				MatchSettingsActivity.PLAYER_AWAY));
		
		int maxGames = getIntent().getIntExtra(MatchSettingsActivity.MAX_GAMES, 5);

		this.currentMatch = new Match(maxGames);

		View playerScoreHome = findViewById(R.id.score_home);
		playerScoreHome.setOnTouchListener(new OnTouchListener() {
			float yStart;

			@Override
			public boolean onTouch(View v, MotionEvent event) {

				switch (event.getAction()) {

				case MotionEvent.ACTION_DOWN:
					yStart = event.getY();
					return true;

				case MotionEvent.ACTION_UP:
					float yEnd = event.getY();
					if (yEnd - yStart <= 0.0) {
						addPointHome(v);
					} else {
						deductPointHome(v);
					}
					return true;
				}

				return false;
			}
		});

		View playerScoreAway = findViewById(R.id.score_away);
		playerScoreAway.setOnTouchListener(new OnTouchListener() {
			float yStart;

			@Override
			public boolean onTouch(View v, MotionEvent event) {

				switch (event.getAction()) {

				case MotionEvent.ACTION_DOWN:
					yStart = event.getY();
					return true;
				case MotionEvent.ACTION_UP:
					float yEnd = event.getY();
					if (yEnd - yStart <= 0.0) {
						addPointAway(v);
					} else {
						deductPointAway(v);
					}
					return true;
				}

				return false;
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_score_board, menu);
		return true;
	}

	public void addPointHome(View view) {
		TextView playerScore = (TextView) findViewById(R.id.score_home);
		currentMatch.addPoint(Player.HOME);
		playerScore.setText(Integer.toString(currentMatch
				.getCurrentPoints(Player.HOME)));
	}

	public void deductPointHome(View view) {
		TextView playerScore = (TextView) findViewById(R.id.score_home);
		currentMatch.deductPoint(Player.HOME);
		playerScore.setText(Integer.toString(currentMatch
				.getCurrentPoints(Player.HOME)));
	}

	public void addPointAway(View view) {
		TextView playerScore = (TextView) findViewById(R.id.score_away);
		currentMatch.addPoint(Player.AWAY);
		playerScore.setText(Integer.toString(currentMatch
				.getCurrentPoints(Player.AWAY)));
	}

	public void deductPointAway(View view) {
		TextView playerScore = (TextView) findViewById(R.id.score_away);
		currentMatch.deductPoint(Player.AWAY);
		playerScore.setText(Integer.toString(currentMatch
				.getCurrentPoints(Player.AWAY)));
	}

	public void startNextGame(View view) {
		if (currentMatch.startNextGame()) {
			TableLayout scoresTable = (TableLayout) findViewById(R.id.game_scores);
			scoresTable.removeAllViews();

			for (Game game : currentMatch.getGames()) {
				if (game.isGameOver()) {
					TextView gameScore = new TextView(this);
					gameScore.setText(game.getCurrentPoints(Player.HOME)
							+ " - " + game.getCurrentPoints(Player.AWAY));
					scoresTable.addView(gameScore);
				}
			}

			((TextView) findViewById(R.id.score_home)).setText("0");
			((TextView) findViewById(R.id.score_away)).setText("0");

			((TextView) findViewById(R.id.games_home)).setText(Integer
					.toString(currentMatch.getCurrentGames(Player.HOME)));
			((TextView) findViewById(R.id.games_away)).setText(Integer
					.toString(currentMatch.getCurrentGames(Player.AWAY)));

		}

	}

}
