/**
 * 
 */
package com.swe4j.pdf;

import com.swe4j.Input;

/**
 * @author srinivasab
 * 
 */
public class Input4PDF extends Input {

	/**
	 * Enumeration of available paper sizes.
	 * 
	 */
	public static enum PaperSize {
		A3("A3"), A4("A4"), A5("A5"), LEGAL("Legal"), LETTER("Letter"), TABLOID(
				"Tabloid");

		String size = "";

		PaperSize(String size) {
			this.size = size;
		}

		public String toString() {
			return size;
		}
	}

	/**
	 * Enumeration of available orientations.
	 */
	public static enum Orientaion {
		LANDSCAPE("landscape"), PORTRAIT("portrait");

		String orientaion = "";

		Orientaion(String orientaion) {
			this.orientaion = orientaion;
		}

		public String toString() {
			return orientaion;
		}
	}

	private PaperSize paperSize = PaperSize.A4;
	private Orientaion orientaion = Orientaion.PORTRAIT;
	private int border = 1;

	public PaperSize getPaperSize() {
		return paperSize;
	}

	public void setPaperSize(PaperSize paperSize) {
		this.paperSize = paperSize;
	}

	public void setPaperSizeByName(String paperSizeName) {
		this.paperSize = PaperSize.valueOf(paperSizeName);
	}

	public Orientaion getOrientaion() {
		return orientaion;
	}

	public void setOrientaion(Orientaion orientaion) {
		this.orientaion = orientaion;
	}

	public void setOrientaionByName(String orientationName) {
		this.orientaion = Orientaion.valueOf(orientationName);
	}

	public int getBorder() {
		return border;
	}

	/**
	 * @param border
	 *            border to set in the PDF to be generated.
	 */
	public void setBorder(int border) {
		this.border = border;
	}
}
