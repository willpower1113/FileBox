package com.willpower.filebox.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 冀毅 on 2018/6/1.
 */

public class FolderBean<T> implements Serializable{

    private static final long serialVersionUID = 1L;

    public String fileName;

    public String filePath;

    public ArrayList<T> childs;

    public String coverPath;

    public FolderBean(String fileName, String filePath, ArrayList<T> childs, String coverPath) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.childs = childs;
        this.coverPath = coverPath;
    }

    public FolderBean() {
    }
}
