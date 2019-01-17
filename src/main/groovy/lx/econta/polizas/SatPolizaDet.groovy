package lx.econta.polizas

import groovy.transform.builder.Builder

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlAttribute
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlType

@Builder(includes = 'cuenta, descripcion, concepto, debe, haber')
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = [
    "comprobanteNacional",
    "comprobanteNacionalOtro",
    "comprobanteExtranjero",
    "cheques",
    "transferencia",
    "otroMetodoDePago"
])
class SatPolizaDet {

    @XmlAttribute(name = "NumCta", required = true)
    String cuenta

    @XmlAttribute(name = "DesCta", required = true)
    String descripcion

    @XmlAttribute(name = "Concepto", required = true)
    String concepto

    @XmlAttribute(name = "Debe", required = true)
    BigDecimal debe

    @XmlAttribute(name = "Haber", required = true)
    BigDecimal haber

    @XmlElement(name = "CompNal")
    List<ComprobanteNacional> comprobanteNacional = []

    @XmlElement(name = "CompNalOtr")
    List<ComprobanteNacionalOtro> comprobanteNacionalOtro = []

    @XmlElement(name = "CompExt")
    List<ComprobanteExtranjero> comprobanteExtranjero = []

    @XmlElement(name = "Cheque")
    List<Cheque> cheques = []

    @XmlElement(name = "Transferencia")
    List<Transferencia> transferencia = []

    @XmlElement(name = "OtrMetodoPago")
    List<OtroMetodoDePago> otroMetodoDePago = []


}
