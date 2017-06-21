package nutcracker.formatter;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugin.logging.SystemStreamLog;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.text.BadLocationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import com.google.common.base.Charsets;
import com.marvinformatics.formatter.ConfigurationSource;
import com.marvinformatics.formatter.LineEnding;
import com.marvinformatics.formatter.java.JavaFormatter;
import com.marvinformatics.formatter.model.ConfigReadException;
import com.marvinformatics.formatter.model.ConfigReader;
import com.marvinformatics.formatter.support.io.Resource;
import com.marvinformatics.formatter.support.io.Resource.UnknownResourceException;

public class EclipseFormatter implements Formatter {
	private static final Logger LOGGER = LoggerFactory.getLogger(EclipseFormatter.class);

	public final static String DEFAULT_FORMATTER_FILE = "classpath:/java-formatter.xml";

	private JavaFormatter javaFormatter;

	public EclipseFormatter() {
		javaFormatter = new JavaFormatter(getFormattingOptions(DEFAULT_FORMATTER_FILE),
				getDefaultConfigurationSource());
	}

	public EclipseFormatter(String formatterPath) {
		javaFormatter = new JavaFormatter(getFormattingOptions(formatterPath), getDefaultConfigurationSource());
	}

	@Override
	public String format(String content) {
		try {
			return javaFormatter.doFormat(content);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (BadLocationException e) {
			LOGGER.error(e.getMessage(), e);
		}

		return null;
	}

	private Map<String, String> getFormattingOptions(String cfgFile) {
		if (cfgFile == null)
			return new HashMap<String, String>();

		try {
			return getOptionsFromConfigFile(Resource.forPath(cfgFile));
		} catch (Resource.UnknownResourceException e) {
			throw new IllegalStateException("Error loading Java config", e);
		}
	}

	private Map<String, String> getOptionsFromConfigFile(Resource configFile) {

		try (InputStream configInput = configFile.asInputStream();) {
			ConfigReader configReader = new ConfigReader();
			return configReader.read(configInput);
		} catch (IOException | SAXException e) {
			throw new IllegalArgumentException("Cannot parse config file [" + configFile + "]", e);
		} catch (ConfigReadException | UnknownResourceException e) {
			throw new IllegalStateException(e.getMessage(), e);
		}
	}

	private ConfigurationSource getDefaultConfigurationSource() {
		return new ConfigurationSource() {

			public File getTargetDirectory() {
				return new File(System.getProperty("java.io.tmpdir"));
			}

			public Log getLog() {
				return new SystemStreamLog();
			}

			public Charset getEncoding() {
				return Charsets.UTF_8;
			}

			public String getCompilerSources() {
				return JavaCore.VERSION_9;
			}

			public String getCompilerCompliance() {
				return JavaCore.VERSION_9;
			}

			public String getCompilerCodegenTargetPlatform() {
				return JavaCore.VERSION_9;
			}

			@Override
			public LineEnding lineEnding() {
				return LineEnding.LF;
			}

			@Override
			public boolean isDryRun() {
				return false;
			}
		};
	}
}
