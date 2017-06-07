package nutcracker.formatter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReverseFormatter implements Formatter {

	@Override
	public List<String> format(List<String> content) {
		List<String> result = new ArrayList<String>(content);

		Collections.reverse(result);

		return result;
	}
}
