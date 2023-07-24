package models;
import java.util.Date;

import db.TipoConta;

public class ContaPoupanca extends Conta {
    private double taxaDeJuros;
    private String CPF;

    public ContaPoupanca(String nome, String CPF, Double saldo, TipoConta tipoConta, Date data) {
        super(nome, saldo, tipoConta, data);
        this.CPF = CPF;
    }

    public double getTaxaDeJuros() {
        return this.taxaDeJuros;
    }

    public void setTaxaDeJuros(double taxaDeJuros) {        
        this.taxaDeJuros = taxaDeJuros;
    }

    public String getCPF() {
        return this.CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }
    public boolean saque(double sacar){
        double disponivelParaSaque = getSaldo();
        if (sacar > disponivelParaSaque || sacar <= 0){
                return false;
                }
            else {
                this.saldo = disponivelParaSaque - sacar;
                return true;
        }   
        
    }
    public double getDesposito(){
        return this.saldo += this.desposito;
    }
    
}
