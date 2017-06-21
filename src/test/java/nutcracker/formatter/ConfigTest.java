package nutcracker.formatter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.File;

import org.junit.Test;

public class ConfigTest {

	@Test
	public void testDefault() {
		Config config = Config.createDefault();

		assertEquals("classpath:/java-formatter.xml", config.getEclipseFormatterConfigFile());
	}

	@Test
	public void testReadFromFileForNonExistingFile() {
		Config config = Config.readFromFile(new File("bogus15002900"));

		assertNull(config);
	}
}
