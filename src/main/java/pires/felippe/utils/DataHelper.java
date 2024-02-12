package pires.felippe.utils;

import pires.felippe.modelo.Funcionario;
import pires.felippe.modelo.FuncionarioComBeneficio;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class DataHelper {
    public static YearMonth transformarData(String data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
        YearMonth yearMonth = YearMonth.parse(data, formatter);
        return yearMonth;
    }
    private static boolean isDataAnterior(String dataContratacao, String data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
        YearMonth dataContratacaoYM = YearMonth.parse(dataContratacao, formatter);
        YearMonth dataYM = YearMonth.parse(data, formatter);
        return dataContratacaoYM.isBefore(dataYM) || dataContratacaoYM.equals(dataYM);
    }

    public static List<Funcionario> filtrarFuncionarioPorContratacao(List<Funcionario> funcionarios, String data) {
        return funcionarios.stream()
                .filter(funcionario -> {
                    String dataContratacao = funcionario.getAnoContratacao();
                    return DataHelper.isDataAnterior(dataContratacao, data);
                }).collect(Collectors.toList());
    }
    public static List<FuncionarioComBeneficio> filtrarFuncionarioComBeneficioPorContratacao(List<FuncionarioComBeneficio> funcionarios, String data) {
        return funcionarios.stream()
                .filter(funcionario -> {
                    String dataContratacao = funcionario.getAnoContratacao();
                    return DataHelper.isDataAnterior(dataContratacao, data);
                }).collect(Collectors.toList());
    }
}
