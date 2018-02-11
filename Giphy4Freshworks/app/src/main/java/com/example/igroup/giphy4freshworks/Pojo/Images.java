package com.example.igroup.giphy4freshworks.Pojo;

/**
 * Created by iGroup on 2/10/2018.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Images {

    @SerializedName("preview_gif")
    @Expose
    private PreviewGif previewGif;

    public PreviewGif getPreviewGif() {
        return previewGif;
    }

    public void setPreviewGif(PreviewGif previewGif) {
        this.previewGif = previewGif;
    }

}