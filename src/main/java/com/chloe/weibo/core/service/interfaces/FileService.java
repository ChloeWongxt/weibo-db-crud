package com.chloe.weibo.core.service.interfaces;

import com.chloe.weibo.core.entity.File;

import java.util.List;

/**
 * @author ChloeWong
 * @date 2019/4/11
 */
public interface FileService {
    void addFile(File file);
    void addFiles(List<File> files);
    void addFileAddress(Integer weiboId,String fileAddress);
    void addFileAddressList(Integer weiboId,List<String> filesAddressList);
    void deleteFileByFileId(Integer fileId);
    void deleteFilesByWeiboId(Integer weiboId);
    void deleteFileByWeiboIdAndFileAddress(Integer weiboId,String fileAddress);
    void updateFileByFileAddress(Integer weiboId,String fileListString);
    List<String> getFilesAddressByWeiboId(Integer weiboId);
    File getFileByWeiboId(Integer weiboId);
    void updateFiles(Integer weiboId,List<String> org_filesAddress,List<String> new_filesAddress);
    void addFileListString(Integer weiboId,String fileListString);
    void updateFileListString(Integer weiboId,String fileListString);
}
