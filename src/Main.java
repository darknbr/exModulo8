import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import db.ContaCorrenteDB;
import db.ContaPoupancaDB;
import db.ContaSalarioDB;
import db.TipoConta;
import models.Conta;
import models.ContaCorrente;
import models.ContaCorrenteJuridica;
import models.ContaPoupanca;
import models.ContaSalario;

public class Main {

    static ContaCorrenteDB contaCorrenteDB = new ContaCorrenteDB();
    static ContaPoupancaDB contaPoupancaDB = new ContaPoupancaDB();
    static ContaSalarioDB contaSalarioDB = new ContaSalarioDB();

    public static void main(String[] args) throws Exception {
        Locale.setDefault(Locale.US);

        Scanner sc = new Scanner(System.in);

        int option;

        do {
            System.out.println("---BEM VINDO AO BANCO MENTORAMA ---");
            System.out.println("---------------------------------");
            System.out.println("1 - Cadastrar Novo Cliente: ");
            System.out.println("4 - Saque por número da conta (ver na listagem): ");
            System.out.println("5 - Depósito por número da conta (ver na listagem): ");
            System.out.println("6 - Transferência entre contas (ver na listagem): ");
            System.out.println("8 - Saldo total em todas as contas báncarias:");
            System.out.println("9 - Listar Contas Cadastradas: ");
            System.out.println("0 - Sair");
            System.out.println("---------------------------------");

            System.out.println("Qual operação deseja realizar: ");
            System.out.println("---------------------------------");
            option = sc.nextInt();

            while (option == 1) {
                System.out.println("1 - Conta Corrente: ");
                System.out.println("2 - Conta Poupança: ");
                System.out.println("3 - Conta Salário: ");

                System.out.println("0 - Sair");

                System.out.print("Qual operação deseja realizar: ");
                option = sc.nextInt();

                process(option);

                option = -1;
            }
            process(option);

        } while (option != 0);
    }

    public static void process(int option) throws Exception {

        Scanner sc = new Scanner(System.in);

        TipoConta tipoConta = TipoConta.ContaCorrentePessoaFisica;
        double chequeEspecial = 0;
        String CNPJ = null;
        String CPF = null;

        switch (option) {

            case 1: {
                System.out.println("---------------------------------");
                System.out.println("----- Conta Corrente: ");
                System.out.println("---------------------------------");
                int contaTipo, contaTipoCS;

                do {
                    System.out.println("Conta para pessoa física ou jurídica: (PF = 1) ou (PJ = 2): ");
                    contaTipo = sc.nextInt();
                    if (contaTipo == 1) {
                        tipoConta = TipoConta.ContaCorrentePessoaFisica;
                    } else if (contaTipo == 2) {
                        tipoConta = TipoConta.ContaCorrentePessoaJuridica;
                    } else {
                        System.out.println("Digite uma opção válida!!!");
                    }
                } while (contaTipo != 1 && contaTipo != 2);

                sc.nextLine();
                System.out.println("Qual o nome do cliente: ");
                String nome = sc.nextLine();

                if (contaTipo == 1) {
                    System.out.println("Qual o CPF: ");
                    CPF = sc.nextLine();
                } else if (contaTipo == 2) {
                    System.out.println("Qual o CNPJ: ");
                    CNPJ = sc.nextLine();
                }

                System.out.println("Qual o saldo incial da conta: ");
                Double saldoInicial = sc.nextDouble();

                do {
                    System.out.println("Deseja aderir o cheque especial?: (S = 1) ou (N = 2): ");
                    contaTipoCS = sc.nextInt();
                    if (contaTipoCS == 1) {
                        chequeEspecial = saldoInicial * 2;
                    } else if (contaTipoCS == 2) {
                        chequeEspecial = 0;
                    } else {
                        System.out.println("Digite uma opção válida!!!");
                    }
                } while (contaTipoCS != 1 && contaTipoCS != 2);

                System.out.print("Qual a data de aniversário (DD/MM/AAAA): ");
                String dataString = sc.next();
                Date dataDeAniversario = new SimpleDateFormat("dd/MM/yyyy").parse(dataString);

                if (contaTipo == 1) {
                    ContaCorrente contaCorrente = new ContaCorrente(nome, CPF, saldoInicial, tipoConta,
                            dataDeAniversario,
                            chequeEspecial);
                    contaCorrenteDB.addNovaConta(contaCorrente);
                } else if (contaTipo == 2) {
                    ContaCorrenteJuridica contaCorrenteJ = new ContaCorrenteJuridica(nome, CNPJ, saldoInicial,
                            tipoConta, dataDeAniversario,
                            chequeEspecial);
                    contaCorrenteDB.addNovaConta(contaCorrenteJ);
                }

                System.out.println("---------------------------------");
                System.out.println("CLIENTE CADASTRADO COM SUCESSO!!!");
                System.out.println("---------------------------------");
                break;
            }
            case 2: {
                System.out.println("---------------------------------");
                System.out.println("----- Conta Poupança: ");
                System.out.println("---------------------------------");

                sc.nextLine();
                System.out.println("Qual o nome do cliente: ");
                String nome = sc.nextLine();

                System.out.println("Qual o CPF: ");
                CPF = sc.nextLine();

                System.out.println("Qual o saldo incial da conta: ");
                Double saldoInicial = sc.nextDouble();

                System.out.print("Qual a data de aniversário (DD/MM/AAAA): ");
                String dataString = sc.next();
                Date dataDeAniversario = new SimpleDateFormat("dd/MM/yyyy").parse(dataString);

                tipoConta = tipoConta.ContaPoupanca;

                ContaPoupanca contaPoupanca = new ContaPoupanca(nome, CPF, saldoInicial, tipoConta, dataDeAniversario);
                contaPoupancaDB.addNovaConta(contaPoupanca);

                System.out.println("---------------------------------");
                System.out.println("CLIENTE CADASTRADO COM SUCESSO!!!");
                System.out.println("---------------------------------");
                break;
            }
            case 3: {
                System.out.println("---------------------------------");
                System.out.println("----- Conta Salário: ");
                System.out.println("---------------------------------");

                sc.nextLine();
                System.out.println("Qual o nome do cliente: ");
                String nome = sc.nextLine();

                System.out.println("Qual o CPF: ");
                CPF = sc.nextLine();

                System.out.println("Qual o saldo incial da conta: ");
                Double saldoInicial = sc.nextDouble();

                System.out.print("Qual a data de aniversário (DD/MM/AAAA): ");
                String dataString = sc.next();
                Date dataDeAniversario = new SimpleDateFormat("dd/MM/yyyy").parse(dataString);

                tipoConta = tipoConta.ContaSalario;

                ContaSalario contaSalario = new ContaSalario(nome, CPF, saldoInicial, tipoConta, dataDeAniversario);
                contaSalarioDB.addNovaConta(contaSalario);

                System.out.println("---------------------------------");
                System.out.println("CLIENTE CADASTRADO COM SUCESSO!!!");
                System.out.println("---------------------------------");
                break;
            }
            case 4: {
                System.out.println("---------------------------------");
                System.out.println("----- Saque: ");
                System.out.println("---------------------------------");

                System.out.println("Qual o número da conta: ");
                int numeroDaConta = sc.nextInt();

                System.out.println("Qual o valor do saque: ");
                double valorDoSaque = sc.nextDouble();

                List<ContaCorrente> listaDeContas = contaCorrenteDB.getContaLista();
                List<ContaPoupanca> listaDeContasPoupancas = contaPoupancaDB.getPoupancaLista();
                List<ContaSalario> listaDeContasSalarios = contaSalarioDB.getSalarioList();

                for (ContaCorrente contasCorrentes : listaDeContas) {
                    if (contasCorrentes.getNumeroDaConta() == numeroDaConta) {
                        System.out.println("Saldo atual: " + contasCorrentes.getSaldo());
                        if (contasCorrentes.saque(valorDoSaque)) {
                            System.out.println("Saque realizado com sucesso!");
                        } else {
                            System.out.println("Não foi possível realizar o saque!");
                        }
                    }
                }
                for (ContaPoupanca contasPoupancas : listaDeContasPoupancas) {
                    if (contasPoupancas.getNumeroDaConta() == numeroDaConta) {
                        Date dataNascimento = contasPoupancas.getDataNascimento();                        
                        if(dataNascimento.getDate() > 20){                            
                            contasPoupancas.setSaldo(contasPoupancas.getSaldo() * 0.005); 
                        }
                        System.out.println("Saldo atual: " + contasPoupancas.getSaldo());
                        if (contasPoupancas.saque(valorDoSaque)) {
                            System.out.println("Saque realizado com sucesso!");
                        } else {
                            System.out.println("Não foi possível realizar o saque!");
                        }
                    }
                }
                for (ContaSalario contaSalarios : listaDeContasSalarios) {
                    if (contaSalarios.getNumeroDaConta() == numeroDaConta) {
                        contaSalarios.setLimiteDeSaque(1);
                        System.out.println("Saldo atual: " + contaSalarios.getSaldo());
                        System.out.println("Limite de saques: " + contaSalarios.getLimiteDeSaque());
                        if (contaSalarios.saque(valorDoSaque) && contaSalarios.getLimiteDeSaque() < 2) {
                            System.out.println("Saque realizado com sucesso!");
                        } else {
                            System.out.println("Não foi possível realizar o saque!");
                        }
                    }
                }
                break;
            }
            case 5: {
                System.out.println("---------------------------------");
                System.out.println("----- Depósito: ");
                System.out.println("---------------------------------");

                System.out.println("Qual o número da conta: ");
                int numeroDaConta = sc.nextInt();

                System.out.println("Qual o valor do deposito: ");
                double valorDoDeposito = sc.nextDouble();

                List<ContaCorrente> listaDeContas = contaCorrenteDB.getContaLista();
                List<ContaPoupanca> listaDeContasPoupancas = contaPoupancaDB.getPoupancaLista();
                List<ContaSalario> listaDeContasSalarios = contaSalarioDB.getSalarioList();

                for (ContaCorrente contasCorrentes : listaDeContas) {
                    if (contasCorrentes.getNumeroDaConta() == numeroDaConta) {
                        System.out.println("Saldo atual: " + contasCorrentes.getSaldo());
                        contasCorrentes.setSaldo(contasCorrentes.getSaldo() + valorDoDeposito);
                        System.out.println("Depósito realizado com sucesso!");
                    }
                }
                for (ContaPoupanca contasPoupancas : listaDeContasPoupancas) {
                    if (contasPoupancas.getNumeroDaConta() == numeroDaConta) {                        
                        System.out.println("Saldo atual: " + contasPoupancas.getSaldo());
                        contasPoupancas.setSaldo(contasPoupancas.getSaldo() + valorDoDeposito);
                        System.out.println("Depósito realizado com sucesso!");
                    }
                }
                for (ContaSalario contaSalarios : listaDeContasSalarios) {
                    if (contaSalarios.getNumeroDaConta() == numeroDaConta) {
                        System.out.println("Saldo atual: " + contaSalarios.getSaldo());
                        contaSalarios.setSaldo(contaSalarios.getSaldo() + valorDoDeposito);
                        System.out.println("Depósito realizado com sucesso!");
                    }
                }
                break;
            }
            case 6: {
                System.out.println("---------------------------------");
                System.out.println("----- Transferência: ");
                System.out.println("---------------------------------");
                
                System.out.println("Qual o número da conta de origem: ");
                int numeroDaContaOrigem = sc.nextInt();

                System.out.println("Qual o número da conta de destino: ");
                int numeroDaContaDestino = sc.nextInt();

                System.out.println("Qual o valor da transferência: ");
                double valorDaTransferencia = sc.nextDouble();

                if(numeroDaContaOrigem == numeroDaContaDestino){
                    System.out.println("Não é possível realizar transferência para a mesma conta!");
                    break;
                }

                if(valorDaTransferencia <= 0){
                    System.out.println("Não é possível realizar transferência com valor menor ou igual a zero!");
                    break;
                }

                if((contaCorrenteDB.getContaPorID(numeroDaContaOrigem).getTipoConta() == TipoConta.ContaCorrentePessoaFisica)
                 && (contaCorrenteDB.getContaPorID(numeroDaContaDestino).getTipoConta() == TipoConta.ContaCorrentePessoaFisica)){
                    System.out.println("------------------------------------------------------------");
                    System.out.println("Transferência entre contas correntes de pessoas físicas!");
                    System.out.println("Saldo atual da conta de origem: " + contaCorrenteDB.getContaPorID(numeroDaContaOrigem).getSaldo());
                    System.out.println("Saldo atual da conta de destino: " + contaCorrenteDB.getContaPorID(numeroDaContaDestino).getSaldo());
                    System.out.println("Valor da transferência: " + valorDaTransferencia);
                    System.out.println("Saldo da conta de origem após transferência: " + (contaCorrenteDB.getContaPorID(numeroDaContaOrigem).getSaldo() - valorDaTransferencia));
                    System.out.println("Saldo da conta de destino após transferência: " + (contaCorrenteDB.getContaPorID(numeroDaContaDestino).getSaldo() + valorDaTransferencia));
                    System.out.println("------------------------------------------------------------");
                    if(contaCorrenteDB.getContaPorID(numeroDaContaOrigem).getSaldo() < valorDaTransferencia){
                        System.out.println("Saldo insuficiente para realizar a transferência!");
                        break;
                    }
                    double saldoFinalOrigem = contaCorrenteDB.getContaPorID(numeroDaContaOrigem).getSaldo() - valorDaTransferencia;
                    double saldoFinalDestino = contaCorrenteDB.getContaPorID(numeroDaContaDestino).getSaldo() + valorDaTransferencia;
                    contaCorrenteDB.getContaPorID(numeroDaContaOrigem).setSaldo(saldoFinalOrigem);
                    contaCorrenteDB.getContaPorID(numeroDaContaDestino).setSaldo(saldoFinalDestino);
                    System.out.println("Transferência realizada com sucesso!");                   
                } else {
                    System.out.println("Não é possível realizar transferência entre contas de tipos diferentes!");
                }

                if((contaCorrenteDB.getContaPorID(numeroDaContaOrigem).getTipoConta() == TipoConta.ContaCorrentePessoaJuridica)
                 && (contaCorrenteDB.getContaPorID(numeroDaContaDestino).getTipoConta() == TipoConta.ContaCorrentePessoaJuridica)){
                    System.out.println("------------------------------------------------------------");
                    System.out.println("Transferência entre contas correntes de pessoas jurídicas!");
                    System.out.println("Saldo atual da conta de origem: " + contaCorrenteDB.getContaPorID(numeroDaContaOrigem).getSaldo());
                    System.out.println("Saldo atual da conta de destino: " + contaCorrenteDB.getContaPorID(numeroDaContaDestino).getSaldo());
                    System.out.println("Valor da transferência: " + valorDaTransferencia);
                    System.out.println("Saldo da conta de origem após transferência: " + (contaCorrenteDB.getContaPorID(numeroDaContaOrigem).getSaldo() - valorDaTransferencia));
                    System.out.println("Saldo da conta de destino após transferência: " + (contaCorrenteDB.getContaPorID(numeroDaContaDestino).getSaldo() + valorDaTransferencia));
                    System.out.println("------------------------------------------------------------");
                    if(contaCorrenteDB.getContaPorID(numeroDaContaOrigem).getSaldo() < valorDaTransferencia){
                        System.out.println("Saldo insuficiente para realizar a transferência!");
                        break;
                    }
                    double saldoFinalOrigem = contaCorrenteDB.getContaPorID(numeroDaContaOrigem).getSaldo() - valorDaTransferencia;
                    double saldoFinalDestino = contaCorrenteDB.getContaPorID(numeroDaContaDestino).getSaldo() + valorDaTransferencia;
                    contaCorrenteDB.getContaPorID(numeroDaContaOrigem).setSaldo(saldoFinalOrigem);
                    contaCorrenteDB.getContaPorID(numeroDaContaDestino).setSaldo(saldoFinalDestino);
                    System.out.println("Transferência realizada com sucesso!");                   
                } else {
                    System.out.println("Não é possível realizar transferência entre contas de tipos diferentes!");
                }

                if((contaPoupancaDB.getContaPorID(numeroDaContaOrigem).getTipoConta() == TipoConta.ContaPoupanca)
                 && (contaPoupancaDB.getContaPorID(numeroDaContaDestino).getTipoConta() == TipoConta.ContaPoupanca)){
                    System.out.println("------------------------------------------------------------");
                    System.out.println("Transferência entre contas poupanças!");
                    System.out.println("Saldo atual da conta de origem: " + contaPoupancaDB.getContaPorID(numeroDaContaOrigem).getSaldo());
                    System.out.println("Saldo atual da conta de destino: " + contaPoupancaDB.getContaPorID(numeroDaContaDestino).getSaldo());
                    System.out.println("Valor da transferência: " + valorDaTransferencia);
                    System.out.println("Saldo da conta de origem após transferência: " + (contaPoupancaDB.getContaPorID(numeroDaContaOrigem).getSaldo() - valorDaTransferencia));
                    System.out.println("Saldo da conta de destino após transferência: " + (contaPoupancaDB.getContaPorID(numeroDaContaDestino).getSaldo() + valorDaTransferencia));
                    System.out.println("------------------------------------------------------------");
                    if(contaPoupancaDB.getContaPorID(numeroDaContaOrigem).getSaldo() < valorDaTransferencia){
                        System.out.println("Saldo insuficiente para realizar a transferência!");
                        break;
                    }
                    double saldoFinalOrigem = contaPoupancaDB.getContaPorID(numeroDaContaOrigem).getSaldo() - valorDaTransferencia;
                    double saldoFinalDestino = contaPoupancaDB.getContaPorID(numeroDaContaDestino).getSaldo() + valorDaTransferencia;
                    contaPoupancaDB.getContaPorID(numeroDaContaOrigem).setSaldo(saldoFinalOrigem);
                    contaPoupancaDB.getContaPorID(numeroDaContaDestino).setSaldo(saldoFinalDestino);
                    System.out.println("Transferência realizada com sucesso!");                   
                } else {
                    System.out.println("Não é possível realizar transferência entre contas de tipos diferentes!");
                }

                if((contaSalarioDB.getContaPorID(numeroDaContaOrigem).getTipoConta() == TipoConta.ContaSalario)
                 && (contaSalarioDB.getContaPorID(numeroDaContaDestino).getTipoConta() == TipoConta.ContaSalario)){
                    System.out.println("------------------------------------------------------------");
                    System.out.println("Transferência entre contas sálarios!");
                    System.out.println("Saldo atual da conta de origem: " + contaSalarioDB.getContaPorID(numeroDaContaOrigem).getSaldo());
                    System.out.println("Saldo atual da conta de destino: " + contaSalarioDB.getContaPorID(numeroDaContaDestino).getSaldo());
                    System.out.println("Valor da transferência: " + valorDaTransferencia);
                    System.out.println("Saldo da conta de origem após transferência: " + (contaSalarioDB.getContaPorID(numeroDaContaOrigem).getSaldo() - valorDaTransferencia));
                    System.out.println("Saldo da conta de destino após transferência: " + (contaSalarioDB.getContaPorID(numeroDaContaDestino).getSaldo() + valorDaTransferencia));
                    System.out.println("------------------------------------------------------------");
                    if(contaSalarioDB.getContaPorID(numeroDaContaOrigem).getSaldo() < valorDaTransferencia){
                        System.out.println("Saldo insuficiente para realizar a transferência!");
                        break;
                    }
                    double saldoFinalOrigem = contaSalarioDB.getContaPorID(numeroDaContaOrigem).getSaldo() - valorDaTransferencia;
                    double saldoFinalDestino = contaSalarioDB.getContaPorID(numeroDaContaDestino).getSaldo() + valorDaTransferencia;
                    contaSalarioDB.getContaPorID(numeroDaContaOrigem).setSaldo(saldoFinalOrigem);
                    contaSalarioDB.getContaPorID(numeroDaContaDestino).setSaldo(saldoFinalDestino);
                    System.out.println("Transferência realizada com sucesso!");                   
                } else {
                    System.out.println("Não é possível realizar transferência entre contas de tipos diferentes!");
                }
                
                break;
            }
            case 8: {
                double saldoTotalContas = 0;

                List<ContaCorrente> listaDeContas = contaCorrenteDB.getContaLista();
                List<ContaPoupanca> listaDeContasPoupancas = contaPoupancaDB.getPoupancaLista();
                List<ContaSalario> listaDeContasSalarios = contaSalarioDB.getSalarioList();

                for (ContaCorrente contasCorrentes : listaDeContas) {
                    saldoTotalContas += contasCorrentes.getSaldo();
                    System.out.println(saldoTotalContas);
                }
                for (ContaPoupanca contasPoupancas : listaDeContasPoupancas) {
                    saldoTotalContas += contasPoupancas.getSaldo();
                    System.out.println(saldoTotalContas);
                }
                for (ContaSalario contaSalarios : listaDeContasSalarios) {
                    saldoTotalContas += contaSalarios.getSaldo();
                    System.out.println(saldoTotalContas);
                }
                System.out.println("Saldo total de $$ no banco, S/ cheques especiais");
                System.out.println("Sem juros nas poupanças, apenas SALDOS. ");
                System.out.println("Valor total R$ " + saldoTotalContas);
                break;

            }
            case 9: {
                System.out.println("---------------------------------");
                System.out.println("LISTA DE CONTAS: ");
                System.out.println("---------------------------------");

                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

                List<ContaCorrente> listaDeContas = contaCorrenteDB.getContaLista();
                List<ContaPoupanca> listaDeContasPoupancas = contaPoupancaDB.getPoupancaLista();
                List<ContaSalario> listaDeContasSalarios = contaSalarioDB.getSalarioList();

                for (ContaCorrente contasCorrentes : listaDeContas) {
                    System.out.println("---------------------------------");
                    System.out.println("Número da conta: " + contasCorrentes.getNumeroDaConta());
                    System.out.println("Agência: " + contasCorrentes.getAgencia());
                    System.out.println("Nome: " + contasCorrentes.getNome());
                    if (contasCorrentes.getTipoConta() == tipoConta.ContaCorrentePessoaFisica) {
                        System.out.println("CPF: " + contasCorrentes.getCPF());
                    } else if (contasCorrentes.getTipoConta() == tipoConta.ContaCorrentePessoaJuridica) {
                        System.out.println("CNPJ: " + contasCorrentes.getCNPJ());
                    }
                    System.out.println("Saldo: " + contasCorrentes.getSaldo());
                    System.out.println("Cheque Especial: " + contasCorrentes.getChequeEspecial());
                    System.out.println("Data de Nascimento: " + formato.format(contasCorrentes.getDataNascimento()));
                    System.out.println("---------------------------------");
                }
                for (ContaPoupanca contasPoupancas : listaDeContasPoupancas) {
                    System.out.println("---------------------------------");
                    System.out.println("Número da conta: " + contasPoupancas.getNumeroDaConta());
                    System.out.println("Agência: " + contasPoupancas.getAgencia());
                    System.out.println("Nome: " + contasPoupancas.getNome());
                    System.out.println("CPF: " + contasPoupancas.getCPF());
                    System.out.println("Saldo: " + contasPoupancas.getSaldo());
                    System.out.println("Data de Nascimento: " + formato.format(contasPoupancas.getDataNascimento()));
                    System.out.println("Data de aniversário da poupança juros 0.5% a/m.");
                    System.out.println("---------------------------------");
                }
                for (ContaSalario contaSalarios : listaDeContasSalarios) {
                    System.out.println("---------------------------------");
                    System.out.println("Número da conta: " + contaSalarios.getNumeroDaConta());
                    System.out.println("Agência: " + contaSalarios.getAgencia());
                    System.out.println("Nome: " + contaSalarios.getNome());
                    System.out.println("CPF: " + contaSalarios.getCPF());
                    System.out.println("Saldo: " + contaSalarios.getSaldo());
                    System.out.println("Conta limitada a 2 saques mensais.");
                    System.out.println("---------------------------------");
                }
                break;
            }
        }
    }
}
