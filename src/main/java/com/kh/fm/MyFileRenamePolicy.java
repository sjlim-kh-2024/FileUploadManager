package com.kh.fm;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class MyFileRenamePolicy implements FileRenamePolicy {

	/**
	 * rename 메소드 재정의
	 * => 파일명 수정 후 수정된 파일을 반환해주는 메소드
	 */
	@Override
	public File rename(File orgFile) {

		// 원본 파일명 ( test.jpg )
		String originName = orgFile.getName();
		
		// => 수정할 파일명 ("20240912160000xxxxx.jpg") : 파일 업로드 시간(년월일시분초) + 5자리 랜덤값 (10000 ~ 99999) + 원본파일의 확장자
		
		// 1) 파일 업로드 시간 (년월일시분초)
		String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		
		// 2) 5자리 랜덤값 : Math.random()
		int randNum = (int)(Math.random() * 90000 + 10000);		// 12123
		
		// 3) 원본 파일의 확장자
		//    문자열.substring(마지막 .의 위치)
		//    				 -> 문자열.lastIndexOf(찾을문자열) => 마지막 위치
		String ext = originName.substring( originName.lastIndexOf(".") ); // => .jpg
		
		String changeName = currentTime + randNum + ext;	// -> 024091206000013123.jpg
		
		return new File(orgFile.getParent(), changeName);	// new File(원본파일 디렉토리, 변경할 파일명)
		
	}
	
	

}
