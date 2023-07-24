package models;

import java.util.Date;

import db.TipoConta;

public class ContaSalario extends Conta implements Tributavel{
    private int limiteDeSaque;
    private String CPF;

    public ContaSalario(String nome, String CPF, Double saldo, TipoConta tipoConta, Date data){
        super(nome, saldo, tipoConta, data);
        this.CPF = CPF;
    }

    public int getLimiteDeSaque() {
        return this.limiteDeSaque;
    }

    public void setLimiteDeSaque(int limiteDeSaque) {
        this.limiteDeSaque = limiteDeSaque;
    }

    public String getCPF() {
        return this.CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }
    public boolean saque(double sacar){
        double disponivelParaSaque = this.saldo;
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

    public double getValorImposto() {
        return this.saldo * 0.05;
    }

}
