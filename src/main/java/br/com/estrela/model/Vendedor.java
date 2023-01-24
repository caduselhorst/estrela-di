package br.com.estrela.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Vendedor {
	
	private String cgc;
	private String cgcdist;
	private Long codusur;
	private Long codsupervisor;
	private String nomeusr;
	private String nomesup;
	private String emailusr;
	
	@Override
	public String toString() {
		return String.format("%s|%s|%d|%d|%s|%s|%s", cgc, cgcdist, codusur, codsupervisor, nomeusr, nomesup, emailusr == null ? "" : emailusr);
	}

}
