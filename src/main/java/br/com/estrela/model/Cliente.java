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
public class Cliente {
	
	private String cgc;
	private String cgcdist;
	private String cgcmatriz;
	private Long codcliprinc;
	private String cgcent;
	private Long codcli;
	private String cliente;
	private String rede;
	private String fantasia;
	private Date dtcadastro;
	private Long codatv1;
	private String endercom;
	private String numeroent;
	private String bairrocom;
	private String cepcom;
	private Long codmunicipio;
	private Double limcred;
	private String telcom;
	private String email;
	private String contato;
	
	@Override
	public String toString() {
		
		return String.format("%s|%s|%s|%d|%s|%d|%s|%s|%s|%s|%d|%s|%s|%s|%s|%d|%s|%s|%s|%s", cgc, cgcdist, cgcmatriz, codcliprinc, cgcent,
				codcli, cliente, rede, fantasia, new SimpleDateFormat("yyyy-MM-dd").format(dtcadastro), codatv1, endercom, numeroent, bairrocom,
				cepcom, codmunicipio, limcred, telcom, email, contato);
		
	}

}
