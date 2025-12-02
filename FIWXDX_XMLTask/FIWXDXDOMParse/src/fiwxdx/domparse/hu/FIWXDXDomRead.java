package fiwxdx.domparse.hu;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class FIWXDXDomRead {
    public static void main(String argv[]) throws SAXException, IOException, ParserConfigurationException
    {
        File xmlFile = new File("FIWXDXDomParse/FIWXDX_XML.xml");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        DocumentBuilder dBuilder = factory.newDocumentBuilder();

        Document fiwxdx = dBuilder.parse(xmlFile);

        fiwxdx.getDocumentElement().normalize();

        System.out.println("\nGyökér elem: " + fiwxdx.getDocumentElement().getNodeName());

        System.out.println("============================================================");

        // Országok kiíratása
        NodeList orszagok = fiwxdx.getElementsByTagName("orszag");

        System.out.println("\nOrszágok listája:");
        for(int i = 0; i < orszagok.getLength(); ++i)
        {
            Node node = orszagok.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                String id =  element.getAttribute("orszag_id");
                String nev = element.getElementsByTagName("nev").item(0).getTextContent();
                String kod =  element.getElementsByTagName("kod").item(0).getTextContent();

                System.out.println("\nID: " + id +  "\nNev: " + nev + "\nKod: " + kod);
            }
        }

        System.out.println("============================================================");

        // Felszerelések kiíratása
        NodeList felszerelesek = fiwxdx.getElementsByTagName("felszereles");

        System.out.println("\nFelszerelések listája:");
        for (int i = 0; i < felszerelesek.getLength(); ++i)
        {
            Node node = felszerelesek.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                String id =  element.getAttribute("felszereles_id");
                String marka =  element.getElementsByTagName("marka").item(0).getTextContent();
                String modell =   element.getElementsByTagName("modell").item(0).getTextContent();
                String suly = element.getElementsByTagName("suly").item(0).getTextContent();

                System.out.println("\nID: " + id + "\nMarka: " + marka + "\nModell: " + modell + "\nSúly: " + suly + "g");
            }
        }

        System.out.println("============================================================");

        // Játékosok kiíratása
        NodeList jatekosok = fiwxdx.getElementsByTagName("jatekos");

        System.out.println("\nJátékosok listája:");
        for (int i = 0; i < jatekosok.getLength(); ++i)
        {
            Node node = jatekosok.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                String id =  element.getAttribute("jatekos_id");
                String orszagId = element.getAttribute("orszag_id");
                String felszerelesId = element.getAttribute("felszereles_id");

                String vezeteknev =  element.getElementsByTagName("vezeteknev").item(0).getTextContent();
                String keresztnev =  element.getElementsByTagName("keresztnev").item(0).getTextContent();
                String becenev =   element.getElementsByTagName("becenev").item(0).getTextContent();
                String nemzetiseg =   element.getElementsByTagName("nemzetiseg").item(0).getTextContent();
                String szuletesiDatum = element.getElementsByTagName("szuletesi_datum").item(0).getTextContent();
                String aktualisHelyezes = element.getElementsByTagName("aktualis_helyezes").item(0).getTextContent();

                System.out.println("\nID: " + id + "\nOrszágID: " + orszagId + "\nFelszerelesID: " + felszerelesId + "\nNév: " + keresztnev + " " + vezeteknev +
                        "\nBecenév: " + becenev + "\nNemzetiség: " + nemzetiseg + "\nSzületési dátum: " + szuletesiDatum + "\nAktuális helyezés: " +  aktualisHelyezes);
            }
        }

        System.out.println("============================================================");

        // Versenyek kiíratása
        NodeList versenyek =  fiwxdx.getElementsByTagName("verseny");

        System.out.println("\nVersenyek listája:");
        for (int i = 0; i < versenyek.getLength(); ++i)
        {
            Node node = versenyek.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                String id =  element.getAttribute("verseny_id");
                String nev = element.getElementsByTagName("nev").item(0).getTextContent();
                String helyszin =  element.getElementsByTagName("helyszin").item(0).getTextContent();
                String datum =   element.getElementsByTagName("datum").item(0).getTextContent();
                String email =    element.getElementsByTagName("email").item(0).getTextContent();

                System.out.println("\nID: " + id + "\nNév: " + nev + "\nHelyszín: " + helyszin + "\nDátum: " + datum + "\nEmail: " + email);
            }
        }

        System.out.println("============================================================");

        //Mérkőzések kiíratása
        NodeList merkozesek =   fiwxdx.getElementsByTagName("merkozes");

        System.out.println("\nMérkőzések listája:");
        for (int i = 0; i < merkozesek.getLength(); ++i)
        {
            Node node = merkozesek.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                String id =  element.getAttribute("merkozes_id");
                String datum =   element.getElementsByTagName("datum").item(0).getTextContent();
                String legjobbLeg =    element.getElementsByTagName("legjobb_leg").item(0).getTextContent();
                String jatekosA =  element.getElementsByTagName("jatekosA").item(0).getTextContent();
                String jatekosB =  element.getElementsByTagName("jatekosB").item(0).getTextContent();

                System.out.println("\nID: " + id + "\nDátum: " + datum + "\nLegjobb leg: " + legjobbLeg + "\nJátékos (A): " + jatekosA + "\nJátékos (B): " + jatekosB);
            }
        }

        System.out.println("============================================================");

        // Kapcsolat: Tartalmak kiíratása
        NodeList tartalmak =    fiwxdx.getElementsByTagName("tartalmaz");

        System.out.println("\nTartalmak listája:");
        for (int i = 0; i < tartalmak.getLength(); ++i)
        {
            Node node = tartalmak.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                String versenyId = element.getAttribute("verseny_id");
                String merkozesId = element.getAttribute("merkozes_id");

                System.out.println("\nVerseny ID: " + versenyId + "\nMérkőzés ID: "  + merkozesId);
            }
        }

        System.out.println("============================================================");

        // Kapcsolat: Résztvettek kiíratása
        NodeList resztvettek =    fiwxdx.getElementsByTagName("resztvesz");

        System.out.println("\nRészvettek listája:");
        for (int i = 0; i < resztvettek.getLength(); ++i)
        {
            Node node = resztvettek.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                String versenyId = element.getAttribute("verseny_id");
                String jatekosId = element.getAttribute("jatekos_id");

                System.out.println("\nVerseny ID: " + versenyId + "\nJátékos ID: " +  jatekosId);
            }
        }

        System.out.println("============================================================");

        // Kapcsolat: Játszottak kiíratása
        NodeList jatszottak =     fiwxdx.getElementsByTagName("jatszott");

        System.out.println("\nJátszottak listája:");
        for (int i = 0; i < jatszottak.getLength(); ++i)
        {
            Node node = jatszottak.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                String merkozesId = element.getAttribute("merkozes_id");
                String jatekosId = element.getAttribute("jatekos_id");

                System.out.println("\nMérkőzés ID: " + merkozesId + "\nJátékos ID: " +   jatekosId);
            }
        }
    }
}
