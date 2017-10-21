package br.com.rp.services;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;


@Singleton
public class ContadorRequisicaoService {	
	public int contador;
	
	@Lock(LockType.READ)
	public int getAcesso(){
		return contador;
	}
	
	@Lock(LockType.WRITE)
	public void setAcesso(){
		contador++;
	}
}
