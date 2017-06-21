package nutcracker.formatter;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
	private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

	private final static String FORMATTER_HOME_DIR_NAME = ".formatter";
	private final static String FORMATTER_DEFAULT_FILE_NAME = "config.properties";

	public static void main(String[] args) {
		Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
			public void uncaughtException(Thread t, Throwable e) {
				LOGGER.error(e.getMessage(), e);
				LOGGER.error("Application will exit with error code 1.");
				System.exit(1);
			}
		});

		LOGGER.info("Application started with args: ");
		for (String arg : args) {
			LOGGER.info(arg);
		}

		Config config = getConfig();

		FileContentReader fileContentReader = new FileContentReader();
		FileContentWriter fileContentWriter = new FileContentWriter();
		// Formatter formatter = new DefaultFormatter();
		// Formatter formatter = new ReverseFormatter();

		// Formatter formatter = new EclipseFormatter();
		Formatter formatter = new EclipseFormatter(config.getEclipseFormatterConfigFile());

		String content = fileContentReader.readContent(System.in);
		fileContentWriter.writeContent(formatter.format(content), System.out);

		LOGGER.info("Application finished");
	}

	private final static Config getConfig() {
		String configFile = System.getProperty("user.home") + "/" + FORMATTER_HOME_DIR_NAME + "/"
				+ FORMATTER_DEFAULT_FILE_NAME;

		Config config = Config.readFromFile(new File(configFile));

		if (config != null) {
			LOGGER.info(String.format("Successfuly read configuration from %s:", configFile));
			LOGGER.info(config.toString());

			return config;
		}

		LOGGER.warn(String.format("Error while read configuration from %s", configFile));
		LOGGER.info("Creating a default configuration.");

		return Config.createDefault();
	}
}
