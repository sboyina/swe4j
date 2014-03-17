/**
 * 
 */
package org.swe4j;

import java.io.File;
import java.net.URL;

/**
 * @author srinivasab
 * 
 */
public class Input {

	private URL urlToExport;

	public Input() {

	}

	public URL getUrlToExport() {
		return urlToExport;
	}

	public void setUrlToExport(URL urlToExport) {
		this.urlToExport = urlToExport;
	}

	public void setFileToExport(File fileToExport) {
		try {
			this.urlToExport = new URL("file:///"
					+ fileToExport.getAbsolutePath());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
