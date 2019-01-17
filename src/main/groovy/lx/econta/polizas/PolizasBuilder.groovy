package lx.econta.polizas


import javax.xml.XMLConstants
import javax.xml.bind.JAXBContext
import javax.xml.bind.Marshaller
import javax.xml.validation.Schema
import javax.xml.validation.SchemaFactory


class PolizasBuilder {

    static String xsiSchemaLocation = "http://sat.gob.mx/esquemas/ContabilidadE/1_3/PolizasPeriodo/PolizasPeriodo_1_3.xsd"

    private JAXBContext context

    Marshaller marshaller

    @Lazy
    Schema schema = {
        File xsd = new File('src/main/resources/schemas/PolizasPeriodo_1_3.xsd')
        def factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
        factory.newSchema(xsd)
    }()

    PolizasBuilder() {
        init()
    }

    private init() {
        context = JAXBContext.newInstance(SatPolizas.class)
        marshaller = context.createMarshaller()
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true)
        marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, xsiSchemaLocation)
    }

    String buildXml(SatPolizas polizas) {
        Writer writer = new StringWriter()
        this.marshaller.marshal(polizas, writer)
        return writer.toString()
    }

    static String getSatFileName(SatPolizas balanza) {
        return "${balanza.rfc}${balanza.ejercicio}${balanza.mes.value}PL.xml"

    }

    static File saveToFile(File dir, SatPolizas polizas, String xmlString){
        String name = getSatFileName(polizas)
        File target = new File(dir, name)
        target.write(xmlString, 'UTF-8')
        return target
    }


}
