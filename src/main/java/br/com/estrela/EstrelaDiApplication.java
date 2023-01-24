package br.com.estrela;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import br.com.estrela.model.Constantes;
import br.com.estrela.service.FileService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class EstrelaDiApplication implements CommandLineRunner {
	
	@Autowired
	private FileService fileService;

	public static void main(String[] args) {
		//SpringApplication.run(EstrelaDiApplication.class, args);
		new SpringApplicationBuilder(EstrelaDiApplication.class)
			.web(WebApplicationType.NONE)
			.headless(true)
			.run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("**********************************************");
		log.info(" Estrela - UAU Ingleza DI - v" + Constantes.VERSAO);
		log.info("**********************************************");
		fileService.geraArquivos();
		
	}
	
	

}
