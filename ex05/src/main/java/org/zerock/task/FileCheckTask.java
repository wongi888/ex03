package org.zerock.task;

import java.util.List;

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
	
	
	@Scheduled(cron="0 * * * * *")
	public void checkFiles()throws Exception{
		
		log.warn("File Check Task run.................");
		
		
		List<BoardAttachVO> fileList = attachMapper.getOldFiles();
		
		
		fileList.forEach(attach -> {
			
			log.info(attach);
			
		});
		
		
		
	}

}
