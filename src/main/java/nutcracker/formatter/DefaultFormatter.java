package nutcracker.formatter;

import java.util.List;

public class DefaultFormatter implements Formatter {

	@Override
	public List<String> format(List<String> content) {
		return content;
	}

}
