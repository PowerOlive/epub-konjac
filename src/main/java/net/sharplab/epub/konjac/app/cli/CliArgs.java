package net.sharplab.epub.konjac.app.cli;

import com.beust.jcommander.Parameter;
import lombok.Data;

import java.io.File;
import java.util.List;

/**
 * Represents command line arguments
 */
@Data
public class CliArgs {
    @Parameter
    private List<String> parameters;

    @Parameter(names = {"--src"}, description = "source file", required = true)
    private File src;

    @Parameter(names = {"--dst"}, description = "destination file")
    private File dst;

    @Parameter(names = { "--srcLang" }, description = "source language")
    private String srcLang;

    @Parameter(names = { "--dstLang" }, description = "destination language")
    private String dstLang;

    @Parameter(names = {"--help", "-h"}, description = "print help", help = true)
    private boolean help;
}
