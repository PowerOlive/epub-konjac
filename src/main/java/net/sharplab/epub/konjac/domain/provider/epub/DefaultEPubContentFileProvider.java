package net.sharplab.epub.konjac.domain.provider.epub;

import net.sharplab.epub.konjac.domain.model.EPubContentFile;
import net.sharplab.epub.konjac.domain.model.FileEntry;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * EPubContentFileを返却するプロバイダ
 */
@Order
@Component
public class DefaultEPubContentFileProvider implements EPubContentFileProvider {


    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canHandle(FileEntry fileEntry) {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EPubContentFile provide(FileEntry fileEntry) {
        if(!canHandle(fileEntry)){
            throw new IllegalArgumentException();
        }
        return new EPubContentFile(fileEntry.getName(), fileEntry.getData());
    }
}
