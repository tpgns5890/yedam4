package com.eventi.left.files;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.eventi.left.files.mapper.FilesMapper;

@Controller
public class FileController {
   @Autowired
   FilesMapper mapper;
   @Autowired
   UploadFileMethod newUp;
   
   // 이미지파일 이름값 얻을때 사용
   // 경로에 이미지파일만 업로드됨, db에는 등록x
   @PostMapping("/getImgName")
   @ResponseBody
   public String bandImgUpload(@RequestParam("file") MultipartFile uploadfile, Model model) throws IllegalStateException, IOException{    
      String newName = null;
     
      //경로에 저장된 파일명 반환
      newName = newUp.uploadOnce(uploadfile, model);
      return newName;
   }
   
   //첨부파일 여러개 업로드할 때 사용
   @PostMapping("/usedfilesUpload")
   public String filesUpload(@RequestParam MultipartFile[] file, Model model, String targetId, String category) throws IllegalStateException, IOException{    
     
	   List<FileDto> list= new ArrayList<FileDto>();
      //파일 업로드하는 기능 부르기+데베에 저장하기/첨부파일 테이블에 저장할 때 쓰임
      list = newUp.uploadFiles(file, model, targetId, category);
      return "redirect:/contest/List"; //공모전전체리스트 임시..
   }
   


}