package br.com.estrela.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.estrela.model.Bonificacao;
import br.com.estrela.model.Cliente;
import br.com.estrela.model.CondicaoPagamento;
import br.com.estrela.model.Devolucao;
import br.com.estrela.model.Estoque;
import br.com.estrela.model.Faturamento;
import br.com.estrela.model.Produto;
import br.com.estrela.model.RamoAtividade;
import br.com.estrela.model.Vendedor;
import br.com.estrela.model.api.QueryModel;

@Component
public class QueryServiceClient {
	
	@Autowired
	private Configuracao configuracao;
	
	private HttpEntity<QueryModel> getHttpEntity(QueryModel queryModel) {
		
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<QueryModel> reqEntity = new HttpEntity<>(queryModel, headers);
		
		return reqEntity;
	}
	
	public List<Produto> carregaProdutos(QueryModel queryModel) {
		
		RestTemplate template = new RestTemplate();
		
		HttpEntity<QueryModel> reqEntity = getHttpEntity(queryModel); 
		
		Produto[] produtos = template.postForObject(configuracao.getUrlQueryService(), reqEntity, Produto[].class);
		
		return Arrays.asList(produtos);
		
	}
	
	public List<Cliente> carregaClientes(QueryModel queryModel) {
		
		RestTemplate template = new RestTemplate();
		
		HttpEntity<QueryModel> reqEntity = getHttpEntity(queryModel); 
		
		Cliente[] clientes = template.postForObject(configuracao.getUrlQueryService(), reqEntity, Cliente[].class);
		
		return Arrays.asList(clientes);
	}
	
	public List<CondicaoPagamento> carregaCondicoesPagamento(QueryModel queryModel) {
		
		RestTemplate template = new RestTemplate();
				
		HttpEntity<QueryModel> reqEntity = getHttpEntity(queryModel); 
		
		CondicaoPagamento[] condicoes = template.postForObject(configuracao.getUrlQueryService(), reqEntity, CondicaoPagamento[].class);
		
		return Arrays.asList(condicoes);
	}
	
	public List<RamoAtividade> carregaRamosAtividade(QueryModel queryModel) {
		
		RestTemplate template = new RestTemplate();
		
		HttpEntity<QueryModel> reqEntity = getHttpEntity(queryModel) ;
		
		RamoAtividade[] ramos = template.postForObject(configuracao.getUrlQueryService(), reqEntity, RamoAtividade[].class);
		
		return Arrays.asList(ramos);
	}
	
	public List<Estoque> carregaEstoque(QueryModel queryModel) {
		
		RestTemplate template = new RestTemplate();
		
		HttpEntity<QueryModel> reqEntity = getHttpEntity(queryModel); 
		
		Estoque[] estoque = template.postForObject(configuracao.getUrlQueryService(), reqEntity, Estoque[].class);
		
		return Arrays.asList(estoque);
	}
	
	public List<Vendedor> carregaVendedor(QueryModel queryModel) {
		
		RestTemplate template = new RestTemplate();
		
		HttpEntity<QueryModel> reqEntity = getHttpEntity(queryModel); 
		
		Vendedor[] vendedores = template.postForObject(configuracao.getUrlQueryService(), reqEntity, Vendedor[].class);
		
		return Arrays.asList(vendedores);
	}
	
	public List<Faturamento> carregaFaturamento(QueryModel queryModel) {
		
		RestTemplate template = new RestTemplate();
		
		HttpEntity<QueryModel> reqEntity = getHttpEntity(queryModel); 
		
		Faturamento[] faturamento = template.postForObject(configuracao.getUrlQueryService(), reqEntity, Faturamento[].class);
		
		return Arrays.asList(faturamento);
	}
	
	public List<Devolucao> carregaDevolucao(QueryModel queryModel) {
		
		RestTemplate template = new RestTemplate();
		
		HttpEntity<QueryModel> reqEntity = getHttpEntity(queryModel); 
		
		Devolucao[] devolucao = template.postForObject(configuracao.getUrlQueryService(), reqEntity, Devolucao[].class);
		
		return Arrays.asList(devolucao);
	}
	
	public List<Bonificacao> carregaBonificacao(QueryModel queryModel) {
		
		RestTemplate template = new RestTemplate();
		
		HttpEntity<QueryModel> reqEntity = getHttpEntity(queryModel); 
		
		Bonificacao[] bonificacao = template.postForObject(configuracao.getUrlQueryService(), reqEntity, Bonificacao[].class);
		
		return Arrays.asList(bonificacao);
	}

}
