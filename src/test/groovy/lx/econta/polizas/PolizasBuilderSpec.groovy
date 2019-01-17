package lx.econta.polizas

import lx.econta.ContaUtils
import lx.econta.Mes
import lx.econta.SatMoneda
import org.xml.sax.SAXParseException
import spock.lang.Specification

import javax.xml.transform.Source
import javax.xml.transform.stream.StreamSource
import javax.xml.validation.Schema

class PolizasBuilderSpec extends Specification{

    PolizasBuilder builder

    def setup() {
        this.builder = PolizasBuilder.newInstance()
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
            <PLZ:Polizas></PLZ:Polizas>
        """
        def source = new StreamSource(new StringReader(invalidXml))
        def validator = builder.schema.newValidator()

        when: 'Validating'
        validator.validate(source)

        then:
        def e = thrown(SAXParseException)
        println e.message
    }

    def "should generate a valid xml file for Polizas"() {
        given: 'A poliza object'
        def polizas = build()
        def validator = builder.schema.newValidator()

        when: 'We marshal the object to XML'
        def xmlString = builder.buildXml(polizas)
        println "XML:  " + xmlString

        and: 'Lo validamos'
        Reader reader = new StringReader(xmlString)
        Source source = new StreamSource(reader)
        validator.validate(source)

        then:
        notThrown(SAXParseException)
    }

    def "it shoulld build a valid file SAT name"() {
        given: 'An poliza object'
        def polizas = build()

        expect:
        "PAP830101CR3201801PL.xml" == builder.getSatFileName(polizas)

    }

    def "should save a valid xml file"() {
        given: 'A Balanza  xml string'
        def polizas = build()
        def xmlString = builder.buildXml(polizas)

        and: 'A test dir'
        def dir = new File('src/test/resources')
        assert dir.isDirectory()

        when: 'We save to a file'
        PolizasBuilder.saveToFile(dir, polizas, xmlString)

        then: 'We reed a valid XML File'
        File xmlFile = new File(dir, 'PAP830101CR3201801PL.xml')
        xmlFile.text == xmlString


    }


    SatPolizas build() {

        SatPolizas polizas = SatPolizas.builder()
        .ejercicio(2018)
        .mes(Mes.ENERO)
        .rfc('PAP830101CR3')
        .tipoSolicitud(TipoSolicitud.DEVOLUCION)
        .build()
        polizas.polizas = buildPolizas()
        return polizas
    }

    List<SatPoliza> buildPolizas() {
        List<SatPoliza> rows = []
        (1..10).each{
            SatPoliza poliza = SatPoliza.builder()
                    .concepto("Demo $it")
            .numUnIdenPol("DIARIO_$it")
            .build()
            poliza.setFechaDePoliza(new Date())

            // Transacciones *******/
            SatPolizaDet cargo = SatPolizaDet.builder()
                    .cuenta('105-0000-0000')
                    .descripcion('CLIENTES')
                    .concepto('CLIENTES')
                    .debe(10.0)
                    .haber(0.0)
                    .build()
            cargo.comprobanteNacional.add(ComprobanteNacional
                    .builder()
                    .rfc('CARR700318585')
                    .montoTotal(10.00)
                    .uuidcfdi("71A7359A-8CBE-40F0-B566-781CEEAFB7F2")
                    .build()
            )

            poliza.transacciones.add(cargo)

            SatPolizaDet abono = SatPolizaDet.builder()
                    .cuenta('102-0000-0000')
                    .descripcion('CLIENTES')
                    .concepto('BANCOS')
                    .debe(0.0)
                    .haber(10.0)
                    .build()
            if(it % 2 == 0) {
                Cheque cheque = Cheque.builder()
                    .rfc('GPO8712052S1')
                    .fecha(ContaUtils.getXmlGregorianCalendar(new Date()))
                    .benef('BENEFICIARIO')
                    .num("$it")
                    .banEmisNal("002")
                    .ctaOri("1000")
                    .monto(10.0)
                    .build()
                abono.cheques.add(cheque)
            } else {
                // bancoOriNal, ctaDest, bancoDestNal, fecha, benef, monto
                Transferencia transferencia = Transferencia.builder()
                        .rfc('GPO8712052S1')
                        .bancoOriNal('002')
                        .ctaOri("1000")
                        .ctaDest('23330')
                        .bancoDestNal('014')
                        .fecha(ContaUtils.getXmlGregorianCalendar(new Date()))
                        .benef('BENEFICIARIO')
                        .monto(10.00)
                        .build()
                abono.transferencia.add(transferencia)
            }


            poliza.transacciones.add(abono)



            rows.add(poliza)

        }
        /*
        rows << SatPoliza.builder()
        .concepto("PRUEBAS 1")
        .numUnIdenPol("D001")
           */
        return rows
    }
}
