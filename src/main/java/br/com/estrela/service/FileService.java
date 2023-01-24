package br.com.estrela.service;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estrela.config.Configuracao;
import br.com.estrela.model.Bonificacao;
import br.com.estrela.model.Cliente;
import br.com.estrela.model.CondicaoPagamento;
import br.com.estrela.model.Constantes;
import br.com.estrela.model.Devolucao;
import br.com.estrela.model.Estoque;
import br.com.estrela.model.Faturamento;
import br.com.estrela.model.Produto;
import br.com.estrela.model.RamoAtividade;
import br.com.estrela.model.Vendedor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FileService {
	
	@Autowired
	private LoadDataService loadDataService;
	
	@Autowired
	private Configuracao config;
	
	public void geraArquivos() {
		
		log.info("Iniciando a geração de arquivos");
		log.info("Repositório de arquivos: " + config.getRepositorioArquivos());
		log.info("Owner tabelas: " + (config.getOwnerTabelas() == null ? "usuário conexão" : config.getOwnerTabelas()));
		
		geraArquivoBonificacao();
		geraArquivoClientes();
		geraArquivoCondicoesPagamento();
		geraArquivoDevolucao();
		geraArquivoEstoque();
		geraArquivoFaturamento();
		geraArquivoProdutos();
		geraArquivoRamosDeAtividade();
		geraArquivoVendedor();
		
		log.info("Geração de arquivos finalizada");
	}
	
	public void geraArquivoProdutos() {
		log.info("Gerando arquivo DI de PRODUTOS");
		try {
			List<Produto> produtos = loadDataService.carregaProdutoDi();
			
			File file = new File(config.getRepositorioArquivos() + "/" + getDataFormatada() + "_PRODUTOS.txt");
			
			PrintWriter out = new PrintWriter(file);
			out.println(Constantes.PRODUTOS_CABECALHO);
			for(Produto p : produtos) {
				out.println(p.toString());
			}
			out.flush();
			out.close();
			log.info(String.format("Foram escritas %d linhas", produtos.size() + 1));
			log.info("Arquivo DI de PRODUTOS gerado com sucesso");
		} catch (IOException e) {
			throw new RuntimeException("Ocorreu um erro ao gerar o arquivo de produtos", e);
		}
		
	}
	
	public void geraArquivoClientes() {
		log.info("Gerando arquivo DI de CLIENTES");
		try {
			List<Cliente> clientes = loadDataService.carregaClienteDi();
			
			File file = new File(config.getRepositorioArquivos() + "/" + getDataFormatada() + "_CLIENTES.txt");
			
			PrintWriter out = new PrintWriter(file);
			out.println(Constantes.CLIENTES_CABECALHO);
			for(Cliente c : clientes) {
				out.println(c.toString());
			}
			out.flush();
			out.close();
			log.info(String.format("Foram escritas %d linhas", clientes.size() + 1));
			log.info("Arquivo DI de CLIENTES gerado com sucesso");
		} catch (IOException e) {
			throw new RuntimeException("Ocorreu um erro ao gerar o arquivo de clientes", e);
		}
		
	}
	
	public void geraArquivoCondicoesPagamento() {
		log.info("Gerando arquivo DI de CONDIÇÕES DE PAGAMENTO");
		try {
			List<CondicaoPagamento> condsPagamento = loadDataService.carregaCondicaoPagamentoDi();
			
			File file = new File(config.getRepositorioArquivos() + "/" + getDataFormatada() + "_CONDPAGTO.txt");
			
			PrintWriter out = new PrintWriter(file);
			out.println(Constantes.CONDSPAGTO_CABECALHO);
			for(CondicaoPagamento c : condsPagamento) {
				out.println(c.toString());
			}
			out.flush();
			out.close();
			log.info(String.format("Foram escritas %d linhas", condsPagamento.size() + 1));
			log.info("Arquivo DI de CONDIÇÕES DE PAGAMENTO gerado com sucesso");
		} catch (IOException e) {
			throw new RuntimeException("Ocorreu um erro ao gerar o arquivo de condições de pagamento", e);
		}
		
	}
	
	public void geraArquivoRamosDeAtividade() {
		log.info("Gerando arquivo DI de RAMOS DE ATIVIDADE");
		try {
			List<RamoAtividade> ramosAtividade = loadDataService.carregaRamosAtividadeDi();
			
			File file = new File(config.getRepositorioArquivos() + "/" + getDataFormatada() + "_RAMO_ATIVIDADE.txt");
			
			PrintWriter out = new PrintWriter(file);
			out.println(Constantes.RAMOSATIVIDADE_CABECALHO);
			for(RamoAtividade r : ramosAtividade) {
				out.println(r.toString());
			}
			out.flush();
			out.close();
			log.info(String.format("Foram escritas %d linhas", ramosAtividade.size() + 1));
			log.info("Arquivo DI de RAMOS DE ATIVIDADE gerado com sucesso");
		} catch (IOException e) {
			throw new RuntimeException("Ocorreu um erro ao gerar o arquivo de ramos de atividade", e);
		}
		
	}
	
	public void geraArquivoEstoque() {
		log.info("Gerando arquivo DI de ESTOQUE");
		try {
			List<Estoque> estoque = loadDataService.carregaEstoqueDi();
			
			File file = new File(config.getRepositorioArquivos() + "/" + getDataFormatada() + "_ESTOQUE.txt");
			
			PrintWriter out = new PrintWriter(file);
			out.println(Constantes.ESTOQUE_CABECALHO);
			for(Estoque r : estoque) {
				out.println(r.toString());
			}
			out.flush();
			out.close();
			log.info(String.format("Foram escritas %d linhas", estoque.size() + 1));
			log.info("Arquivo DI de ESTOQUE gerado com sucesso");
		} catch (IOException e) {
			throw new RuntimeException("Ocorreu um erro ao gerar o arquivo de ramos de atividade", e);
		}
		
	}
	
	public void geraArquivoVendedor() {
		log.info("Gerando arquivo DI de VENDEDORES");
		try {
			List<Vendedor> vendedores = loadDataService.carregaVendedoresDi();
			
			File file = new File(config.getRepositorioArquivos() + "/" + getDataFormatada() + "_VENDEDOR.txt");
			
			PrintWriter out = new PrintWriter(file);
			out.println(Constantes.VENDEDOR_CABECALHO);
			for(Vendedor v : vendedores) {
				out.println(v.toString());
			}
			out.flush();
			out.close();
			log.info(String.format("Foram escritas %d linhas", vendedores.size() + 1));
			log.info("Arquivo DI de VENDEDORES gerado com sucesso");
		} catch (IOException e) {
			throw new RuntimeException("Ocorreu um erro ao gerar o arquivo de vendedores", e);
		}
		
	}
	
	public void geraArquivoFaturamento() {
		log.info("Gerando arquivo DI de FATURAMENTO");
		try {
			List<Faturamento> faturamento = loadDataService.carregaFaturamentoDi();
			
			File file = new File(config.getRepositorioArquivos() + "/" + getDataFormatada() + "_FATURAMENTO.txt");
			
			PrintWriter out = new PrintWriter(file);
			out.println(Constantes.FATURAMENTO_CABECALHO);
			for(Faturamento f : faturamento) {
				out.println(f.toString());
			}
			out.flush();
			out.close();
			log.info(String.format("Foram escritas %d linhas", faturamento.size() + 1));
			log.info("Arquivo DI de FATURAMENTO gerado com sucesso");
		} catch (IOException e) {
			throw new RuntimeException("Ocorreu um erro ao gerar o arquivo de faturamento", e);
		}
		
	}
	
	public void geraArquivoDevolucao() {
		log.info("Gerando arquivo DI de DEVOLUCAO");
		try {
			List<Devolucao> devolucao = loadDataService.carregaDevolucaoDi();
			
			File file = new File(config.getRepositorioArquivos() + "/" + getDataFormatada() + "_DEVOLUCAO.txt");
			
			PrintWriter out = new PrintWriter(file);
			out.println(Constantes.DEVOLUCAO_CABECALHO);
			for(Devolucao d : devolucao) {
				out.println(d.toString());
			}
			out.flush();
			out.close();
			log.info(String.format("Foram escritas %d linhas", devolucao.size() + 1));
			log.info("Arquivo DI de DEVOLUCAO gerado com sucesso");
		} catch (IOException e) {
			throw new RuntimeException("Ocorreu um erro ao gerar o arquivo de devolucao", e);
		}
		
	}
	
	public void geraArquivoBonificacao() {
		log.info("Gerando arquivo DI de BONIFICAÇÃO");
		try {
			List<Bonificacao> bonificacao = loadDataService.carregaBonificacaoDi();
			
			File file = new File(config.getRepositorioArquivos() + "/" + getDataFormatada() + "_BONIFICACAO.txt");
			
			PrintWriter out = new PrintWriter(file);
			out.println(Constantes.BONIFICACAO_CABECALHO);
			for(Bonificacao b : bonificacao) {
				out.println(b.toString());
			}
			out.flush();
			out.close();
			log.info(String.format("Foram escritas %d linhas", bonificacao.size() + 1));
			log.info("Arquivo DI de BONIFICAÇÃO gerado com sucesso");
		} catch (IOException e) {
			throw new RuntimeException("Ocorreu um erro ao gerar o arquivo de bonificação", e);
		}
		
	}
	
	private String getDataFormatada() {
		return new SimpleDateFormat("yyyyMMdd").format(new Date());
	}

}
