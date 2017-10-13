package net.sharplab.epub.konjac.domain.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by ynojima on 2017/04/22.
 */
@Data
public class Options implements Serializable{

    private static final long serialVersionUID = 5429271785598522923L;
    private String ContentType;
    private String Category;
}
