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
package com.strategicbase.swe4j;

import java.io.File;
import java.net.URL;

/**
 * <p>
 * {@linkplain Exporter} defines the top level interface of this module. An
 * {@link Exporter} is used to export either a url or a html file to a file (of
 * different format ex: PDF, JPEG, GIF etc.,).
 * </p>
 * 
 * @author sboyina
 */
public interface Exporter {

	/**
	 * <p>
	 * Exports the html returned by the given {@link url} into a file.
	 * </p>
	 * 
	 * @param url
	 * @return
	 * @throws ExportException
	 *             if there is an exception during export process.
	 */
	public abstract File export(URL url) throws ExportException;

	/**
	 * <p>
	 * Exports the html file into a file of different format.
	 * </p>
	 * 
	 * 
	 * @param file
	 * @return
	 * @throws ExportException
	 */
	public abstract File export(File file) throws ExportException;
}