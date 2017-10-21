package br.com.rp.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class InterceptorClass {

	@AroundInvoke
	public Object printTimeInterceptor(InvocationContext invocation) throws Exception{
		System.out.println("iniciou as " + LocalDateTime.now());
		try{			
			return invocation.proceed();	
		}finally{
			System.out.println("Finalizou as " + LocalDateTime.now());	
		}
   }
	
	}
