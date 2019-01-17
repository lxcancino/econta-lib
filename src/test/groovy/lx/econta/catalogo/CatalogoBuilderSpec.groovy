package lx.econta.catalogo

import lx.econta.Mes
import lx.econta.catalogo.Catalogo
import lx.econta.catalogo.CatalogoBuilder
import lx.econta.catalogo.Cuenta
import lx.econta.catalogo.Naturaleza
import spock.lang.Specification

import javax.xml.XMLConstants
import javax.xml.transform.Source
import javax.xml.transform.stream.StreamSource
import javax.xml.validation.SchemaFactory
import javax.xml.validation.Validator

class CatalogoBuilderSpec extends Specification{

    def "should marshall a valid xml"() {

        given: 'An existing Catalogo'
        def catalogo = buildCatalogo()
        and: 'A builder'
        CatalogoBuilder builder = new CatalogoBuilder()

        when: 'We marshal the object to XML'
        def xmlString = builder.build(catalogo)
        println xmlString
        and: 'Lo validamos'
        Reader reader = new StringReader(xmlString)
        Source source = new StreamSource(reader)
        validator.validate(source)

        then: noExceptionThrown()

    }

    def "it shoulld build a valid file SAT name"() {
        given: 'An existing Catalogo'
        def catalogo = buildCatalogo()

        expect:
        "PAP830101CR3201801CT.xml" == CatalogoBuilder.getSatFileName(catalogo)

    }

    def "should save a valid xml file"() {
        given: 'A valid Catlogo xml string'
        def catalogo = buildCatalogo()
        CatalogoBuilder builder = new CatalogoBuilder()
        def xmlString = builder.build(catalogo)

        and: 'A test dir'
        def dir = new File('src/test/resources')
        assert dir.isDirectory()

        when: 'We save to a file'
        CatalogoBuilder.saveToFile(dir, catalogo, xmlString)

        then: 'We reed a valid XML File'
        File xmlFile = new File(dir, CatalogoBuilder.getSatFileName(catalogo))
        xmlFile.text == xmlString


    }

    Validator getValidator() {
        File xsd = new File('src/main/resources/CatalogoCuentas_1_3.xsd')
        def factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
        def schema = factory.newSchema(xsd)
        def validator = schema.newValidator()
        return validator
    }

    Catalogo buildCatalogo() {
        Catalogo catalogo = new Catalogo('1.3', 'PAP830101CR3', 2018, Mes.ENERO)
        catalogo.cuentas = buildCuentasDePrueba()
        return catalogo
    }

    List<Cuenta> buildCuentasDePrueba() {
        List<Cuenta> ctas = []
        ctas.add(new Cuenta('100','100-0000-0000-0000', 'CAJA', null, 1, Naturaleza.ACREDORA))
        ctas.add(new Cuenta('105','105-0000-0000-0000', 'CLIENTES', null, 1, Naturaleza.DEUDORA))
        ctas.add(new Cuenta('102','400-0000-0000-0000', 'BANCOS', null, 1, Naturaleza.ACREDORA))
        ctas.add(new Cuenta('502','102-0000-0000-0000', 'COMPRAS', null, 1, Naturaleza.DEUDORA))

        return ctas
    }
}
