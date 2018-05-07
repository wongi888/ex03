package org.zerock.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.domain.AttachFileDTO;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;

@Controller
@Log4j
public class UploadController {
	
	@GetMapping("/uploadForm")
	public void uploadForm() {
		
		log.info("upload form");
	}
	
	private String getFolder() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Date date = new Date();
		
		String str = sdf.format(date);
		
		return str.replace("-", File.separator);
	}
	
	private boolean checkImageType(File file) {
		
		try {
			String contentType = Files.probeContentType(file.toPath());
			
			return contentType.startsWith("image");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	

	@PostMapping(value= "/uploadAjaxAction", produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<AttachFileDTO>> uploadAjaxPost(MultipartFile[] uploadFile) {
		
		List<AttachFileDTO> list = new ArrayList<>();
		
		
		String uploadFolder = "C:\\upload";
		
		String uploadFolderPath = getFolder();
		
		File uploadPath = new File(uploadFolder, uploadFolderPath);
		
		log.info("upload path: "+ uploadPath);
		
		if(uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}
		
		for (MultipartFile multipartFile : uploadFile) {
			
			log.info("-------------------------------------");
			log.info("Upload File Name: " +multipartFile.getOriginalFilename());
			log.info("Upload File Size: " +multipartFile.getSize());
			
			UUID uuid = UUID.randomUUID();
			
			String uploadFileName = multipartFile.getOriginalFilename();
			
			//IE has file path 
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") +1);
			
			log.info("Upload File Name with IE: " +uploadFileName);
			
			File saveFile = new File(uploadPath, uuid.toString()+"_"+uploadFileName);
			
			try {
				
				multipartFile.transferTo(saveFile);
				
				AttachFileDTO attachDTO = new AttachFileDTO();
				attachDTO.setFileName(uploadFileName);
				attachDTO.setUuid(uuid.toString());
				attachDTO.setUploadPath(uploadFolderPath);
				
				
				//check image type file 
				if(checkImageType(saveFile)) {
					
					attachDTO.setImage(true);
					
					FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, "s_"+uuid.toString()+"_"+uploadFileName)); 
					
					Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumbnail, 100, 100); 
					
					thumbnail.close();
					
				}
				
				//add to List
				list.add(attachDTO);
				
			} catch (Exception e) {
				log.error(e.getMessage());
			}//end catch
			
		}//end for 
		
		return new ResponseEntity<>(list, HttpStatus.OK);
		
	}
	
	@GetMapping("/display")
	@ResponseBody
	public ResponseEntity<byte[]> getFile(String fileName){
		
		log.info("fileName: " + fileName);
		
		File file = new File("c:\\upload\\" + fileName);
		
		log.info("file: " + file);
		
		ResponseEntity<byte[]> result = null;
		
		try {
			HttpHeaders  header = new HttpHeaders();
			
			header.add("Content-Type", Files.probeContentType(file.toPath()));
			result =  new ResponseEntity<>(FileCopyUtils.copyToByteArray(file) , header, HttpStatus.OK);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	

	
	

	
	
//	@PostMapping("/uploadAjaxAction")
//	public void uploadAjaxPost(MultipartFile[] uploadFile) {
//		
//		
//		String uploadFolder = "C:\\upload";
//		
//		String uploadFolderPath = getFolder();
//		
//		File uploadPath = new File(uploadFolder, uploadFolderPath);
//		
//		log.info("upload path: "+ uploadPath);
//		
//		if(uploadPath.exists() == false) {
//			uploadPath.mkdirs();
//		}
//		
//		for (MultipartFile multipartFile : uploadFile) {
//			
//			log.info("-------------------------------------");
//			log.info("Upload File Name: " +multipartFile.getOriginalFilename());
//			log.info("Upload File Size: " +multipartFile.getSize());
//			
//			UUID uuid = UUID.randomUUID();
//			
//			String uploadFileName = uuid.toString() +"_" + multipartFile.getOriginalFilename();
//			
//			File saveFile = new File(uploadPath, uploadFileName);
//			
//			try {
//				
//				multipartFile.transferTo(saveFile);
//				
//				//check image type file 
//				if(checkImageType(saveFile)) {
//					
//					FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, "s_"+ uploadFileName)); 
//					
//					Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumbnail, 100, 100); 
//					
//					thumbnail.close();
//				}
//				
//			} catch (Exception e) {
//				log.error(e.getMessage());
//			}//end catch
//			
//		}//end for 
//		
//	}	
	
	
	
	@PostMapping("/uploadFormAction")
	public void uploadFormPost(MultipartFile[] uploadFile, Model model) {
		
		String uploadFolder = "C:\\upload";
		
		for (MultipartFile multipartFile : uploadFile) {
			
			log.info("-------------------------------------");
			log.info("Upload File Name: " +multipartFile.getOriginalFilename());
			log.info("Upload File Size: " +multipartFile.getSize());
			
			
			File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());
			
			try {
				multipartFile.transferTo(saveFile);
			} catch (Exception e) {
				log.error(e.getMessage());
			}//end catch
			
		}//end for 
		
	}
	
	
	@GetMapping("/uploadAjax")
	public void uploadAjax() {
		
		log.info("upload ajax");
	}
}




