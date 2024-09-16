import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Scanner;

public class CsvCreator {
    public static void main(String[] args) {

        // Usar try-with-resources para garantir que o Scanner seja fechado corretamente
        try (Scanner scanner = new Scanner(System.in)) {

            // Solicitar o número de colunas (mínimo 3)
            System.out.println("Informe o número de colunas (mínimo 3): ");
            int numColunas = scanner.nextInt();
            scanner.nextLine();  // Consumir nova linha

            if (numColunas < 3) {
                System.out.println("É necessário no mínimo 3 colunas!");
                return;
            }

            // Obter nomes das colunas
            String[] colunas = new String[numColunas];
            for (int i = 0; i < numColunas; i++) {
                System.out.println("Informe o nome da coluna " + (i + 1) + ": ");
                colunas[i] = scanner.nextLine();
            }

            // Solicitar o número de linhas
            System.out.println("Informe o número de linhas: ");
            int numLinhas = scanner.nextInt();
            scanner.nextLine();  // Consumir nova linha

            // Criar o diretório "output" se ele não existir
            File dir = new File("output");
            if (!dir.exists()) {
                dir.mkdirs();  // Cria o diretório se ele não existir
            }

            // Criar o arquivo CSV dentro da pasta "output"
            try (PrintWriter writer = new PrintWriter(new File(dir, "planilha.csv"))) {

                // Escrever cabeçalho (nomes das colunas)
                for (int i = 0; i < numColunas; i++) {
                    writer.print(colunas[i]);
                    if (i < numColunas - 1) {
                        writer.print(",");  // Adicionar separador
                    }
                }
                writer.println();  // Nova linha

                // Obter os dados de cada linha
                for (int i = 0; i < numLinhas; i++) {
                    System.out.println("Informe os dados para a linha " + (i + 1) + ":");

                    for (int j = 0; j < numColunas; j++) {
                        System.out.print("Coluna " + colunas[j] + ": ");
                        String dado = scanner.nextLine();

                        writer.print(dado);
                        if (j < numColunas - 1) {
                            writer.print(",");  // Adicionar separador
                        }
                    }
                    writer.println();  // Nova linha
                }

                System.out.println("Arquivo CSV criado com sucesso na pasta 'output'!");

            } catch (IOException e) {
                System.out.println("Erro ao criar o arquivo CSV: " + e.getMessage());
            }

        } catch (Exception e) {
            System.out.println("Erro na entrada de dados: " + e.getMessage());
        }
    }
}
