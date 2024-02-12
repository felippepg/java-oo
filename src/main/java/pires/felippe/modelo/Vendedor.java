package pires.felippe.modelo;

import pires.felippe.utils.DataHelper;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

public class Vendedor extends Funcionario implements FuncionarioComBeneficio {
    private final BigDecimal salario = new BigDecimal("12000");
    private final Double beneficio = 0.3;
    Map<String, BigDecimal> vendasNoMes = new HashMap<>();

    public Vendedor (String nome, String anoContratacao, Cargo cargo) {
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
        return salario.add(new BigDecimal("1800").multiply(BigDecimal.valueOf(anos)));
    }

    @Override
    public BigDecimal getBeneficio(String data) {
        var valor = this.vendasNoMes.get(data);
        if(valor != null) {
            return this.vendasNoMes.get(data).multiply(BigDecimal.valueOf(beneficio));
        }
        return BigDecimal.ZERO;
    }

    /**
     * Registra as vendas realizadas pelo vendedor em um determinado mês.
     * @param data A data do mês das vendas.
     * @param valor O valor total das vendas no mês.
     */
    public void cadastrarVendas(String data, BigDecimal valor) {
        vendasNoMes.put(data, valor);
    }

    /**
     * Busca as vendas realizadas pelo vendedor em um determinado mês.
     * @param data A data do mês das vendas.
     * @return Um mapa contendo as vendas realizadas pelo vendedor no mês especificado.
     */
    public Map<String, BigDecimal> buscarVendas(String data) {
        Map<String, BigDecimal> vendasNoMes = this.vendasNoMes;
        if (vendasNoMes.get(data) == null) {
            return null;
        }
        return vendasNoMes;
    }


}
