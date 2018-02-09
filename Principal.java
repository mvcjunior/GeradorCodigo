package main.com.br;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import main.com.br.dominios.Definicao;

public class Principal {

	private static final String atributo = "atributo";
	private static final String metodo = "metodo";
	static ArrayList<Definicao> definicao = new ArrayList<Definicao>();
	
	public static void main(String[] args) throws IOException {
		if (args.length == 0 || args.length == 1 || args.length > 2) {
			printTela("Caminho e/ou classe Java não informados. Favor preencher \"classeJava.java\" \"diretório\\completo\"", 1);
		}
		String classeJava = args[0];
		String path = args[1];
		
		if (!classeJava.contains(".java")) {
			printTela("Classe Java inválida", 1);
		}
		
		String pathClasseJava = path + "\\" + classeJava;
		
		LeituraClasse(pathClasseJava);
		
		String pathClasseTestJava = path + "\\" + GeraNomeClasseTeste(classeJava);
		
		GeraCodigoTeste(pathClasseTestJava, GeraNomeClasseTeste(classeJava), classeJava);
		
	}
	
	private static String GeraNomeClasseTeste(String classeJava) {
		return classeJava.replace(".java", "Test.java");
	}

	private static void GeraCodigoTeste(String saida, String classeTeste, String classe) throws IOException {
		
		BufferedWriter wr = null;
		try {
			wr = new BufferedWriter(new FileWriter(saida));
		} catch (IOException e) {
			printTela("Nao foi possivel criar arquivo de teste: " +  saida, 1);
			e.printStackTrace();
		}
		
		String classeLimpa = classe.replace(".java", "");
		String variavel = "v" + classeLimpa;
		
		wr.append("public class " + classeTeste.replace(".java", "")+ " {\n\n");
		wr.append("\t@Test\n");
		wr.append("\tpublic void " + "valida" + classeLimpa + "() {\n");
		wr.append("\t\t" + classeLimpa + " " + variavel + " = new " + classeLimpa + "();\n\n");
		
		GravaAtribuicoes(wr, variavel);
		
		GravaComparacoes(wr, variavel);
		
		wr.append("\t}\n");
		wr.append("}\n");
		wr.close();
	}

	private static void GravaComparacoes(BufferedWriter wr, String variavel) throws IOException {
		for (Definicao definicaoLoop: definicao) {
			if (definicaoLoop.getTipo().equals(metodo) && definicaoLoop.getClasseTipoPrimitivo().equals("")) {
				String valor = ObterValorAtributo(definicaoLoop.getNome());
				wr.append("\t\tassertEquals(" + valor + "," + variavel + "." + definicaoLoop.getNome() + "());\n");
			}
		}
	}

	private static void GravaAtribuicoes(BufferedWriter wr, String variavel) throws IOException {
		for (Definicao definicaoLoop: definicao) {
			if (definicaoLoop.getTipo().equals(metodo) && !definicaoLoop.getClasseTipoPrimitivo().equals("")) {
				String valor = ObterValorAtributo(definicaoLoop.getNome());
				wr.append("\t\t" + variavel + "." + definicaoLoop.getNome() + "(" + valor + ");\n");
			}
		}
		wr.append("\n");
		
	}

	private static String ObterValorAtributo(String nome) {
		String valor = "\" \"";
		nome = nome.toLowerCase().substring(3);
		for (Definicao definicaoLoop: definicao) {
			if (definicaoLoop.getNome().toLowerCase().equals(nome) ) {
				valor = definicaoLoop.getValor();
			}
		}
		return valor;
	}

	private static String ObterValor(String tipo) {
		Random random = new Random();
		String valor = new String();
		switch (tipo) {
			case "String" :
				valor = "\"ABCDEFGHIJK\"";
				break;
			case "char" :
				valor = String.valueOf(random.nextInt());
				break;
			case "int" :
				valor = String.valueOf(random.nextInt());
				break;
			case "long" :
				valor = String.valueOf(random.nextLong()) + "L";
				break;
			case "double" :
				valor = String.valueOf(random.nextDouble());
				break;
			case "float" :
				valor = String.valueOf(random.nextFloat());
				break;
			case "boolean" :
				valor = String.valueOf(random.nextBoolean());
				break;
			default :
				valor = "\" \"";
				break;
		}
		
		return valor;
	}

	private static void LeituraClasse(String pathClasseJava) throws IOException {
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new FileReader(pathClasseJava));
		} catch (FileNotFoundException e) {
			printTela("Caminho e/ou classe Java informados não são válidos: " +  pathClasseJava, 1);
			e.printStackTrace();
		}
		
		while(br.ready()){
			   String linha = br.readLine();
			   if ((linha.contains("private") || linha.contains("public")) && !linha.contains("class")) {
				   definicao.add(LeituraAtributosMetodos(linha));
			   }
		}
		
		br.close();
		
	}

	private static Definicao LeituraAtributosMetodos(String linha) {
		Definicao definicao = new Definicao();
		String[] palavras = linha.split(" "); 

		if (linha.contains("{")) {
			definicao.setTipo(metodo);
			definicao.setClasseTipoPrimitivoRetorno(palavras[1]);
			
			String[] quebraNome = palavras[2].split("[(]");
			definicao.setNome(quebraNome[0]);
			if (quebraNome[1].equals(")")) {
				definicao.setClasseTipoPrimitivo("");
			} else {
				definicao.setClasseTipoPrimitivo(quebraNome[1]);
			}
			
		} else {
			definicao.setTipo(atributo);
			definicao.setNome(palavras[2].replace(";", ""));
			definicao.setClasseTipoPrimitivo(palavras[1]);
			definicao.setClasseTipoPrimitivoRetorno("");
			definicao.setValor(ObterValor(palavras[1]));
		}
		return definicao;
	}

	public static void printTela (String mensagem, int i) {
		System.out.println(mensagem);
		if (i == 1) {
			System.exit(0);
		}
		
	}

}
