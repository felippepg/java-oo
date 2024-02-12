package pires.felippe.modelo;

import pires.felippe.utils.DataHelper;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;

public class Secretario extends Funcionario implements FuncionarioComBeneficio{
    private final BigDecimal salario = new BigDecimal("7000");
    private final Double beneficio = 0.2;
    public Secretario(String nome, String anoContratacao, Cargo cargo) {
        super(nome, anoContratacao, cargo);
    }

    @Override
    public BigDecimal calcularSalario(String ano) {
        var anoAtual = DataHelper.transformarData(ano);
        var anoContratacao = DataHelper.transformarData(this.anoContratacao);
        long diff = ChronoUnit.MONTHS.between(anoContratacao, anoAtual);
        var anos = (int) Math.floor((double) diff / 12);

        if(anos <= 0) {
            return this.salario ;
        }

        return salario.add((new BigDecimal("1000").multiply(BigDecimal.valueOf(anos))));

    }


    @Override
    public BigDecimal getBeneficio(String data) {
        return this.calcularSalario(data).multiply(BigDecimal.valueOf(beneficio));
    }
}
