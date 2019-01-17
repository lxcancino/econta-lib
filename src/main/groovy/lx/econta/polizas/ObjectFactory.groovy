package lx.econta.polizas


import javax.xml.bind.annotation.XmlRegistry

/**
 * Factory para generar las instancias adecuadas
 */
@XmlRegistry
class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances
     * of schema derived classes for package: lx.econta.balanza
     * 
     */
    ObjectFactory() {
    }

    /**
     * Create an instance of {@link SatPolizas}
     *
     * @return
     */
    SatPolizas createPolizas() {
        return new SatPolizas()
    }

    /**
     * Create an instance of {@link SatPoliza}
     *
     * @return
     */
    SatPoliza createPoliza() {
        return new SatPoliza()
    }



}
