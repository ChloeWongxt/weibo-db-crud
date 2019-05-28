package com.chloe.weibo.core.service;

import com.chloe.weibo.core.dao.FileDao;
import com.chloe.weibo.core.entity.File;
import com.chloe.weibo.core.entity.entityExample.FileExample;
import com.chloe.weibo.common.exception.WeiboException;
import com.chloe.weibo.core.service.interfaces.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ChloeWong
 * @date 2019/4/11
 */
@Service
public class FileServiceImpl implements FileService {
    @Autowired
    FileDao fileDao;

    @Transactional
    @Override
    public void addFile(File file) {
        if(fileDao.insertSelective(file)<=0){
            throw new WeiboException("添加文件失败：数据库未知原因！");
        }
    }

    @Transactional
    @Override
    public void addFiles(List<File> files) {
        for (File file:files){
            addFile(file);
        }
    }

    @Transactional
    @Override
    public void addFileAddress(Integer weiboId, String fileAddress) {
        File file=new File();
        file.setWeiboId(weiboId);
        file.setFileAddress(fileAddress);
        file.setFileType(0);
        file.setIsDel(false);
        if (fileDao.insertSelective(file)<0){
            throw new WeiboException("添加文件失败：数据库未知错误！");
        }
    }

    @Transactional
    @Override
    public void addFileAddressList(Integer weiboId, List<String> filesAddressList) {
        if (filesAddressList==null){
            return;
        }else {
            for (String fileAddress:filesAddressList){
                addFileAddress(weiboId,fileAddress);
            }
        }
    }

    @Transactional
    @Override
    public void deleteFileByFileId(Integer fileId) {
        File file=fileDao.selectByPrimaryKey(fileId);
        file.setIsDel(true);
        if (fileDao.updateByPrimaryKeySelective(file)<=0){
            throw new WeiboException("删除文件失败：数据库未知原因");
        }
    }

    @Transactional
    @Override
    public void deleteFilesByWeiboId(Integer weiboId) {
        FileExample fileExample=new FileExample();
        fileExample.createCriteria().andWeiboIdEqualTo(weiboId).andIsDelEqualTo(false);
        File file=new File();
        file.setIsDel(true);
        if (fileDao.updateByExampleSelective(file,fileExample)<=0){
            throw new WeiboException("删除文件失败：数据库未知原因");
        }
    }

    @Transactional
    @Override
    public void deleteFileByWeiboIdAndFileAddress(Integer weiboId, String fileAddress) {
        FileExample fileExample=new FileExample();
        fileExample.createCriteria().andWeiboIdEqualTo(weiboId).andFileAddressEqualTo(fileAddress).andIsDelEqualTo(false);
        File file=new File();
        file.setIsDel(true);
        if (fileDao.updateByExampleSelective(file,fileExample)<=0){
            throw new WeiboException("删除文件失败：数据库未知原因");
        }
    }

    @Transactional
    @Override
    public void updateFileByFileAddress(Integer weiboId, String fileListString) {
        File org_file= getFileByWeiboId(weiboId);
        if (org_file==null){
            throw new WeiboException("原文件不存在！");
        }
        org_file.setFileAddress(fileListString);
        if (fileDao.updateByPrimaryKeySelective(org_file)<=0){
            throw new WeiboException("更新文件失败：数据未知原因！");
        }
    }

    @Transactional
    @Override
    public List<String> getFilesAddressByWeiboId(Integer weiboId) {
        List<String> filesAddress=fileDao.selectFileAddressListByWeiboId(weiboId);
        return filesAddress;
    }

    @Transactional
    @Override
    public File getFileByWeiboId(Integer weiboId) {
        FileExample fileExample=new FileExample();
        fileExample.createCriteria().andWeiboIdEqualTo(weiboId).andIsDelEqualTo(false);
        File file=fileDao.selectByExample(fileExample).get(0);
        return file;
    }

    @Transactional
    @Override
    public void updateFiles(Integer weiboId, List<String> org_files, List<String> new_files) {
        int org_filesNum=0;
        if (org_files!=null){
            org_filesNum=org_files.size();
        }
        int new_filesNum=0;
        if (new_files!=null){
            new_filesNum=new_files.size();
        }
        if (org_filesNum!=0&&new_filesNum!=0){
            //原微博有图片,现微博有图片
            //比对图片是否有修改
            int min_size=org_filesNum<=new_filesNum? org_filesNum:new_filesNum;
            for (int i=0;i<min_size;i++){
                String orgAddress=org_files.get(i);
                String newAddress=new_files.get(i);
                if(orgAddress!=newAddress){
                    updateFileByFileAddress(weiboId,newAddress);
                }
            }
            if (org_filesNum<=new_filesNum){
                //有新增图片
                for (int i=min_size;i<new_filesNum;i++){
                    addFileAddress(weiboId,new_files.get(i));
                }
            }else {
                //图片有删减
                for (int i=min_size;i<org_filesNum;i++){
                    deleteFileByWeiboIdAndFileAddress(weiboId,org_files.get(i));
                }
            }
        }

        if (org_filesNum==0&&new_filesNum!=0){
            //原微博没有图片,现微博有图片
            //增加图片地址到tb_file
            addFileAddressList(weiboId,new_files);
        }

        if (org_filesNum!=0&&new_filesNum==0){
            //原微博有图片,现微博没有图片
            deleteFilesByWeiboId(weiboId);
        }

        if (org_filesNum==0&&new_filesNum==0){
            //原微博没有图片,也现微博没有图片
            //什么都不做
        }
    }

    @Transactional
    @Override
    public void addFileListString(Integer weiboId, String fileListString) {
        if (fileListString!=null){
            addFileAddress(weiboId,fileListString);
        }
    }

    @Transactional
    @Override
    public void updateFileListString(Integer weiboId, String fileListString) {
        if (fileListString!=null){
            updateFileByFileAddress(weiboId,fileListString);
        }
    }
}
