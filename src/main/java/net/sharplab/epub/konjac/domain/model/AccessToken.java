package net.sharplab.epub.konjac.domain.model;

import lombok.Data;

/**
 * Created by ynojima on 2017/04/22.
 */
@Data
public class AccessToken {
    private String value;

    public AccessToken(String value){
        this.value = value;
    }

    public AccessToken(){

    }
}
