package gabriel.util;

import java.util.Random;

public class UUIDGen {

	public static Long getUUID() {
		return new Random().nextLong();
	}

}
