package br.com.rp.services;

import java.io.IOException;
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
public class EstadoCacheService {

	private Map<String, Estado> estados = new HashMap<>();
	
	@PostConstruct
	public void carregarEstados(){
		try{
			Charset charset = Charset.forName("ISO_8859_1"); 
			Files.readAllLines(Paths.get("D:/PosJava/EJB/jee-blank-master/estados.txt"),charset).stream()
					.forEach(linha -> {
						String[] estado = linha.split(";");
						Estado uf = new Estado();
						uf.setSigla(estado[0]);
						uf.setNome(estado[1]);
						estados.put(uf.getSigla(), uf);
					});
			
		} catch (IOException e){
			throw new RuntimeException(e);
		}
	}
	
	@Lock(LockType.READ)
	public Estado geEstadoPorSigla(String sigla){
		return estados.get(sigla);
	}
	@Lock(LockType.READ)
	public List<Estado> getEstados(){
		return estados.entrySet().stream().map(entry -> entry.getValue())
					.collect(Collectors.toList());
	}
}
