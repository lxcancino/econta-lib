package lx.econta.polizas

import groovy.transform.Canonical
import groovy.transform.TupleConstructor
import groovy.transform.builder.Builder
import lx.econta.SatMoneda
import lx.econta.ContaUtils

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlAttribute
import javax.xml.bind.annotation.XmlSchemaType
import javax.xml.bind.annotation.XmlType
import javax.xml.datatype.XMLGregorianCalendar


@Canonical
@TupleConstructor(includes = "rfc, num, banEmisNal, ctaOri, benef, monto")
@Builder( includes = 'num, banEmisNal, ctaOri, benef, rfc, monto, fecha')
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
class Cheque {

    @XmlAttribute(name = "RFC", required = true)
    String rfc

    @XmlAttribute(name = "Num", required = true)
    String num

    @XmlAttribute(name = "BanEmisNal", required = true)
    String banEmisNal

    @XmlAttribute(name = "CtaOri", required = true)
    String ctaOri

    @XmlAttribute(name = "Fecha", required = true)
    @XmlSchemaType(name = "date")
    XMLGregorianCalendar fecha

    @XmlAttribute(name = "Benef", required = true)
    String benef

    @XmlAttribute(name = "Monto", required = true)
    BigDecimal monto

    @XmlAttribute(name = "BanEmisExt")
    String banEmisExt

    @XmlAttribute(name = "Moneda")
    SatMoneda moneda

    @XmlAttribute(name = "TipCamb")
    BigDecimal tipCamb



    void setFechaDelCheque(Date fecha) {
        this.fecha = ContaUtils.getXmlGregorianCalendar(fecha)
    }

    Date getFecha() {
        return this.fecha
    }


}
