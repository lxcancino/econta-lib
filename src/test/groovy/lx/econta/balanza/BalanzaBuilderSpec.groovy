package lx.econta.balanza

import lx.econta.Mes
import org.xml.sax.SAXParseException
import spock.lang.Specification

import javax.xml.transform.Source
import javax.xml.transform.stream.StreamSource
import javax.xml.validation.Schema

class BalanzaBuilderSpec extends Specification{

    BalanzaBuilder builder

    def setup() {
        this.builder = BalanzaBuilder.newInstance()
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
            <BCE:Balanza></BCE:Balanza>
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

        when: 'the tipoDeEnvio is Normal (N)'
        balanza.tipoDeEnvio = 'N'

        then:
        "PAP830101CR3201801BN.xml" == builder.getSatFileName(balanza)

        when: 'the tipoDeEnvio is Normal (C)'
        balanza.tipoDeEnvio = 'C'

        then:
        "PAP830101CR3201801BC.xml" == BalanzaBuilder.getSatFileName(balanza)
    }

    def "should save a valid xml file"() {
        given: 'A Balanza  xml string'
        def balanza = build()
        def xmlString = builder.buildXml(balanza)

        and: 'A test dir'
        def dir = new File('src/test/resources')
        assert dir.isDirectory()

        when: 'We save to a file'
        BalanzaBuilder.saveToFile(dir, balanza, xmlString)

        then: 'We reed a valid XML File'
        File xmlFile = new File(dir, 'PAP830101CR3201801BN.xml')
        xmlFile.text == xmlString


    }


    Balanza build() {
        // ObjectFactory factory = new ObjectFactory()
        Balanza balanza = Balanza.builder()
        .ejercicio(2018)
        .mes(Mes.ENERO)
        .rfc('PAP830101CR3')
        .tipoDeEnvio('N')
        .build()
        // Balanza balanza = new Balanza('PAP830101CR3', 2018, Mes.ENERO)
        balanza.partidas.add(new BalanzaDet("100-0000-0000-0000", 0.0, 10, 10, 0.0))
        balanza.partidas.add(new BalanzaDet("102-0000-0000-0000", 0.0, 10, 10, 0.0))
        balanza.partidas.add(new BalanzaDet("103-0000-0000-0000", 0.0, 10, 10, 0.0))
        balanza.partidas.add(new BalanzaDet("104-0000-0000-0000", 0.0, 10, 10, 0.0))
        return balanza


    }
}
