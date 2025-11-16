package xpathfiwxdx;

import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class xPathQuery {
    public static void main(String[] args) {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse("studentFIWXDX.xml");
            document.getDocumentElement().normalize();
            XPath xPath = XPathFactory.newInstance().newXPath();
            /*
            1
            String FIWXDX = "students";
            2
            String FIWXDX = "students/student[@id = 2]";
            3
            String FIWXDX = "students/*";
            4
           	String FIWXDX = "students/student[2]";
           	5
            String FIWXDX = "students/student[last()]";
            6
            String FIWXDX = "students/student[last()-1]";
            7.

            */
            String FIWXDX = "students/student";
            NodeList fiwxdx = (NodeList) xPath.compile(FIWXDX).evaluate(document, XPathConstants.NODESET);

            for (int i = 0; i < fiwxdx.getLength(); i++) {
                Node node = fiwxdx.item(i);
                System.out.println("\nAktuális elem: " + node.getNodeName());

                if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("student")) {
                    Element element = (Element) node;
                    System.out.println("Hallgató ID: " + element.getAttribute("id"));
                    System.out.println("keresztnév: " + element.getElementsByTagName("keresztnev").item(0).getTextContent());
                    System.out.println("Vezetéknév: " + element.getElementsByTagName("vezeteknev").item(0).getTextContent());
                    System.out.println("Becenév: " + element.getElementsByTagName("becenev").item(0).getTextContent());
                    System.out.println("Kor: " + element.getElementsByTagName("kor").item(0).getTextContent());
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
    }
}
