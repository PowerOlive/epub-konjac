package net.sharplab.epub.konjac.domain;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * Created by ynojima on 2017/09/27.
 */
public class KonjacMessageSource extends ResourceBundleMessageSource {
    // ~ Constructors
    // ===================================================================================================

    public KonjacMessageSource() {
        setBasename("net.sharplab.epub.konjac.domain.messages");
    }

    // ~ Methods
    // ========================================================================================================

    public static MessageSourceAccessor getAccessor() {
        return new MessageSourceAccessor(new KonjacMessageSource());
    }
}
