package net.sharplab.epub.konjac.domain.model;

import lombok.Data;
import net.sharplab.epub.konjac.domain.exception.ContentFileProviderNotFoundException;
import net.sharplab.epub.konjac.domain.provider.epub.EPubContentFileProvider;

import java.util.List;

/**
 * ファイルエントリ
 */
@Data
public class FileEntry {
    /**
     * ファイル名
     */
    private String name;
    /**
     * データ
     */
    private byte[] data;

    /**
     * ファイルエントリのコンストラクタ
     * @param name ファイル名
     * @param data データ
     */
    public FileEntry(String name, byte[] data){
        this.name = name;
        this.data = data;
    }

    /**
     * ePubコンテンツファイルを作成する
     * @return ePubコンテンツファイル
     */
    EPubContentFile getContentFile(List<EPubContentFileProvider> ePubContentFileProviders){
        for(EPubContentFileProvider ePubContentFileProvider : ePubContentFileProviders){
            if(ePubContentFileProvider.canHandle(this)){
                return ePubContentFileProvider.provide(this);
            }
        }
        throw new ContentFileProviderNotFoundException();
    }

}
