package net.sharplab.epub.konjac.domain.model;

import lombok.Getter;
import net.sharplab.epub.konjac.domain.provider.epub.EPubContentFileProvider;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * EPubFile
 */
public class EPubFile {

    @Getter
    private File file;

    public EPubFile(File file){
        this.file = file;
    }

    public List<EPubContentFile> getContentFiles(List<EPubContentFileProvider> ePubContentFileProviders){
        List<EPubContentFile> items = new LinkedList<>();
        try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(this.file))){
            for (ZipEntry zipEntry = zipInputStream.getNextEntry(); zipEntry != null; zipEntry = zipInputStream.getNextEntry()) {
                if (zipEntry.isDirectory()){
                    continue;
                }
                byte[] data = readZipEntry(zipInputStream);
                FileEntry fileEntry = new FileEntry(zipEntry.getName(), data);
                items.add(fileEntry.getContentFile(ePubContentFileProviders));
            }
            return  items;
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }




    /**
     * ZipEntryを読み込む
     * @param zipInputStream Zip入力ストリーム
     * @return ZipEntryに含まれるファイルのバイト配列表現
     */
    private byte[] readZipEntry(ZipInputStream zipInputStream){
        final int bufferSize = 1024;
        final byte[] buffer = new byte[bufferSize];
        try(ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()){
            while (true) {
                int readSize = zipInputStream.read(buffer, 0, buffer.length);
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
