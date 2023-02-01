package com.eventi.left;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//@RunWith(SpringRunner.class)
//@SpringBootTest
class EventiApplicationTests {
	
	public static void main(String[] args) throws JsonProcessingException {

		   String url = "https://developers.nonghyup.com/ReceivedTransferOtherBank.nh";
			String bankName = "";//moneyVO.getBankName();
			String bankAccount = "";//moneyVO.getBankAccount();
			int moPrice = 0;//moneyVO.getMoPrice();
			
			LocalDate now = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
			String today = now.format(formatter);
			
			LocalTime nowTime = LocalTime.now();
			DateTimeFormatter tformatter = DateTimeFormatter.ofPattern("HHmmss");
			String formatedNow = nowTime.format(tformatter);
			String IsTuno = today+formatedNow;
		   
			Map<String, Object> param = new HashMap<String, Object>();
			Map<String, Object> header = new HashMap<String, Object>();
			header.put("ApiNm","ReceivedTransferOtherBank");
			header.put("Tsymd",today);
			header.put("Trtm",formatedNow);
			header.put("Iscd","001712");
			header.put("FintechApsno","001");
			header.put("ApiSvcCd","DrawingTransferA");
			header.put("IsTuno",IsTuno);
			header.put("AccessToken","2baa445640c38231cf67e1b099c3d4ec97a7e51a33dec5e5757cde32f5a86c4b");
			param.put("Header",header);
			param.put("Bncd", bankName);
			param.put("Acno", bankAccount);
			param.put("Tram", moPrice);
			param.put("DractOtlt", "이벤티");
			
			ObjectMapper obMapper = new ObjectMapper();
			System.out.println("ddd");
			System.out.println(obMapper.writeValueAsString(param));

	    }
}
