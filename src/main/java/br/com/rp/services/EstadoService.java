package br.com.rp.services;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;

import br.com.rp.domain.Estado;

@Singleton
public class EstadoService {	
	private Map<String, Estado> estados = new HashMap<String, Estado>();
	
	@PostConstruct
	public void carregarEstados(){
		Charset charset = Charset.forName("iso-8859-1");
		try{
			Files.readAllLines(Paths.get("D:/estado/estado.txt"), charset).stream()
			.forEach(x ->{
				String[] estado = x.split(";");
				Estado uf = new Estado();
				uf.setSigla(estado[0]);
				uf.setNome(estado[1]);
				estados.put(uf.getSigla(), uf);
			});
			
		}catch(Exception ex){
			throw new RuntimeException(ex); 
		}		
	}
	
	@Lock(LockType.READ)
	public Estado getEstadoPorSigla(String sigla){
		return estados.get(sigla);
	}
	
	@Lock(LockType.READ)
	public List<Estado> getEstados(){
		return estados.entrySet().stream().map(x->x.getValue()).collect(Collectors.toList());
	}	
	
}
