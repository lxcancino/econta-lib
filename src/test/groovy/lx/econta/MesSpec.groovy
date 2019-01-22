package lx.econta

import spock.lang.Specification
import spock.lang.Unroll

class MesSpec extends Specification{

    def "toString method"() {
        setup:
        def expected = "ENERO (01)"

        when:
        def result = Mes.ENERO.toString()

        then:
        result == expected
    }

    @Unroll
    def "el valor de #mes debe ser #value"() {
        expect:
        mes.value == value

        where:
        mes || value
        Mes.ENERO || '01'
        Mes.FEBRERO || '02'
        Mes.MARZO || '03'
        Mes.ABRIL || '04'
        Mes.MAYO || '05'
        Mes.JUNIO || '06'
        Mes.JULIO || '07'
        Mes.AGOSTO || '08'
        Mes.SEPTIEMBRE || '09'
        Mes.OCTUBRE || '10'
        Mes.NOVIEMBRE || '11'
        Mes.DICIEMBRE || '12'
        Mes.TRECE || '13'

    }

    @Unroll
    def "el valor entero de #mes debe ser #value"() {
        expect:
        Mes.valueOf(mes) == value

        where:
        mes || value
        1 || Mes.ENERO
        2 || Mes.FEBRERO
        3 || Mes.MARZO
        4 || Mes.ABRIL
        5 || Mes.MAYO
        6 || Mes.JUNIO
        7 || Mes.JULIO
        8 || Mes.AGOSTO
        9 || Mes.SEPTIEMBRE
        10 || Mes.OCTUBRE
        11 || Mes.NOVIEMBRE
        12 || Mes.DICIEMBRE
        13 || Mes.TRECE



    }
}
