package lx.econta.catalogo

import lx.econta.Mes
import spock.lang.Specification

class CatalogoSpec extends Specification{

    def 'should marshall Catalogo object to xml '(){

        given: 'A new Catalogo'
        def catalogo = buildCatalogo()
        and: 'a the builder'
        def builder = new CatalogoBuilder()

        when: 'marshalling to xml'
        def xmlString = builder.build(catalogo)
        println xmlString

        then: xmlString == """catalogos"""

    }


    Catalogo buildCatalogo() {
        Catalogo catalogo = new Catalogo('1.3', 'PAP830101CR3', 2018, Mes.ENERO)
        catalogo.cuentas = buildCuentasDePrueba()
        return catalogo
    }

    List<Cuenta> buildCuentasDePrueba() {
        List<Cuenta> ctas = []
        ctas.add(new Cuenta('01','600-0000-0000-0000', 'GASTOS', '', 1, Naturaleza.ACREDORA))
        ctas.add(new Cuenta('01','105-0000-0000-0000', 'CLIENTES', '', 1, Naturaleza.DEUDORA))
        ctas.add(new Cuenta('01','400-0000-0000-0000', 'BANCOS', '', 1, Naturaleza.ACREDORA))
        ctas.add(new Cuenta('01','102-0000-0000-0000', 'COMPRAS', '', 1, Naturaleza.DEUDORA))

        return ctas
    }

}
