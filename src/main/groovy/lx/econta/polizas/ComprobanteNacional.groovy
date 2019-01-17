package lx.econta.polizas

import groovy.transform.Canonical
import groovy.transform.builder.Builder
import lx.econta.SatMoneda

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlAttribute
import javax.xml.bind.annotation.XmlType

@Builder(includes = 'uuidcfdi, rfc, montoTotal')
@Canonical
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
class ComprobanteNacional {

    @XmlAttribute(name = "UUID_CFDI", required = true)
    String uuidcfdi

    @XmlAttribute(name = "RFC", required = true)
    String rfc

    @XmlAttribute(name = "MontoTotal", required = true)
    BigDecimal montoTotal

    @XmlAttribute(name = "Moneda")
    SatMoneda moneda

    @XmlAttribute(name = "TipCamb")
    BigDecimal tipCamb


}
