package com.zerotoone.n17r.mandarin.Memrise;

/**
 * Created by Azat Kun on 8/7/2017.
 */

public class Data {
    public int imageId;
    public String txt;

    public Data(int imageId, String txt) {
        this.imageId = imageId;
        this.txt = txt;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }
}
