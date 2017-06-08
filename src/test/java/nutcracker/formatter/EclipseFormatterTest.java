package nutcracker.formatter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.marvinformatics.formatter.support.io.Resource;
import com.marvinformatics.formatter.support.io.Resource.UnknownResourceException;

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

	@Test
	public void testDefaultFormatterResource() throws UnknownResourceException {
		Resource resource = Resource.forPath(EclipseFormatter.DEFAULT_FORMATTER_FILE);

		assertNotNull(resource.asInputStream());
	}
}
