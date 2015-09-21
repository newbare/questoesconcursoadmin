package br.com.questoesconcursoadmin.mb;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

@ManagedBean
@SessionScoped
public class TesteMB {

	public String teste(){
		
		System.out.println("TESTEEEEE");
		
		return null;
	}
	
	private SaltSource saltSource;
	
	public static void main (String args[]){
		
		
		Md5PasswordEncoder md5 = new Md5PasswordEncoder();  
		String strEncodedPassword = md5.encodePassword("admin", null);  
		System.out.println(strEncodedPassword);  
		
	}
	
}
