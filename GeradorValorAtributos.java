package main.com.br;

import java.util.Random;

public class GeradorValorAtributos {

	public static String ObterValor(String tipo) {
		Random random = new Random();
		String valor = new String();
		switch (tipo) {
			case "String" :
				valor = "\"ABCDEFGHIJK\"";
				break;
			case "char" :
				valor = "'X'";
				break;
			case "int" :
				valor = String.valueOf(random.nextInt());
				break;
			case "long" :
				valor = String.valueOf(random.nextLong()) + "L";
				break;
			case "double" :
				valor = String.valueOf(random.nextDouble()) + "D";
				break;
			case "float" :
				valor = String.valueOf(random.nextFloat()) + "F";
				break;
			case "boolean" :
				valor = String.valueOf(random.nextBoolean());
				break;
			case "Integer" :
				valor = tipo + ".valueOf(" + String.valueOf(random.nextInt()) + ")";
				break;
			case "Long" :
				valor = tipo + ".valueOf(" + String.valueOf(random.nextLong()) + "L" + ")";
				break;
			case "Double" :
				valor = tipo + ".valueOf(" + String.valueOf(random.nextDouble()) + "D" + ")";
				break;
			case "Float" :
				valor = tipo + ".valueOf(" + String.valueOf(random.nextFloat()) + "F" + ")";
				break;
			case "Boolean" :
				valor = tipo + ".valueOf(" + String.valueOf(random.nextBoolean()) + ")";
				break;
			case "Character" :
				valor = tipo + ".valueOf(" + "'X'" + ")";
				break;
			default :
				valor = "\" \"";
				break;
		}
		
		return valor;
	}

}
