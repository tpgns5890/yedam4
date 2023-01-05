package com.eventi.left.files.service;



import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
public class FilesVO { 
	String fNo;
	String targetId;
	String fNm;
	String sevNm;
	String saveAddr;
	String category;
}