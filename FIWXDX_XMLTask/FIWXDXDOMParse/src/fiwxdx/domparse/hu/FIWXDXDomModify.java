package fiwxdx.domparse.hu;

import java.io.File;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class FIWXDXDomModify {
    public static void main(String argv[]) {
        try {
            File inputFile = new File("FIWXDXDomParse/FIWXDX_XML.xml");

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            Document doc = dBuilder.parse(inputFile);

            doc.getDocumentElement().normalize();

            System.out.println("Gyökér elem: " + doc.getDocumentElement().getNodeName());

            // 1. módosítás: első játékos attribútumának átírása NamedNodeMap segítségével
            Node jatekos1 = doc.getElementsByTagName("jatekos").item(0);
            NamedNodeMap attr = jatekos1.getAttributes();
            Node orszagAttr = attr.getNamedItem("orszag_id");
            orszagAttr.setTextContent("99"); // új ország ID

            // 2. módosítás: második játékos becenevének átírása NodeList segítségével
            Node jatekos2 = doc.getElementsByTagName("jatekos").item(1);
            NodeList childNodes = jatekos2.getChildNodes();
            for (int i = 0; i < childNodes.getLength(); i++) {
                Node node = childNodes.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) node;
                    if ("nev".equals(elem.getNodeName())) {
                        NodeList nevChildren = elem.getChildNodes();
                        for (int j = 0; j < nevChildren.getLength(); j++) {
                            Node nevNode = nevChildren.item(j);
                            if (nevNode.getNodeType() == Node.ELEMENT_NODE) {
                                Element nevElem = (Element) nevNode;
                                if ("becenev".equals(nevElem.getNodeName())) {
                                    nevElem.setTextContent("Lightning");
                                }
                            }
                        }
                    }
                }
            }

            // 3. módosítás: első verseny dátumának átírása
            Node verseny1 = doc.getElementsByTagName("verseny").item(0);
            Element eVerseny1 = (Element) verseny1;
            eVerseny1.getElementsByTagName("datum").item(0).setTextContent("2026-01-01");

            // 4. módosítás: első felszerelés súlyának átírása
            Node felszereles1 = doc.getElementsByTagName("felszereles").item(0);
            Element eFelszereles1 = (Element) felszereles1;
            eFelszereles1.getElementsByTagName("suly").item(0).setTextContent("30");

            // Kiíratás a konzolra
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);

            System.out.println("\n--- Módosított XML fájl ---\n");
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
