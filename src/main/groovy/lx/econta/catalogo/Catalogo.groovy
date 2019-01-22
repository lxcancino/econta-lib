package lx.econta.catalogo

import groovy.transform.Canonical
import groovy.transform.builder.Builder
import lx.econta.Mes

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlAttribute
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement


/**
 * Clase modelo que representa el catálog de cuentas contables
 *  <p> Se basa en el  schema establecido por el SAT en:
 *  <a href="http://www.sat.gob.mx/esquemas/ContabilidadE/1_3/CatalogoCuentas/CatalogoCuentas_1_3.xsd">
 *      <i>CatalogoCuentas_1_3.xsd</i></a>.
 *  En dicho documento  se establecen todas las reglas de estructura que se deben seguir para transformar
 *  una instancia de esta clase en un archivo XML valido
 *
 * Implementa diversas AST Transformations para simplificar su uso  por ejemplo:
 * <pre>
 * new Catalogo(version: '1.3', ejercicio: 2019, mes: Mes.Enero)
 * </pre>
 * or the with statement:
 * <pre>
 * new Catalogo().with {
 *     ejercicio = 2019
 *     mes = Mes.ENERO
 * }
 * </pre>
 * También se puede usar <a href="https://en.wikipedia.org/wiki/Fluent_interfaced">
 *     <em>fluent api calls</em></a>.
 * <pre>
 *     Catalogo.builder()
 *     .ejercicio(2018)
 *     .mes(Mes.Enero)
 *     .build()
 * </pre>
 *
 * @author Ruben Cancino
 */
@Canonical
@Builder
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


