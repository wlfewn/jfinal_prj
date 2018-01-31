package com.jfinal.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import com.jfinal.common.BaseService;
import com.jfinal.common.Constant;
import com.jfinal.message.Message;
import com.jfinal.message.MessageWithData;
import com.jfinal.message.Status;
import com.jfinal.message.utils.MessageUtil;
import com.jfinal.model.Images;
import com.jfinal.upload.UploadFile;
import com.jfinal.utils.SerialNumberUtil;
import com.jfinal.utils.SystemUtil;


public class ImagesService extends BaseService{

	/**
	 * 上传图片处理
	 * @param uploadFile
	 * @return
	 */
	public Message upload(UploadFile uploadFile) {
		File file = uploadFile.getFile();
		Path path = Paths.get(file.toURI());
		Message message;
		try {
			//图片处理
			String uploadPath = uploadFile.getUploadPath();
			String newFileName = SerialNumberUtil.createUUID();
			String fileName = uploadFile.getFileName();
			
			//获取图片后缀
			String fileType = fileName.substring(fileName.lastIndexOf("."));
			String newFileNameAndType = newFileName+fileType;
			String filePath = uploadPath+File.separator+newFileNameAndType;
			
			FileOutputStream fileOutputStream 
				= new FileOutputStream(new File(filePath));
			//1kb = 1024字节
			long bytes = Files.copy(path, fileOutputStream);
			
			Images images = new Images();
			images.setDistUrl(filePath);//绝对路径
			images.setId(SerialNumberUtil.createUUID());
			//用于url访问相对路径
			images.setRelativePath(Constant.uploadPath+"/"+newFileNameAndType);
			images.setCreateDate(new Date());
			images.setUpdateDate(images.getCreateDate());
			images.setSize(new Double(bytes));
			images.setName(newFileNameAndType);
			images.save();
			//上传成功，返回保存的新图片名称
			message = new MessageWithData(Status.success, "上传成功", images);
		} catch (IOException e) {
			e.printStackTrace();
			message = MessageUtil.createErrorMsg("上传失败");
		}
		//最后删除原来的文件
		try {
			Files.deleteIfExists(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return message;
	}
	
	/**
	 * 删除文件记录及文件
	 * @return
	 */
	public Message delete(String name){
		try {
			Images images =	Images.dao.findFirst("select * from t_images where name = ?",name);
			
			if( null == images )
				return MessageUtil.createErrorMsg("删除失败，文件不存在");
			
			boolean flag = Files.deleteIfExists(Paths.get(images.getDistUrl()));
			if(flag)//文件删除成功
				images.delete();//删除记录
			
			return MessageUtil.createSuccessMsg("删除成功");
		} catch (IOException e) {
			e.printStackTrace();
			return MessageUtil.createErrorMsg("删除失败，文件不存在");
		}
	}
	
	/**
	 * 根据id集合查找全部数据
	 * @param imagesArr
	 * @return
	 */
	public List<Images> findListByIds(String[] imagesArr){
		String inparam = SystemUtil.createIn(imagesArr);
		return Images.dao.find("select * from t_images where id in (?) ",inparam);
	}
	
	
}
