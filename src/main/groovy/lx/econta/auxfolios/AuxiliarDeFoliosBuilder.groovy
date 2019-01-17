package lx.econta.auxfolios


import javax.xml.XMLConstants
import javax.xml.bind.JAXBContext
import javax.xml.bind.Marshaller
import javax.xml.validation.Schema
import javax.xml.validation.SchemaFactory


class AuxiliarDeFoliosBuilder {

    static String xsiSchemaLocation = "http://sat.gob.mx/esquemas/ContabilidadE/1_3/AuxiliarFolios/AuxiliarFolios_1_3.xsd"

    private JAXBContext context

    Marshaller marshaller

    @Lazy
    Schema schema = {
        File xsd = new File('src/main/resources/schemas/AuxiliarFolios_1_3.xsd')
        def factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
        factory.newSchema(xsd)
    }()

    AuxiliarDeFoliosBuilder() {
        init()
    }

    private init() {
        context = JAXBContext.newInstance(AuxiliarDeFolios.class)
        marshaller = context.createMarshaller()
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true)
        marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, xsiSchemaLocation)
    }

    String buildXml(AuxiliarDeFolios auxiliar) {
        Writer writer = new StringWriter()
        this.marshaller.marshal(auxiliar, writer)
        return writer.toString()
    }

    static String getSatFileName(AuxiliarDeFolios auxiliar) {
        return "${auxiliar.rfc}${auxiliar.ejercicio}${auxiliar.mes.value}XF.xml"

    }

    static File saveToFile(File dir, AuxiliarDeFolios auxiliar, String xmlString){
        String name = getSatFileName(auxiliar)
        File target = new File(dir, name)
        target.write(xmlString, 'UTF-8')
        return target
    }


}
