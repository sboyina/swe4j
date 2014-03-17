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
import java.net.MalformedURLException;

import org.junit.Test;
import org.swe4j.phantom.pdf.PhantomPDFExporter;

import com.swe4j.ExportException;
import com.swe4j.pdf.Input4PDF;

/**
 * Unit test for simple App.
 */
public class PDFExporterTest extends ExporterTestBase {

	@Test
	public void testFileExportAsPDF() throws ExportException,
			MalformedURLException {
		PhantomPDFExporter pdfExporter = new PhantomPDFExporter();
		Input4PDF input = new Input4PDF();
		input.setFileToExport(getTestFile());
		File outputFile = pdfExporter.export(input);
		assertExistence(outputFile);
	}

	/*
	 * Set render timeout more than max export time. Then, check whether the
	 * process is failing with an exception.
	 */
	@Test(expected = ExportException.class)
	public void testExportTimeout() throws ExportException {
		PhantomPDFExporter pdfExporter = new PhantomPDFExporter();
		pdfExporter.setRenderTime(200);
		pdfExporter.setMaxExportTimeAlowed(100);
		Input4PDF input = new Input4PDF();
		input.setFileToExport(getTestFile());
		pdfExporter.export(input);
	}
}
