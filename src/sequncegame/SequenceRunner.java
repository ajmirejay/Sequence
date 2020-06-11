package sequncegame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class SequenceRunner
{

	public static void main(String[] args)
	{
		SequenceRunner runner = new SequenceRunner();
		GameInitialiser game = new GameInitialiser();
		ActionFunctions actionFunction = new ActionFunctions(game);

		ArrayList<String> playerNames = runner.getPlayerNames();
		runner.initialiseGame(playerNames, game);
		actionFunction.displayCardsOfPlayers();
		runner.gameLoop(game, actionFunction);
	}

	private void initialiseGame(ArrayList<String> playerNames, GameInitialiser game)
	{
		game.startGame(playerNames);
	}

	private ArrayList<String> getPlayerNames()
	{
		// TODO Scanner needed here for player names
		Scanner sc = new Scanner(System.in);
		ArrayList<String> playerNames = new ArrayList<String>();
		playerNames.add("P1");
		playerNames.add("P2");
		playerNames.add("P3");
		return playerNames;
	}

	private void gameLoop(GameInitialiser game, ActionFunctions actionFunction)
	{

		Scanner sc = new Scanner(System.in);
		while (true)
		{
			for (String playerName : game.getPlayerNames())
			{

				System.out.println(
						"Name of player -> " + playerName + " and cards :" + game.getPlayersAndCards().get(playerName));
				boolean repeat;
				do
				{
					repeat = true;
					System.out.println("Enter in hand card name ");
					String inhand = sc.nextLine();
					System.out.println("Enter the choice " + "1.Place a chip " + "2.Remove a chip " + " 3.Dead card ");
					int input = sc.nextInt();

					switch (input)
					{
					case 1:
						System.out.println("Enter the index of card on board ");
						int boardIndex = sc.nextInt();
						if (actionFunction.placeChip(inhand, boardIndex))
						{
							int removeFromHandCards = game.getPlayersAndCards().get(playerName).indexOf(inhand);
							// game.getPlayersAndCards().get(playerName).remove(removeFromHandCards);
							game.getPlayersAndCards().get(playerName).set(removeFromHandCards,
									game.genrateRandomCard());
							System.out.println("chip placed ");
							System.out.print(game.getOnBoard()[boardIndex]);
						} else
						{
							System.out.print(game.getOnBoard()[boardIndex]);
							System.out.println(" enter valid card ");
							repeat = false;
						}
						break;
					case 2:
						System.out.println("Enter the index of card on board ");
						int removeIndex = sc.nextInt();
						if (actionFunction.removeChip(inhand, removeIndex))
						{
							System.out.println("chip removed ");
							System.out.print(game.getOnBoard()[removeIndex]);
						} else
						{
							System.out.println("You cannot remove chip with this card ");
							repeat = false;
						}
					case 3:

					}
					if (sc.hasNextLine())
					{
						sc.nextLine();
					}
				} while (!repeat);
			}
		}
	}
}
