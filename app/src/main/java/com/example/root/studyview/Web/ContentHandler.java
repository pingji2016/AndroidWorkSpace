package com.example.root.studyview.Web;

import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ContentHandler extends DefaultHandler {
    private String nodename;

    private StringBuilder id;

    private StringBuilder name;

    private StringBuilder version;

    private static final String TAG = "ContentHandler";
    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        id = new StringBuilder();
        name = new StringBuilder();
        version = new StringBuilder();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        nodename = localName;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        switch (nodename){
            case "id":
                id.append(ch,start,length);
                break;
            case "name":
                name.append(ch,start,length);
                break;
            case "version": {
                version.append(ch,start,length);
                break;
            }
            default:
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ("apps".equals(localName)){
            Log.i(TAG, "showXMLResopnse:2 id = " + id.toString().trim());
            Log.i(TAG, "showXMLResopnse:2 name = " + name.toString().trim());
            Log.i(TAG, "showXMLResopnse:2 version = " + version.toString().trim());
            id.setLength(0);
            name.setLength(0);
            version.setLength(0);
        }
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }
}
