package com.example.spring02.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;
import javax.imageio.ImageIO;
import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

public class UploadFileUtils {
	//로깅을 위한 변수
	private static final Logger logger 
		= LoggerFactory.getLogger(UploadFileUtils.class);

	public static String uploadFile(String uploadPath
			, String originalName, byte[] fileData) 
					throws Exception {
		// uuid 발급
		UUID uid = UUID.randomUUID();
		String savedName = uid.toString() + "_" + originalName;
		// 업로드할 디렉토리 생성
		String savedPath = calcPath(uploadPath);
		File target = new File(uploadPath 
				+ savedPath, savedName);
		// 임시 디렉토리에 업로드된 파일을 지정된 디렉토리로 복사
		FileCopyUtils.copy(fileData, target);
		// 파일의 확장자 검사(.다음 마지막 이름이 확장자임을 활용)
		// a.jpg / aaa.bbb.ccc.jpg
		String formatName = originalName.substring(
				originalName.lastIndexOf(".") + 1);
		String uploadedFileName = null;
		// 이미지 파일은 썸네일(작은 이미지) 사용
		if (MediaUtils.getMediaType(formatName) != null) {
			// 썸네일 생성
			uploadedFileName = makeThumbnail(uploadPath
					, savedPath, savedName);
		} else {
			uploadedFileName = makeIcon(uploadPath, savedPath
					, savedName);
		}
		return uploadedFileName;
	}
//썸네일 이미지(아이콘) 생성, pom.xml에서 썸네일 관련 라이브러리 셋팅해 두었음
	private static String makeIcon(String uploadPath
			, String path, String fileName) throws Exception {
		// 아이콘의 이름
		String iconName = uploadPath + path + File.separator 
				+ fileName;
		// 아이콘 이름을 리턴
		// File.separatorChar : 디렉토리 구분자
		// 윈도우 \ , 유닉스(리눅스) /
		return iconName.substring(uploadPath.length())
				.replace(File.separatorChar, '/');
	}

	private static String makeThumbnail(String uploadPath
			, String path, String fileName) throws Exception {
		// 이미지를 읽기 위한 버퍼
		BufferedImage sourceImg = ImageIO.read(
				new File(uploadPath + path, fileName));
		// 100픽셀 단위의 썸네일 생성
		BufferedImage destImg = Scalr.resize(
				sourceImg, Scalr.Method.AUTOMATIC
				, Scalr.Mode.FIT_TO_HEIGHT, 100);
		// 썸네일의 이름
		String thumbnailName = uploadPath + path 
				+ File.separator + "s_" + fileName;
		File newFile = new File(thumbnailName);
		String formatName = fileName.substring(
				fileName.lastIndexOf(".") + 1);
		// 썸네일 생성
		ImageIO.write(
				destImg, formatName.toUpperCase(), newFile);
		// 썸네일의 이름을 리턴함
		return thumbnailName.substring(
uploadPath.length()).replace(File.separatorChar, '/');
	}
	//날짜처리 (년,00월,00일)
	private static String calcPath(String uploadPath) {
		Calendar cal = Calendar.getInstance();
		String yearPath = File.separator 
				+ cal.get(Calendar.YEAR);
		String monthPath = yearPath + File.separator 
				+ new DecimalFormat("00").format(
						cal.get(Calendar.MONTH) + 1);
		String datePath = monthPath + File.separator 
				+ new DecimalFormat("00").format(cal.get(
						Calendar.DATE));
		//디렉토리 생성 메소드 호출
		makeDir(uploadPath, yearPath, monthPath, datePath);
		logger.info(datePath);
		return datePath;
	}

	//디렉토리 생성
	private static void makeDir(String uploadPath
			, String... paths) { 
		//String... 은 가변사이즈 매개변수 (배열의 요소가 몇개든 상관없이 처리)
		//paths가 바로 위 yearPath, monthPath, datePath 를 다 처리함.
		//디렉토리가 존재하면 skip
		if (new File(paths[paths.length - 1]).exists()) {
			return;
		}
		for (String path : paths) {
			File dirPath = new File(uploadPath + path);
			if (!dirPath.exists()) {
				dirPath.mkdir(); // 디렉토리 생성
			}
		}
	}
}


