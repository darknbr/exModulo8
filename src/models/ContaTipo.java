package models;

import db.TipoConta;

public abstract class ContaTipo {
	private String nome;
	private TipoConta tipoConta;
	
	public ContaTipo(String nome, TipoConta tipoConta){
        this.nome = nome;
        this.tipoConta = tipoConta;
    }

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public TipoConta getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(TipoConta tipoConta) {
		this.tipoConta = tipoConta;
	}

	@Override
	public String toString() {
		return "ContaTipo [nome=" + nome + ", tipoConta=" + tipoConta + "]";
	}
	
	
}
