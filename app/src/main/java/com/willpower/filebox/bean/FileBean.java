package com.willpower.filebox.bean;

import java.io.Serializable;

/**
 * Created by 冀毅 on 2018/6/1.
 */

public class FileBean implements Serializable{

    private static final long serialVersionUID = 1L;

    public String path;

    public String fileName;

    public String fileSize;

    public volatile String describe;

    public long lastModifyTime;

    public String parentName;

    public FileBean() {
    }

    public FileBean(String path, String fileName) {
        this.path = path;
        this.fileName = fileName;
    }

    public FileBean(String path, String fileName, String parentName) {
        this.path = path;
        this.fileName = fileName;
        this.parentName = parentName;
    }

    public FileBean(String path, String fileName, String fileSize, String describe, long lastModifyTime) {
        this.path = path;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.describe = describe;
        this.lastModifyTime = lastModifyTime;
    }

    public FileBean(String path, String fileName, String fileSize, String describe, long lastModifyTime, String parentName) {
        this.path = path;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.describe = describe;
        this.lastModifyTime = lastModifyTime;
        this.parentName = parentName;
    }
}
