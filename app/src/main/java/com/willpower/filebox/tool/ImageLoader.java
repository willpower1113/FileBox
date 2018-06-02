package com.willpower.filebox.tool;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.willpower.filebox.R;

import java.io.File;


/**
 * Created by 冀毅 on 2018/6/1.
 */

public class ImageLoader {

    private static ImageLoader loader = new ImageLoader();

    private boolean init = false;

    private Context context;

    public static synchronized ImageLoader getLoader() {
        return loader;
    }

    public void init(Context context) {
        init = true;
        this.context = context.getApplicationContext();
    }

    public void loadDeviceImage(String path, ImageView view) {
        if (path == null) return;
        if (!init) return;
        Glide.with(context)
                .load(Uri.fromFile(new File(path)))
                .into(view);
    }


}
