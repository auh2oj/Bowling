import java.util.Character;

/** A class that calculates the score of a bowling game.
 *	@author Joshua Goldwasser
*/

static class BowlingScore {

	int _curr;

	int _next;

	int _afterNext;

	public int strike(char curr, char next, char afterNext) {
		_curr = 10;

		if (next == 'X') {
			_next = 10;
		} else {
			_next = Character.getNumericalValue(next);
		}

		if (afterNext == '/' || afterNext == 'X') {
			_afterNext = 10
		} else {
			_afterNext = Character.getNumericalValue(afterNext);
		}

		return _curr + _next + _afterNext;
	}

	public int spare(int curr, int next) {
		return curr + next;
	}
}