package net.sharplab.epub.konjac.domain.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by ynojima on 2017/04/22.
 */
@Data
public class Text implements Serializable{


    private static final long serialVersionUID = 2469741141359658176L;

    private String value;

    public Text(String value){
        this.value = value;
    }
}
