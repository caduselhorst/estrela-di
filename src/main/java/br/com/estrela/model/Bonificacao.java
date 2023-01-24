package br.com.estrela.model;

import java.text.SimpleDateFormat;
import java.util.Date;

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
public class Bonificacao {
	
	private String cgc;
	private String cgcdist;
	private Long codprod;
	private Long codcli;
	private Long codusur;
	private String codfiscal;
	private Long codplpag;
	private Date dtmov;
	private Long numnota;
	private String serie;
	private Integer numseq;
	private Double faturado;
	private Double imposto;
	private Double qtdecx;
	private Integer qt;
	private Double vldesconto;
	private String unidade;
	
	@Override
	public String toString() {
		return String.format("%s|%s|%d|%d|%d|%s|%d|%s|%d|%s|%d|%s|%s|%s|%d|%s|%s", cgc, cgcdist, codprod, codcli, codusur, codfiscal,
				codplpag, getDataFormata(dtmov), numnota, serie, numseq, faturado, imposto, qtdecx, qt, vldesconto, unidade);
	}
	
	private String getDataFormata(Date date) {
		if(date == null) {
			return "";
		}
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}

}
