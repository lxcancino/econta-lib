package lx.econta.balanza

import groovy.transform.Canonical

import groovy.transform.builder.Builder
import lx.econta.ContaUtils
import lx.econta.Mes

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlAttribute
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement
// import javax.xml.bind.annotation.XmlType

@Canonical
@XmlRootElement(name = "Balanza")
@XmlAccessorType(XmlAccessType.FIELD)
@Builder(includes = 'ejercicio, mes, rfc, tipoDeEnvio')
class Balanza {

    @XmlAttribute(name = "Version", required = true)
    String version = '1.3'

    @XmlAttribute(name = "RFC", required = true)
    String rfc

    @XmlAttribute(name = "Mes", required = true)
    Mes mes

    @XmlAttribute(name = "Anio", required = true)
    Integer ejercicio

    @XmlAttribute(name = "TipoEnvio", required = true)
    String tipoDeEnvio = 'N'

    @XmlAttribute(name = "Sello")
    String sello

    @XmlAttribute(name = "noCertificado")
    String noCertificado

    @XmlAttribute(name = "Certificado")
    String certificado

    @XmlElement(name = "Ctas", required = true)
    List<BalanzaDet> partidas = []

    private Date modificacion

    @XmlAttribute(name = "FechaModBal")
    private String fechaModBal


    void setModificacion(Date fecha) {
        this.fechaModBal = ContaUtils.formatDate(fecha)
    }

    Date getModificacion() {
        return this.modificacion
    }
}
