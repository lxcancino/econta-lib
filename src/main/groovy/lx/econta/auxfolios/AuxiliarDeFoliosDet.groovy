package lx.econta.auxfolios

import groovy.transform.Canonical
import groovy.transform.builder.Builder
import lx.econta.ContaUtils

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlAttribute
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlSchemaType
import javax.xml.bind.annotation.XmlType
import javax.xml.datatype.XMLGregorianCalendar

@Canonical
@Builder
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = [
        "nacionales",
        "otrosNacionales",
        "extranjeros"
])
class AuxiliarDeFoliosDet {

    @XmlAttribute(name = "NumUnIdenPol", required = true)
    String numUnIdenPol

    @XmlAttribute(name = "Fecha", required = true)
    @XmlSchemaType(name = "date")
    XMLGregorianCalendar fecha

    @XmlElement(name = "ComprNal")
    List<ComprNal> nacionales = []
    
    @XmlElement(name = "ComprNalOtr")
    List<ComprNalOtr> otrosNacionales
    
    @XmlElement(name = "ComprExt")
    List<ComprExt> extranjeros


    void setFechaDeAuxiliar(Date fecha) {
        this.fecha = ContaUtils.getXmlGregorianCalendar(fecha)
    }

    
}
