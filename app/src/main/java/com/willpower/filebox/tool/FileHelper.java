package com.willpower.filebox.tool;

import android.os.Environment;

import com.willpower.filebox.bean.FileBean;
import com.willpower.filebox.bean.FolderBean;
import com.willpower.filebox.comm.Constrant;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by 冀毅 on 2018/6/1.
 */

public class FileHelper {
    private static FileHelper helper = new FileHelper();

    public static FileHelper getHelper() {
        return helper;
    }

    public List<File> allFiles;

    public Set<String> allFolders;

    /**
     * SD Card是否ready
     *
     * @return
     */
    public static boolean isSdcardReady() {
        return Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState());
    }

    /**
     * @return 设备存储根目录
     */
    public static String getDeviceRootPath() {
        if (isSdcardReady()) {
            return Environment.getExternalStorageDirectory().toString();
        } else {
            return null;
        }
    }

    /**
     * 获取所有的照片
     */
    public ArrayList<FolderBean<FileBean>> findPictures() {
        return find(Constrant.FILE_TYPE_PHOTO);
    }
    /**
     * 获取所有的音乐
     */
    public ArrayList<FolderBean<FileBean>> findMusics() {
        return find(Constrant.FILE_TYPE_MUSIC);
    }
    /**
     * 获取所有的视频
     */
    public ArrayList<FolderBean<FileBean>> findVideos() {
        return find(Constrant.FILE_TYPE_VIDEO);
    }
    private ArrayList<FolderBean<FileBean>> find(int type) {
        File root = new File(getDeviceRootPath());
        allFiles = new ArrayList<>();
        allFolders = new HashSet<>();
        queryAll(root.listFiles(),type);
        ArrayList<FolderBean<FileBean>> mData = new ArrayList<>();
        Iterator<String> iterator = allFolders.iterator();
        while (iterator.hasNext()) {
            String parentPath = iterator.next();
            ArrayList<FileBean> childList = new ArrayList<>();
            FolderBean<FileBean> folderBean = new FolderBean<>();
            folderBean.filePath = parentPath;
            for (File file : allFiles) {
                if (file.getParentFile().getPath().equals(parentPath)) {
                    childList.add(new FileBean(file.getPath(), file.getName(), getStringFileSize(file),"图片",file.lastModified(),file.getParentFile().getName()));
                }
            }
            folderBean.childs = childList;
            folderBean.fileName = childList.get(0).parentName;
            folderBean.coverPath = childList.get(0).path;
            mData.add(folderBean);
        }
        return mData;
    }


    private void queryAll(File[] files, int type) {
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    queryAll(files[i].listFiles(), type);
                } else {
                    if (checkFileType(files[i].getName(), type)) {
                        allFolders.add(files[i].getParentFile().getPath());
                        allFiles.add(files[i]);
                    }
                }
            }
        }
    }


    /**
     * 检查文件类型
     */
    private boolean checkFileType(String fileName, int type) {
        String suffix = getFileSuffix(fileName);
        if (Constrant.FILETYPEMAP().containsKey(suffix)) {
            int check = Constrant.FILETYPEMAP().get(suffix);
            if (check == type) {
                return true;
            }
        }
        return false;
    }


    /**
     * 获取文件的后缀名
     */
    public static String getFileSuffix(String fName) {
        String fileType = "";
        if (fName == null) return fileType;
        //获取后缀名前的分隔符"."在fName中的位置。
        int dotIndex = fName.lastIndexOf(".");
        if (dotIndex < 0) {
            return fileType;
        }
        /* 获取文件的后缀名*/
        fileType = fName.substring(dotIndex, fName.length()).toLowerCase();
        return fileType;
    }

    /**
     * 删除文件
     */
    public static boolean deleteFile(String path){
        File file = new File(path);
        if (file.exists()){
            return file.delete();
        }
        return false;
    }

    /**
     * 获取文件path的大小(String)
     *
     * @return String path的大小
     **/
    public static String getStringFileSize(File path){
        if (path.exists()) {
            try (FileInputStream fis = new FileInputStream(path)){
                DecimalFormat df = new DecimalFormat("#.00");
                String sizeStr;
                long size  = fis.available();
                if (size < 1024) {
                    sizeStr = size + "B";
                } else if (size < 1048576) {
                    sizeStr = df.format(size / (double) 1024) + "KB";
                } else if (size < 1073741824) {
                    sizeStr = df.format(size / (double) 1048576) + "MB";
                } else {
                    sizeStr = df.format(size / (double) 1073741824) + "GB";
                }
                return sizeStr;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }
}
