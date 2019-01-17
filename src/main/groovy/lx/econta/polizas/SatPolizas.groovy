package lx.econta.polizas

import groovy.transform.builder.Builder

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlAttribute
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlEnum
import javax.xml.bind.annotation.XmlEnumValue
import javax.xml.bind.annotation.XmlRootElement
import javax.xml.bind.annotation.XmlType

import lx.econta.Mes

@Builder( includes = "rfc, ejercicio, mes, tipoSolicitud, partidas")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = ['polizas'])
@XmlRootElement(name = "Polizas")
class SatPolizas {

    @XmlAttribute(name = "Version", required = true)
    String version = '1.3'

    @XmlAttribute(name = "RFC", required = true)
    String rfc

    @XmlAttribute(name = "Mes", required = true)
    Mes mes

    @XmlAttribute(name = "Anio", required = true)
    Integer ejercicio

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

    @XmlElement(name = "Poliza", required = true)
    List<SatPoliza> polizas

}

@XmlType
@XmlEnum(String.class)
enum TipoSolicitud {

    @XmlEnumValue("AF") ACTO_DE_FISCALIACIOM,
    @XmlEnumValue("FC") FISCALIZACION_COMPULSA,
    @XmlEnumValue("DE") DEVOLUCION,
    @XmlEnumValue("CO") COMPENSACION
}


