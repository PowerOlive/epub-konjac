package net.sharplab.epub.konjac.domain.model;

import lombok.Getter;

/**
 * Access Token
 */
@Getter
public class AccessToken {
    private String value;

    public AccessToken(String value){
        this.value = value;
    }

}
