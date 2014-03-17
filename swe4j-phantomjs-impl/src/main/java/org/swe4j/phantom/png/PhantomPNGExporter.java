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
package org.swe4j.phantom.png;

import java.io.File;
import java.util.HashMap;

import org.swe4j.phantom.BaseImageExporter;

import com.swe4j.ExportException;
import com.swe4j.png.Input4PNG;
import com.swe4j.png.PNGExporter;

/**
 * <p>
 * {@link PhantomPNGExporter} exports a HTML to PNG format.
 * </p>
 * 
 * @author sboyina
 * 
 */
public class PhantomPNGExporter extends BaseImageExporter implements
		PNGExporter {

	public PhantomPNGExporter() {
		super("png");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.strategicbase.webexporter.phantom.PNGExporter#export(com.strategicbase
	 * .webexporter.png.Input4PNG)
	 */
	public File export(Input4PNG input) throws ExportException {
		return super.export(input.getUrlToExport(),
				new HashMap<String, String>());
	}

}
