package nutcracker.formatter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ReverseFormatter implements Formatter {

	@Override
	public String format(String content) {
		List<String> result = Arrays.asList(content.split("\\r?\\n"));

		Collections.reverse(result);

		StringBuilder sb = new StringBuilder();
		for (String line : result) {
			sb.append(line);
			sb.append("\n");
		}

		return sb.toString();
	}
}
