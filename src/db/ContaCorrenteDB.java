package db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.ContaCorrente;

public class ContaCorrenteDB {
	private Map<Integer, ContaCorrente> contaCorrenteMap = new HashMap<>();

    public List<ContaCorrente> getContaLista() {
        List <ContaCorrente> contasCorrentes = new ArrayList<>();
        for (Map.Entry<Integer, ContaCorrente> contaCorrente : contaCorrenteMap.entrySet()){
            contasCorrentes.add(contaCorrente.getValue());
        }
        return contasCorrentes;
    }
    public void addNovaConta(ContaCorrente contaCorrente){
        contaCorrenteMap.put(contaCorrente.getNumeroDaConta(), contaCorrente);
    }
    public ContaCorrente getContaPorID(Integer numeroDaConta){
        return contaCorrenteMap.get(numeroDaConta);
    }
}

