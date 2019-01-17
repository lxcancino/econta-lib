package lx.econta.catalogo

import javax.xml.bind.annotation.XmlRegistry

@XmlRegistry
class ObjectFactory {

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: lx.cfdi.v33
     *
     */
    ObjectFactory() {
    }

    /**
     * Create an instance of {@link Catalogo }
     *
     */
    Catalogo createComprobante() {
        return new Catalogo()
    }

}
