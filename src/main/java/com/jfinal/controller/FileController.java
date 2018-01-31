package com.jfinal.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.jfinal.core.Controller;
import com.jfinal.message.Message;
import com.jfinal.service.ImagesService;
import com.jfinal.upload.UploadFile;

/**
 * 文件上传action
 * @author geng
 *2018年1月29日
 */
public class FileController extends Controller{

	static ImagesService imagesService = new ImagesService();
	
	/**
	 * 文件上传
	 */
	public void upload(){
		UploadFile uploadFile = getFile();
		//System.out.println(uploadFile.getFileName());
		//System.out.println(uploadFile.getUploadPath());
		//System.out.println(uploadFile.getContentType());
		
		Message message = imagesService.upload(uploadFile);
		
		//返回json
		renderJson(message);
	}
	
	/**
	 * 删除图片
	 */
	public void delete(){
		String imageName = getPara("name");
		Message message = imagesService.delete(imageName);
		renderJson(message);
	}
}
