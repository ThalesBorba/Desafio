import java.util.Scanner;

public class TesteNumeroPorExtenso {

	public static void main(String[] args) {
		
		Conversor c = new Conversor();
		System.out.println("Digite um numero entre 0 e 10.000.000 (sem pontos): ");
		Scanner leitura = new Scanner(System.in);
		if(!leitura.hasNextDouble()) {
			throw new NumeroInvalidoException("N�mero Inv�lido");
		}
		double numero = leitura.nextDouble();
		if(0 > numero || numero > 10000) {
			throw new NumeroForaDeRangeException("N�mero fora dos par�metros do programa.");
		}
		int inteiro = (int) numero;
		leitura.close();
		
		
		System.out.println(c.numeroPorExtenso(inteiro));
		}
	
}