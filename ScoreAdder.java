public interface ScoreAdder {
	public abstract int num(char character);

	public abstract int strikeAdder(char t);

	public abstract int openFrameAdder(char first, char second);
}