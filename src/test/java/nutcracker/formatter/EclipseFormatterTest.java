package nutcracker.formatter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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

		String formattedCode = formatter.format("public class \n Test{\n}");

		assertEquals("public class Test {\n}", formattedCode);
	}

	@Test
	public void testDefaultFormatterResource() throws UnknownResourceException {
		Resource resource = Resource.forPath(EclipseFormatter.DEFAULT_FORMATTER_FILE);

		assertNotNull(resource.asInputStream());
	}
}
