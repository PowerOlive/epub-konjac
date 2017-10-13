package net.sharplab.epub.konjac.domain.repository;

import net.sharplab.epub.konjac.domain.model.EPubFile;
import org.springframework.stereotype.Repository;

import java.io.File;

/**
 * Created by ynojima on 2017/04/23.
 */
@Repository
public class EPubFileRepository {

    public EPubFile read(File file){
        throw new IllegalStateException();
    }

}
