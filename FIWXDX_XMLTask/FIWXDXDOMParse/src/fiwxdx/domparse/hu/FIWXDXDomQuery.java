package fiwxdx.domparse.hu;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class FIWXDXDomQuery {
    public static void main(String argv[]) {
        try {
            File inputFile = new File("FIWXDXDomParse/FIWXDX_XML.xml");

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            Document doc = dBuilder.parse(inputFile);

            doc.getDocumentElement().normalize();

            System.out.print("Gyökér elem: ");
            System.out.println(doc.getDocumentElement().getNodeName());

            // 1. lekérdezés: Minden ország kiíratása
            NodeList orszagList = doc.getElementsByTagName("orszag");

            System.out.println("\n--- Országok ---");
            for (int i = 0; i < orszagList.getLength(); i++) {
                Node nNode = orszagList.item(i); // először Node
                if (nNode.getNodeType() == Node.ELEMENT_NODE) { // ellenőrzés
                    Element e = (Element) nNode; // csak utána cast
                    System.out.println("ID: " + e.getAttribute("orszag_id") +
                            ", Név: " + e.getElementsByTagName("nev").item(0).getTextContent() +
                            ", Kód: " + e.getElementsByTagName("kod").item(0).getTextContent());
                }
            }

            // 2. lekérdezés: Magyar játékosok listázása
            NodeList jatekosList = doc.getElementsByTagName("jatekos");

            System.out.println("\n--- Magyar játékosok ---");
            for (int i = 0; i < jatekosList.getLength(); i++) {
                Node nNode = jatekosList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element e = (Element) nNode;
                    String nemzetiseg = e.getElementsByTagName("nemzetiseg").item(0).getTextContent();
                    if (nemzetiseg.equalsIgnoreCase("Magyar")) {
                        String nev = e.getElementsByTagName("vezeteknev").item(0).getTextContent() + " " +
                                e.getElementsByTagName("keresztnev").item(0).getTextContent();
                        System.out.println("Név: " + nev + ", Nemzetiség: " + nemzetiseg);
                    }
                }
            }

            // 3. lekérdezés: Versenyek dátumai
            NodeList versenyList = doc.getElementsByTagName("verseny");

            System.out.println("\n--- Versenyek dátumai ---");
            for (int i = 0; i < versenyList.getLength(); i++) {
                Node nNode = versenyList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element e = (Element) nNode;
                    String nev = e.getElementsByTagName("nev").item(0).getTextContent();
                    String datum = e.getElementsByTagName("datum").item(0).getTextContent();
                    System.out.println(nev + " -> " + datum);
                }
            }

            // 4. lekérdezés: Mérkőzések legjobb leg értékei
            NodeList merkozesList = doc.getElementsByTagName("merkozes");

            System.out.println("\n--- Mérkőzések legjobb leg ---");
            for (int i = 0; i < merkozesList.getLength(); i++) {
                Node nNode = merkozesList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element e = (Element) nNode;
                    String id = e.getAttribute("merkozes_id");
                    String leg = e.getElementsByTagName("legjobb_leg").item(0).getTextContent();
                    System.out.println("Mérkőzés ID: " + id + ", Legjobb leg: " + leg);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
