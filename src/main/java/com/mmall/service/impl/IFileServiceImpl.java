package com.mmall.service.impl;

import com.google.common.collect.Lists;
import com.mmall.service.IFileService;
import com.mmall.util.FTPUtil;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service("iFileService")
@Slf4j
public class IFileServiceImpl implements IFileService {

// LoggerFactory.getLogger: 使用指定类初始化日志对象，在日志输出的时候，可以打印出日志信息所在类
//    private Logger logger = LoggerFactory.getLogger(IFileServiceImpl.class);

    @Override
    public String upload(MultipartFile file, String path) {
        String fileName = file.getOriginalFilename();
        //扩展名
        //lastIndexOf:返回"."在fileName中最后一个匹配项的索引位置,即abc.jpg会返回.jpg
        String fileExtensionName = fileName.substring(fileName.lastIndexOf(".")+1);
        //为了防止不同用户上传图片时，两张图片的文件名完全相同导致覆盖的情况，这里对文件名加上UUID防重复
        String uploadFileName = UUID.randomUUID().toString() + "." + fileExtensionName;
        //打印日志，通过{}进行占位，也就是一个占位符对应后面的一个数据，类似于c里面的printf("%c",h);
        log.info("开始上传文件,上传文件的文件名:{},上传的路径:{},新文件名:{}",fileName,path,uploadFileName);

        //创建上传路径目录的文件对象
        File fileDir = new File(path);
        if (!fileDir.exists()) {
            //不存在
            // 设置可写权限
            fileDir.setWritable(true);

            //mkdir()：当前级别
            //mkdirs()：如果上传的文件所在的文件是/a,/b,/c等，直接传到服务器上时，这些文件夹都没有，用mkdirs()就可以自动创建
            fileDir.mkdirs();
        }
        File targetFile = new File(path, uploadFileName);

        try {
            //文件已经上传成功
            file.transferTo(targetFile);

            //将targetFile上传到我们的FTP服务器
            FTPUtil.uploadFile(Lists.newArrayList(targetFile));

            //上传完之后，删除upload下面的文件
            targetFile.delete();


        }catch (IOException e) {
            log.error("上传文件异常",e);
            return null;
        }
        return targetFile.getName();
    }








}
