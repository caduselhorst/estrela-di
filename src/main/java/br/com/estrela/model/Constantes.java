package br.com.estrela.model;

public class Constantes {
	
	public static final String VERSAO = "1.2.1";

	public static final String PRODUTOS_CABECALHO       = "FABRICANTE_CNPJ|DISTRIBUIDOR_CNPJ|PRODUTO_FAB_CODIGO|PRODUTO_CODIGO|PRODUTO_EAN_UN|PRODUTO_EAN_CX|PRODUTO_DESCRICAO|PRODUTO_DATA_CADASTRO";
	public static final String CLIENTES_CABECALHO       = "FABRICANTE_CNPJ|DISTRIBUIDOR_CNPJ|CLIENTE_MATRIZ_CNPJ|CLIENTE_CODIGO_MATRIZ|CLIENTE_CNPJ|CLIENTE_CODIGO|CLIENTE_NOME|CLIENTE_REDE|CLIENTE_NOME_FANTASIA|CLIENTE_DATA_CADASTRO|RAMO_CODIGO|CLIENTE_LOGRADOURO|CLIENTE_NUMERO|CLIENTE_BAIRRO|CEP|CIDADE_IBGE|CLIENTE_LIMITE_CREDITO|CLIENTE_TELEFONE|CLIENTE_EMAIL|CLIENTE_CONTATO";
	public static final String CONDSPAGTO_CABECALHO     = "FABRICANTE_CNPJ|DISTRIBUIDOR_CNPJ|COND_PAGTO_CODIGO|COND_PAGTO|COND_PAGTO_PRAZO";
	public static final String RAMOSATIVIDADE_CABECALHO = "FABRICANTE_CNPJ|DISTRIBUIDOR_CNPJ|RAMO_CODIGO|RAMO_DESCRICAO";
	public static final String ESTOQUE_CABECALHO        = "FABRICANTE_CNPJ|DISTRIBUIDOR_CNPJ|PRODUTO_FAB_CODIGO|PRODUTO_CODIGO|PRODUTO_EAN_UN|PRODUTO_EAN_CX|ESTOQUE_DATA|ESTOQUE_DT_ULTIMA_ENT|ESTOQUE_DT_ULTIMA_SAI|ESTOQUE_QTDE";
	public static final String VENDEDOR_CABECALHO       = "FABRICANTE_CNPJ|DISTRIBUIDOR_CNPJ|VENDEDOR_CODIGO|SUPERVISOR_CODIGO|VENDEDOR|SUPERVISOR|VENDEDOR_EMAIL";
	public static final String FATURAMENTO_CABECALHO    = "FABRICANTE_CNPJ|DISTRIBUIDOR_CNPJ|PRODUTO_CODIGO|CLIENTE_CODIGO|VENDEDOR_CODIGO|CFOP_CODIGO|PRAZO_PAGTO_CODIGO|FATURAMENTO_DATA|FATURAMENTO_NF_NUMERO|FATURAMENTO_NF_SERIE|FATURAMENTO_NF_SEQ|FATURAMENTO_VALOR_BRUTO|FATURAMENTO_VALOR_IMPOSTO|FATURAMENTO_QTDE_CX|FATURAMENTO_QTDE_UN|FATURAMENTO_VALOR_DESCONTO|FATURAMENTO_UNIDADE";
	public static final String DEVOLUCAO_CABECALHO      = "FABRICANTE_CNPJ|DISTRIBUIDOR_CNPJ|PRODUTO_CODIGO|CLIENTE_CODIGO|VENDEDOR_CODIGO|CFOP_CODIGO|PRAZO_PAGTO_CODIGO|DEVOLUCAO_DATA|DEVOLUCAO_NF_NUMERO|DEVOLUCAO_NF_SERIE|DEVOLUCAO_NF_SEQ|DEVOLUCAO_VALOR_BRUTO|DEVOLUCAO_VALOR_IMPOSTO|DEVOLUCAO_QTDE_CX|DEVOLUCAO_QTDE_UN|DEVOLUCAO_VALOR_DESCONTO|DEVOLUCAO_UNIDADE";
	public static final String BONIFICACAO_CABECALHO    = "FABRICANTE_CNPJ|DISTRIBUIDOR_CNPJ|PRODUTO_CODIGO|CLIENTE_CODIGO|VENDEDOR_CODIGO|CFOP_CODIGO|PRAZO_PAGTO_CODIGO|BONIFICACAO_DATA|BONIFICACAO_NF_NUMERO|BONIFICACAO_NF_SERIE|BONIFICACAO_NF_SEQ|BONIFICACAO_VALOR_BRUTO|BONIFICACAO_VALOR_IMPOSTO|BONIFICACAO_QTDE_CX|BONIFICACAO_QTDE_UN|BONIFICACAO_VALOR_DESCONTO|BONIFICACAO_UNIDADE";
	
}
