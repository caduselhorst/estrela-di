package br.com.estrela.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estrela.config.Configuracao;
import br.com.estrela.config.QueryServiceClient;
import br.com.estrela.model.Bonificacao;
import br.com.estrela.model.Cliente;
import br.com.estrela.model.CondicaoPagamento;
import br.com.estrela.model.Devolucao;
import br.com.estrela.model.Estoque;
import br.com.estrela.model.Faturamento;
import br.com.estrela.model.Produto;
import br.com.estrela.model.RamoAtividade;
import br.com.estrela.model.Vendedor;
import br.com.estrela.model.api.MetaDataModel;
import br.com.estrela.model.api.QueryModel;

@Service
public class LoadDataService {
	
	@Autowired
	private QueryServiceClient queryServiceClient;
	
	@Autowired
	private Configuracao configuracao;
	
	private String getOwner() {
		return configuracao.getOwnerTabelas() == null ? "" : configuracao.getOwnerTabelas() + ".";
	}
	
	public List<Produto> carregaProdutoDi() {
		 
		
		String query = "SELECT DISTINCT replace(replace(replace(FORN.CGC,'-',''),'/',''),'.','') as CGC, "
				+ "replace(replace(replace(DIST.CGC,'-',''),'/',''),'.','') as CGCDIST, "
				+ "PRO.CODFAB, "
				+ "PRO.CODPROD, "
				+ "PRO.CODAUXILIAR, "
				+ "PRO.CODAUXILIAR2, "
				+ "PRO.DESCRICAO, "
				+ "PRO.DTCADASTRO "
				+ "FROM " + getOwner() + "PCPRODUT PRO, "
				+ getOwner() + "PCMOV MOV, "
				+ getOwner() + "PCFORNEC FORN, "
				+ getOwner() + "PCFILIAL DIST "
				+ "WHERE MOV.DTMOV between to_date(to_char(sysdate-7, 'yyyymmdd'), 'yyyymmdd') "
				+ 	"and to_date(to_char(" + configuracao.getDataReferencia() + ", 'yyyymmdd') || '235959', 'yyyymmddhh24miss') "
				+ "AND MOV.CODPROD = PRO.CODPROD "
				+ "AND MOV.CODFORNEC = FORN.CODFORNEC "
				+ "AND MOV.CODOPER = 'S' "
				+ "AND MOV.CODFILIAL = DIST.CODIGO "
				+ "AND DIST.codigo = '1' "
				+ "AND replace(replace(replace(FORN.CGC,'-',''),'/',''),'.','') IN (" + configuracao.getCnpjIndustria() + ")";
		
		List<MetaDataModel>  metaData = Arrays.asList(
				MetaDataModel.builder().columnName("cgc").columnType("STRING").build(),
				MetaDataModel.builder().columnName("cgcdist").columnType("STRING").build(),
				MetaDataModel.builder().columnName("codfab").columnType("STRING").build(),
				MetaDataModel.builder().columnName("codprod").columnType("LONG").build(),
				MetaDataModel.builder().columnName("codauxiliar").columnType("LONG").build(),
				MetaDataModel.builder().columnName("codauxiliar2").columnType("LONG").build(),
				MetaDataModel.builder().columnName("descricao").columnType("STRING").build(),
				MetaDataModel.builder().columnName("dtcadastro").columnType("DATE").build());
		
		QueryModel queryModel = new QueryModel();
		queryModel.setQuery(query);
		queryModel.setMetaData(metaData);
		
		return queryServiceClient.carregaProdutos(queryModel);
		
	}
	
	public List<Cliente> carregaClienteDi() {
		
		String query = "SELECT DISTINCT " +
			    "replace(replace(replace(FORN.CGC,'-',''),'/',''),'.','') as CGC, " +
			    "replace(replace(replace(DIST.CGC,'-',''),'/',''),'.','') as CGCDIST, " +
			    "replace(replace(replace(COALESCE(MAT.CGCENT, CLI.CGCENT),'-',''),'/',''),'.','') as CGCMATRIZ, " +
			    "COALESCE(CLI.CODCLIPRINC,CLI.CODCLI) as CODCLIPRINC, " +
			    "replace(replace(replace(CLI.CGCENT,'-',''),'/',''),'.','') as CGCENT, " +
			    "CLI.CODCLI, " +
			    "CLI.CLIENTE, " +
			    "COALESCE(RCL.DESCRICAO,CLI.CLIENTE) as REDE, " +
			    "CLI.FANTASIA, " +
			    "CLI.DTCADASTRO, " +
			    "CLI.CODATV1, " +
			    "CLI.ENDERCOM, " +
			    "CLI.NUMEROENT, " +
			    "CLI.BAIRROCOM, " +
			    "CLI.CEPCOM, " +
			    "CLI.CODMUNICIPIO, " +
			    "CLI.LIMCRED, " +
			    "CLI.TELCOM, " +
			    "CLI.EMAIL, " +
			    "MAT.CGCENT as CONTATO " +
			    "FROM " + getOwner() + "PCCLIENT CLI, " +
			         getOwner() + "PCCLIENT MAT, " +
			         getOwner() + "PCREDECLIENTE RCL, " +
			         getOwner() + "PCMOV MOV, " +
			         getOwner() + "PCFORNEC FORN, " +
			         getOwner() + "PCFILIAL DIST " +
			    "WHERE MOV.DTMOV between to_date(to_char(sysdate-7, 'yyyymmdd'), 'yyyymmdd') " +
			                "and to_date(to_char(" + configuracao.getDataReferencia() + ", 'yyyymmdd') || '235959', 'yyyymmddhh24miss') " +
			      "AND MOV.CODCLI = CLI.CODCLI " +
			      "AND COALESCE(CLI.CODREDE,CLI.CODCLI) = RCL.CODREDE(+) " +
			      "AND COALESCE(CLI.CODCLIPRINC,CLI.CODCLI) = MAT.CODCLI " +
			      "AND MOV.CODFORNEC = FORN.CODFORNEC " +
			      "AND MOV.CODOPER = 'S' " +
			      "AND MOV.CODFILIAL = DIST.CODIGO " +
			      "and dist.codigo = '1' " +
			      "AND replace(replace(replace(FORN.CGC,'-',''),'/',''),'.','') IN (" + configuracao.getCnpjIndustria() + ")";
		
		List<MetaDataModel>  metaData = Arrays.asList(
				MetaDataModel.builder().columnName("cgc").columnType("STRING").build(),
				MetaDataModel.builder().columnName("cgcdist").columnType("STRING").build(),
				MetaDataModel.builder().columnName("cgcmatriz").columnType("STRING").build(),
				MetaDataModel.builder().columnName("codcliprinc").columnType("LONG").build(),
				MetaDataModel.builder().columnName("cgcent").columnType("STRING").build(),
				MetaDataModel.builder().columnName("codcli").columnType("LONG").build(),
				MetaDataModel.builder().columnName("cliente").columnType("STRING").build(),
				MetaDataModel.builder().columnName("rede").columnType("STRING").build(),
				MetaDataModel.builder().columnName("fantasia").columnType("STRING").build(),
				MetaDataModel.builder().columnName("dtcadastro").columnType("DATE").build(),
				MetaDataModel.builder().columnName("codatv1").columnType("LONG").build(),
				MetaDataModel.builder().columnName("endercom").columnType("STRING").build(),
				MetaDataModel.builder().columnName("numeroent").columnType("STRING").build(),
				MetaDataModel.builder().columnName("bairrocom").columnType("STRING").build(),
				MetaDataModel.builder().columnName("cepcom").columnType("STRING").build(),
				MetaDataModel.builder().columnName("codmunicipio").columnType("LONG").build(),
				MetaDataModel.builder().columnName("limcred").columnType("STRING").build(),
				MetaDataModel.builder().columnName("telcom").columnType("STRING").build(),
				MetaDataModel.builder().columnName("email").columnType("STRING").build(),
				MetaDataModel.builder().columnName("contato").columnType("STRING").build());
		
		QueryModel queryModel = new QueryModel();
		queryModel.setQuery(query);
		queryModel.setMetaData(metaData);
		
		return queryServiceClient.carregaClientes(queryModel);
		
	}
	
	public List<CondicaoPagamento> carregaCondicaoPagamentoDi() {
		
		String query = "SELECT DISTINCT " +
			    "replace(replace(replace(FORN.CGC,'-',''),'/',''),'.','') as CGC, " +
			    "replace(replace(replace(DIST.CGC,'-',''),'/',''),'.','') as CGCDIST, " +
			    "MOV.CODPLPAG, " +
			    "PAG.DESCRICAO, " +
			    "PAG.NUMDIAS " +
			    "FROM " + getOwner() + "PCPLPAG PAG, " +
			    getOwner() + "PCFORNEC FORN, " +
			    getOwner() + "PCFILIAL DIST, " +
			    getOwner() + "PCMOV MOV " +
			    "WHERE MOV.DTMOV between to_date(to_char(sysdate-7, 'yyyymmdd'), 'yyyymmdd') " +
			        "and to_date(to_char(" + configuracao.getDataReferencia() + ", 'yyyymmdd') || '235959', 'yyyymmddhh24miss') " +
			      "AND MOV.CODPLPAG = PAG.CODPLPAG " +
			      "AND MOV.CODFORNEC = FORN.CODFORNEC " +
			      "AND MOV.CODFILIAL = DIST.CODIGO " +
			      "AND DIST.CODIGO = '1' " +
			      "AND replace(replace(replace(FORN.CGC,'-',''),'/',''),'.','') IN (" + configuracao.getCnpjIndustria() + ")";
		
		List<MetaDataModel>  metaData = Arrays.asList(
				MetaDataModel.builder().columnName("cgc").columnType("STRING").build(),
				MetaDataModel.builder().columnName("cgcdist").columnType("STRING").build(),
				MetaDataModel.builder().columnName("codplpag").columnType("STRING").build(),
				MetaDataModel.builder().columnName("descricao").columnType("STRING").build(),
				MetaDataModel.builder().columnName("numdias").columnType("INT").build());
		
		QueryModel queryModel = new QueryModel();
		queryModel.setQuery(query);
		queryModel.setMetaData(metaData);
		
		return queryServiceClient.carregaCondicoesPagamento(queryModel);
		
	}
	
	public List<RamoAtividade> carregaRamosAtividadeDi() {
		
		String query = "SELECT DISTINCT " +
			    "replace(replace(replace(FORN.CGC,'-',''),'/',''),'.','') as CGC, " +
			    "replace(replace(replace(DIST.CGC,'-',''),'/',''),'.','') as CGCDIST, " +
			    "CLI.CODATV1, " +
			    "ATI.RAMO " +
			    "FROM " + getOwner() + "PCCLIENT CLI, " +
			    getOwner() + "PCATIVI ATI, " +
			    getOwner() + "PCMOV MOV, " +
			    getOwner() + "PCFORNEC FORN, " +
			    getOwner() + "PCFILIAL DIST " +
			    "WHERE MOV.DTMOV between to_date(to_char(sysdate-7, 'yyyymmdd'), 'yyyymmdd') " +
			        "and to_date(to_char(" + configuracao.getDataReferencia() + ", 'yyyymmdd') || '235959', 'yyyymmddhh24miss') " +
			      "AND MOV.CODCLI = CLI.CODCLI " +
			      "AND CLI.CODATV1 = ATI.CODATIV " +
			      "AND MOV.CODFORNEC = FORN.CODFORNEC " +
			      "AND MOV.CODOPER = 'S' " +
			      "AND MOV.CODFILIAL = DIST.CODIGO " +
			      "AND DIST.CODIGO='1' " +
			      "AND replace(replace(replace(FORN.CGC,'-',''),'/',''),'.','') IN (" + configuracao.getCnpjIndustria() + ")";
		
		List<MetaDataModel>  metaData = Arrays.asList(
				MetaDataModel.builder().columnName("cgc").columnType("STRING").build(),
				MetaDataModel.builder().columnName("cgcdist").columnType("STRING").build(),
				MetaDataModel.builder().columnName("codatv1").columnType("LONG").build(),
				MetaDataModel.builder().columnName("ramo").columnType("STRING").build());
		
		QueryModel queryModel = new QueryModel();
		queryModel.setQuery(query);
		queryModel.setMetaData(metaData);
		
		return queryServiceClient.carregaRamosAtividade(queryModel);
		
	}
	
	public List<Estoque> carregaEstoqueDi() {
		
		String query = "SELECT DISTINCT " +
			    "replace(replace(replace(FORN.CGC,'-',''),'/',''),'.','') as CGC, " +
			    "replace(replace(replace(DIST.CGC,'-',''),'/',''),'.','') as CGCDIST, " +
			    "PRO.CODFAB, " +
			    "EST.CODPROD, " +
			    "PRO.CODAUXILIAR, " +
			    "PRO.CODAUXILIAR2, " +
			    "sysdate dataestoque, " +
			    "EST.DTULTENT, " +
			    "EST.DTULTFAT, " +
			    "ROUND(EST.QTESTGER / PRO.QTUNITCX, 2) QTESTGER " +
			    "FROM " + getOwner() + "PCEST EST, " +
			    getOwner() + "PCPRODUT PRO, " +
			    getOwner() + "PCFORNEC FORN, " +
			    getOwner() + "PCFILIAL DIST " +
			    "WHERE EST.CODFILIAL = DIST.CODIGO " +
			      "AND EST.CODPROD = PRO.CODPROD " +
			      "AND PRO.CODFORNEC = FORN.CODFORNEC " +
			      "AND EST.QTESTGER <> 0 " +
			      "AND DIST.CODIGO = '1' " +
			      "AND replace(replace(replace(FORN.CGC,'-',''),'/',''),'.','') IN (" + configuracao.getCnpjIndustria() + ")";
		
		List<MetaDataModel>  metaData = Arrays.asList(
				MetaDataModel.builder().columnName("cgc").columnType("STRING").build(),
				MetaDataModel.builder().columnName("cgcdist").columnType("STRING").build(),
				MetaDataModel.builder().columnName("codfab").columnType("STRING").build(),
				MetaDataModel.builder().columnName("codprod").columnType("LONG").build(),
				MetaDataModel.builder().columnName("codauxiliar").columnType("LONG").build(),
				MetaDataModel.builder().columnName("codauxiliar2").columnType("LONG").build(),
				MetaDataModel.builder().columnName("dataestoque").columnType("DATE").build(),
				MetaDataModel.builder().columnName("dtultent").columnType("DATE").build(),
				MetaDataModel.builder().columnName("dtultfat").columnType("DATE").build(),
				MetaDataModel.builder().columnName("qtestger").columnType("DOUBLE").build());
		
		QueryModel queryModel = new QueryModel();
		queryModel.setQuery(query);
		queryModel.setMetaData(metaData);
		
		return queryServiceClient.carregaEstoque(queryModel);
		
	}


	public List<Vendedor> carregaVendedoresDi() {
		
		String query = "SELECT DISTINCT " +
			    "replace(replace(replace(FORN.CGC,'-',''),'/',''),'.','') as CGC, " +
			    "replace(replace(replace(DIST.CGC,'-',''),'/',''),'.','') as CGCDIST, " +
			    "MOV.CODUSUR, " +
			    "VEN.CODSUPERVISOR, " +
			    "VEN.NOME as NOMEUSR, " +
			    "SUP.NOME as NOMESUP, " +
			    "VEN.EMAIL as EMAILUSR " +
			"FROM " + getOwner() + "PCUSUARI VEN, " +
			getOwner() + "PCSUPERV SUP, " +
			getOwner() + "PCFORNEC FORN, " +
			getOwner() + "PCFILIAL DIST, " +
			getOwner() + "PCMOV MOV " +
			"WHERE MOV.DTMOV between to_date(to_char(sysdate-7, 'yyyymmdd'), 'yyyymmdd') " +
			    "and to_date(to_char(" + configuracao.getDataReferencia() + ", 'yyyymmdd') || '235959', 'yyyymmddhh24miss') " +
			    "AND MOV.CODUSUR = VEN.CODUSUR " +
			    "AND VEN.CODSUPERVISOR = SUP.CODSUPERVISOR " +
			    "AND FORN.CODFORNEC = MOV.CODFORNEC " +
			    "AND DIST.CODIGO = MOV.CODFILIAL " +
			    "AND DIST.CODIGO = '1' " +
			    "AND replace(replace(replace(FORN.CGC,'-',''),'/',''),'.','') IN (" + configuracao.getCnpjIndustria() + ")";
		
		List<MetaDataModel>  metaData = Arrays.asList(
				MetaDataModel.builder().columnName("cgc").columnType("STRING").build(),
				MetaDataModel.builder().columnName("cgcdist").columnType("STRING").build(),
				MetaDataModel.builder().columnName("codusur").columnType("LONG").build(),
				MetaDataModel.builder().columnName("codsupervisor").columnType("LONG").build(),
				MetaDataModel.builder().columnName("nomeusr").columnType("STRING").build(),
				MetaDataModel.builder().columnName("nomesup").columnType("STRING").build(),
				MetaDataModel.builder().columnName("emailusr").columnType("STRING").build());
		
		QueryModel queryModel = new QueryModel();
		queryModel.setQuery(query);
		queryModel.setMetaData(metaData);
		
		return queryServiceClient.carregaVendedor(queryModel);
		
	}
	
	public List<Faturamento> carregaFaturamentoDi() {
		
		String query = "SELECT DISTINCT " +
			    "replace(replace(replace(FORN.CGC,'-',''),'/',''),'.','') as CGC, " +
			    "replace(replace(replace(DIST.CGC,'-',''),'/',''),'.','') as CGCDIST, " +
			    "MOV.CODPROD, " +
			    "MOV.CODCLI, " +
			    "MOV.CODUSUR, " +
			    "MOV.CODFISCAL, " +
			    "MOV.CODPLPAG, " +
			    "MOV.DTMOV, " +
			    "MOV.NUMNOTA, " +
			    "(select serie from " + getOwner() + "pcnfsaid where numtransvenda = MOV.numtransvenda) serie, " +
			    "MOV.NUMSEQ, " +
			    "MOV.PUNITCONT*MOV.QTCONT as FATURADO, " +
			    "(MOV.VLCOFINS+MOV.VLPIS+(mov.baseicms * (mov.percicm/100))) as IMPOSTO, " +
			    "round(MOV.QTCONT/MOV.QTUNITCX,4)as QTDECX, " +
			    "MOV.QTCONT as QT, " +
			    "MOV.VLDESCONTO, " +
			    "MOV.UNIDADE " +
			"FROM " + getOwner() + "PCMOV MOV, " +
			getOwner() + "PCFORNEC FORN, " +
			getOwner() + "PCFILIAL DIST " +
			"WHERE MOV.DTMOV between to_date(to_char(sysdate-7, 'yyyymmdd'), 'yyyymmdd') " +
			    "and to_date(to_char(" + configuracao.getDataReferencia() + ", 'yyyymmdd') || '235959', 'yyyymmddhh24miss') " +
			    "AND MOV.CODFORNEC = FORN.CODFORNEC " +
			    "AND MOV.CODOPER = 'S' " +
			    "AND MOV.STATUS <> 'B' " +
			    "AND MOV.CODFILIAL = DIST.CODIGO " +
			    "AND DIST.CODIGO = '1' " +
			    "AND replace(replace(replace(FORN.CGC,'-',''),'/',''),'.','') IN (" + configuracao.getCnpjIndustria() + ") " +
			"order by mov.dtmov, mov.numnota";
		
		List<MetaDataModel>  metaData = Arrays.asList(
				MetaDataModel.builder().columnName("cgc").columnType("STRING").build(),
				MetaDataModel.builder().columnName("cgcdist").columnType("STRING").build(),
				MetaDataModel.builder().columnName("codprod").columnType("LONG").build(),
				MetaDataModel.builder().columnName("codcli").columnType("LONG").build(),
				MetaDataModel.builder().columnName("codusur").columnType("LONG").build(),
				MetaDataModel.builder().columnName("codfiscal").columnType("STRING").build(),
				MetaDataModel.builder().columnName("codplpag").columnType("LONG").build(),
				MetaDataModel.builder().columnName("dtmov").columnType("DATE").build(),
				MetaDataModel.builder().columnName("numnota").columnType("LONG").build(),
				MetaDataModel.builder().columnName("serie").columnType("STRING").build(),
				MetaDataModel.builder().columnName("numseq").columnType("INT").build(),
				MetaDataModel.builder().columnName("faturado").columnType("DOUBLE").build(),
				MetaDataModel.builder().columnName("imposto").columnType("DOUBLE").build(),
				MetaDataModel.builder().columnName("qtdecx").columnType("DOUBLE").build(),
				MetaDataModel.builder().columnName("qt").columnType("INT").build(),
				MetaDataModel.builder().columnName("vldesconto").columnType("DOUBLE").build(),
				MetaDataModel.builder().columnName("unidade").columnType("STRING").build());
		
		QueryModel queryModel = new QueryModel();
		queryModel.setQuery(query);
		queryModel.setMetaData(metaData);
		
		return queryServiceClient.carregaFaturamento(queryModel);
		
	}
	
	public List<Devolucao> carregaDevolucaoDi() {
		
		String query = "SELECT DISTINCT " +
			    "replace(replace(replace(FORN.CGC,'-',''),'/',''),'.','') as CGC, " +
			    "replace(replace(replace(DIST.CGC,'-',''),'/',''),'.','') as CGCDIST, " +
			    "MOV.CODPROD, " +
			    "MOV.CODCLI, " +
			    "MOV.CODUSUR, " +
			    "MOV.CODFISCAL, " +
			    "MOV.CODPLPAG, " +
			    "MOV.DTMOV, " +
			    "MOV.NUMNOTA, " +
			    "(select serie from " + getOwner() + "pcnfent where numtransent = mov.numtransent) serie, " +
			    "MOV.NUMSEQ, " +
			    "MOV.PUNIT*MOV.QT as FATURADO, " +
			    "nvl((MOV.VLCOFINS+MOV.VLPIS+(mov.baseicms * (mov.percicm / 100))), 0) as IMPOSTO, " +
			    "round(MOV.QT/MOV.QTUNITCX,4)as QTDECX, " +
			    "MOV.QT, " +
			    "MOV.VLDESCONTO, " +
			    "MOV.UNIDADE " +
			"FROM " + getOwner() + "PCMOV MOV, " +
			getOwner() + "PCFORNEC FORN, " +
			getOwner() + "PCFILIAL DIST " +
			"WHERE MOV.DTMOV between to_date(to_char(sysdate-7, 'yyyymmdd'), 'yyyymmdd') " +
			    "and to_date(to_char(" + configuracao.getDataReferencia() + ", 'yyyymmdd') || '235959', 'yyyymmddhh24miss') " +
			    "AND MOV.CODOPER IN ('ED')  " +
			    "AND MOV.CODFORNEC = FORN.CODFORNEC " +
			    "AND MOV.CODFILIAL = DIST.CODIGO " +
			    "AND DIST.CODIGO = '1' " +
			    "AND replace(replace(replace(FORN.CGC,'-',''),'/',''),'.','') IN (" + configuracao.getCnpjIndustria() + ") " +
			"ORDER BY MOV.DTMOV, MOV.NUMNOTA, MOV.NUMSEQ";
		
		List<MetaDataModel>  metaData = Arrays.asList(
				MetaDataModel.builder().columnName("cgc").columnType("STRING").build(),
				MetaDataModel.builder().columnName("cgcdist").columnType("STRING").build(),
				MetaDataModel.builder().columnName("codprod").columnType("LONG").build(),
				MetaDataModel.builder().columnName("codcli").columnType("LONG").build(),
				MetaDataModel.builder().columnName("codusur").columnType("LONG").build(),
				MetaDataModel.builder().columnName("codfiscal").columnType("STRING").build(),
				MetaDataModel.builder().columnName("codplpag").columnType("LONG").build(),
				MetaDataModel.builder().columnName("dtmov").columnType("DATE").build(),
				MetaDataModel.builder().columnName("numnota").columnType("LONG").build(),
				MetaDataModel.builder().columnName("serie").columnType("STRING").build(),
				MetaDataModel.builder().columnName("numseq").columnType("INT").build(),
				MetaDataModel.builder().columnName("faturado").columnType("DOUBLE").build(),
				MetaDataModel.builder().columnName("imposto").columnType("DOUBLE").build(),
				MetaDataModel.builder().columnName("qtdecx").columnType("DOUBLE").build(),
				MetaDataModel.builder().columnName("qt").columnType("INT").build(),
				MetaDataModel.builder().columnName("vldesconto").columnType("DOUBLE").build(),
				MetaDataModel.builder().columnName("unidade").columnType("STRING").build());
		
		QueryModel queryModel = new QueryModel();
		queryModel.setQuery(query);
		queryModel.setMetaData(metaData);
		
		return queryServiceClient.carregaDevolucao(queryModel);
		
	}
	
	public List<Bonificacao> carregaBonificacaoDi() {
		
		String query = "SELECT DISTINCT " +
			    "replace(replace(replace(FORN.CGC,'-',''),'/',''),'.','') as CGC, " +
			    "replace(replace(replace(DIST.CGC,'-',''),'/',''),'.','') as CGCDIST, " +
			    "MOV.CODPROD, " +
			    "MOV.CODCLI, " +
			    "MOV.CODUSUR, " +
			    "MOV.CODFISCAL, " +
			    "MOV.CODPLPAG, " +
			    "MOV.DTMOV, " +
			    "MOV.NUMNOTA, " +
			    "(SELECT SERIE FROM " + getOwner() + "PCNFSAID WHERE NUMTRANSVENDA = MOV.NUMTRANSVENDA) SERIE, " +
			    "MOV.NUMSEQ, " +
			    "MOV.PUNIT*MOV.QT as FATURADO, " +
			    "(MOV.VLCOFINS+MOV.VLPIS+MOV.BASEICMS * (MOV.PERICM * 100)) as IMPOSTO, " +
			    "round(MOV.QT/MOV.QTUNITCX,4)as QTDECX, " +
			    "MOV.QT, " +
			    "MOV.VLDESCONTO, " +
			    "MOV.UNIDADE " +
			"FROM " + getOwner() + "PCMOV MOV, " +
			getOwner() + "PCFORNEC FORN, " +
			getOwner() + "PCFILIAL DIST " +
			"WHERE MOV.DTMOV BETWEEN TO_DATE(TO_CHAR(SYSDATE-7, 'yyyymmdd'), 'yyyymmdd') " +
			    "AND TO_DATE(TO_CHAR(" + configuracao.getDataReferencia() + ", 'yyyymmdd') || '235959', 'yyyymmddhh24miss') " +
			    "AND MOV.CODOPER IN ('SB') " +
			    "AND MOV.CODFORNEC = FORN.CODFORNEC " +
			    "AND MOV.CODFILIAL = DIST.CODIGO " +
			    "AND replace(replace(replace(FORN.CGC,'-',''),'/',''),'.','') IN (" + configuracao.getCnpjIndustria() + ") " +
			"ORDER BY MOV.dtmov, MOV.NUMNOTA, MOV.NUMSEQ";

		List<MetaDataModel>  metaData = Arrays.asList(
				MetaDataModel.builder().columnName("cgc").columnType("STRING").build(),
				MetaDataModel.builder().columnName("cgcdist").columnType("STRING").build(),
				MetaDataModel.builder().columnName("codprod").columnType("LONG").build(),
				MetaDataModel.builder().columnName("codcli").columnType("LONG").build(),
				MetaDataModel.builder().columnName("codusur").columnType("LONG").build(),
				MetaDataModel.builder().columnName("codfiscal").columnType("STRING").build(),
				MetaDataModel.builder().columnName("codplpag").columnType("LONG").build(),
				MetaDataModel.builder().columnName("dtmov").columnType("DATE").build(),
				MetaDataModel.builder().columnName("numnota").columnType("LONG").build(),
				MetaDataModel.builder().columnName("serie").columnType("STRING").build(),
				MetaDataModel.builder().columnName("numseq").columnType("INT").build(),
				MetaDataModel.builder().columnName("faturado").columnType("DOUBLE").build(),
				MetaDataModel.builder().columnName("imposto").columnType("DOUBLE").build(),
				MetaDataModel.builder().columnName("qtdecx").columnType("DOUBLE").build(),
				MetaDataModel.builder().columnName("qt").columnType("INT").build(),
				MetaDataModel.builder().columnName("vldesconto").columnType("DOUBLE").build(),
				MetaDataModel.builder().columnName("unidade").columnType("STRING").build());
		
		QueryModel queryModel = new QueryModel();
		queryModel.setQuery(query);
		queryModel.setMetaData(metaData);
		
		return queryServiceClient.carregaBonificacao(queryModel);
	}
}
