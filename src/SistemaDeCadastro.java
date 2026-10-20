
import java.util.ArrayList;
import java.util.Scanner;
import java.text.NumberFormat;
import java.util.Locale;

public class SistemaDeCadastro {

    public static void main(String[] args) {

        ArrayList<Funcionario> listaDeFuncionarios =
                new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        NumberFormat moeda =
                NumberFormat.getCurrencyInstance(
                        Locale.forLanguageTag("pt-BR")
                );

        System.out.println("=== CADASTRO DE FUNCIONÁRIOS ===");

        while (true) {

            String nome;

            while (true) {

                System.out.print(
                        "\nDigite o nome do funcionário " + "(ou 'sair' para encerrar): ");

                nome = scanner.nextLine().trim();

                if (nome.equalsIgnoreCase("sair")) {
                    break;
                }

                if (nome.isEmpty()) {
                    System.out.println(
                            "O nome não pode ficar vazio!"
                    );
                    continue;
                }

                if (!nome.matches(".*\\p{L}.*")) {
                    System.out.println(
                            "O nome deve conter pelo menos uma letra!"
                    );
                    continue;
                }

                break;
            }

            if (nome.equalsIgnoreCase("sair")) {
                break;
            }

            String cargo;

            while (true) {

                System.out.print("Digite o cargo do funcionário: ");
                cargo = scanner.nextLine().trim();

                if (cargo.isEmpty()) {
                    System.out.println(
                            "O cargo não pode ficar vazio!"
                    );
                    continue;
                }

                break;
            }

            double salario;

            while (true) {

                System.out.print("Digite o salário mensal: R$ ");

                try {
                    salario = Double.parseDouble(
                            scanner.nextLine()
                                    .trim()
                                    .replace(",", ".")
                    );

                    if (salario > 0 && Double.isFinite(salario)) {
                        break;
                    }

                    System.out.println(
                            "O salário deve ser maior que zero!"
                    );

                } catch (NumberFormatException e) {
                    System.out.println(
                            "Salário inválido! Digite um número."
                    );
                }
            }

            Funcionario novoFuncionario =
                    new Funcionario(nome, cargo, salario);

            listaDeFuncionarios.add(novoFuncionario);

            System.out.println("Funcionário " + nome + " cadastrado com sucesso!");

            System.out.println("\n=== RELATÓRIO DE FUNCIONÁRIOS ===");

            if (listaDeFuncionarios.isEmpty()) {

                System.out.println("Nenhum funcionário cadastrado.");

            } else {

                double totalSalarios = 0;

                for (Funcionario funcionario : listaDeFuncionarios) {

                    double salarioOriginal = funcionario.getSalario();
                    double salarioReajustado =
                            funcionario.calcularReajuste();

                    System.out.println("\nNome: " + funcionario.getNome());
                    System.out.println("Cargo: " + funcionario.getCargo());

                    System.out.println(
                            "Salário original: " + moeda.format(salarioOriginal));

                    System.out.println(
                            "Salário reajustado: " + moeda.format(salarioReajustado));

                    totalSalarios += salarioReajustado;
                }

                System.out.println("\n------------------------------");

                System.out.println("Total de funcionários: " + listaDeFuncionarios.size());

                System.out.println("Folha salarial total após reajuste: " + moeda.format(totalSalarios));
            }

            System.out.println("\nSistema encerrado. Obrigado!");
        }

    }
}


