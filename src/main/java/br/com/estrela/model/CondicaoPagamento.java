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
public class CondicaoPagamento {
	
	private String cgc;
	private String cgcdist;
	private String codplpag;
	private String descricao;
	private Integer numdias;
	
	@Override
	public String toString() {
		return String.format("%s|%s|%s|%s|%d", cgc, cgcdist, codplpag, descricao, numdias);
	}

}
