package lx.econta

import javax.xml.bind.annotation.XmlEnum
import javax.xml.bind.annotation.XmlEnumValue
import javax.xml.bind.annotation.XmlType

@XmlType
@XmlEnum(String.class)
enum Mes {

    @XmlEnumValue("01") ENERO('01'),
    @XmlEnumValue("02") FEBRERO('02'),
    @XmlEnumValue("03") MARZO('03'),
    @XmlEnumValue("04") ABRIL('04'),
    @XmlEnumValue("05") MAYO('05'),
    @XmlEnumValue("06") JUNIO('06'),
    @XmlEnumValue("07") JULIO('07'),
    @XmlEnumValue("08") AGOSTO('08'),
    @XmlEnumValue("09") SEPTIEMBRE('09'),
    @XmlEnumValue("10") OCTUBRE('10'),
    @XmlEnumValue("11") NOVIEMBRE('11'),
    @XmlEnumValue("12") DICIEMBRE('12'),
    @XmlEnumValue("13") TRECE('13')


    private String value

    Mes(String value) {
        this.value = value
    }

    String getValue() {
        return this.value
    }


    String toString() {
        return "${name()} (${value})"
    }


}