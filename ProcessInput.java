import java.lang.IllegalArgumentException;
import java.util.Scanner;
import java.util.ArrayList;
import java.lang.Error;

//import static Bowling.Utils.*;

/** An input processor whose input is an ArrayList
 * of strings gotten from ReadInput. The processor
 * processes each element of the list element by element
 * and then accumulates their values.
 * @author Joshua Goldwasser
 */
class ProcessInput implements ScoreAdder {

	/** An ArrayList of frames that make up a
	 * complete game. */
	ArrayList<String> gameList;

	/** The game's score. */
	private int score;


	/** The current frame. */
	private Frame frame;

	/** The number of frames in a valid game. */
	private static final int GAME_LENGTH = 10;

	/** An input processor that accumulates the score
	 * of a game. */
	ProcessInput(ArrayList<String> game) {
		this.score = 0;
		gameList = game;
	}

	/** Calculates the score of a game after first
	 * verifying that the game is valid. */
	int calculateScore() {
		checkLength(gameList);
		checkFrames(gameList);
		return accumulateScore(gameList);
	}

	/** Adds the worth of the frames in game list G to
	 * get the overall game score. */
	private int accumulateScore(ArrayList<String> G) {
		for (int f = 0; f < G.size(); f++) {
			int frameWorth = 0;
			String thisFrame = G.get(f);
			Frame curr = new Frame(thisFrame);
			if (curr.containsMark()) {
				frameWorth += 10;
				if (curr.containsSpare()) {
					if (f < 9) {
						String nextFrame = G.get(f + 1);
						char nextShot = nextFrame.charAt(0);
						frameWorth += strikeAdder(nextShot);
					} else { // tenth frame
						if (curr.containsStrike()) {
							frameWorth += 10;
						} else {
							char fill = thisFrame.charAt(2);
							frameWorth += num(fill);
						} // end else
					} // end else
				} // end if

				else if (curr.containsStrike()) {
					if (f < 8) {
						String nextFrame = G.get(f + 1);
						Frame next = new Frame(nextFrame);
						String afterNextFrame = G.get(f + 2);
						char afterNextFrameShot1 = afterNextFrame.charAt(0);
						Frame afterNext = new Frame(afterNextFrame);

						if (next.containsStrike()) {
							frameWorth += 10;
							frameWorth += strikeAdder(afterNextFrameShot1);
						} else if (next.containsSpare()) {
							frameWorth += 10;
						} else {
							char firstThrow = nextFrame.charAt(0);
							char secondThrow = nextFrame.charAt(1);
							frameWorth += openFrameAdder(firstThrow, secondThrow);
						}
					} else if (f == 8) { // foundation frame
						String nextFrame = G.get(f + 1);
						char throw1 = nextFrame.charAt(0);
						char throw2 = nextFrame.charAt(1);

						if (throw2 == '/') {
							frameWorth += 10;
						} else {
							frameWorth += strikeAdder(throw1)
								+ strikeAdder(throw2);
						}

					} else { // tenth frame
						char middle = thisFrame.charAt(1);
						char fill = thisFrame.charAt(2);

						if (fill == '/') {
							frameWorth += 10;
						} else {
							frameWorth += strikeAdder(middle)
								+ strikeAdder(fill);
						}
					} //end else
				} // end else if

			} else {
				char firstThrow = thisFrame.charAt(0);
				char secondThrow = thisFrame.charAt(1);
				frameWorth += openFrameAdder(firstThrow, secondThrow);
			}
			// System.out.println(frameWorth);
			score += frameWorth;
		}
		return score;
	}

	/** Adds the numeric values of FIRST and SECOND. */
	public int openFrameAdder(char first, char second) {
		int firstThrowWorth;
		int secondThrowWorth;
		if (first == '-' || first == 'F') {
			firstThrowWorth = 0;
		} else {
			firstThrowWorth = num(first);
		}
		if (second == '-' || second == 'F') {
			secondThrowWorth = 0;
		} else {
			secondThrowWorth = num(second);
		}
		return firstThrowWorth + secondThrowWorth;
	}

	/** Return 10 if character T is a strike.
	Otherwise, returns the numeric value of T. */
	public int strikeAdder(char t) {
		if (t == 'X') {
			return 10;
		} else {
			return num(t);
		}
	}

	/** Adds 10 - the numerical value of PREV to W if CURR
	is '/', otherwise adds the numerical value of CURR to W. */
	private void spareAdder(char curr, char prev, int w) {
		if (curr == '/') {
			w += 10 - num(prev);
		} else {
			w += num(prev);
		}
	}

	/** Returns the numeric value of CHARACTER. */
	public int num(char character) {
		return Character.getNumericValue(character);
	}


	/** ===Internal methods=== */

	/** Makes sure the game list FRAMES is the right length. */
	private void checkLength(ArrayList<String> frames) {
		int size = frames.size();
		if (size < GAME_LENGTH) {
			throw err("Too few frames. A game must have %d frames.", GAME_LENGTH);
			//System.err.println(String.format("Too few frames. A game must have %d frames.", GAME_LENGTH));
		}
		if (size > GAME_LENGTH) {
			throw err("Too many frames. A game must have %d frames.", GAME_LENGTH);
			//System.err.println(String.format("Too many frames. A game must have %d frames.", GAME_LENGTH));
		}
	}

	/** Checks that the elements of the throw
	list THROWLIST are the right length and format. */
	private void checkFrames(ArrayList<String> list) {
		int gameSize = list.size();
		for (int i = 0; i < gameSize - 1; i++) {
			String currentFrame = list.get(i);
			Frame f = new Frame(currentFrame);
			int frameSize = currentFrame.length();

			if (!f.testFrameStrike() && !f.testFrameSpare()
				&& !f.testOpenFrame()) {
				throw err("Invalid frame. Frame number: %d", i + 1);
			} //end if
		} //end for

		String lastFrame = list.get(gameSize - 1);
		Frame last = new Frame(lastFrame);
		int frameSize = lastFrame.length();

		if (last.testTenthFrame()) {
			if (!last.middleChar() && !last.validFillShot()) {
				throw err("Invalid tenth frame: invalid fill shot.");
			}
			//count score
		} else if (last.testOpenFrame()) {
			//count score
		} else {
			throw err("Invalid tenth frame.");
		}
	}

	static IllegalArgumentException err(String msg, Object... args) {
		return new IllegalArgumentException(String.format(msg, args));
	}

}