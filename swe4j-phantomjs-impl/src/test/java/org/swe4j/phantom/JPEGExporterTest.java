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

import org.junit.Before;
import org.junit.Test;

import com.strategicbase.swe4j.ExportException;
import com.strategicbase.swe4j.jpeg.Input4JPEG;
import com.strategicbase.swe4j.phantom.jpeg.PhantomJPEGExporter;

/**
 * @author sboyina
 * 
 */
public class JPEGExporterTest extends ExporterTestBase {

	private PhantomJPEGExporter jpegExporter;

	@Before
	public void initialize() {
		this.jpegExporter = new PhantomJPEGExporter();
	}

	@Test
	public void testFileExportAsJPEG() throws ExportException {
		Input4JPEG input = new Input4JPEG();
		input.setFileToExport(getTestFile());
		File outputFile = jpegExporter.export(input);
		assertExistence(outputFile);
	}

}
