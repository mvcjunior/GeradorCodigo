package main.com.br.dominios;

public class Definicao {
	private String tipo;
	private String nome;
	private String classeTipoPrimitivo;
	private String classeTipoPrimitivoRetorno;
	private String valor;
	
	public String getTipo() {
		return tipo;
	}
	public String getNome() {
		return nome;
	}
	public String getClasseTipoPrimitivo() {
		return classeTipoPrimitivo;
	}
	public String getClasseTipoPrimitivoRetorno() {
		return classeTipoPrimitivoRetorno;
	}
	public String getValor() {
		return valor;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setClasseTipoPrimitivo(String classeTipoPrimitivo) {
		this.classeTipoPrimitivo = classeTipoPrimitivo;
	}
	public void setClasseTipoPrimitivoRetorno(String classeTipoPrimitivoRetorno) {
		this.classeTipoPrimitivoRetorno = classeTipoPrimitivoRetorno;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "{ tipo: " + tipo + "," 
			+ " nome: " + nome + ","
			+ " classeTipoPrimitivo: " + classeTipoPrimitivo + ","
			+ " classeTipoPrimitivoRetorno: " + classeTipoPrimitivoRetorno + "," 
			+ " valor: " + valor + "}";
	}
}
