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
	private String enderent;
	private String numeroent;
	private String bairroent;
	private String cepent;
	private Long codmunicipio;
	private Double limcred;
	private String telent;
	private String email;
	private String contato;
	
	@Override
	public String toString() {
		
		return String.format("%s|%s|%s|%d|%s|%d|%s|%s|%s|%s|%d|%s|%s|%s|%s|%d|%s|%s|%s|%s", cgc, cgcdist, cgcmatriz, codcliprinc, cgcent,
				codcli, cliente, rede, fantasia, new SimpleDateFormat("yyyy-MM-dd").format(dtcadastro), codatv1, enderent, numeroent, bairroent,
				cepent, codmunicipio, limcred, telent, email, contato);
		
	}

}
