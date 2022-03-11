package com.example.htrsinhvin.NEWS;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class PH18428_XMLPar {
    //lấy tài liệu
    public Document getDocument(String xml) throws IOException, SAXException{
        Document document = null;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        }catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        InputSource source = new InputSource();

        source.setCharacterStream(new StringReader(xml));
        source.setEncoding("UTF-8");

        document = builder.parse(source);
        return document;
    }

    //lấy title
    public String getValue(Element node, String name){
        String value = "";
        NodeList listNode = node.getElementsByTagName(name);

        value = getTextOfNode(listNode.item(0));
        return value;
    }

    private String getTextOfNode (Node item){
        Node chid;

        if(item != null){
            if (item.hasChildNodes()){
                for (chid=item.getFirstChild(); chid!=null;chid=chid.getNextSibling()){
                    return chid.getNodeValue();
                }
            }
        }
        return "";
    }
}
