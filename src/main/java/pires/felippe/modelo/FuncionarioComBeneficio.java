package pires.felippe.modelo;

import java.math.BigDecimal;

public interface FuncionarioComBeneficio {

    public BigDecimal getBeneficio(String data);

    String getAnoContratacao();
}
