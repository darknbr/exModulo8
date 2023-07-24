package models;

import java.util.Date;

import db.TipoConta;

public class ContaCorrenteJuridica extends ContaCorrente {
    private String CNPJ;

    public ContaCorrenteJuridica(String nome, String CNPJ, Double saldo, TipoConta tipoConta, Date data,
            Double chequeEspecial) {
        super(nome, CNPJ, saldo, tipoConta, data, chequeEspecial);
        this.CNPJ = CNPJ;
    }

    public String getCNPJ() {
        return this.CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }
    
    
}
