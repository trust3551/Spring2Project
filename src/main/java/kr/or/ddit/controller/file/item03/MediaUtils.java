package kr.or.ddit.controller.file.item03;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;


public class MediaUtils {

	private static Map<String, MediaType> mediaMap;
	
	// static 그룹 선언
	static {
		mediaMap = new HashMap<String, MediaType>();
		mediaMap.put("JPG", MediaType.IMAGE_JPEG);
		mediaMap.put("GIF", MediaType.IMAGE_GIF);
		mediaMap.put("PNG", MediaType.IMAGE_PNG);
	}
	
	public static MediaType getMediaType(String type) {
		return mediaMap.get(type.toUpperCase());
	}
}
