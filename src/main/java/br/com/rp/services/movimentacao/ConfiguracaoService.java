package br.com.rp.services.movimentacao;

import java.util.Calendar;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import br.com.rp.domain.Configuracao;
import br.com.rp.domain.Usuario;
import br.com.rp.repository.ConfiguracaoRepository;
import br.com.rp.repository.impl.AbstractRepositoryImpl;
import br.com.rp.repository.impl.LogInterceptor;

@Stateless
public class ConfiguracaoService extends AbstractRepositoryImpl<Configuracao>  {
	
	@EJB
	private ConfiguracaoRepository configuracaoRepository;

	public ConfiguracaoService() {
		super(Configuracao.class);
	}
	
	@Interceptors({LogInterceptor.class,PermissaoGerenteSegurancaInterceptor.class})
	public void alterarConfiguracao(Usuario usuarioLogado, Calendar horarioMinimoMovimentacao, Calendar horarioMaximoMovimentacao){
		
		Configuracao configuracao = configuracaoRepository.findById(1L);
		configuracao.setHorarioMinimoMovimentacao(horarioMinimoMovimentacao);
		configuracao.setHorarioMinimoMovimentacao(horarioMinimoMovimentacao);
		configuracaoRepository.save(configuracao);	
	}
	
}
