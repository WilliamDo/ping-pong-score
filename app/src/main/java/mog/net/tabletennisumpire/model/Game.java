package mog.net.tabletennisumpire.model;

import mog.net.tabletennisumpire.model.Match.Player;

public class Game {

	private static final int GAME_UP = 11;
	private static final int GAME_CLEAR = 2;
	private int scoreHome;
	private int scoreAway;

	public Game() {
		scoreHome = 0;
		scoreAway = 0;
	}

	public void addPoint(Player player) {
		if (!isGameOver()) {
			if (player == Player.HOME) {
				scoreHome++;
			} else if (player == Player.AWAY) {
				scoreAway++;
			}
		}
	}

	public boolean isGameOver() {
		return (scoreHome >= GAME_UP && scoreHome - scoreAway >= GAME_CLEAR)
				|| (scoreAway >= GAME_UP && scoreAway - scoreHome >= GAME_CLEAR);
	}

	public Player getWinner() {
		if (isGameOver()) {
			return scoreHome > scoreAway ? Player.HOME : Player.AWAY;
		}

		return null;
	}

	public int getCurrentPoints(Player player) {
		if (player == Player.HOME) {
			return scoreHome;
		} else if (player == Player.AWAY) {
			return scoreAway;
		} else {
			return -1;
		}
	}

	public void deductPoint(Player player) {
		if (!isGameOver() || player == getWinner()) {
			if (player == Player.HOME && scoreHome != 0) {
				scoreHome--;
			} else if (player == Player.AWAY && scoreAway != 0) {
				scoreAway--;
			}
		}

	}

}
