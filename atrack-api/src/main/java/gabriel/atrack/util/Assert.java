package gabriel.atrack.util;

public abstract class Assert {

	public static <T> T notNull(T t, String message) {
		if (t == null) {
			throw new IllegalArgumentException(message);
		}
		return t;
	}

	public static String notNullString(String string, String message) {
		notNull(string, message);
		string = string.trim();
		if (string.length() == 0)
			throw new IllegalArgumentException(message);
		return string;
	}

	public static String notNullLC(String string, String message) {
		notNull(string, message);
		return string.trim().toLowerCase();
	}

	public static <T> void isNull(T t, String message) {
		if (t != null) {
			throw new IllegalArgumentException(message);
		}

	}
}
