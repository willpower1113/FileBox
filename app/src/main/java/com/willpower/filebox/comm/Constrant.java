package com.willpower.filebox.comm;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 冀毅 on 2018/6/1.
 */

public class Constrant {
    
    public static final int FILE_TYPE_WORD = 1;
    public static final int FILE_TYPE_PHOTO = 2;
    public static final int FILE_TYPE_MUSIC = 3;
    public static final int FILE_TYPE_VIDEO = 4;

    public static final Map<String,Integer> FILETYPEMAP(){
        Map<String,Integer> typeMap = new HashMap<>();
        typeMap.put(".xls", FILE_TYPE_WORD);
        typeMap.put(".doc", FILE_TYPE_WORD);
        typeMap.put(".ppt", FILE_TYPE_WORD);
        typeMap.put(".pptx", FILE_TYPE_WORD);
        typeMap.put(".docx", FILE_TYPE_WORD);
        typeMap.put(".html", FILE_TYPE_WORD);
        typeMap.put(".txt", FILE_TYPE_WORD);
        typeMap.put(".3gp", FILE_TYPE_VIDEO);
        typeMap.put(".avi", FILE_TYPE_VIDEO);
        typeMap.put(".m4u", FILE_TYPE_VIDEO);
        typeMap.put(".mov", FILE_TYPE_VIDEO);
        typeMap.put(".mp4", FILE_TYPE_VIDEO);
        typeMap.put(".mpe", FILE_TYPE_VIDEO);
        typeMap.put(".mpeg", FILE_TYPE_VIDEO);
        typeMap.put(".mpg", FILE_TYPE_VIDEO);
        typeMap.put(".mpg4", FILE_TYPE_VIDEO);
        typeMap.put(".rmvb", FILE_TYPE_VIDEO);
        typeMap.put(".m3u", FILE_TYPE_MUSIC);
        typeMap.put(".m4a", FILE_TYPE_MUSIC);
        typeMap.put(".m4b", FILE_TYPE_MUSIC);
        typeMap.put(".m4p", FILE_TYPE_MUSIC);
        typeMap.put(".mp2", FILE_TYPE_MUSIC);
        typeMap.put(".mp3", FILE_TYPE_MUSIC);
        typeMap.put(".mpga", FILE_TYPE_MUSIC);
        typeMap.put(".ogg", FILE_TYPE_MUSIC);
        typeMap.put(".mp3", FILE_TYPE_MUSIC);
        typeMap.put(".mpga", FILE_TYPE_MUSIC);
        typeMap.put(".ogg", FILE_TYPE_MUSIC);
        typeMap.put(".wav", FILE_TYPE_MUSIC);
        typeMap.put(".wma", FILE_TYPE_MUSIC);
        typeMap.put(".wmv", FILE_TYPE_MUSIC);
        typeMap.put(".jpg", FILE_TYPE_PHOTO);
        typeMap.put(".jpeg", FILE_TYPE_PHOTO);
        typeMap.put(".png", FILE_TYPE_PHOTO);
        typeMap.put(".psd", FILE_TYPE_PHOTO);
        typeMap.put(".gif", FILE_TYPE_PHOTO);
        typeMap.put(".bmp", FILE_TYPE_PHOTO);
        typeMap.put(".bpm", FILE_TYPE_PHOTO);
        typeMap.put(".webp", FILE_TYPE_PHOTO);
        return typeMap;
    }
    
}
