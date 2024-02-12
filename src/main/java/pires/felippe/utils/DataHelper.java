package pires.felippe.utils;

import pires.felippe.modelo.Funcionario;
import pires.felippe.modelo.FuncionarioComBeneficio;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe utilitária para auxiliar na manipulação de datas e filtragem de colaborador por dotas.
 */
public class DataHelper {
    /**
     * Transforma uma string de data no formato "MM/yyyy" em um objeto YearMonth.
     * @param data A string de data no formato "MM/yyyy".
     * @return Um objeto YearMonth representando a data especificada.
     */
    public static YearMonth transformarData(String data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
        YearMonth yearMonth = YearMonth.parse(data, formatter);
        return yearMonth;
    }
    /**
     * Verifica se uma data de contratação é anterior a outra data especificada.
     * @param dataContratacao A data de contratação no formato "MM/yyyy".
     * @param data A data de referência no formato "MM/yyyy".
     * @return true se a data de contratação for anterior à data especificada, false caso contrário.
     */
    private static boolean isDataAnterior(String dataContratacao, String data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
        YearMonth dataContratacaoYM = YearMonth.parse(dataContratacao, formatter);
        YearMonth dataYM = YearMonth.parse(data, formatter);
        return dataContratacaoYM.isBefore(dataYM) || dataContratacaoYM.equals(dataYM);
    }

    /**
     * Filtra uma lista de funcionários com base na data de contratação especificada.
     * @param funcionarios A lista de funcionários a ser filtrada.
     * @param data A data de referência para filtragem no formato "MM/yyyy".
     * @return Uma lista contendo os funcionários cuja data de contratação é anterior à data especificada.
     */
    public static List<Funcionario> filtrarFuncionarioPorContratacao(List<Funcionario> funcionarios, String data) {
        return funcionarios.stream()
                .filter(funcionario -> {
                    String dataContratacao = funcionario.getAnoContratacao();
                    return DataHelper.isDataAnterior(dataContratacao, data);
                }).collect(Collectors.toList());
    }

    /**
     * Filtra uma lista de funcionários com benefício com base na data de contratação especificada.
     * @param funcionarios A lista de funcionários com benefício a ser filtrada.
     * @param data A data de referência para filtragem no formato "MM/yyyy".
     * @return Uma lista contendo os funcionários com benefício cuja data de contratação é anterior à data especificada.
     */
    public static List<FuncionarioComBeneficio> filtrarFuncionarioComBeneficioPorContratacao(List<FuncionarioComBeneficio> funcionarios, String data) {
        return funcionarios.stream()
                .filter(funcionario -> {
                    String dataContratacao = funcionario.getAnoContratacao();
                    return DataHelper.isDataAnterior(dataContratacao, data);
                }).collect(Collectors.toList());
    }
}
