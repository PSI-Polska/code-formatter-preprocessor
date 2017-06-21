package nutcracker.formatter;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Config extends Properties {
	private static final long serialVersionUID = 4379957287725630286L;

	private static final Logger LOGGER = LoggerFactory.getLogger(Config.class);

	private final static String ECLIPSE_FORMATTER_CONFIG_FILE = "eclipse.formatter.config.file";

	private Config() {

	}

	public String getEclipseFormatterConfigFile() {
		return this.getProperty(ECLIPSE_FORMATTER_CONFIG_FILE, EclipseFormatter.DEFAULT_FORMATTER_FILE);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Object key : this.keySet()) {
			sb.append(key.toString());
			sb.append(" = ");
			sb.append(get(key));
			sb.append("\n\r");
		}

		return sb.toString();
	}

	public static Config createDefault() {
		return new Config();
	}

	public static Config readFromFile(File file) {
		try {
			FileInputStream fis = new FileInputStream(file.getAbsolutePath());
			Config config = new Config();
			config.load(fis);
			fis.close();
			return config;
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);

			return null;
		}
	}
}
