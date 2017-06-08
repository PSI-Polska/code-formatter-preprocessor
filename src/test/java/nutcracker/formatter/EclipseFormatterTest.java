package nutcracker.formatter;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class EclipseFormatterTest {

	@Test
	public void testDefaultConstructor() {
		new EclipseFormatter();
	}

	@Test
	public void testDefaultDoFormat() {
		EclipseFormatter formatter = new EclipseFormatter();

		List<String> code = new ArrayList<String>();
		code.add("public class ");
		code.add("Test{}");

		String formattedCode = formatter.format2(code);

		assertEquals("public class Test {\n}\n", formattedCode);
	}
}
