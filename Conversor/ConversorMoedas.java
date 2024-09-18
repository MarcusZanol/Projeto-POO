package Conversor;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class ConversorMoedas {
    // Arrays que armazenam os códigos das moedas e suas taxas em relação ao USD
    private static final String[] moedas = {"USD", "EUR", "JPY", "GBP", "BRL"};
    private static final double[] taxas = {1.0, 0.85, 110.0, 0.75, 5.4};

    // Método para encontrar o índice da moeda no array
    public static int findCurrencyIndex(String currency) {
        for (int i = 0; i < moedas.length; i++) {
            if (moedas[i].equals(currency)) {
                return i;
            }
        }
        throw new IllegalArgumentException("Moeda não encontrada: " + currency);
    }

    // Método para converter o valor entre as moedas
    public static double convert(double amount, String fromCurrency, String toCurrency) {
        int fromIndex = findCurrencyIndex(fromCurrency);
        int toIndex = findCurrencyIndex(toCurrency);
        double valorEmUSD = amount / taxas[fromIndex];  // Converter para USD
        return valorEmUSD * taxas[toIndex];              // Converter de USD para moeda destino
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Solicita entrada do usuário
        System.out.print("Digite o valor a ser convertido: ");
        double valor = scanner.nextDouble();
        System.out.print("Digite a moeda de origem (USD, EUR, JPY, GBP, BRL): ");
        String moedaOrigem = scanner.next().toUpperCase();
        System.out.print("Digite a moeda de destino (USD, EUR, JPY, GBP, BRL): ");
        String moedaDestino = scanner.next().toUpperCase();

        try {
            // Realiza a conversão
            double valorConvertido = convert(valor, moedaOrigem, moedaDestino);

            // Formata o valor convertido
            NumberFormat formatoMoeda = NumberFormat.getCurrencyInstance(Locale.US);
            System.out.println("Valor convertido: " + formatoMoeda.format(valorConvertido));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        scanner.close();
    }
}
