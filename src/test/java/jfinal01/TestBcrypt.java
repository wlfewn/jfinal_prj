package jfinal01;

import org.junit.Test;
import org.mindrot.jbcrypt.BCrypt;

/**
 * 
 */
public class TestBcrypt {
	
	@Test
	public void test01(){
		//$2a$10$HmSdTZ2rI9xQlwzYYSyQgeuoXCEQZm8AmP4WkH1slGNYnBYCSkfZ2
		String encodePwd = BCrypt.hashpw("admin123", BCrypt.gensalt());
		System.out.println(encodePwd);
	}
	
	@Test
	public void test02(){
		String hashed = "$2a$10$HmSdTZ2rI9xQlwzYYSyQgeuoXCEQZm8AmP4WkH1slGNYnBYCSkfZ2";
		System.out.println(BCrypt.checkpw("admin123", hashed));
	}
}
