package lx.econta.balanza

import javax.xml.XMLConstants
import javax.xml.bind.JAXBContext
import javax.xml.bind.Marshaller
import javax.xml.validation.Schema
import javax.xml.validation.SchemaFactory


class BalanzaBuilder {

    static String xsiSchemaLocation = "http://sat.gob.mx/esquemas/ContabilidadE/1_3/BalanzaComprobacion/BalanzaComprobacion_1_3.xsd"

    private JAXBContext context

    Marshaller marshaller

    @Lazy
    Schema schema = {
        File xsd = new File('src/main/resources/schemas/BalanzaComprobacion_1_3.xsd')
        def factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
        factory.newSchema(xsd)
    }()

    BalanzaBuilder() {
        init()
    }

    private init() {
        context = JAXBContext.newInstance(Balanza.class)
        marshaller = context.createMarshaller()
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true)
        marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, xsiSchemaLocation)
    }

    String buildXml(Balanza balanza) {
        Writer writer = new StringWriter()
        this.marshaller.marshal(balanza, writer)
        return writer.toString()
    }

    static String getSatFileName(Balanza balanza) {
        return "${balanza.rfc}${balanza.ejercicio}${balanza.mes.value}B${balanza.tipoDeEnvio}.xml"

    }

    static File saveToFile(File dir, Balanza balanza, String xmlString){
        String name = getSatFileName(balanza)
        File target = new File(dir, name)
        target.write(xmlString, 'UTF-8')
        return target
    }


}
