package lx.econta.catalogo

import lx.econta.Mes
import spock.lang.Specification

class CatalogoSpec extends Specification{

    def "should provide a Fluent API support"() {
        given:
        Catalogo catalogo = Catalogo.builder()
            .ejercicio(2018)
            .mes(Mes.ENERO)
            .version('1.3')
            .build()
        expect:
        catalogo.ejercicio == 2018
        catalogo.mes == Mes.ENERO
        catalogo.version == '1.3'
    }

    def 'should provide Tupple constructor '(){
        given:
        Catalogo catalogo = new Catalogo('1.6', 'RFC',2018, Mes.ENERO)

        expect:
        catalogo.mes == Mes.ENERO
        catalogo.ejercicio == 2018

    }


}
