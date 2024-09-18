import java.util.Scanner;

public class ConversorNumerosRomanos {
    // Arrays que armazenam os valores e símbolos romanos
    private static final int[] valores = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    private static final String[] simbolos = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    public static String toRoman(int numero) {
        StringBuilder resultado = new StringBuilder();
        // Laço para converter o número inteiro para romano
        for (int i = 0; i < valores.length; i++) {
            while (numero >= valores[i]) {
                resultado.append(simbolos[i]);
                numero -= valores[i];
            }
        }
        return resultado.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite um número para converter para números romanos (1-3999): ");
        int numero = scanner.nextInt();
        if (numero >= 1 && numero <= 3999) {
            System.out.println("O número " + numero + " em números romanos é: " + toRoman(numero));
        } else {
            System.out.println("Número fora do intervalo. Por favor, insira um número entre 1 e 3999.");
        }
        scanner.close();
    }
}
