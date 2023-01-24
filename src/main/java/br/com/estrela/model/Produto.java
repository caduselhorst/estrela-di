package br.com.estrela.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Produto {
	
	private String cgc;
	private String cgcdist;
	private String codfab;
	private Long codprod;
	private Long codauxiliar;
	private Long codauxiliar2;
	private String descricao;
	private Date dtcadastro;
	
	@Override
	public String toString() {
		
		return String.format("%s|%s|%s|%d|%d|%d|%s|%s", cgc, cgcdist, 
				codfab, codprod, codauxiliar, codauxiliar2, descricao, new SimpleDateFormat("yyyy-MM-dd").format(dtcadastro));
		
	}

}
