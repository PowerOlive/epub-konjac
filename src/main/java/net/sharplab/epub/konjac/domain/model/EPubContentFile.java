package net.sharplab.epub.konjac.domain.model;

import lombok.Getter;
import net.sharplab.epub.konjac.domain.KonjacMessageSource;
import org.springframework.context.support.MessageSourceAccessor;

import java.io.*;

/**
 * EPubContentFile
 */
public class EPubContentFile {

    protected MessageSourceAccessor messages = KonjacMessageSource.getAccessor();

    @Getter
    protected String name;
    @Getter
    protected byte[] data;

    public EPubContentFile(String name, byte[] data){
        this.name = name;
        this.data = data;
    }

    public EPubContentFile(String name, File file){
        this(name, readFile(file));
    }

    public String getContents(){
        try {
            return new String(data, "UTF-8");
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }

    static byte[] readFile(File file){

        final int bufferSize = 1024;
        final byte[] buffer = new byte[bufferSize];
        try {
            final FileInputStream fileInputStream = new FileInputStream(file);
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while (true) {
                int readSize = fileInputStream.read(buffer, 0, buffer.length);
                if (readSize < 0){
                    break;
                }
                byteArrayOutputStream.write(buffer, 0, readSize);
            }
            return byteArrayOutputStream.toByteArray();
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }

}
