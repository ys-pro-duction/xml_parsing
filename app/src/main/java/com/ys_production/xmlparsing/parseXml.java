package com.ys_production.xmlparsing;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;

public class parseXml {
    private static final String TAG = "parseXml";
    private ArrayList<feedMain>  application;

    public parseXml() {
        this.application = new ArrayList<>();
    }
    public ArrayList<feedMain> getApplication() {
        return application;
    }
public boolean parse(String xmldata){
        boolean status = true;
        feedMain currentrecord = null;
        boolean inEntry = false;
        String textValue = "";
        try {
            XmlPullParserFactory parserFactory = XmlPullParserFactory.newInstance();
            parserFactory.setNamespaceAware(true);
            XmlPullParser pullParser = parserFactory.newPullParser();
            pullParser.setInput(new StringReader(xmldata));
            int entryType = pullParser.getEventType();

            while (entryType != pullParser.END_DOCUMENT){
                String currentTag = pullParser.getName();
                switch (entryType){
                    case XmlPullParser.START_TAG:
                        if ("entry".equalsIgnoreCase(currentTag)){
                            inEntry = true;
                            currentrecord = new feedMain();
                        }
                        break;
                    case XmlPullParser.TEXT:
                        textValue = pullParser.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if (inEntry){
                            if ("entry".equalsIgnoreCase(currentTag)) {
                                inEntry = false;
                                application.add(currentrecord);
                            }
                            else if ("name".equalsIgnoreCase(currentTag)){
                                currentrecord.setName(textValue);
                            }
                            else if ("artist".equalsIgnoreCase(currentTag)){
                                currentrecord.setArtist(textValue);
                            }
                            else if ("releaseDate".equalsIgnoreCase(currentTag)){
                                currentrecord.setReleasedate(textValue);
                            }
                            else if ("image".equalsIgnoreCase(currentTag)){
                                currentrecord.setImageURL(textValue);
                            }
                        }
                        break;
                    default:
//                        Log.e(TAG, "parse: "+"bero koni ke hoyo");
                }
                entryType = pullParser.next();
            }
        }
        catch (Exception e){
            status = false;
            e.printStackTrace();
        }
        return status;
}
}
