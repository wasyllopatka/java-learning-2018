package storage.xml;

import model.Album;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import storage.AbstractStorage;
import storage.Constants;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XmlAlbumStorage implements AbstractStorage<Album> {

    @Override
    public List<Album> findAll() {
        List<Album> albums = new ArrayList<>();
        Album album;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(Constants.XML_ALBUM_PATH));
            document.getDocumentElement().normalize();
            NodeList nodes = document.getElementsByTagName("album");
            for (int i = 0; i < nodes.getLength(); i++) {
                Node node = nodes.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    album = new Album();
                    album.setId(Integer.parseInt(element.getAttribute("id")));
                    album.setName(element.getElementsByTagName("name").item(0).getTextContent());
                    album.setArtist(element.getElementsByTagName("artist").item(0).getTextContent());
                    album.setYear(Integer.parseInt(element.getElementsByTagName("year").item(0).getTextContent()));
                    album.setGenreId(Integer.parseInt(element.getElementsByTagName("genreId").item(0).getTextContent()));
                    albums.add(album);
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return albums;
    }

    @Override
    public Album findOnId(Integer id) {
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        Album album = new Album();
        try {
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document xml = documentBuilder.parse(Constants.XML_ALBUM_PATH);
            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xPath = xPathFactory.newXPath();
            XPathExpression expr = xPath.compile("/albums/album[@id='" + id + "']");
            Node node = (Node) expr.evaluate(xml, XPathConstants.NODE);
            Element element = (Element) node;
            album.setId(Integer.parseInt(element.getAttribute("id")));
            album.setName(element.getElementsByTagName("name").item(0).getTextContent());
            album.setArtist(element.getElementsByTagName("artist").item(0).getTextContent());
            album.setYear(Integer.parseInt(element.getElementsByTagName("year").item(0).getTextContent()));
            album.setGenreId(Integer.parseInt(element.getElementsByTagName("genreId").item(0).getTextContent()));
        } catch (ParserConfigurationException | IOException | XPathExpressionException | SAXException e) {
            e.printStackTrace();
        }
        return album;
    }

    @Override
    public Album add(Album entity) {
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        documentFactory.setIgnoringElementContentWhitespace(true);
        try {
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document xml = documentBuilder.parse(Constants.XML_ALBUM_PATH);
            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xPath = xPathFactory.newXPath();
            int maxId = ((Number) xPath.evaluate("count(/albums/album)", xml, XPathConstants.NUMBER)).intValue();

            Element root = xml.getDocumentElement();
            if (xml.getDocumentElement() != null) {
                Element album = xml.createElement("album");
                root.appendChild(album);

                Attr attr = xml.createAttribute("id");
                attr.setValue(Integer.toString(maxId + 1));
                album.setAttributeNode(attr);

                Element name = xml.createElement("name");
                name.appendChild(xml.createTextNode(entity.getName()));
                album.appendChild(name);

                Element artist = xml.createElement("artist");
                artist.appendChild(xml.createTextNode(entity.getArtist()));
                album.appendChild(artist);

                Element year = xml.createElement("year");
                year.appendChild(xml.createTextNode(Integer.toString(entity.getYear())));
                album.appendChild(year);

                Element genreId = xml.createElement("genreId");
                year.appendChild(xml.createTextNode(Integer.toString(entity.getGenreId())));
                album.appendChild(genreId);

                xml.getDocumentElement().normalize();

                Transformer transformer = TransformerFactory.newInstance().newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.transform(new DOMSource(xml), new StreamResult(new File(Constants.XML_ALBUM_PATH)));
            }
        } catch (ParserConfigurationException | SAXException | IOException | TransformerException | XPathExpressionException e) {
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public void update(Album entity) {
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document xml = documentBuilder.parse(Constants.XML_ALBUM_PATH);
            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xPath = xPathFactory.newXPath();
            XPathExpression expr = xPath.compile("/albums/album[@id='" + entity.getId() + "']");
            Node node = (Node) expr.evaluate(xml, XPathConstants.NODE);
            Element element = (Element) node;
            element.getElementsByTagName("name").item(0).setTextContent(entity.getName());
            element.getElementsByTagName("artist").item(0).setTextContent(entity.getArtist());
            element.getElementsByTagName("year").item(0).setTextContent(String.valueOf(entity.getYear()));
            element.getElementsByTagName("genreId").item(0).setTextContent(String.valueOf(entity.getGenreId()));

            xml.getDocumentElement().normalize();
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(new DOMSource(xml), new StreamResult(new File(Constants.XML_ALBUM_PATH)));
        } catch (ParserConfigurationException | IOException | XPathExpressionException | SAXException | TransformerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document xml = documentBuilder.parse(Constants.XML_ALBUM_PATH);
            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xPath = xPathFactory.newXPath();
            XPathExpression expr = xPath.compile("/albums/album[@id='" + id + "']");
            Node node = (Node) expr.evaluate(xml, XPathConstants.NODE);
            node.getParentNode().removeChild(node);

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(new DOMSource(xml), new StreamResult(new File(Constants.XML_ALBUM_PATH)));
        } catch (ParserConfigurationException | IOException | XPathExpressionException | SAXException | TransformerException e) {
            e.printStackTrace();
        }
    }
}
