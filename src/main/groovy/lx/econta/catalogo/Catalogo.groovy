package lx.econta.catalogo

import groovy.transform.Canonical
import lx.econta.Mes

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlAttribute
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement


@Canonical
@XmlRootElement( name = "Catalogo")
@XmlAccessorType(XmlAccessType.FIELD)
class Catalogo {

    @XmlAttribute(name = "Version", required = true)
    String version = '1.3'

    @XmlAttribute(name = "RFC", required = true)
    String rfc

    @XmlAttribute(name = "Anio", required = true)
    Integer ejercicio

    @XmlAttribute(name = "Mes", required = true)
    Mes mes

    @XmlAttribute(name = "noCertificado", required = false)
    String numeroDeCertificado

    @XmlAttribute(name = "Certificado", required = false)
    String certificado

    @XmlAttribute(name = "Sello", required = false)
    String sello

    @XmlElement( name = "Ctas" )
    List<Cuenta> cuentas

}


