package lx.econta.auxcuentas

import lx.econta.ContaUtils
import lx.econta.Mes

import lx.econta.polizas.TipoSolicitud

import org.xml.sax.SAXParseException
import spock.lang.Specification

import javax.xml.transform.Source
import javax.xml.transform.stream.StreamSource
import javax.xml.validation.Schema

class AuxiliarDeCuentasSpec extends Specification{

    AuxiliarDeCuentasBuilder builder

    def setup() {
        this.builder = AuxiliarDeCuentasBuilder.newInstance()
    }

    def "should buld a valid Schema"() {
        when:
        Schema schema = builder.getSchema()

        then: 'A valid schema'
        schema
        schema.newValidator()
    }

    def "should  validate a xml file"() {
        given: ' An invalid Balanza xml string'
        def invalidXml = """
            <AuxiliarCtas:AuxilliarCtas></AuxiliarCtas:AuxilliarCtas>
        """
        def source = new StreamSource(new StringReader(invalidXml))
        def validator = builder.schema.newValidator()

        when: 'Validating'
        validator.validate(source)

        then:
        def e = thrown(SAXParseException)
        println e.message
    }

    def "should generate a valid xml file from a AuxiliarDeCuentas object"() {
        given: 'A balanza object'
        def auxiliar = build()
        def validator = builder.schema.newValidator()

        when: 'We marshal the object to XML'
        def xmlString = builder.buildXml(auxiliar)
        println xmlString

        and: 'Lo validamos'
        Reader reader = new StringReader(xmlString)
        Source source = new StreamSource(reader)
        validator.validate(source)

        then:
        notThrown(SAXParseException)
    }

    def "it shoulld build a valid file SAT name"() {
        given: 'An balanza object'
        def auxiliar = build()

        expect:
        "PAP830101CR3201801XC.xml" == AuxiliarDeCuentasBuilder.getSatFileName(auxiliar)

    }

    def "should save a valid xml file"() {
        given: 'A Balanza  xml string'
        def auxiliar = build()
        def xmlString = builder.buildXml(auxiliar)

        and: 'A test dir'
        def dir = new File('src/test/resources')
        assert dir.isDirectory()

        when: 'We save to a file'
        AuxiliarDeCuentasBuilder.saveToFile(dir, auxiliar, xmlString)

        then: 'We reed a valid XML File'
        File xmlFile = new File(dir, 'PAP830101CR3201801XC.xml')
        xmlFile.text == xmlString


    }


    AuxiliarDeCuentas build() {

        AuxiliarDeCuentas auxiliar = AuxiliarDeCuentas.builder()
        .ejercicio(2018)
        .mes(Mes.ENERO)
        .rfc('PAP830101CR3')
        .tipoSolicitud(TipoSolicitud.DEVOLUCION)
        .cuentas([])
        .build()
        (1..5).each { c ->
            AuxCuenta cuenta = AuxCuenta.builder()
                    .cuenta("1005-0001-0001-${c}")
                    .descripcion('CLIENTES')
                    .saldoIni(0.0)
                    .saldoFin(100.00)
                    .detalles([])
                    .build()
            auxiliar.cuentas.add(cuenta)
            [25,25,25,25].each {
                AuxCuentaDet det = AuxCuentaDet.builder()
                        .numUnIdenPol('VENTAS_1')
                        .fecha(ContaUtils.getXmlGregorianCalendar(new Date()))
                        .debe(it)
                        .concepto("VENTA $it")
                        .haber(0.0)
                        .build()
                cuenta.detalles.add(det)
            }
        }
        return auxiliar

    }
}
