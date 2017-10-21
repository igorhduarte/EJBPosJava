package br.com.rp.services;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class ContaCorrenteDesempenhoInterceptor {
	
	private static final SimpleDateFormat DF = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
	                     
    @AroundInvoke
    public Object LogTempo(InvocationContext ic) throws Exception {
    	Calendar c = Calendar.getInstance();
    	System.out.println("Data de inicio: " + DF.format(c.getTime()));
    	try {
    		return ic.proceed();
    	} finally {
    		c = Calendar.getInstance();
    		System.out.println("Data final: " + DF.format(c.getTime()));
    	}
    	
    }
    
}
