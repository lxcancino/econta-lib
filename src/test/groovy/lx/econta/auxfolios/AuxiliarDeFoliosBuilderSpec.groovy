package lx.econta.auxfolios

import lx.econta.ContaUtils
import lx.econta.Mes
import lx.econta.polizas.TipoSolicitud
import org.xml.sax.SAXParseException
import spock.lang.Specification

import javax.xml.transform.Source
import javax.xml.transform.stream.StreamSource
import javax.xml.validation.Schema

class AuxiliarDeFoliosSpec extends Specification{

    AuxiliarDeFoliosBuilder builder

    def setup() {
        this.builder = AuxiliarDeFoliosBuilder.newInstance()
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
            <RepAux:RepAuxFol></RepAux:RepAuxFol>
        """
        def source = new StreamSource(new StringReader(invalidXml))
        def validator = builder.schema.newValidator()

        when: 'Validating'
        validator.validate(source)

        then:
        def e = thrown(SAXParseException)
        println e.message
    }

    def "should generate a valid xml file from a Balanza"() {
        given: 'A balanza object'
        def balanza = build()
        def validator = builder.schema.newValidator()

        when: 'We marshal the object to XML'
        def xmlString = builder.buildXml(balanza)
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
        def balanza = build()

        expect:
        "PAP830101CR3201801XF.xml" == AuxiliarDeFoliosBuilder.getSatFileName(balanza)

    }

    def "should save a valid xml file"() {
        given: 'A Balanza  xml string'
        def auxiliar = build()
        def xmlString = builder.buildXml(auxiliar)

        and: 'A test dir'
        def dir = new File('src/test/resources')
        assert dir.isDirectory()

        when: 'We save to a file'
        AuxiliarDeFoliosBuilder.saveToFile(dir, auxiliar, xmlString)

        then: 'We reed a valid XML File'
        File xmlFile = new File(dir, 'PAP830101CR3201801XF.xml')
        xmlFile.text == xmlString


    }


    AuxiliarDeFolios build() {

        AuxiliarDeFolios auxiliar = AuxiliarDeFolios.builder()
        .ejercicio(2018)
        .mes(Mes.ENERO)
        .rfc('PAP830101CR3')
        .tipoSolicitud(TipoSolicitud.DEVOLUCION)
        .build()

        AuxiliarDeFoliosDet det = AuxiliarDeFoliosDet.builder()
        .fecha(ContaUtils.getXmlGregorianCalendar(new Date()))
        .numUnIdenPol('VENTAS_1')
        .nacionales([])
        .build()

        auxiliar.partidas.add(det)

        def uuids = ['00018D1B-626C-4DBD-BB12-903D95BD364B',
        '0003C09D-5F87-40F5-B523-25DB8FEF790E',
        '00043FCC-E73F-4810-83C5-C711C48038F6',
        '0006AA2B-BF96-4D3B-98C2-785A2D282C6B',
        '00089967-8441-4063-9F55-698B91B41564',
        '000A40D5-5B88-4042-A8EA-72D120ECF399',
        '000FC1FE-E5DD-47E9-B6ED-D3E7FC167066',
        '0010F248-598C-430C-ACBB-2CF6A39EDF7F',
        '00129928-8336-4DDB-B689-BB2B70838FC1',
        '0012F680-05A0-4593-BF56-ED177EAA5585']
        int row = 1
        uuids.each {
            ComprNal c = ComprNal.builder()
            .rfc('AAHA560203N38')
            .uuidcfdi(it)
            .montoTotal(100.00)

            .build()
            det.nacionales.add(c)
            row++
        }

        return auxiliar


    }
}
