import java.util.Scanner;

public class TesteExtensoParaNumero {

	public static void main(String[] args) {
		
	
	System.out.println("Digite um numero entre zero e dez mil: ");
	Scanner leitura = new Scanner(System.in);
	String numero = leitura.nextLine();
//	condi��es para numero ser aceit�vel
	Conversor c = new Conversor();
	System.out.println(c.ExtensoParaNumero(numero));
	
	
	}	
}
