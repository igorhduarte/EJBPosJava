package br.com.rp.repository.impl;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.ejb.EJB;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import br.com.rp.domain.Log;
import br.com.rp.domain.TipoOperacao;
import br.com.rp.repository.LogRepository;

public class LogInterceptor {

	@EJB
	private LogRepository repository;

	@AroundInvoke
	public Object aroundInvoke(InvocationContext ic) throws Exception {
		if (ic.getTarget() instanceof LogRepository) {
			return ic.proceed();
		} else {
			try {
				return ic.proceed();
			} finally {
				saveLog(ic.getTarget().getClass().getSimpleName(), ic.getMethod().getName(), ic.getParameters(), new GregorianCalendar().getTime());
			}
		}
	}

	private void saveLog(String className, String method, Object[] parameters, Date data) {
		Log log = new Log();
		log.setTipoOperacao(TipoOperacao.EXTRATO);
		log.setDetalhes(className + method + parameters);
		log.setUsuario("xpto");
		log.setData(data);
		repository.save(log);
	}

}
