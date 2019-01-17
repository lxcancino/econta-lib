package lx.econta.auxfolios

import groovy.transform.Canonical
import groovy.transform.builder.Builder
import lx.econta.Mes
import lx.econta.polizas.TipoSolicitud

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlAttribute
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement
import javax.xml.bind.annotation.XmlType

@Canonical
@Builder(includes = "rfc, ejercicio, mes, tipoSolicitud")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = ["partidas"])
@XmlRootElement(name = "RepAuxFol")
class AuxiliarDeFolios {

    @XmlAttribute(name = "Version", required = true)
    String version = '1.3'

    @XmlAttribute(name = "RFC", required = true)
    String rfc

    @XmlAttribute(name = "Anio", required = true)
    Integer ejercicio

    @XmlAttribute(name = "Mes", required = true)
    Mes mes

    @XmlAttribute(name = "TipoSolicitud", required = true)
    TipoSolicitud tipoSolicitud

    @XmlAttribute(name = "NumOrden")
    String numOrden

    @XmlAttribute(name = "NumTramite")
    String numTramite

    @XmlAttribute(name = "Sello")
    String sello

    @XmlAttribute(name = "noCertificado")
    String noCertificado

    @XmlAttribute(name = "Certificado")
    String certificado

    @XmlElement(name = "DetAuxFol")
    List<AuxiliarDeFoliosDet> partidas = []


}
