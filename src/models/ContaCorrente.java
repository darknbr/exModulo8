package models;

import java.util.Date;

import db.TipoConta;

public class ContaCorrente extends Conta implements Tributavel{ 

    private String CNPJ;
    private String CPF;
    private Double chequeEspecial;
    

    public ContaCorrente(String nome, String CPF, double saldo, TipoConta tipoConta, Date data, Double chequeEspecial) {
        super(nome, saldo, tipoConta, data);
        this.CPF = CPF;
        this.chequeEspecial = chequeEspecial;
    }
  
    public Double getChequeEspecial() {
        return chequeEspecial;
    }

    public void setChequeEspecial(Double chequeEspecial) {
        this.chequeEspecial = this.saldo * 2;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getCNPJ() {
        return this.CNPJ;
    }

    public double getSaldo(){
        return this.saldo + this.chequeEspecial;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public boolean saque(double sacar){
        double disponivelParaSaque = this.chequeEspecial + this.saldo;
        if (sacar > disponivelParaSaque || sacar <= 0){
                return false;
                }
            else {
                this.saldo -= sacar;
                return true;
        } 
    }

    public double getDesposito(){
        return this.saldo += this.desposito;
    }

    public double getValorImposto(){
        return this.saldo * 0.1;
    }
    
}
