package lx.econta


import spock.lang.Specification
import spock.lang.Unroll

import javax.xml.transform.Transformer

/**
 * Spec para validar el comportamiento esperado en la construccion de cadenas derivadas
 * de la transformacion de archivos XML mediante XSLT
 *
 */
class CadenaBuilderSpec extends  Specification{

    CadenaBuilder builder

    def setup() {
        builder = CadenaBuilder.newInstance()
    }

    @Unroll
    def "it should get  an InputStream for #tipo #nombre xslt file"() {
        expect:
        builder.getXsltStream(tipo)  != null

        where:
        nombre      | tipo                        || value
        'Catalogos' | CadenaBuilder.Tipo.CATALOGO || ''
        'Balanza'   | CadenaBuilder.Tipo.BALANZA  || ''
        'Polizas'   | CadenaBuilder.Tipo.POLIZAS  || ''
        'Auxiliar Folios'   | CadenaBuilder.Tipo.AUXILIAR_FOLIOS  || ''
        'Auxiliar Cuentas'   | CadenaBuilder.Tipo.AUXILIAR_CUENTAS  || ''
    }

    @Unroll
    def "it should build a valid XSLT Transformer for #nombre "() {

        when: 'Ask for Transformer de catalogos '
        Transformer transformer = builder.getTransformer(tipo)

        then:
        transformer != null

        where:
        nombre      | tipo                        || value
        'Catalogos' | CadenaBuilder.Tipo.CATALOGO || ''
        'Balanza'   | CadenaBuilder.Tipo.BALANZA  || ''
        'Polizas'   | CadenaBuilder.Tipo.POLIZAS  || ''
        'Auxiliar Folios'   | CadenaBuilder.Tipo.AUXILIAR_FOLIOS  || ''
        'Auxiliar Cuentas'   | CadenaBuilder.Tipo.AUXILIAR_CUENTAS  || ''
    }

    @Unroll
    def "it should transform a #nombre xml file  to a one line String  "() {
        given: ' A catalogo file'
        def dir = new File('src/test/resources')
        File xmlFile = new File(dir, file)


        when: 'Solicitamos la cadena'
        String cadena = builder.getCadena(xmlFile.getBytes(), tipo)
        println "Cadena generada: ${tipo}" + cadena

        then:
        noExceptionThrown()
        cadena != null

        where:
        nombre      | tipo                       | file || value
        'Catalogo' | CadenaBuilder.Tipo.CATALOGO | 'CatalogoDePruebas.xml' || ''
        'Balanza'   | CadenaBuilder.Tipo.BALANZA | 'BalanzaDePruebas.xml'  || ''
        'Polizas'   | CadenaBuilder.Tipo.POLIZAS | 'PolizasDePrueba.xml'  || ''
        'Auxiliar Folios'   | CadenaBuilder.Tipo.AUXILIAR_FOLIOS | 'AuxiliarDeFoliosDePrueba.xml'   || ''
        'Auxiliar Cuentas'   | CadenaBuilder.Tipo.AUXILIAR_CUENTAS | 'AuxiliarDeCtasDePrueba.xml'   || ''

    }
}
