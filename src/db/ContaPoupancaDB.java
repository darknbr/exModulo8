package db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.ContaPoupanca;

public class ContaPoupancaDB {
    private Map<Integer, ContaPoupanca> contaPoupancaMap = new HashMap<>();

    public List<ContaPoupanca> getPoupancaLista() {
        List <ContaPoupanca> contasPoupancas = new ArrayList<>();
        for (Map.Entry<Integer, ContaPoupanca> contaPoupanca : contaPoupancaMap.entrySet()){
            contasPoupancas.add(contaPoupanca.getValue());
        }
        return contasPoupancas;
    }
    public void addNovaConta(ContaPoupanca contaPoupanca){
        contaPoupancaMap.put(contaPoupanca.getNumeroDaConta(), contaPoupanca);
    }
    public ContaPoupanca getContaPorID(Integer numeroDaConta){
        return contaPoupancaMap.get(numeroDaConta);
    }
}
