package lx.econta.balanza



import javax.xml.bind.annotation.XmlRegistry;


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
     * Create an instance of {@link Balanza}
     *
     * @return
     */
    Balanza createBalanza() {
        return new Balanza()
    }

    /**
     * Create an instance of {@link BalanzaDet}
     *
     * @return
     */
    BalanzaDet createBalanzaDet() {
        return new BalanzaDet()
    }



}
