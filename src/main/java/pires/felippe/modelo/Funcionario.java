package pires.felippe.modelo;

import java.math.BigDecimal;

public abstract class Funcionario {
    String nome;
    String anoContratacao;
    Cargo cargo;
    private final BigDecimal beneficio = BigDecimal.ZERO;

    /**
     * Construtor da classe Funcionario.
     * @param nome O nome do funcionário.
     * @param anoContratacao O ano de contratação do funcionário.
     * @param cargo O cargo do funcionário.
     */
    public Funcionario(String nome, String anoContratacao, Cargo cargo) {
        this.nome = nome;
        this.anoContratacao = anoContratacao;
        this.cargo = cargo;
    }

    public Funcionario() {
    }

    /**
     * Calcula o salário do funcionário para um determinado ano.
     * @param data O ano para o qual o salário deve ser calculado.
     * @return O salário do funcionário para o ano especificado.
     */
    public abstract BigDecimal calcularSalario(String data);
    /**
     * Obtém o benefício do funcionário para um determinado mês.
     * @param data A data para a qual o benefício deve ser calculado.
     * @return O benefício do funcionário para o mês especificado.
     */
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
