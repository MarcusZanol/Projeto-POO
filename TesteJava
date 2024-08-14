import java.util.Scanner;

public class CalculadoraSimples {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        while (true) {
            System.out.println("Menu:");
            System.out.println("[1] - Calcular Preço Total");
            System.out.println("[2] - Calcular Troco");
            System.out.println("[3] - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            if (opcao == 1) {
                System.out.print("Digite a quantidade da planta: ");
                int quantidade = scanner.nextInt();
                System.out.print("Digite o preço unitário da planta: ");
                double precoUnitario = scanner.nextDouble();
                double precoTotal = quantidade * precoUnitario;
                System.out.printf("O preço total é: R$ %.2f%n", precoTotal);
            } else if (opcao == 2) {
                System.out.print("Digite o valor pago pelo cliente: ");
                double valorPago = scanner.nextDouble();
                System.out.print("Digite o valor total da compra: ");
                double valorCompra = scanner.nextDouble();
                double troco = valorPago - valorCompra;
                System.out.printf("O troco a ser dado é: R$ %.2f%n", troco);
            } else if (opcao == 3) {
                System.out.println("Saindo do programa...");
                break;
            } else {
                System.out.println("Opção inválida. Tente novamente.");
            }

            System.out.println(); // Espaço em branco para separação
        }

        scanner.close();
    }
}
