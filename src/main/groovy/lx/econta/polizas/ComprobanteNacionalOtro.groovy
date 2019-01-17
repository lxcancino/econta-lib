package lx.econta.polizas

import groovy.transform.builder.Builder
import lx.econta.SatMoneda

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlAttribute
import javax.xml.bind.annotation.XmlType

@Builder( includes = "cfdcbbNumFol, rfc, montoTotal")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
class ComprobanteNacionalOtro {

    @XmlAttribute(name = "CFD_CBB_NumFol", required = true)
    BigInteger cfdcbbNumFol

    @XmlAttribute(name = "RFC", required = true)
    String rfc

    @XmlAttribute(name = "MontoTotal", required = true)
    BigDecimal montoTotal

    @XmlAttribute(name = "CFD_CBB_Serie")
    String cfdcbbSerie

    @XmlAttribute(name = "Moneda")
    SatMoneda moneda

    @XmlAttribute(name = "TipCamb")
    BigDecimal tipCamb
}
