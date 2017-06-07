package nutcracker.formatter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Formatter {

	private static final Logger LOGGER = LoggerFactory.getLogger(Formatter.class);

	public static void main(String[] args) {
		LOGGER.info("Application started with args: ");
		for (String arg : args) {
			LOGGER.info(arg);
		}

		readInputFile(System.in);

		LOGGER.info("Application finished");
	}

	private static List<String> readInputFile(InputStream is) {
		long lastReadTime = System.currentTimeMillis();
		long timeout = 1000;
		BufferedReader bsr = new BufferedReader(new InputStreamReader(is));

		try {
			while (System.currentTimeMillis() - lastReadTime < timeout) {
				if (bsr.ready()) {
					String line = bsr.readLine();
					lastReadTime = System.currentTimeMillis();
					System.out.println(line);
				}
			}
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		}

		return null;
	}
}
