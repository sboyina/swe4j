package org.swe4j.phantom;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.LogManager;

import org.junit.Assert;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(value = BlockJUnit4ClassRunner.class)
public class ExporterTestBase {

	static {
		try {
			Properties properties = new Properties();
			properties.load(ClassLoader
					.getSystemResourceAsStream("test.properties"));
			System.setProperty("webexporter.phantomjs.path",
					getResourcePath(properties
							.getProperty("phantomjs.exe.path")));
			LogManager.getLogManager().readConfiguration(
					new FileInputStream(
							getResourcePath("classpath:logging.properties")));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static String getResourcePath(String path) {
		String classPathStr = "classpath:";
		if (path.startsWith(classPathStr)) {
			return new File(ClassLoader.getSystemResource(
					path.substring(classPathStr.length())).getFile())
					.getAbsolutePath();
		}
		return path;
	}

	public static File getTestFile() {
		return new File(getResourcePath("classpath:html/webshot.html"));
	}

	protected void assertExistence(File outputFile) {
		Assert.assertTrue(outputFile != null && outputFile.exists()
				&& outputFile.length() > 0);
	}
}