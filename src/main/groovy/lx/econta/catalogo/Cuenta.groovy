package lx.econta.catalogo

import groovy.transform.Canonical

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlAttribute
import javax.xml.bind.annotation.XmlEnum
import javax.xml.bind.annotation.XmlEnumValue
import javax.xml.bind.annotation.XmlType

@Canonical
@XmlAccessorType(XmlAccessType.FIELD)
class Cuenta {

    /**
     * Corresponde al atributo CodAgrup del SAT
     */
    @XmlAttribute(name = "CodAgrup", required = true)
    String condigo

    /**
     * Corresponde al atributo NumCta
     */
    @XmlAttribute(name = "NumCta", required = true)
    String clave

    /**
     * Corresponde al atributo Desc
     */
    @XmlAttribute(name = "Desc", required = true)
    String descripcion

    /**
     * Corresponde al atributo SubCtaDe
     */
    @XmlAttribute(name = "SubCtaDe", required = false)
    String subcuentaDe

    @XmlAttribute(name = "Nivel", required = true)
    int nivel

    /**
     * Corresponde al atributo Natur
     */
    @XmlAttribute(name = "Natur", required = true)
    Naturaleza naturaleza
}

@XmlType
@XmlEnum(String.class)
enum Naturaleza {

    @XmlEnumValue("D") DEUDORA('D'),
    @XmlEnumValue("A") ACREDORA('A')

    private value

    Naturaleza(String value){
        this.value = value
    }

    String getValue() {
        return value
    }

    String toString() {
        return value
    }
}
