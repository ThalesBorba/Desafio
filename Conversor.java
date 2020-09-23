import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Conversor {

	private static String Numeros[][] = {  
			{"zero", "um", "dois", "três", "quatro", "cinco", "seis", "sete", "oito", "nove", "dez",  
		     "onze", "doze", "treze", "quatorze", "quinze", "dezesseis", "dezessete", "dezoito", "dezenove"},  
		    {"vinte", "trinta", "quarenta", "cinquenta", "sessenta", "setenta", "oitenta", "noventa"},  
		    {"cem", "cento", "duzentos", "trezentos", "quatrocentos", "quinhentos", "seiscentos",  
		     "setecentos", "oitocentos", "novecentos"},{"mil"}
  	          };  
	
	ArrayList<String> lista = new ArrayList<String>();
	int unidade, dezena, centena, milhar = 0;
	
	
	
	public String numeroPorExtenso(double numero) {
		if((numero / 1000) >= 2) {
			milhar = (int) numero / 1000;
			lista.add(Numeros[0][milhar]);
		}
		if(numero / 1000 >= 1) {
			lista.add(Numeros[3][0]);
		}
		if(((numero % 1000) / 100) > 0 && (numero % 1000) != 100) {
			if(numero % 100 == 0 && !lista.isEmpty()) {
				lista.add("e");
			}
			centena = (int) (numero % 1000) / 100;
			lista.add(Numeros[2][centena]);
		}
		if(numero % 1000 == 100) {
			if(!lista.isEmpty()) {
				lista.add("e");
			}
			lista.add(Numeros[2][0]);
		}
		if((numero % 100) > 19) {
			if(!lista.isEmpty()) {
				lista.add("e");
			}
			dezena = (int) (numero % 100) / 10;
			lista.add(Numeros[1][dezena - 2]);
			if(numero % 10 > 0) {
				unidade = (int) numero % 10;
				lista.add("e " + Numeros[0][unidade]);
			}
		}
		if((numero % 100) <= 19 && (numero % 100) > 0) {
			if(!lista.isEmpty()) {
				lista.add("e");
			}
			lista.add(Numeros[0][(int) (numero % 100)]);
		}
		if(numero == 0) {
			lista.add(Numeros[0][0]);
		}
		String porExtenso = String.join(" ", lista);
		
		porExtenso = porExtenso.substring(0,1).toUpperCase() + porExtenso.substring(1).toLowerCase();
		
		return porExtenso;
		}
	
	ArrayList<String> porExtenso = new ArrayList<>(Arrays.asList("zero", "um", "dois", "três", "quatro", "cinco",
			"seis", "sete", "oito", "nove", "dez", "onze", "doze", "treze", "quatorze", "quinze", "dezesseis",
			"dezessete", "dezoito", "dezenove", "vinte", "trinta", "quarenta", "cinquenta", "sessenta", 
			"setenta", "oitenta", "noventa", "cem", "cento", "duzentos", "trezentos", "quatrocentos", "quinhentos",
			"seiscentos", "setecentos", "oitocentos", "novecentos","mil"));
	
	ArrayList<String> Numerais = new ArrayList<>(Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", 
			"11", "12", "13", "14", "15", "16", "17", "18", "19"));
	
	public int ExtensoParaNumero(String numero) {
		
		String listaDeNumerosPorExtenso[] = numero.toLowerCase().split(" ");
		
		List<String> n = new ArrayList<>();
		String unidade, dezena, centena, milhar = "";
		
		
		for(int c = 0; c<= 9; c++) {
			if(porExtenso.get(c).matches(listaDeNumerosPorExtenso[listaDeNumerosPorExtenso.length -1])) {
			unidade = Numerais.get(c);
			n.add(unidade);
			}
			}
		
		
		for(int c = 10; c<= 19; c++) {
				if(porExtenso.get(c).matches(listaDeNumerosPorExtenso[listaDeNumerosPorExtenso.length-1])) {
			unidade = Numerais.get(c-10);
			n.add(unidade);
			n.add("1");
			}
			
		}
		
		for(int a = listaDeNumerosPorExtenso.length; a > 0; a--) {
			for(int c = 20; c<= 27; c++) {
				if(porExtenso.get(c).matches(listaDeNumerosPorExtenso[a-1])) {
			dezena = Numerais.get(c-18);
			n.add(dezena);
			}
			}
		}
		
		for(int a = listaDeNumerosPorExtenso.length; a > 0; a--) {
			for(int c = 29; c<= 37; c++) {
				if(porExtenso.get(c).matches(listaDeNumerosPorExtenso[a-1])) {
			centena = Numerais.get(c-28);
			n.add(centena);
			}
			}
		}
		
		if(porExtenso.get(38).matches(listaDeNumerosPorExtenso[0])) {
			if(listaDeNumerosPorExtenso.length == 1) {
				n.add("1000");
			} else {
			n.add("1");
		}
		}
			
		if(listaDeNumerosPorExtenso.length > 1 && porExtenso.get(38).matches(listaDeNumerosPorExtenso[1])) {
			for(int c = 2; c<= 9; c++) {
			if(porExtenso.get(c).matches(listaDeNumerosPorExtenso[0])) {
			milhar = Numerais.get(c);
			n.add(milhar);
			}
			}
		}
		
		if(porExtenso.get(28).matches(listaDeNumerosPorExtenso[0])) {
			n.clear();
			n.add("100");
		}
		
		Collections.reverse(n);
		int numeralConvertido = Integer.parseInt(String.join("", n));
		
		return numeralConvertido;
	
	}
}
