package sequncegame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class GameInitialiser
{
	private static final String[] suits = { "H", "S", "C", "D" };
	private static final String[] values = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };
	public static final String[] board = { "WD", "2S", "3S", "4S", "5S", "6S", "7S", "8S", "9S", "WD", "6C", "5C", "4C",
			"3C", "2C", "AH", "KH", "QH", "10H", "10S", "7C", "AS", "2D", "3D", "4D", "5D", "6D", "7D", "9H", "QS",
			"8C", "KS", "6C", "5C", "4C", "3C", "2C", "8D", "8H", "KS", "9C", "QS", "7C", "6H", "5H", "4H", "AH", "9D",
			"7H", "AS", "10C", "10S", "8C", "7H", "2H", "3H", "KH", "10D", "6H", "2D", "QC", "9S", "9C", "8H", "9H",
			"10H", "QH", "QD", "5H", "3D", "KC", "8S", "10C", "QC", "KC", "AC", "AD", "KD", "4H", "4D", "AC", "7S",
			"6S", "5S", "4S", "3S", "2S", "2H", "3H", "5D", "WD", "AD", "KD", "QD", "10D", "9D", "8D", "7D", "6D",
			"WD" };
	private static final HashMap<Integer, Integer> playersToCardsMap = new HashMap<Integer, Integer>() {
		{
			put(2, 7);
			put(3, 6);
			put(4, 6);
			put(6, 3);
			put(8, 3);
			put(9, 3);
			put(10, 3);
			put(12, 2);
		}
	};

	private ArrayList<String> deck = new ArrayList<String>();
	private HashMap<String, ArrayList<String>> playersAndCards = new HashMap<String, ArrayList<String>>();
	private ArrayList<String> playerNames;

	private boolean[] onBoard = new boolean[100];

	public GameInitialiser()
	{

	}

	protected void startGame(ArrayList<String> playerNames)
	{
		this.playerNames = playerNames;
		int numberOfPlayers = playerNames.size();
		initialiseDeck();
		if (validatePlayerCount(numberOfPlayers))
		{
			for (String playerName : playerNames)
			{
				ArrayList<String> distributedCards = distributeCards(numberOfPlayers);
				playersAndCards.put(playerName, distributedCards);

			}
			initialiseBaord();
			displayBoard(board);
		} else
		{
			System.out.println("enter valid number of players ");
			System.exit(1);
		}
	}

	private void initialiseDeck()
	{
		for (int k = 0; k < 2; k++)
		{
			for (int i = 0; i < suits.length; i++)
			{
				for (int j = 0; j < values.length; j++)
					deck.add(values[j] + suits[i]);
			}
		}
		Collections.shuffle(this.deck);
	}

	private boolean validatePlayerCount(int numberOfPlayers)
	{
		if ((numberOfPlayers >= 2 || numberOfPlayers <= 12) && (numberOfPlayers % 2 == 0 || numberOfPlayers % 3 == 0))
		{
			return true;
		}
		return false;
	}

	private void initialiseBaord()
	{
		for (int i = 0; i < 100; i++)
		{
			this.onBoard[i] = false;
		}
	}

	private ArrayList<String> distributeCards(int numberOfPlayers)
	{
		ArrayList<String> inHandCards = new ArrayList<String>();

		for (int i = 0; i < playersToCardsMap.get(numberOfPlayers); i++)
		{
			inHandCards.add(genrateRandomCard());
		}

		return inHandCards;
	}

	protected String genrateRandomCard()
	{
		String genratedCard = null;
		Random random = new Random();
		if (!this.deck.isEmpty())
		{
			genratedCard = deck.get(random.nextInt(deck.size()));
			deck.remove(genratedCard);
		}
		return genratedCard;
	}

	void displayBoard(String[] board)
	{
		for (int i = 0; i < board.length; i++)
		{
			if (i % 10 == 0 && i > 0)
			{
				System.out.println();
			}
			System.out.print(board[i] + " ");
		}
		System.out.println();
	}

	public HashMap<String, ArrayList<String>> getPlayersAndCards()
	{
		return playersAndCards;
	}

	public ArrayList<String> getPlayerNames()
	{
		return playerNames;
	}

	public boolean[] getOnBoard()
	{
		return onBoard;
	}

}
