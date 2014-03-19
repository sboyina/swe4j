/**
 * 
 */
package com.strategicbase.swe4j;


/**
 * @author srinivasab
 * 
 */
public class Input4Image extends Input {

	private int width = 1024;

	private int height = 768;

	private int margin = 1;

	public int getWidth() {
		return width;
	}

	/**
	 * @param width
	 *            width of the image to be generatedG.
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	/**
	 * @param height
	 *            height of the image to be generated.
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	public int getMargin() {
		return margin;
	}

	/**
	 * @param margin
	 *            margin to be set for the image to be generated.
	 */
	public void setMargin(int margin) {
		this.margin = margin;
	}

}
