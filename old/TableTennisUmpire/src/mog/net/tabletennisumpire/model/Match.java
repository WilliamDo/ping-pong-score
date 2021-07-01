package mog.net.tabletennisumpire.model;

import java.util.ArrayList;
import java.util.List;


public class Match {
	
	public enum Player {
		HOME, AWAY
	}
	
	private final int maxNoOfGames;
	
	private final ArrayList<Game> games;
	
	private Game currentGame;
	
	public Match(int maxNumberOfGames) {
		this.maxNoOfGames = maxNumberOfGames;
		games = new ArrayList<Game>(maxNoOfGames);
		startNextGame();
	}
	
	public void addPoint(Player player) {
		if (!isMatchOver() && !currentGame.isGameOver()) {
			currentGame.addPoint(player);
		}
	}
	
	public void deductPoint(Player player) {
		if (!isMatchOver()) {
			currentGame.deductPoint(player);
		}
	}
	
	public boolean startNextGame() {
		if (!isMatchOver()) {
			if (currentGame == null || currentGame.isGameOver()) {
				currentGame = new Game();
				games.add(currentGame);
				return true;
			}
		}
		return false;
	}
	
	public boolean isMatchOver() {
		int gamesWonByHome = 0;
		int gamesWonByAway = 0;
		
		for (Game playedGame : games) {
			if (playedGame != null && playedGame.isGameOver()) {
				Player winner = playedGame.getWinner();
				if (winner == Player.HOME) {
					gamesWonByHome++;
				} else if (winner == Player.AWAY) {
					gamesWonByAway++;
				}
			}
		}
		
		return gamesWonByHome == gamesToWin()
				|| gamesWonByAway == gamesToWin();
	}
	
	private int gamesToWin() {
		return (maxNoOfGames / 2) + 1;
	}
	
	public int getCurrentPoints(Player player) {
		return currentGame.getCurrentPoints(player);
	}
	
	public List<Game> getGames() {
		return games;
	}
	
	public int getCurrentGames(Player player) {
		int gamesWon = 0;
		
		for (Game g : games) {
			if (g.getWinner() == player) {
				gamesWon++;
			}
		}
		
		return gamesWon;
	}

}
