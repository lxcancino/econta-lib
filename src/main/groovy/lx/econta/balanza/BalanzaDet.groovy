package lx.econta.balanza

import groovy.transform.Canonical

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlAttribute
import javax.xml.bind.annotation.XmlType

@Canonical
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
class BalanzaDet {

    @XmlAttribute(name = "NumCta", required = true)
    String cuenta

    @XmlAttribute(name = "SaldoIni", required = true)
    BigDecimal saldoInicial

    @XmlAttribute(name = "Debe", required = true)
    BigDecimal debe

    @XmlAttribute(name = "Haber", required = true)
    BigDecimal haber

    @XmlAttribute(name = "SaldoFin", required = true)
    BigDecimal saldoFinal
}
