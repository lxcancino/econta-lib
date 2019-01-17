package lx.econta.polizas

import groovy.transform.Canonical
import groovy.transform.builder.Builder
import lx.econta.SatMoneda

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlAttribute
import javax.xml.bind.annotation.XmlType

@Builder(includes = 'numFactExt, montoTotal')
@Canonical
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
class ComprobanteExtranjero {

    @XmlAttribute(name = "NumFactExt", required = true)
    String numFactExt

    @XmlAttribute(name = "TaxID")
    String taxID

    @XmlAttribute(name = "MontoTotal", required = true)
    BigDecimal montoTotal

    @XmlAttribute(name = "Moneda")
    SatMoneda moneda

    @XmlAttribute(name = "TipCamb")
    BigDecimal tipCamb

}
