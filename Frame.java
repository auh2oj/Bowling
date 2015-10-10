public class Frame {

	private String f;

	Frame(String frame) {
		f = frame;
	}


	/** Returns the length of a frame F, i.e., the
	number of characters it contains. */
	private int length() {
		return f.length();
	}

	/** ===Internal boolean methods== */

	/** Returns True if a frame F contains a 'X'. */
	boolean containsStrike() {
		return f.contains("X");
	}

	/** Returns True if a frame F contains a '/'. */
	boolean containsSpare() {
		return f.contains("/");
	}

	/** Returns True if a frame F contains a mark. */
	boolean containsMark() {
		return (containsStrike() || containsSpare());
	}

	/** Returns True if a frame F starts with a '/'. */
	private boolean startsWithSpare() {
		return f.startsWith("/");
	}

	/** Returns True if the middle character of the 10th frame F
	is a 'X' or a '/'. */
	boolean middleChar() {
		return (f.charAt(1) == 'X' || f.charAt(1) == '/');
	}

	/** Returns True if the frame F is equal to 3. Use only
	for the 10th frame. */
	private boolean frameTenLength() {
		return length() == 3;
	}

	/** Returns True if the length of frame F is equal to 2. Do not use
	in the 10th frame unless containsStrike and containsSpare
	return False. */
	private boolean frameLength() {
		return length() == 2;
	}

	/** Returns True if the length of frame F is equal to 1. Do
	not use in the 10th frame and use only if containsStrike returns
	True. */
	private boolean frameLengthStrike() {
		return length() == 1;
	}

	/** Returns True if the fill shot of the 10th frame F
	is valid, i.e., if its value is less than 10 minus 
	the value of the previous shot. Use iff middleChar
	returns False. */
	boolean validFillShot() {
		char fillShot = f.charAt(2);
		int fillShotValue = Character.getNumericValue(fillShot);
		char middleShot = f.charAt(1);
		int middleShotValue = Character.getNumericValue(middleShot);
		return (fillShotValue < 10 - middleShotValue);
	}

	/** Returns True if a frame F worth is less than 10.
	Do not use in 10th frame unless containsStrike and containsSpare
	return false. */
	private boolean validOpen() {
		char firstShot = f.charAt(0);
		char secondShot = f.charAt(1);
		int firstShotValue = Character.getNumericValue(firstShot);
		int secondShotValue = Character.getNumericValue(secondShot);
		return (firstShotValue + secondShotValue < 10);
	}

	/** == Overarching boolean methods. == */

	/** Returns True if the frame F passes all the above 
	relevant boolean tests. Use only in 10th frame. */
	boolean testTenthFrame() {
		return (containsMark() && !startsWithSpare()
			&& frameTenLength());
	}

	/** Returns True if the frame F passes all the above
	relevant boolean tests. Do not use in 10th frame unless
	containsMark returns False. */
	boolean testOpenFrame() {
		return (!containsMark() && frameLength()
			&& validOpen());
	}

	/** Returns True if the frame F contains a Strike
	and	is a valid frame. */
	boolean testFrameStrike() {
		return (containsStrike() && frameLengthStrike());
	}

	/** Returns True if the frame F contains a Spare
	and is a valid frame. */
	boolean testFrameSpare() {
		return (containsSpare() && !startsWithSpare()
			&& frameLength());
	}

}