package kr.or.ddit.vo;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class TestMember {
	private String userId;
	private String password;
	private String userName;
	private String email;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthDay;
	private String gender;
	private String developer;
	private boolean foreigner;
	private String nationality;
	private List<String> carList;
	private List<String> hobbyList;
	private String post;
	private String address;
	private List<Card> cardList;
	private String introduction;
}
