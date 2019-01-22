package lx.econta

import groovy.transform.CompileStatic

import javax.xml.transform.Transformer
import javax.xml.transform.TransformerFactory
import javax.xml.transform.stream.StreamResult
import javax.xml.transform.stream.StreamSource

/**
 * Builder class para la generacion de la cadena de caracteres requerida para firmar digitalmente
 * el archivo XML del catalog de cuentas contables para el SAT
 */
@CompileStatic
class CadenaBuilder {

    InputStream getXsltStream(Tipo tipo) {
        String fileName
        switch (tipo) {
            case Tipo.CATALOGO:
                fileName = 'CatalogoCuentas_1_2.xslt'
                break
            case Tipo.BALANZA:
                fileName = 'BalanzaComprobacion_1_2.xslt'
                break
            case Tipo.POLIZAS:
                fileName = 'PolizasPeriodo_1_2.xslt'
                break
            case Tipo.AUXILIAR_FOLIOS:
                fileName = 'AuxiliarFolios_1_2.xslt'
                break
            case Tipo.AUXILIAR_CUENTAS:
                fileName = 'AuxiliarCtas_1_2.xslt'
                break
            default:
                throw new IllegalArgumentException("No existe archivo definido para el tipo ${tipo}")
        }
        ClassLoader classloader = Thread.currentThread().getContextClassLoader()
        InputStream is = classloader.getResourceAsStream("xslt/${fileName}")
        return is
    }

    Transformer getTransformer(Tipo tipo) {
        TransformerFactory factory=TransformerFactory.newInstance()
        StreamSource source	= new StreamSource(getXsltStream(tipo))
        return factory.newTransformer(source)
    }

    String getCadena(byte[] data, Tipo tipo) {

        StreamSource source = new StreamSource(new ByteArrayInputStream(data))

        ByteArrayOutputStream out = new ByteArrayOutputStream()
        StreamResult target = new StreamResult(out)

        Transformer transformer = getTransformer(tipo)
        transformer.transform(source, target)
        byte[] cadenaOritinal = out.toByteArray()
        String cadena = new String(cadenaOritinal, "UTF-8")
        return cadena
    }

    static enum Tipo {
        CATALOGO, BALANZA, POLIZAS, AUXILIAR_FOLIOS, AUXILIAR_CUENTAS
    }
}
