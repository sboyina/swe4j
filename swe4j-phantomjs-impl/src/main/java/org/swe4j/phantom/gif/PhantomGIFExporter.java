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
package org.swe4j.phantom.gif;

import java.io.File;
import java.util.HashMap;

import org.swe4j.ExportException;
import org.swe4j.gif.GIFExporter;
import org.swe4j.gif.Input4GIF;
import org.swe4j.phantom.BaseImageExporter;

/**
 * <p>
 * {@link PhantomGIFExporter} exports a html to gif format.
 * </p>
 * 
 * @author sboyina
 * 
 */
public class PhantomGIFExporter extends BaseImageExporter implements
		GIFExporter {

	public PhantomGIFExporter() {
		super("gif");
	}

	public File export(Input4GIF input) throws ExportException {
		return super.export(input.getUrlToExport(),
				new HashMap<String, String>());
	}

}
