package gabriel.atrack;

import java.io.IOException;

import gabriel.atrack.dto.Activity;
import gabriel.rest.client.WEBClient;

public class TestWebUsecase {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		WEBClient client = WEBClient.getInstance("http://localhost:8080/spring/");
		
		Activity jog = new Activity();
		jog.setActivityCode(1L);
		jog.setOwner(1L);
		jog.setData("someString", "stringValue");
		jog.setData("someLong", 5000L);
		client.postJson("activity/create.json",jog);

	}

}
