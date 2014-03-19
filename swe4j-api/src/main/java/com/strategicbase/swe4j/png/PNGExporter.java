package com.strategicbase.swe4j.png;

import java.io.File;

import com.strategicbase.swe4j.ExportException;

public interface PNGExporter {

	public abstract File export(Input4PNG input) throws ExportException;

}