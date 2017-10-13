package net.sharplab.epub.konjac.domain.model;

import net.sharplab.epub.konjac.domain.exception.EPubContentHandlingException;
import org.junit.Test;

import java.util.Collections;

/**
 * Test for FileEntry
 */
public class FileEntryTest {

    @Test(expected = EPubContentHandlingException.class)
    public void getContentFile_with_empty_provider_list() throws Exception{
        FileEntry fileEntry = new FileEntry("dummy", null);
        fileEntry.getContentFile(Collections.emptyList());
    }
}
