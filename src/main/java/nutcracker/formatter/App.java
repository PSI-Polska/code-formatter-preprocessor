package nutcracker.formatter;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {

	private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		LOGGER.info("Application started with args: ");
		for (String arg : args) {
			LOGGER.info(arg);
		}

		FileContentReader fileContentReader = new FileContentReader();
		FileContentWriter fileContentWriter = new FileContentWriter();
//		Formatter formatter = new DefaultFormatter();
		Formatter formatter = new ReverseFormatter();
		
		List<String> content = fileContentReader.readContent(System.in);
		fileContentWriter.writeContent(formatter.format(content), System.out);

		LOGGER.info("Application finished");
	}

}
