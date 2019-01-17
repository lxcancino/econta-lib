package lx.econta.polizas

import groovy.transform.Canonical
import groovy.transform.builder.Builder
import lx.econta.SatMoneda
import lx.econta.ContaUtils

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlAttribute
import javax.xml.bind.annotation.XmlSchemaType
import javax.xml.bind.annotation.XmlType
import javax.xml.datatype.XMLGregorianCalendar


@Builder( includes = 'rfc, ctaOri, bancoOriNal, ctaDest, bancoDestNal, fecha, benef, monto')
@Canonical
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
class Transferencia {

    @XmlAttribute(name = "CtaOri")
    String ctaOri

    @XmlAttribute(name = "BancoOriNal", required = true)
    String bancoOriNal

    @XmlAttribute(name = "BancoOriExt")
    String bancoOriExt

    @XmlAttribute(name = "CtaDest", required = true)
    String ctaDest

    @XmlAttribute(name = "BancoDestNal", required = true)
    String bancoDestNal

    @XmlAttribute(name = "BancoDestExt")
    String bancoDestExt

    @XmlAttribute(name = "Benef", required = true)
    String benef

    @XmlAttribute(name = "RFC", required = true)
    String rfc

    @XmlAttribute(name = "Fecha", required = true)
    @XmlSchemaType(name = "date")
    XMLGregorianCalendar fecha

    @XmlAttribute(name = "Monto", required = true)
    BigDecimal monto

    @XmlAttribute(name = "Moneda")
    SatMoneda moneda

    @XmlAttribute(name = "TipCamb")
    BigDecimal tipCamb

    void setFechaTransferencia(Date fecha) {
        this.fecha = ContaUtils.getXmlGregorianCalendar(fecha)
    }

}
