package lx.econta.auxcuentas

import groovy.transform.Canonical
import groovy.transform.builder.Builder
import lx.econta.ContaUtils

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlAttribute
import javax.xml.bind.annotation.XmlSchemaType
import javax.xml.bind.annotation.XmlType
import javax.xml.datatype.XMLGregorianCalendar

@Canonical
@Builder
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
class AuxCuentaDet {

    @XmlAttribute(name = "Fecha", required = true)
    @XmlSchemaType(name = "date")
    XMLGregorianCalendar fecha
    
    @XmlAttribute(name = "NumUnIdenPol", required = true)
    String numUnIdenPol
    
    @XmlAttribute(name = "Concepto", required = true)
    String concepto
    
    @XmlAttribute(name = "Debe", required = true)
    BigDecimal debe
    
    @XmlAttribute(name = "Haber", required = true)
    BigDecimal haber

    void setFechaDeAuxiliar(Date fecha) {
        this.fecha = ContaUtils.getXmlGregorianCalendar(fecha)
    }
    
}
