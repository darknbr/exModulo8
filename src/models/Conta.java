package models;

import java.util.Date;

import db.TipoConta;

public abstract class Conta {
    private static int proximoNumeroConta = 1;
    private String nome;
    protected double saldo;
    private  int agencia = 0001;
    private Date dataNascimento;
    private Integer numeroDaConta;
    private TipoConta tipoConta;
    protected double sacar;
    protected double desposito;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Integer getNumeroDaConta() {
        return numeroDaConta;
    }

    public void setNumeroDaConta(Integer numeroDaConta) {
        this.numeroDaConta = numeroDaConta;
    }

    @Override
    public String toString() {
        return "Conta [nome=" + nome + ", saldo=" + saldo + ", numeroDaConta=" + numeroDaConta + ", tipoConta="
                + tipoConta + "]";
    }

    public TipoConta getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(TipoConta tipoConta) {
        this.tipoConta = tipoConta;
    }

    public Conta(String nome, Double saldo, TipoConta tipoConta, Date dataNascimento) {
        super();
        this.nome = nome;
        this.saldo = saldo;
        this.numeroDaConta = proximoNumeroConta++;
        this.tipoConta = tipoConta;
        this.dataNascimento = dataNascimento;        
    }

    public Date getDataNascimento() {        
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public int getAgencia() {
        return this.agencia;
    }

    public void setAgencia(int agencia) {
        this.agencia = agencia;
    }
    
    public Conta(double sacar){
        this.sacar = sacar;
    }

    public void setSacar(double sacar) {
        this.sacar = sacar;
    }

    public abstract double getDesposito();

    public void setDesposito(double desposito) {
        this.desposito = desposito;
    }

}
