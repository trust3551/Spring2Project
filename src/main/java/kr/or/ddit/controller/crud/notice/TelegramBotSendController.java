package kr.or.ddit.controller.crud.notice;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class TelegramBotSendController {

		public void send(String name, String title, String type) throws Exception{
			String chat_id = "-4567683210";
			// 메세지를 전송하기 위한 endPoint
			String urlName = "https://api.telegram.org/bot6310783618:AAEI3ptPtCArMaRYoKBHxfHhjOuYcDbVUaY/sendMessage";
			
			String text = typeTemplate(name, title, type);
			
			URL url  = new URL(urlName + "?chat_id=" + chat_id + "&text=" + URLEncoder.encode(text, "UTF-8"));
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("user-Agent", "Mozilla/5.0");
			int respCode = conn.getResponseCode();
			System.out.println("### 텔레그램 봇 API를 이용한 알림 결과 코드 : " + respCode);
		}

		private String typeTemplate(String name, String title, String type) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Date now = new Date();
			String date = format.format(now);
			
			String result = null;
			StringBuffer sb = null;
			
			if(name != null) {
				sb = new StringBuffer();
				sb.append("▩ 알림 - "  + date + "\n");
				
				switch (type) {
				case "login":
					sb.append(":::" + name + "님, LOGIN!");
					break;
				case "register":
					sb.append(":::" + name + "님, 가입완료!");
					break;
				case "notice":
					sb.append("::: NOTICE WRITE");
					sb.append("[제 목] " + title + "\n");
					sb.append("[작성자]" + name + "\n");
					break;
				default:
					break;
				}
				result = sb.toString();
			}
			
			return result;
		}
}
