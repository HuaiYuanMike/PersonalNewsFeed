package com.example.mikehhsu.personalnewsfeed.parser;

import android.util.Log;
import android.util.Xml;

import com.example.mikehhsu.personalnewsfeed.db.Article;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by mikehhsu on 10/13/16.
 */
public class ForbesNewsParser {//// TODO: 10/20/16 can probably use a interface for each parser
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
            //// TODO: 10/20/16 skip tags that we don't need
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

    private Article parseItems(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException{
        xmlPullParser.nextTag();
        xmlPullParser.require(XmlPullParser.START_TAG, null, "item");

        //parse "title"
        xmlPullParser.nextTag();
        xmlPullParser.require(XmlPullParser.START_TAG, null, "title");
        String title =  xmlPullParser.nextText();
        //parse "link"//// TODO: 10/20/16 add "link" field
        xmlPullParser.nextTag();
        xmlPullParser.require(XmlPullParser.START_TAG, null, "link");
        String link = xmlPullParser.nextText();
        //parse "description"
        xmlPullParser.nextTag();
        xmlPullParser.require(XmlPullParser.START_TAG, null, "description");
        String desc = xmlPullParser.nextText();

        //// TODO: 10/20/16 time_Stamp needs to be set
        Article article = new Article("", desc, "", 0l, title, "");

        return article;
    }
}
