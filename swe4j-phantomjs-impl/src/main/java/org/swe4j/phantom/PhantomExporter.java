/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *  http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.swe4j.phantom;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.swe4j.ExportException;

/**
 * <p>
 * {@link PhantomExporter} is the base class that holds all the logic to
 * <ul>
 * <li>invoke the phantom js with arguments</li>
 * <li>log the process output</li>
 * <li>terminate the process if it exceeds the set-timeout.</li>
 * </ul>
 * </p>
 * 
 * @author sboyina
 * 
 */
public abstract class PhantomExporter {

	public static final Logger LOGGER = Logger.getLogger(PhantomExporter.class
			.getName());

	private static final String PAHNTOM_JS_EXE_PATH;
	private static final String WEBEXPORTER_JS;

	static {
		PAHNTOM_JS_EXE_PATH = System.getProperty("webexporter.phantomjs.path");
		WEBEXPORTER_JS = new File(ClassLoader.getSystemResource(
				"js/webexporter-phantom.js").getFile()).getAbsolutePath();
	}
	/*
	 * After this time, export operation will commence. This time will be
	 * helpful for the engine to run javascript, flash component etc.,. Based on
	 * the need adjust this setting.
	 */
	private int renderTime = 5000;
	private String typeExtension = null;
	private int zoomFactor = 1;
	private int maxExportTimeAlowed = 30000;// 30 seconds

	/**
	 * @param typeExtension
	 *            represents the type extension used the by this exporter.
	 */
	public PhantomExporter(String typeExtension) {
		this.typeExtension = typeExtension;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.webexporter.Exporter#export(java.net.URL)
	 */
	public File export(URL url, Map<String, String> optionMap)
			throws ExportException {
		try {
			File outputFile = getTempFile(this.typeExtension);
			List<String> command = getCommand(url, outputFile, optionMap);
			BlockingProcess bProcess = new BlockingProcess("PHANOTMJS",
					command, LOGGER, maxExportTimeAlowed);
			Process process = bProcess.start();
			if (process.exitValue() != 0) {
				throw new ExportException("Failed to export.");
			}
			return outputFile;
		} catch (Exception e) {
			throw new ExportException("Failed to capture, due to", e);
		}
	}

	private List<String> getCommand(URL url, File outputFile,
			Map<String, String> optionMap) {
		List<String> command = new LinkedList<String>();
		Map<String, String> optionsMap = this.getOptionsMap(optionMap);
		command.add(bindWithQuotes(PAHNTOM_JS_EXE_PATH));
		command.add(bindWithQuotes(WEBEXPORTER_JS));
		command.add(bindWithQuotes(url.toString()));
		command.add(bindWithQuotes(outputFile.getAbsolutePath()));
		command.add(bindWithQuotes(toString(optionsMap)));
		return command;
	}

	private String bindWithQuotes(String input) {
		return "\"" + input + "\"";
	}

	/**
	 * @return the maximum time allowed to run this process.
	 */
	public int getMaxExportTimeAlowed() {
		return maxExportTimeAlowed;
	}

	/**
	 * @param maxExportTimeAlowed
	 *            maximum time allowed for the phantom process to run.
	 */
	public void setMaxExportTimeAlowed(int maxExportTimeAlowed) {
		this.maxExportTimeAlowed = maxExportTimeAlowed;
	}

	/**
	 * @return the time to wait for the webpage render.
	 */
	public int getRenderTime() {
		return renderTime;
	}

	/**
	 * @param renderTime
	 *            the time to wait for the webpage render.
	 */
	public void setRenderTime(int renderTime) {
		this.renderTime = renderTime;
	}

	/**
	 * @return a map of options to be use to invoke the converter js
	 */
	protected Map<String, String> getOptionsMap(
			Map<String, String> originalOptionMap) {
		Map<String, String> optionMap = new HashMap<String, String>();
		optionMap.put("render-time", Long.toString(this.renderTime));
		optionMap.put("zoom-factor", Long.toString(this.zoomFactor));
		if (originalOptionMap != null) {
			for (String key : originalOptionMap.keySet()) {
				optionMap.put(key, originalOptionMap.get(key));
			}
		}
		return optionMap;
	}

	private File getTempFile(String extension) throws ExportException {
		try {
			File tempFile = File.createTempFile("PHANTOM_SHOT_", "."
					+ extension);
			// tempFile.deleteOnExit();
			return tempFile;
		} catch (IOException ie) {
			throw new ExportException("Not able to create the temp file.", ie);
		}
	}

	private String toString(Map<String, String> optionMap) {
		StringBuilder builder = new StringBuilder();
		for (String name : optionMap.keySet()) {
			builder.append(name).append("=").append(optionMap.get(name))
					.append(";");
		}
		int length = builder.length();
		if (length > 1) {
			builder.deleteCharAt(length - 1);
		}
		return builder.toString();
	}
}
