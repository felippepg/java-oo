package pires.felippe.modelo;

import pires.felippe.utils.DataHelper;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;

public class Gerente extends Funcionario {
    private final BigDecimal salario = new BigDecimal("20000");
    public Gerente(String nome, String anoContratacao, Cargo cargo) {
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

        return salario.add(new BigDecimal("3000").multiply(BigDecimal.valueOf(anos)));
    }
}
