package pires.felippe;

import pires.felippe.modelo.*;
import pires.felippe.servicos.FuncionarioService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //Serviço responsavel por manipular funcionarios
        FuncionarioService service = new FuncionarioService();

        Secretario ana = new Secretario("Ana", "01/2018", Cargo.SECRETARIO);
        Secretario bia = new Secretario("Bia", "01/2019", Cargo.SECRETARIO);

        Vendedor jonas  = new Vendedor("Jonas", "01/2019", Cargo.VENDEDOR );
        jonas.cadastrarVendas("02/2019", new BigDecimal(5200));
        jonas.cadastrarVendas("02/2022", new BigDecimal(2100));
        jonas.cadastrarVendas("02/2024", new BigDecimal(10000));

        Vendedor paulo = new Vendedor("Paulo", "01/2019", Cargo.VENDEDOR);
        paulo.cadastrarVendas("01/2021", new BigDecimal(5200));
        paulo.cadastrarVendas("02/2024", new BigDecimal(3200));

        Vendedor alexandre = new Vendedor("Alexandre", "03/2023", Cargo.VENDEDOR);
        alexandre.cadastrarVendas("01/2021", new BigDecimal(5200));
        alexandre.cadastrarVendas("02/2024", new BigDecimal(250000));

        Gerente carlos = new Gerente("Carlos", "02/2018", Cargo.GERENTE);

        List<Funcionario> listaFuncionarios = new ArrayList<>();
        listaFuncionarios.add(ana);
        listaFuncionarios.add(bia);
        listaFuncionarios.add(jonas);
        listaFuncionarios.add(carlos);

        List<FuncionarioComBeneficio> funcionarioComBeneficios = new ArrayList<>();
        funcionarioComBeneficios.add(ana);
        funcionarioComBeneficios.add(bia);
        funcionarioComBeneficios.add(jonas);

        List<Vendedor> vendedores = new ArrayList<>();
        vendedores.add(jonas);
        vendedores.add(paulo);
        vendedores.add(alexandre);

        System.out.println("Calculo valor total: R$" + service.calcularValorTotal(listaFuncionarios, "01/2019"));
        System.out.println("Calculo Salario: R$" + service.calcularValorSalario(listaFuncionarios, "02/2018"));
        System.out.println("Total gasto com beneficios: " + service.calcularValorBeneficios(funcionarioComBeneficios, "02/2019"));
        System.out.println("Maior salario é: " + service.maiorSalarioFuncionario(listaFuncionarios, "02/2019"));
        System.out.println("Funcionário com maior beneficio: " + service.maiorBeneficio(funcionarioComBeneficios, "02/2024"));
        service.melhorVendedor(vendedores, "02/2024");
    }
}