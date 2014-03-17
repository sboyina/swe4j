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
package org.swe4j.phantom.pdf;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.swe4j.ExportException;
import com.swe4j.pdf.Input4PDF;
import com.swe4j.pdf.PDFExporter;

/**
 * <p>
 * {@link PhantomPDFExporter} exports a HTML to a PDF.
 * </p>
 * 
 * @author sboyina
 * 
 */
public class PhantomPDFExporter extends PhantomExporter implements PDFExporter {

	public PhantomPDFExporter() {
		super("pdf");
	}

	/* (non-Javadoc)
	 * @see com.strategicbase.webexporter.phantom.PDFExporterInterface#export(com.strategicbase.webexporter.pdf.Input4PDF)
	 */
	public File export(Input4PDF input) throws ExportException {
		Map<String, String> optionMap = new HashMap<String, String>();
		optionMap.put("format", input.getPaperSize().toString());
		optionMap.put("orientation", input.getOrientaion().toString());
		optionMap.put("border", input.getBorder() + "cm");
		return super.export(input.getUrlToExport(), optionMap);
	}
}
