package sequncegame;

import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class ActionFunctions
{
	private GameInitialiser game;

	public ActionFunctions(GameInitialiser game)
	{
		this.game = game;
	}

	protected boolean placeChip(String inhand, int boardIndex)
	{
		if ((inhand.equals(GameInitialiser.board[boardIndex]) || inhand == "JH" || inhand == "JS")
				&& (game.getOnBoard()[boardIndex] == false))
		{
			game.getOnBoard()[boardIndex] = true;
			return true;
		}
		return false;
	}

	protected boolean removeChip(String cardValue, int boardIndex)
	{
		if ((cardValue == "JD" || cardValue == "JC") && game.getOnBoard()[boardIndex] == true)
		{
			game.getOnBoard()[boardIndex] = false;
			return true;
		}
		return false;
	}

	protected void displayCardsOfPlayers()
	{

		for (String playerName : game.getPlayerNames())
		{
			System.out.println(playerName + " " + game.getPlayersAndCards().get(playerName));
		}
	}

	protected Boolean deadCard(String inhand)
	{

		return false;
	}

}
