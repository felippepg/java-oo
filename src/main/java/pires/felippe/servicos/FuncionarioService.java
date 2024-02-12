package pires.felippe.servicos;

import pires.felippe.modelo.Funcionario;
import pires.felippe.modelo.FuncionarioComBeneficio;
import pires.felippe.modelo.Vendedor;
import pires.felippe.utils.DataHelper;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public class FuncionarioService {

    public BigDecimal calcularValorTotal(List<Funcionario> funcionarios, String data) {
        return DataHelper.filtrarFuncionarioPorContratacao(funcionarios, data)
                .stream()
                .map(funcionario -> funcionario.calcularSalario(data).add(funcionario.getBeneficio(data)))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    public BigDecimal calcularValorSalario(List<Funcionario> funcionarios, String data) {
        return DataHelper.filtrarFuncionarioPorContratacao(funcionarios, data)
                .stream()
                 .map(funcionario -> funcionario.calcularSalario(data))
                 .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal calcularValorBeneficios(List<FuncionarioComBeneficio> funcionarioComBeneficios, String data) {
        return DataHelper.filtrarFuncionarioComBeneficioPorContratacao(funcionarioComBeneficios, data).stream()
                 .map(funcionario -> funcionario.getBeneficio(data))
                 .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Funcionario maiorSalarioFuncionario(List<Funcionario> funcionarios, String data) {
        Optional<Funcionario> maior = DataHelper.filtrarFuncionarioPorContratacao(funcionarios, data)
                .stream()
                .max(Comparator.comparing(funcionario -> funcionario.calcularSalario(data)));

        if(maior.isPresent()) {
            return maior.get();
        } else {
            throw new ArrayIndexOutOfBoundsException("Lista vazia");
        }

    }

    public Funcionario maiorBeneficio(List<FuncionarioComBeneficio>funcionarioComBeneficios, String data) {
        Optional<FuncionarioComBeneficio> maior =
                DataHelper.filtrarFuncionarioComBeneficioPorContratacao(funcionarioComBeneficios,data)
                        .stream()
                        .max(Comparator.comparing(funcionario -> funcionario.getBeneficio(data)));

        if(maior.isPresent()) {
            return (Funcionario) maior.get();
        } else {
            throw new ArrayIndexOutOfBoundsException("Lista vazia");
        }
    }


    public Object melhorVendedor(List<Vendedor> vendedores, String data) {
        Vendedor melhorVendedor = null;
        BigDecimal maiorVenda = BigDecimal.ZERO;
        boolean houveVendas = false;

        for (Vendedor vendedor : vendedores) {
            Map<String, BigDecimal> vendasNoMes = vendedor.buscarVendas(data);
            if (vendasNoMes != null && vendasNoMes.containsKey(data) && vendasNoMes.get(data) != null) {
                houveVendas = true;
                BigDecimal vendasNoMesAtual = vendasNoMes.get(data);
                if (vendasNoMesAtual.compareTo(maiorVenda) > 0) {
                    maiorVenda = vendasNoMesAtual;
                    melhorVendedor = vendedor;
                }
            }
        }

        if (!houveVendas) {
            System.out.println("Não houve vendas nesse mês");
        } else {
            System.out.println("O melhor vendedor foi: " + melhorVendedor);
        }

        return melhorVendedor;
    }



}
