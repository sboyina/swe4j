/**
 * 
 */
package org.swe4j.phantom;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author srinivasab
 * 
 */
public final class BlockingProcess {

	private Logger logger;

	private Level level;

	private Process process;

	private String processName;

	private long maxTimeAllowed;

	private List<String> command;

	public BlockingProcess(String name, List<String> command, Logger loggerToUse) {
		this(name, command, loggerToUse, 5);
	}

	public BlockingProcess(String name, List<String> command,
			Logger loggerToUse, long maxTimeAllowed) {
		this(name, command, loggerToUse, maxTimeAllowed, Level.FINE);

	}

	public BlockingProcess(String name, List<String> command,
			Logger loggerToUse, long maxTimeAllowed, Level logLevel) {
		this.logger = loggerToUse;
		this.level = logLevel;
		this.processName = name;
		this.command = command;
		this.maxTimeAllowed = maxTimeAllowed;
	}

	public Process start() {
		try {
			ProcessBuilder builder = new ProcessBuilder(this.command);
			Thread timeOutThread = new ProcessTimeoutThread();
			this.process = builder.start();
			timeOutThread.start();
			this.process.waitFor();
			timeOutThread.interrupt();
		} catch (Exception e) {
			throw new RuntimeException("Following exception occurred.", e);
		} finally {
			flushLog();
		}
		return this.process;
	}

	public void flushLog() {
		final BufferedReader input = new BufferedReader(new InputStreamReader(
				process.getInputStream()));
		String line;
		try {
			while ((line = input.readLine()) != null) {
				logger.log(level, "{0} - {1}",
						new Object[] { processName, line });
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private class ProcessTimeoutThread extends Thread {

		public void run() {
			try {
				Thread.sleep(maxTimeAllowed);
				process.exitValue();
			} catch (InterruptedException ie) {
				// LOGGER.debug("Process Timeout thread is interrupted to get out of sleep.");
			} catch (IllegalThreadStateException ie) {
				logger.log(Level.SEVERE,
						"{0} Process has not yet completed. So destroying it.",
						processName);
				process.destroy();
			} finally {
				flushLog();
			}
		}
	}
}
