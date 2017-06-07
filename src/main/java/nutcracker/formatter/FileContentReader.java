package nutcracker.formatter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileContentReader {
	private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

	public List<String> readContent(InputStream is) {
		long lastReadTime = System.currentTimeMillis();
		long timeout = 1000;
		BufferedReader bsr = new BufferedReader(new InputStreamReader(is));
		List<String> result = new ArrayList<String>();

		try {
			while (System.currentTimeMillis() - lastReadTime < timeout) {
				if (bsr.ready()) {
					String line = bsr.readLine();
					lastReadTime = System.currentTimeMillis();
					result.add(line);
				}
			}
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		}

		return result;
	}
}
