package lx.econta.catalogo

import groovy.util.slurpersupport.GPathResult
import groovy.xml.XmlUtil

import javax.xml.XMLConstants
import javax.xml.bind.JAXBContext
import javax.xml.bind.Marshaller
import javax.xml.bind.Unmarshaller
import javax.xml.validation.Schema
import javax.xml.validation.SchemaFactory

class CatalogoBuilder {

    static String xsiSchemaLocation = "http://www.sat.gob.mx/esquemas/ContabilidadE/1_3/CatalogoCuentas/CatalogoCuentas_1_3.xsd"

    private JAXBContext context

    Marshaller marshaller

    CatalogoBuilder() {
        init()
    }

    private init() {
        context = JAXBContext.newInstance(Catalogo.class)
        marshaller = context.createMarshaller()
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true)
        marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, xsiSchemaLocation)
    }

    def build(Catalogo catalogo) {
        Writer writer = new StringWriter()
        this.marshaller.marshal(catalogo, writer)
        return writer.toString()
        // return XmlUtil.serialize(writer.toString())
    }

    Unmarshaller getUnmarshaller(boolean validation = true) {
        Unmarshaller unmarshaller = context.createUnmarshaller()
        if(validation)
            unmarshaller.setSchema(getSchema())
        return unmarshaller
    }

    Catalogo unmarshall(InputStream is, boolean  validation = true) {
        (Catalogo)getUnmarshaller(validation).unmarshal(is)
    }

    Schema getSchema() {
        File xsd = new File('src/main/resources/CatalogoCuentas_1_3.xsd')
        def factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
        Schema schema = factory.newSchema(xsd)
        return schema
    }

    String serialize(Catalogo catalogo){
        StringWriter writer = new StringWriter()
        marshaller.marshal(catalogo, writer)
        GPathResult res = new XmlSlurper().parse(new ByteArrayInputStream(writer.toString().getBytes('UTF-8')))
        return XmlUtil.serialize(res)
    }

    static File saveToFile(File dir, Catalogo catalogo, String xmlString){
        String name = getSatFileName(catalogo)
        File target = new File(dir, name)
        target.write(xmlString, 'UTF-8')
        return target
    }

    static String getSatFileName(Catalogo catalogo) {
        return "${catalogo.rfc}${catalogo.ejercicio}${catalogo.mes.value}CT.xml"

    }




}
