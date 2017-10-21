package br.com.rp.services;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;



@Stateless
public class ShowMessage {	
	@Interceptors(InterceptorClass.class)	
	public void showMessage(){
		System.out.println();
	}
	
}

