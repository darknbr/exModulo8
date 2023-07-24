package db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.ContaSalario;

public class ContaSalarioDB {
    private Map<Integer, ContaSalario> contaSalarioMap = new HashMap<>();

    public List<ContaSalario> getSalarioList() {
        List <ContaSalario> contasSalarios = new ArrayList<>();
        for (Map.Entry<Integer, ContaSalario> contaSalario : contaSalarioMap.entrySet()){
            contasSalarios.add(contaSalario.getValue());
        }
        return contasSalarios;
    }
    public void addNovaConta(ContaSalario contaSalario){
        contaSalarioMap.put(contaSalario.getNumeroDaConta(), contaSalario);
    }
    public ContaSalario getContaPorID(Integer numeroDaConta){
        return contaSalarioMap.get(numeroDaConta);
    }
}
