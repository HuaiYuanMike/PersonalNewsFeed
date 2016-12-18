package com.example.mikehhsu.personalnewsfeed.parser;

import android.util.Log;
import android.util.Xml;

import com.example.mikehhsu.personalnewsfeed.db.Article;
import com.example.mikehhsu.personalnewsfeed.db.SQLiteDBObject;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by mikehhsu on 10/13/16.
 */
public class NYTNewsListParser {//// TODO: 10/20/16 can probably use a abstract class for each parser
//    private XmlPullParser parser;
    public ArrayList<SQLiteDBObject> parse(InputStream in) {
        ArrayList<SQLiteDBObject> articles = new ArrayList<>();
        try{
            XmlPullParser xmlPullParser = Xml.newPullParser();
            xmlPullParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            xmlPullParser.setInput(in, null);
            xmlPullParser.nextTag();//move to next start tag / end tag
            xmlPullParser.require(XmlPullParser.START_TAG, null, "rss");

            //locate the first item tag
            if(findTag(xmlPullParser, "item")){
                while(xmlPullParser.getEventType() == XmlPullParser.START_TAG &&
                        new String("item").equals(xmlPullParser.getName())) {
                    Article article = new Article();
                    parseItem(xmlPullParser, article);
                    articles.add(article);
                    xmlPullParser.nextTag();
                }

            }else
            {
                Log.d(this.getClass().toString(), "something went wrong! Can't find required tag" + "item" + "to parse");
            }


        }catch (XmlPullParserException xl){
            xl.printStackTrace();
        }catch (IOException io){
            io.printStackTrace();
        }finally{

        }
        return articles;
    }

    private void parseItem(XmlPullParser xmlPullParser, Article article) throws IOException, XmlPullParserException{
        xmlPullParser.require(XmlPullParser.START_TAG, null, "item");
        parseItemIterate(xmlPullParser, article);
    }

    private void parseItemIterate(XmlPullParser xmlPullParser, Article article) throws IOException, XmlPullParserException {
        while(xmlPullParser.next() != XmlPullParser.START_TAG) {
            if(xmlPullParser.getEventType() == XmlPullParser.END_TAG && xmlPullParser.getName().equals("item")){
                return;
            }
            continue;
        }
        String name = xmlPullParser.getName();
        if(name.equals("title")){
            article.setTitle(parseTitle(xmlPullParser));
        }else if(name.equals("guid")){
            article.setGuid(parseTextField(xmlPullParser, "guid"));
        }else if(name.equals("description")){
            article.setDesc(parseTextField(xmlPullParser, "description"));
        }else if(name.equals("dc:creator")){
            article.setAuthor(parseTextField(xmlPullParser, "dc:creator"));
        }else if(name.equals("media:content")){
            String url = xmlPullParser.getAttributeValue(null, "url");
            article.setImg(url);
        }else if(name.equals("link")){
            article.setUrl(parseTextField(xmlPullParser, "link"));
        }else if(name.equals("pubDate")){
            //// TODO: 10/20/16 parse time
            xmlPullParser.nextText();
        }else{
            skip(xmlPullParser);
        }
        parseItemIterate(xmlPullParser, article);
    }

    private String parseTitle(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException{
        xmlPullParser.require(XmlPullParser.START_TAG, null, "title");
        return xmlPullParser.nextText();
    }

    private String parseTextField(XmlPullParser xmlPullParser, String name) throws IOException, XmlPullParserException{
        xmlPullParser.require(XmlPullParser.START_TAG, null, name);
        String text = xmlPullParser.nextText();
        return text;
    }

    private String readText(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException{
        String result = "";
        if(xmlPullParser.next() == XmlPullParser.TEXT){
            result = xmlPullParser.getText();
            xmlPullParser.nextTag();
        }
        return result;
    }

    private void skip(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException{
        if(xmlPullParser.getEventType() != XmlPullParser.START_TAG){
            throw new IllegalStateException("Parser at wrong event type! Should be START_TAG");
        }
        int depth = 1;
        while(depth != 0){
            switch (xmlPullParser.next()){
                case XmlPullParser.START_TAG:
                    depth ++;
                    break;
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.END_DOCUMENT:
                    throw new IllegalStateException("Parser reach End_Document state unexpectedly!");
                default:
                    break;
            }
        }
    }

    private boolean findTag(XmlPullParser xmlPullParser , String tagName) throws XmlPullParserException, IOException{
        if(xmlPullParser.getEventType() != XmlPullParser.START_TAG){
            throw new IllegalStateException("FindTag invocated when the parser not at START_TAG");
        }
        if(!xmlPullParser.getName().equals(tagName)){
            while (xmlPullParser.next() != XmlPullParser.START_TAG){
                if(xmlPullParser.getEventType() == XmlPullParser.END_DOCUMENT){
                    return false;
                }
            }
            return findTag(xmlPullParser, tagName);
        }else{
            return true;
        }

    }
}
