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
public class RamoAtividade {
	
	private String cgc;
	private String cgcdist;
	private Long codatv1;
	private String ramo;
	
	@Override
	public String toString() {
		return String.format("%s|%s|%d|%s", cgc, cgcdist, codatv1, ramo);
	}

}
