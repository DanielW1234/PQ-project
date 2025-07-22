package com.njust.springboot.controller;

import cn.hutool.core.io.FileUtil;
import com.njust.springboot.common.Result;
import com.njust.springboot.exception.CustomException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


//文件相关接口
@RestController
@RequestMapping("/files")
public class filesController {
    //System.getProperty("user.dir")获取你当前项目的根路径
    //文件上传的目录的路径
    private  static final String filePath = System.getProperty("user.dir")+"/files/";

    //小文件上传接口
    @PostMapping("/upload")
    public Result upload(MultipartFile file) {//文件流
        String originalFilename = file.getOriginalFilename();//xxx.png
        if(!FileUtil.isDirectory(filePath)){
            FileUtil.mkdir(filePath);//创建目录
        }
        //提供文件完整路径
        //给文件名加一个唯一标识
        String fileName = System.currentTimeMillis() +"_"+ originalFilename;//时间戳加原文件名，防止重名
        String realPath = filePath + fileName;//完整的文件路径
        try {
            FileUtil.writeBytes(file.getBytes(),realPath);//写入
        } catch (IOException e) {
            e.printStackTrace();
            throw new CustomException("500","文件上传失败");
        }
        String url = "http://localhost:8080/files/download/" + fileName;
        return Result.success(url);
    }
    //大文件上传接口
    @PostMapping(value = "/uploadp" ,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Result uploadp(@RequestParam("file") MultipartFile file) {
        String originalFilename = file.getOriginalFilename();//xxx.png
        if(!FileUtil.isDirectory(filePath)){
            FileUtil.mkdir(filePath);//创建目录
        }
        //提供文件完整路径
        //给文件名加一个唯一标识
        String fileName = System.currentTimeMillis() +"_"+ originalFilename;//时间戳加原文件名，防止重名
        String realPath = filePath + fileName;//完整的文件路径
        File files = new File(realPath);
        try {
            if(!files.exists()) {
                file.transferTo(files);
            }//写入
        } catch (IOException e) {
            e.printStackTrace();
            throw new CustomException("500","文件上传失败");
        }
        String url = "http://localhost:8080/files/download/" + fileName;
        return Result.success(url);
    }


    @GetMapping("/download/{fileName}")
    public void download(@PathVariable String fileName, HttpServletResponse response) {
        try {
            response.addHeader("Content-Disposition","attachment;filename=" + URLEncoder.encode(fileName, StandardCharsets.UTF_8));
            //返回响应头
            response.setContentType("application/octet-stream");
            OutputStream os = response.getOutputStream();
            String realPath = filePath + fileName;
            //获取到文件的字节数组
            byte[] bytes = FileUtil.readBytes(realPath);
            os.write(bytes);
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new CustomException("500" , "文件下载失败");
        }
    }
}
