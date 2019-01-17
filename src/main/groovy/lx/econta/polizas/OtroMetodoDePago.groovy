package lx.econta.polizas

import groovy.transform.Canonical
import groovy.transform.builder.Builder
import lx.econta.ContaUtils
import lx.econta.SatMoneda

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlAttribute
import javax.xml.bind.annotation.XmlSchemaType
import javax.xml.bind.annotation.XmlType
import javax.xml.datatype.XMLGregorianCalendar

@Canonical
@Builder(includes = 'metPagoPol, fecha, benef, rfc, monto')
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
class OtroMetodoDePago {

    @XmlAttribute(name = "MetPagoPol", required = true)
    String metPagoPol

    @XmlAttribute(name = "Fecha", required = true)
    @XmlSchemaType(name = "date")
    XMLGregorianCalendar fechaDePago

    @XmlAttribute(name = "Benef", required = true)
    String benef

    @XmlAttribute(name = "RFC", required = true)
    String rfc

    @XmlAttribute(name = "Monto", required = true)
    BigDecimal monto

    @XmlAttribute(name = "Moneda")
    SatMoneda moneda

    @XmlAttribute(name = "TipCamb")
    BigDecimal tipCamb

    private Date fecha

    void setFecha(Date fecha) {
        this.fechaDePago = ContaUtils.formatDate(fecha)
    }

    Date getFecha() {
        return this.fecha
    }

}
