package lx.econta.polizas

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
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = ["transacciones","fecha"])
@Builder(includes = "numUnIdenPol, concepto, fechaDePoliza ")
class SatPoliza {

    @XmlAttribute(name = "NumUnIdenPol", required = true)
    String numUnIdenPol

    // @XmlAttribute(name = "Fecha", required = true)
    // String fecha

    @XmlAttribute(name = "Fecha", required = true)
    @XmlSchemaType(name = "date")
    XMLGregorianCalendar fecha

    @XmlAttribute(name = "Concepto", required = true)
    String concepto

    @XmlElement(name = "Transaccion", required = true)
    List<SatPolizaDet> transacciones = []


    void setFechaDePoliza(Date fecha) {
        this.fecha = ContaUtils.getXmlGregorianCalendar(fecha)
    }




}
