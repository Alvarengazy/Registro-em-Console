
import java.util.Scanner;
import java.util.ArrayList;

public class App {

    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<String> nomes = new ArrayList<>();
    private static final ArrayList<Double> valores = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("== Bem Vindo ==");

        String name = lertexto("Digite seu nome: ");
        menu(name);
    }

    private static String lertexto(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine().trim();
    }

    private static int lerinteiro(String mensagem) {
        while (true) {
            try {
                return Integer.parseInt(lertexto(mensagem));
            } catch (NumberFormatException e) {
                System.out.println("Digite um número inteiro válido!");
            }
        }
    }

    public static void menu(String name) {
        int opcao;

        do {
            System.out.println("\n==== Olá " + name + " ====");
            System.out.println("1. Registrar despesa");
            System.out.println("2. Listar despesas");
            System.out.println("3. Exibir total gasto");
            System.out.println("4. Limpar despesas");
            System.out.println("0. Encerrar programa");

            opcao = lerinteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1 -> despesas();
                case 2 -> lista();
                case 3 -> total();
                case 4 -> limpar();
                case 0 -> System.out.println("Encerrando programa...");
                default -> System.out.println("Opção inválida!");
            }

        } while (opcao != 0);
    }

    private static void despesas() {
        int opcao;

        do {
            System.out.println("\n== Despesas ==");
            System.out.println("1. Adicionar");
            System.out.println("0. Voltar");

            opcao = lerinteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1 -> add();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida!");
            }

        } while (opcao != 0);
    }

    private static void add() {
        String name;

        do {
            name = lertexto("Digite o nome da despesa: ");

            if (name.isBlank()) {
                System.out.println("O nome não pode ficar vazio!");
            }

        } while (name.isBlank());

        double valor;

        while (true) {
            try {
                valor = Double.parseDouble(
                    lertexto("Digite o valor da despesa: ")
                        .replace(",", ".")
                );

                if (!Double.isFinite(valor) || valor <= 0) {
                    System.out.println("Digite um valor maior que zero!");
                    continue;
                }

                break;

            } catch (NumberFormatException e) {
                System.out.println("Digite um valor numérico válido!");
            }
        }

        nomes.add(name);
        valores.add(valor);

        System.out.println("\nDespesa registrada!");
        System.out.println("Nome: " + name);
        System.out.printf("Valor: R$ %.2f%n", valor);
    }

    private static void lista() {
        if (nomes.isEmpty()) {
            System.out.println("Nenhuma despesa registrada!");
            return;
        }

        System.out.println("\n== Despesas registradas ==");

        for (int i = 0; i < nomes.size(); i++) {
            System.out.printf(
                "%d. %s - R$ %.2f%n",
                i + 1,
                nomes.get(i),
                valores.get(i)
            );
        }
    }

    private static void total() {
        double total = 0;

        for (int i = 0; i < valores.size(); i++) {
            total += valores.get(i);
        }

        System.out.printf("Total gasto: R$ %.2f%n", total);
    }

    private static void limpar() {
        if (nomes.isEmpty()) {
            System.out.println("Não existem despesas para apagar!");
            return;
        }

        String confirmacao = lertexto(
            "Tem certeza que deseja apagar todas as despesas? (s/n): "
        );

        if (confirmacao.equalsIgnoreCase("s")) {
            nomes.clear();
            valores.clear();
            System.out.println("Todas as despesas foram apagadas!");
        } else {
            System.out.println("Operação cancelada!");
        }
    }
}
