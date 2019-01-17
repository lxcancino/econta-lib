package lx.econta.auxfolios

import groovy.transform.Canonical
import groovy.transform.builder.Builder
import lx.econta.SatMoneda

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlAttribute
import javax.xml.bind.annotation.XmlType

@Canonical
@Builder
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
class ComprExt {

    @XmlAttribute(name = "NumFactExt", required = true)
    String numFactExt

    @XmlAttribute(name = "MontoTotal", required = true)
    BigDecimal montoTotal

    @XmlAttribute(name = "TaxID")
    String taxID

    @XmlAttribute(name = "MetPagoAux")
    String metPagoAux

    @XmlAttribute(name = "Moneda")
    SatMoneda moneda

    @XmlAttribute(name = "TipCamb")
    BigDecimal tipCamb


}
