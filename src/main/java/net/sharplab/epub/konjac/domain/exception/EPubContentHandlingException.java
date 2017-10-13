package net.sharplab.epub.konjac.domain.exception;

/**
 * Created by ynojima on 2017/09/28.
 */
public class EPubContentHandlingException extends RuntimeException {

    public EPubContentHandlingException(){

    }

    public EPubContentHandlingException(Throwable throwable){
        super(throwable);
    }
}
