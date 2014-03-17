package com.swe4j.pdf;

import java.io.File;

import com.swe4j.ExportException;

public interface PDFExporter {

	public abstract File export(Input4PDF input) throws ExportException;

}