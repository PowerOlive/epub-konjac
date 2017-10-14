package net.sharplab.epub.konjac.domain.repository;

import net.sharplab.epub.konjac.domain.model.EPubFile;

import java.io.File;

/**
 * Created by ynojima on 2017/10/14.
 */
public interface EPubFileRepository {

    EPubFile read(File file);
}
