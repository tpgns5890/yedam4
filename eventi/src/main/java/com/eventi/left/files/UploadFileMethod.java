package com.eventi.left.files;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.eventi.left.files.mapper.FilesMapper;
import com.eventi.left.files.service.FilesVO;

@Service
public class UploadFileMethod {
	
	@Autowired
	FilesMapper service;
	
	//파일저장된 폴더경로.
	@Value("${spring.servlet.multipart.location}")
	String filePath;

	// 첨부파일 테이블 사용시 쓰이는 메소드
	public List<FileDto> uploadFiles(MultipartFile[] uploadfile, String targetId, String category) throws IllegalStateException, IOException {
		
		List<FileDto> list = new ArrayList<FileDto>();
		
		//해당하는 타겟의 파일이 이미있을경우, 삭제후 insert
		List<FilesVO>fileCheck = service.fileList(targetId);
		if(fileCheck.size() > 0) {
			service.deleteFile(targetId);
		}
		
		// 파일 경로위치에 물리적으로 저장하기
		for (MultipartFile file : uploadfile) {
			System.out.println("==========file: " + file);
			if (!file.isEmpty()) {
				System.out.println("==========file222: " + file);
				FileDto dto = new FileDto(UUID.randomUUID().toString(), file.getOriginalFilename(), file.getContentType());
				list.add(dto);

				File newFileName = new File(dto.getUuid() + "_" + dto.getFileName());

				file.transferTo(newFileName);
				System.out.println(newFileName);
			}
		}

		// 저장한 파일 DB 저장하기
		for (int i = 0; i < list.size(); i++) {
			FilesVO file = new FilesVO();
			file.setfNm(list.get(i).getFileName()); //원본파일명
			file.setSevNm(list.get(i).getUuid() + "_" + list.get(i).getFileName());//서버파일명.
			file.setTargetId(targetId); // 공고번호
			file.setCategory(category); // 카테고리(공모전)
			file.setSaveAddr(filePath); // 저장경로
			service.insertFile(file);
		}
		return list;
	}

	// 첨부파일 수정시.
//		public List<FileDto> updateFiles(MultipartFile[] uploadfile, String targetId, String category) throws IllegalStateException, IOException {
//			
//			List<FileDto> list = new ArrayList<>();
//			List<FilesVO> filesChecklist = new ArrayList<>();
//			FilesVO fileVO = new FilesVO();
//			
//			// 파일이 없다면. insert
//			if(service.fileList(targetId).size() == 0) {
//				uploadFiles(uploadfile,targetId, category);
//			}
//			
//			// 파일 경로위치에 물리적으로 저장하기
//			for (MultipartFile file : uploadfile) {
//				if (!file.isEmpty()) {
//					FileDto dto = new FileDto(UUID.randomUUID().toString(), file.getOriginalFilename(), file.getContentType());
//					list.add(dto);
//
//					File newFileName = new File(dto.getUuid() + "_" + dto.getFileName());
//
//					file.transferTo(newFileName);
//					System.out.println(newFileName);
//				}
//			}
//			filesChecklist = service.fileList(targetId);
//			
//			// 저장한 파일 DB 저장하기
//			for (int i = 0; i < list.size(); i++) {
//				fileVO.setfNo(filesChecklist.get(i).getfNo()); //여러건수정
//				fileVO.setfNm(list.get(i).getFileName()); //원본파일명
//				fileVO.setSevNm(list.get(i).getUuid() + "_" + list.get(i).getFileName());//서버파일명.
//				fileVO.setTargetId(targetId); // 공고번호
//				fileVO.setCategory(category); // 카테고리(공모전)
//				fileVO.setSaveAddr(filePath); // 저장경로
//				service.updateFile(fileVO);
//			}
//			return list;
//		}
	

	

}