package kr.or.ddit.vo.test;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class ProductVO implements Serializable{
	private static final long serialVersionUID = 1L;
	private int prodNo;
	private String prodId;
	private String prodName;
	private long prodPrice;
	private String prodColor;
	private String prodDescription;
	private String prodRegDate;
	
	private String prodThumbnail;
	@JsonIgnore
	private MultipartFile prodImageFile;
	
	public ProductVO() {}
	public ProductVO(int pNo, String pId, String pName, long pPrice, String pColor, String pDescription) {
		this.prodNo = pNo;
		this.prodId = pId;
		this.prodName = pName;
		this.prodPrice = pPrice;
		this.prodColor = pColor;
		this.prodDescription = pDescription;
		this.prodRegDate = getCurrentTime();
	}
	
	private String getCurrentTime() {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		return formatter.format(calendar.getTime());
	}
}
