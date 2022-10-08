package com.solvd.pageranked.test;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class WikiXMLParser {
    private static SAXParserFactory factory;
    private static SAXParser parser;

    //
    public static Page parseXML(String file) {
        factory = SAXParserFactory.newInstance();
        try {
            parser = factory.newSAXParser();
            parser.parse(file, new PageHandler());

        } catch (ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;

    }

    /**
     * Searches for [[LINK_NAME]] occurances in Text argument and returns every
     * occurrance
     * <p>
     * Useage:
     *
     * @author richman
     */
    private static class TextParser {

        private static TextParser textParser;
        private Pattern pat;
        // default pattern for wikipedia link parsing
        private String pattern = "[[.*]]";

        private TextParser() {
        }

        public static TextParser getInstance() {
            if (textParser == null)
                textParser = new TextParser();
            return textParser;
        }

        public void setPattern(String pattern) {
            this.pattern = pattern;
        }

        public List<String> parse(String text) {
            pat = Pattern.compile(pattern);
            Matcher mat = pat.matcher(text);
            while (mat.find()) {
                String group = mat.group();
                System.out.println(group);
            }

            return null;
        }

    }


    private static class PageHandler extends DefaultHandler {

        private Deque<Page> pages = new ArrayDeque<>();
        private Page curPage = null;

        private String tmpT = null;

        @Override
        public void endElement(String uri, String localName, String qName)
                throws SAXException {
            switch (qName) {

                case "title":
                    curPage.pageName = tmpT;
                    break;
                case "text":
                    curPage.out_links.addAll(TextParser.getInstance().parse(tmpT));
                    break;
            }

            if (qName.equalsIgnoreCase("page"))
                pages.add(curPage);
        }

        @Override
        public void startElement(String uri, String localName, String qName,
                                 Attributes attributes) throws SAXException {
            if (qName.equalsIgnoreCase("page")) {
                curPage = new Page();
            }

        }

        @Override
        public void characters(char[] ch, int start, int length)
                throws SAXException {
            tmpT = new String(ch, start, length);
        }

    }

    public static class Page {

        private String pageName;
        private Deque<String> out_links;

        Page() {
            pageName = new String();
            out_links = new ArrayDeque<>();
        }

    }
}
