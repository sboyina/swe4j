package com.strategicbase.swe4j.pdf;

import java.io.File;

import com.strategicbase.swe4j.ExportException;

public interface PDFExporter {

	public abstract File export(Input4PDF input) throws ExportException;

}