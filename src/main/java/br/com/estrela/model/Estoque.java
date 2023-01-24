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
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Estoque {
	
	private String cgc;
	private String cgcdist;
	private String codfab;
	private Long codprod;
	private Long codauxiliar;
	private Long codauxiliar2;
	private Date dataestoque;
	private Date dtultent;
	private Date dtultfat;
	private Double qtestger;
	
	@Override
	public String toString() {
		return String.format("%s|%s|%s|%d|%d|%d|%s|%s|%s|%s", cgc, cgcdist, codfab, codprod,
				codauxiliar, codauxiliar2, getDataFormatada(dataestoque), getDataFormatada(dtultent), getDataFormatada(dtultfat), qtestger);
	}
	
	private String getDataFormatada(Date data) {
		if (data == null) {
			return "";
		}
		return new SimpleDateFormat("yyyy-MM-dd").format(data);
	}
	
}
