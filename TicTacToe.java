import java.util.Scanner;

public class TicTacToe {
	
	
	private Player player1, player2;
	private Board board;
	
	// we cannot make board until we make players as it needs player symbols

	public void startGame() {
		Scanner s = new Scanner(System.in);
		// Player Input
		player1 = takePlayerInput(1);
		player2 = takePlayerInput(2);
		// to check if the symbols of the two players are same, but it might happen that this occurs repetitively so do this until it is finally appropriate
		while(player1.getSymbol() == player2.getSymbol()) {
			System.out.println("Symbol Already taken !! Pick another symbol !!");
			char symbol = s.next().charAt(0);
			player2.setSymbol(symbol);
			
		}
		// Create Board
		board = new Board(player1.getSymbol(), player2.getSymbol());
		
		// Conduct the game
		// we need to remember who's turn is it and do this one by one
		// how to manage turn? 
		// indicator to check which player's turn is it now
		boolean player1Turn = true; // if it's player1's turn, false if player2's turn
		// the player has to tell the coordinates to play its turn
		int status = Board.INCOMPLETE;
		while(status == Board.INCOMPLETE || status == Board.INVALID) { 
			if(player1Turn) {
				System.out.println("Player 1 - " + player1.getName() + "'s turn");
				System.out.println("Enter x: ");
				int x = s.nextInt();
				System.out.println("Enter y: ");
				int y = s.nextInt();
				
				// check if coordinates are valid or not
				// using move function we can know - if valid then it will put the value and if invalid it will prompt that cannot play at this position and will also return the status of board i.e whether incomplete or not or if any player has won or draw etc
				// conventions
				// 1- p1 won, 2 - p2 won , 3 - draw 4 - incomplete board , 5-  invalid move
				status = board.move(player1.getSymbol(), x, y);
				if(status != Board.INVALID) {
					// x and y can be out of bound or already occupied
					// we should not turn the flag false incase the status is invalid so no work to do 
					player1Turn = false;
					board.print();
				}
				else  {
					System.out.println("Invalid Move !! Try Again !!");
				}
				
			}
			else {
				System.out.println("Player 2 - " + player2.getName() + "'s turn");
				System.out.println("Enter x: ");
				int x = s.nextInt();
				System.out.println("Enter y: ");
				int y = s.nextInt();
				status = board.move(player2.getSymbol(), x, y);
				if(status != Board.INVALID) {

				
					player1Turn = true;
					board.print();
				}
				else  {
					System.out.println("Invalid Move !! Try Again !!");
				}
				
				
			}
			
		}
		// after coming out of while loop we have to conclude
		if(status == Board.PLAYER_1_WINS) {
			System.out.println("Player 1 - " + player1.getName() + " wins !!");
		}
		else if(status == Board.PLAYER_2_WINS) {
			System.out.println("Player 2 - " + player2.getName() + " wins !!");
		}
		else {
			System.out.println("Draw !! ");
		}
	}
	
	
	// num = taking input of which player ? 
	private Player takePlayerInput(int num) {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter Player " + num + " name: ");
		String name = s.nextLine();
		System.out.println("Enter Player " + num + " symbol: ");
		char symbol = s.next().charAt(0);
		Player p = new Player(name, symbol);
		return p;
	}

}
