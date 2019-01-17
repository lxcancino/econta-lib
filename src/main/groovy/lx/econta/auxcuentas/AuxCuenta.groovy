package lx.econta.auxcuentas

import groovy.transform.Canonical
import groovy.transform.builder.Builder


import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlAttribute
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlType

@Canonical
@Builder
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = ["detalles"])
class AuxCuenta {

    @XmlElement(name = "DetalleAux", required = true)
    List<AuxCuentaDet> detalles

    /**
     * Clave de la cuenta contable del contribuyente
     * Attributo NumCta en el XML
     */
    @XmlAttribute(name = "NumCta", required = true)
    String cuenta
    
    @XmlAttribute(name = "DesCta", required = true)
    String descripcion
    
    @XmlAttribute(name = "SaldoIni", required = true)
    BigDecimal saldoIni
    
    @XmlAttribute(name = "SaldoFin", required = true)
    BigDecimal saldoFin
    
}
