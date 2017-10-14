package net.sharplab.epub.konjac.domain.repository;

import net.sharplab.epub.konjac.domain.model.EPubFile;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by ynojima on 2017/04/23.
 */
@Repository
public class EPubFileRepositoryImpl implements EPubFileRepository {

    public EPubFile read(File file){
        return new EPubFile(file);
    }

}
