package pires.felippe.modelo;

import java.math.BigDecimal;

public abstract class Funcionario {
    String nome;
    String anoContratacao;
    Cargo cargo;
    private final BigDecimal beneficio = BigDecimal.ZERO;


    public Funcionario(String nome, String anoContratacao, Cargo cargo) {
        this.nome = nome;
        this.anoContratacao = anoContratacao;
        this.cargo = cargo;
    }

    public Funcionario() {
    }

    public abstract BigDecimal calcularSalario(String ano);

    public BigDecimal getBeneficio(String data) {
        return this.calcularSalario(data).multiply(beneficio);
    }

    public String getAnoContratacao() {
        return anoContratacao;
    }

    @Override
    public String toString() {
        return this.nome;
    }

}
