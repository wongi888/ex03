package org.zerock.task;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.zerock.domain.BoardAttachVO;
import org.zerock.mapper.BoardAttachMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Component
public class FileCheckTask  {
	
	@Setter(onMethod = @__({ @Autowired }))
	private BoardAttachMapper attachMapper;
	
	
	private String getFolderYesterDay() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Calendar cal = Calendar.getInstance();
		
		cal.add(Calendar.DATE, -1);

		String str = sdf.format(cal.getTime());

		return str.replace("-", File.separator);
	}
	
	@Scheduled(cron="0 0 2 * * *")
	public void checkFiles()throws Exception{
		
		log.warn("File Check Task run.................");
		
		//file list in database 
		List<BoardAttachVO> fileList = attachMapper.getOldFiles();
		
		//ready for check file in directory with database file list
		List<Path> fileListPaths = fileList.stream()
				.map(vo-> Paths.get("C:\\upload",vo.getUploadPath(), vo.getUuid()+"_"+ vo.getFileName()))
				.collect(Collectors.toList());
		
		//image file has thumnail file 
		fileList.stream().filter(vo -> vo.isFileType() == true)
				.map(vo-> Paths.get("C:\\upload",vo.getUploadPath(), "s_" +vo.getUuid()+"_"+ vo.getFileName()))
				.forEach(p ->fileListPaths.add(p));

		log.warn("===========================================");
		
		fileListPaths.forEach(p -> log.warn(p));
		
		//files in yesterday directory 
		Path targetPath = Paths.get("C:\\upload", getFolderYesterDay());
		
		List<Path> removeFilePaths = Files.walk(targetPath)
				.filter(file -> fileListPaths.contains(file) == false)
				.collect(Collectors.toList());
		
		
		removeFilePaths.forEach(file -> {
			try {
				Files.deleteIfExists(file);
			} catch (IOException e) {
				log.warn(e.getMessage());
			}
		});
	}
}


