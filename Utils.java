package Bowling;

import java.lang.IllegalArgumentException;

public class Utils {

	static IllegalArgumentException err(String msg, Object... args) {
		return new IllegalArgumentException(String.format(msg, args));
	}

}