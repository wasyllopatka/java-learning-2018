package storage.xml;

import model.Genre;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import storage.AbstractStorage;
import storage.Constants;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XmlGenreStorage implements AbstractStorage<Genre> {

    @Override
    public List<Genre> findAll() {
        List<Genre> genres = new ArrayList<>();
        Genre genre;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(Constants.XML_GENRE_PATH));
            document.getDocumentElement().normalize();
            NodeList nodes = document.getElementsByTagName("genre");
            for (int i = 0; i < nodes.getLength(); i++) {
                Node node = nodes.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    genre = new Genre();
                    genre.setId(Integer.parseInt(element.getAttribute("id")));
                    genre.setName(element.getElementsByTagName("name").item(0).getTextContent());
                    genres.add(genre);
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return genres;

    }

    @Override
    public Genre findOnId(Integer id) {
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        Genre genre = new Genre();
        try {
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document xml = documentBuilder.parse(Constants.XML_GENRE_PATH);
            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xPath = xPathFactory.newXPath();
            XPathExpression expr = xPath.compile("/genres/genre[@id='" + id + "']/name/text()");
            String name = (String) expr.evaluate(xml, XPathConstants.STRING);

            genre.setName(name);
            genre.setId(id);
        } catch (ParserConfigurationException | IOException | XPathExpressionException | SAXException e) {
            e.printStackTrace();
        }
        return genre;
    }

    @Override
    public Genre add(Genre entity) {
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document xml = documentBuilder.parse(Constants.XML_GENRE_PATH);
            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xPath = xPathFactory.newXPath();
            int maxId = ((Number) xPath.evaluate("count(/genres/genre)", xml, XPathConstants.NUMBER)).intValue();

            Element root = xml.getDocumentElement();
            if (xml.getDocumentElement() != null) {
                Element genre = xml.createElement("genre");
                root.appendChild(genre);

                Attr attr = xml.createAttribute("id");
                attr.setValue(Integer.toString(maxId+1));
                genre.setAttributeNode(attr);

                Element name = xml.createElement("name");
                name.appendChild(xml.createTextNode(entity.getName()));
                genre.appendChild(name);
                xml.getDocumentElement().normalize();

                Transformer transformer = TransformerFactory.newInstance().newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.transform(new DOMSource(xml), new StreamResult(new File(Constants.XML_GENRE_PATH)));
            }
        } catch (ParserConfigurationException | SAXException | IOException | TransformerException | XPathExpressionException e) {
            e.printStackTrace();
        }

        return entity;
    }

    @Override
    public void update(Genre entity) {}

    @Override
    public void delete(int id) {}
}
