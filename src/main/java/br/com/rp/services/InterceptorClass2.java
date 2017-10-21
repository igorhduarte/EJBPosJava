package br.com.rp.services;

import java.time.LocalTime;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class InterceptorClass2 {

	@AroundInvoke
	public Object validateTimeInterceptor(InvocationContext invocation) throws Exception{
		LocalTime startTime = LocalTime.of(8, 0);
		LocalTime endTime = LocalTime.of(18, 0);
		LocalTime currentTime = LocalTime.now();
		try{
			if(currentTime.isAfter(startTime) && currentTime.isBefore(endTime)){
				return invocation.proceed();	
			}			
			return null;	
		}finally{
			System.out.println("validate");	
		}
   }

}
