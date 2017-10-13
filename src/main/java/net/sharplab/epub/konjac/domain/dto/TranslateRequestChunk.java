package net.sharplab.epub.konjac.domain.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * TranslateRequestChunk
 */
@Data
public class TranslateRequestChunk {

    public List<TranslateRequest> translateRequests;

    public TranslateRequestChunk(){
        this.translateRequests = new ArrayList<>();
    }

}
