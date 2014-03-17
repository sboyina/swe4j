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
package com.swe4j.phantom;

import java.io.File;

import org.junit.Before;
import org.junit.Test;
import org.swe4j.phantom.PhantomPNGExporter;

import com.swe4j.ExportException;
import com.swe4j.png.Input4PNG;

/**
 * @author sboyina
 * 
 */
public class PNGExporterTest extends ExporterTestBase {
	private PhantomPNGExporter pngExporter;

	@Before
	public void initialize() {
		this.pngExporter = new PhantomPNGExporter();
	}

	@Test
	public void testFileExportAsPNG() throws ExportException {
		Input4PNG input = new Input4PNG();
		input.setFileToExport(getTestFile());
		File outputFile = pngExporter.export(input);
		assertExistence(outputFile);
	}
}
