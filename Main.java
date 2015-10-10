import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String... args) {
		Main main = new Main();
		main.run();
	}

	void run() {
        System.out.println("Welcome to the bowling "
		+ "score calculator. Please enter a complete "
		+ "game with the frames separated by spaces.");
		while (true) {
			Scanner input = new Scanner(System.in);
			ReadInput read = new ReadInput(input);
			ArrayList<ArrayList<String>> frames = read.readInputFrames();
			ArrayList<String> ballThrows = read.readInputThrows(frames);
			//System.out.println(frames);
			//System.out.println(ballThrows);
			ProcessInput processor = new ProcessInput(ballThrows);
			int finalScore = processor.calculateScore();
			System.out.println(finalScore);
		}
	}
}