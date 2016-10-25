package com.example.mikehhsu.personalnewsfeed.parser;

import android.util.Log;
import android.util.Xml;

import com.example.mikehhsu.personalnewsfeed.db.Article;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by mikehhsu on 10/13/16.
 */
public class NYTNewsListParser {//// TODO: 10/20/16 can probably use a interface for each parser
//    private XmlPullParser parser;
    public ArrayList<Article> parse(InputStream in) {
        try{
            XmlPullParser xmlPullParser = Xml.newPullParser();
            xmlPullParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            xmlPullParser.setInput(in, null);
            xmlPullParser.nextTag();//move to next start tag / end tag

            //readfeed
            Log.d("mikelog", "start_tag" + XmlPullParser.START_TAG);
            Log.d("mikelog", "end_tag" + XmlPullParser.END_TAG);
            xmlPullParser.require(XmlPullParser.START_TAG, null, "rss");
            //skip tags that we don't need and parse when we encounter "item"
            while(xmlPullParser.getEventType() != XmlPullParser.END_DOCUMENT) {
                if(xmlPullParser.getEventType() == XmlPullParser.START_TAG){
                    if(xmlPullParser.getName().equals("item")){
                        parseItem(xmlPullParser);//// TODO: 10/25/16 store parsed item/article
                    }else{
                        skip(xmlPullParser);
                    }
                }
            }
//// TODO: 10/20/16 need to setup root link and info for the page
            while(xmlPullParser.getEventType() != XmlPullParser.END_DOCUMENT){
                if(xmlPullParser.getEventType() != XmlPullParser.START_TAG)
                {
                    xmlPullParser.next();
                    Log.d("mikelog", "next~~~");
                }else
                {
                    Log.d("mikeLog", " Name: "+ xmlPullParser.getName()
                            + "; text: " + xmlPullParser.nextText()
                            + "; current Type: " + xmlPullParser.getEventType());
                }

            }


        }catch (XmlPullParserException xl){
            xl.printStackTrace();
        }catch (IOException io){
            io.printStackTrace();
        }finally{

        }
        return null;
    }

    private Article parseItem(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException{
        xmlPullParser.require(XmlPullParser.START_TAG, null, "item");
        Article article = new Article();
        while(xmlPullParser.next() != XmlPullParser.END_TAG){
            if(xmlPullParser.getEventType() != XmlPullParser.START_TAG){
                continue;
            }
            String name = xmlPullParser.getName();
            if(name.equals("title")){
                article.setTitle(parseTitle(xmlPullParser));
            }else if(name.equals("guid")){
                //// TODO: 10/20/16 add link to article class
            }else if(name.equals("description")){
                article.setDesc(parseTextField(xmlPullParser, "description"));
            }else if(name.equals("dc:creator")){
                article.setAuthor(parseTextField(xmlPullParser, "dc:creator"));
            }else if(name.equals("pubDate")){
                //// TODO: 10/20/16 parse time
            }
        }
//        //parse "title"
//        xmlPullParser.nextTag();
//        xmlPullParser.require(XmlPullParser.START_TAG, null, "title");
//        String title =  xmlPullParser.nextText();
//        //parse "link"
//        xmlPullParser.nextTag();
//        xmlPullParser.require(XmlPullParser.START_TAG, null, "link");
//        String link = xmlPullParser.nextText();
//        //parse "description"
//        xmlPullParser.nextTag();
//        xmlPullParser.require(XmlPullParser.START_TAG, null, "description");
//        String desc = xmlPullParser.nextText();

//        Article article = new Article("", desc, "", 0l, title, "");

        //we want it to move to next tag
        xmlPullParser.next();
        return article;
    }

    private String parseTitle(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException{
        xmlPullParser.require(XmlPullParser.START_TAG, null, "title");
        return readText(xmlPullParser);
    }

    private String parseTextField(XmlPullParser xmlPullParser, String name) throws IOException, XmlPullParserException{
        xmlPullParser.require(XmlPullParser.START_TAG, null, name);
        return readText(xmlPullParser);
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
        //we want it to move to next tag
        xmlPullParser.next();
    }
}
