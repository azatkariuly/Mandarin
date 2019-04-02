package com.zerotoone.n17r.mandarin.Memrise;

import android.graphics.Path;

/**
 * Created by Azat Kun on 8/8/2017.
 */

public class FingerPath {
    public int color;
    public int strokeWidth;
    public Path path;

    public FingerPath(int color, int strokeWidth, Path path) {
        this.color = color;
        this.strokeWidth = strokeWidth;
        this.path = path;
    }

}
