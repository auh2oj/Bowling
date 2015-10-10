import java.util.Scanner;

/** A class that reads and processes user input in the form of a
 * complete bowling game.
 *	@author Joshua Goldwasser
*/

class ReadInput {

	private Scanner input;

	private int frame;

	private int counter;

	private int score;

	ReadInput(String input) {
		this.input = input;
		this.frame = 1;
		this.counter = 0;
	}

	public static List<Character> readScore(String input) {
		ArrayList<Character> L = new ArrayList<Character>();

		Scanner s = new Scanner(input);
	}


	/** Converts user input INP to a string and returns that string. */
	private static List<Character> readInput(Scanner inp) {
		if (!inp.hasNext()) {
			return null;
		}
		ArrayList<Character> L = new ArrayList<Character>();
		while (inp.hasNext()) {
			L.add(inp.next());
		}
		return L;
	}

	private static void nextFrame() {
		frame++;
	}

	// public static void main(String[] ignored) {
	// 	Scanner inp = new Scanner(System.in);

	// 	while (true) {
	// 		System.out.print("> ");
	// 		ArrayList<Character> list = readInput(inp);
	// 		char curr = list.get(counter);
	// 		char next = list.get(counter + 1);
	// 		char afterNext = list.get(counter + 2);
	// 		if (str == null) {
	// 			break;
	// 		}
	// 		if (curr == ' ' || curr == '|') {
	// 			nextFrame();
	// 		} else if (curr == 'X' && frame < 10) {
	// 			score += BowlingScore.strike(curr, next, afterNext);
	// 		}
	// 	}
	// }
}