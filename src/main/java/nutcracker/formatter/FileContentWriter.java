package nutcracker.formatter;

import java.io.PrintStream;
import java.util.List;

public class FileContentWriter {

	public void writeContent(List<String> content, PrintStream os) {
		for (String line : content) {
			os.println(line);
		}
	}
}
