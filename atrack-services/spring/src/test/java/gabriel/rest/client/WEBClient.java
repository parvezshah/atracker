package gabriel.rest.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.codehaus.jackson.map.ObjectMapper;

public class WEBClient {

	private final String baseURL;

	public WEBClient(String baseURL) {
		this.baseURL = baseURL;
	}

	public static WEBClient getInstance(String baseUrl) {
		return new WEBClient(baseUrl);
	}

	public BufferedReader postJson(String res, Object toSend)
			throws IOException {
		URL url = null;
		try {
			url = new URL(baseURL + res);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json");

		ObjectMapper mapper2 = new ObjectMapper(); // can reuse, share globally

		String serializ = mapper2.writeValueAsString(toSend);
		System.out.println("AAAA" + serializ);
		OutputStream os = conn.getOutputStream();
		os.write(serializ.getBytes());
		os.flush();

		if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));

		return br;

	}

}
