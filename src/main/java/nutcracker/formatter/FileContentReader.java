package nutcracker.formatter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileContentReader {
	private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

	public String readContent(InputStream is) {
		long lastReadTime = System.currentTimeMillis();
		long timeout = 1000;
		BufferedReader bsr = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		try {
			while (System.currentTimeMillis() - lastReadTime < timeout) {
				if (bsr.ready()) {
					String line = bsr.readLine();
					lastReadTime = System.currentTimeMillis();
					sb.append(line);
					sb.append("\n");
				}
			}
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		}

		return sb.toString();
	}
}
