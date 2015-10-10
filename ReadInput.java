import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

/** A class that reads user input in the form of a
 * complete bowling game and maps it into an ArrayList<<>>
 * and then flattens than into an ArrayList<>.
 *	@author Joshua Goldwasser
*/

class ReadInput {

	private Scanner input;

	private int frame;

	private int counter;

	ReadInput(Scanner input) {
		this.input = input;
		this.frame = 1;
		this.counter = 0;
	}

	ArrayList<ArrayList<String>> readInputFrames() {
		ArrayList<ArrayList<String>> L = new ArrayList<ArrayList<String>>();
		String line = getCommand();
		if (line == null) {
			return null;
		}
		Scanner inp = new Scanner(line);
		while (inp.hasNext()) {
			String curr = inp.next();
			L.add(new ArrayList<String>());
			L.get(frame - 1).add(curr);
			nextFrame();
		} //end while
		return L;
	} // end method

	ArrayList<String> readInputThrows(ArrayList<ArrayList<String>> list) {
		ArrayList<String> result = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < list.get(i).size(); j++) {
				result.add(list.get(i).get(j));
			}
		}
		return result;
	}

	/** Taken from ~/canfield/TextPlayer.java
	 * written by P. N. Hilfinger. */
	private String getCommand() {
        while (true) {
            System.err.print("> ");
            System.err.flush();
            if (!input.hasNext()) {
                break;
            }
            String line = input.nextLine();
            line = line.trim();
            if (!line.isEmpty()) {
                return line;
            }
        }
        return null;
	}

	private void nextFrame() {
		frame++;
	}

	private void increment() {
		counter++;
	}
}