package br.com.estrela.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties("app")
@Getter
@Setter
public class Configuracao {

	private String repositorioArquivos = ".";
	private String urlQueryService = "http://localhost:8090/query";
	private String dataReferencia = "sysdate";
	private String cnpjIndustria = "'16404287069864'";
	private String ownerTabelas;
	private boolean usaFtp = false;
	private String  ftpHost;
	private Integer ftpPort;
	private String  ftpPath;
	private String  ftpUsuario;
	private String  ftpSenha;
	private String  ftpHostsConhecidos;
	
}
