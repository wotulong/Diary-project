package com.yzz.diary.controller;

import java.util.Locale;
import java.util.Map;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



import com.yzz.diary.email.EmailSender;
import com.yzz.diary.utils.JsonMapper;
import com.yzz.diary.utils.RSAUtil;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	
	@Autowired
	private EmailSender emailSender;
	
	private JsonMapper jsonMapper = new JsonMapper();
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET, produces="application/json")
	@ResponseBody
	public String home(Locale locale, Model model, @RequestParam(name="test",defaultValue="")String test ) {
		logger.info("Welcome home! The client locale is {}.", locale);
		String pubKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC4Kotllw6b1pjox67f1Y6J7Ke/3S+3oIdqHEY8bls+KSALHzCsiDUnDbwhVy2f6HdEqrylSSQaZ06iLPoevJK4KvN46DWt0turickstYBWqAJAhxHoYgK1z1hvba9EdINzWTWIe6t66Y3ldpAqYpu7TRqTRtQd33Mjasv6pR4aHwIDAQAB";
		String priKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALgqi2WXDpvWmOjHrt/Vjonsp7/dL7egh2ocRjxuWz4pIAsfMKyINScNvCFXLZ/od0SqvKVJJBpnTqIs+h68krgq83joNa3S26uJySy1gFaoAkCHEehiArXPWG9tr0R0g3NZNYh7q3rpjeV2kCpim7tNGpNG1B3fcyNqy/qlHhofAgMBAAECgYBq6dfu7BjBA33VdUbiDrom+8oOqjtvYLP8N9nWb0Js/bbqffrlzlT9DQ4wVMnZ0TycEnpdge6BnYOaxn2VpmeNosvzOWJmnKdJ6ODfl63r9weOjrS2TdS0MsQ6rHkWb8p/y/5hwB4Bc8Jpr7iP74PH2GH9X9/jMpEyCQrQ8YAPEQJBAN0R6c7EHSaX9eYkfgTda2A7PUxbEJTDn50eTakd+DRGz94DiPE8kpa/KanoOpDGk7t/Cj8lpEgfqvyZgbEMSK0CQQDVQ+fNMlC+QfhAkI93EaYdzpeHLx5NJvUYNgf6j2vQ6TgPnZ5jcuTQCv/hJmKAx9l5c+DCeaJPZMXILldVrst7AkBzkBCsEKK4cQmPNBZYTrL5zB3gE16YlaaTiKrKNCtykG3LPgfRK3vpp6aM9iPfWJ8TjXZ6yh+VwFAu2cQNHEHFAkEAlRS8FMhIg0dweAnWEEEpI4sIh/qbLwm9qagTmcbqcoalptzjHNfzNa+KweRoVWffEbS8eJY4rVFfpRkn+gCPwwJARXjLtSiNsXtBXDM1UgfNC0ghLiBbfjeJ9SRBiQmSVmcxPGbN1ArVhpbL1F7O3rucygYJwZJfTiPME3oeUu3ClQ==";

		try{
			Map<String,Object> kMap = RSAUtil.generateKeys();
			
			
			String d1 = "hello,褰卞瓙銆�._A|@!\\=aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa123\n娴嬭瘯";
			String d2 = "鍔犲瘑test!_.\\=Abc";
			
			System.out.println("pubKey: "+pubKey);
			System.out.println("priKey: "+priKey);

			
			byte[] priEncyData = RSAUtil.encyByPriKey( d1.getBytes(), priKey );
			byte[] pubEncyData = RSAUtil.encyByPubKey( d2.getBytes(), pubKey);
			
			System.out.println("priEncyData:"+ (new String(priEncyData)) );
			System.out.println("pubEncyData:"+(new String(pubEncyData)) );
			
			byte[] priDecyData = RSAUtil.decyByPubKey( priEncyData, pubKey);
			byte[] pubDecyData = RSAUtil.decyByPriKey( pubEncyData, priKey );
			
			System.out.println("priDecyData:"+(new String(priDecyData)) );
			System.out.println("pubDecyData:"+(new String(pubDecyData)) );
			

		}catch( Exception e ){
			e.printStackTrace();
		}
		
		//User user = userService.findUserByName("閮戜功鍑�").get(0);
		if( StringUtils.isBlank(test) ){
			return pubKey;
		}
		try{
			System.out.println("test:"+test);
			byte[] pubDecyData = RSAUtil.decyByPriKey( test.getBytes(), priKey );
			System.out.println("pubDecyData:"+(new String(pubDecyData)) );
		}catch( Exception e){
			e.printStackTrace();
		}

		return "Baby,I love you! \n --------------- 閮戜功鍑�";
	}
	
	
	@RequestMapping(value = "/1", method = RequestMethod.GET, produces="application/json")
	@ResponseBody
	public String home2(Locale locale, Model model, @RequestParam(name="test",defaultValue="")String test ) {
		logger.info("Welcome home! The client locale is {}.", locale);
		String pubKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC4Kotllw6b1pjox67f1Y6J7Ke/3S+3oIdqHEY8bls+KSALHzCsiDUnDbwhVy2f6HdEqrylSSQaZ06iLPoevJK4KvN46DWt0turickstYBWqAJAhxHoYgK1z1hvba9EdINzWTWIe6t66Y3ldpAqYpu7TRqTRtQd33Mjasv6pR4aHwIDAQAB";
		String priKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALgqi2WXDpvWmOjHrt/Vjonsp7/dL7egh2ocRjxuWz4pIAsfMKyINScNvCFXLZ/od0SqvKVJJBpnTqIs+h68krgq83joNa3S26uJySy1gFaoAkCHEehiArXPWG9tr0R0g3NZNYh7q3rpjeV2kCpim7tNGpNG1B3fcyNqy/qlHhofAgMBAAECgYBq6dfu7BjBA33VdUbiDrom+8oOqjtvYLP8N9nWb0Js/bbqffrlzlT9DQ4wVMnZ0TycEnpdge6BnYOaxn2VpmeNosvzOWJmnKdJ6ODfl63r9weOjrS2TdS0MsQ6rHkWb8p/y/5hwB4Bc8Jpr7iP74PH2GH9X9/jMpEyCQrQ8YAPEQJBAN0R6c7EHSaX9eYkfgTda2A7PUxbEJTDn50eTakd+DRGz94DiPE8kpa/KanoOpDGk7t/Cj8lpEgfqvyZgbEMSK0CQQDVQ+fNMlC+QfhAkI93EaYdzpeHLx5NJvUYNgf6j2vQ6TgPnZ5jcuTQCv/hJmKAx9l5c+DCeaJPZMXILldVrst7AkBzkBCsEKK4cQmPNBZYTrL5zB3gE16YlaaTiKrKNCtykG3LPgfRK3vpp6aM9iPfWJ8TjXZ6yh+VwFAu2cQNHEHFAkEAlRS8FMhIg0dweAnWEEEpI4sIh/qbLwm9qagTmcbqcoalptzjHNfzNa+KweRoVWffEbS8eJY4rVFfpRkn+gCPwwJARXjLtSiNsXtBXDM1UgfNC0ghLiBbfjeJ9SRBiQmSVmcxPGbN1ArVhpbL1F7O3rucygYJwZJfTiPME3oeUu3ClQ==";

		try{
			Map<String,Object> kMap = RSAUtil.generateKeys();
			
			
			String d1 = "hello,褰卞瓙銆�._A|@!\\=aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa123\n娴嬭瘯";
			String d2 = "鍔犲瘑test!_.\\=Abc";
			
			System.out.println("pubKey: "+pubKey);
			System.out.println("priKey: "+priKey);

			
			byte[] priEncyData = RSAUtil.encyByPriKey( d1.getBytes(), priKey );
			byte[] pubEncyData = RSAUtil.encyByPubKey( d2.getBytes(), pubKey);
			
			System.out.println("priEncyData:"+ (new String(priEncyData)) );
			System.out.println("pubEncyData:"+(new String(pubEncyData)) );
			
			byte[] priDecyData = RSAUtil.decyByPubKey( priEncyData, pubKey);
			byte[] pubDecyData = RSAUtil.decyByPriKey( pubEncyData, priKey );
			
			System.out.println("priDecyData:"+(new String(priDecyData)) );
			System.out.println("pubDecyData:"+(new String(pubDecyData)) );
			

		}catch( Exception e ){
			e.printStackTrace();
		}
		
		//User user = userService.findUserByName("閮戜功鍑�").get(0);
		if( StringUtils.isBlank(test) ){
			return priKey;
		}
		try{
			System.out.println("test:"+test);
			byte[] pubDecyData = RSAUtil.decyByPriKey( test.getBytes(), priKey );
			System.out.println("pubDecyData:"+(new String(pubDecyData)) );
		}catch( Exception e){
			e.printStackTrace();
		}

		return "Baby,I love you! \n --------------- 閮戜功鍑�";
	}
	
	@RequestMapping( value="/mail", produces="application/json" )
	@ResponseBody
	public String sendEmail(){
		emailSender.sendMail("zskyouxiang@126.com", "www.baidu.com");

		return "鍙戦�侀偖浠讹紒";
	}
	
	@RequestMapping( value="/index")
	public String hello(){
		logger.warn("warn test!");
		return "/hello/hello";
	}
}
