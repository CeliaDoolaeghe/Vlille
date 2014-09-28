package stations;

import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Celia on 20/09/2014.
 */
public class StationsParser {

    private static final String ns = null;

    public List<Station> parse(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in,"utf-16");
            parser.nextTag();
            return readXML(parser);
        } finally {
            in.close();
        }
    }

    private List<Station> readXML(XmlPullParser parser) throws XmlPullParserException, IOException {
        List stations = new ArrayList();

        parser.require(XmlPullParser.START_TAG, ns, "markers");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            // Starts by looking for the entry tag
            if (name.equals("marker")) {
                stations.add(readStation(parser));
            } else {
                skip(parser);
            }
        }
        return stations;
    }

    private Station readStation(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "marker");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
        }
        String stationId = parser.getAttributeValue(0);
        String stationName = parser.getAttributeValue(3);
        Station station = new Station(stationId, stationName);
        station.setLat(parser.getAttributeValue(1));
        station.setLng(parser.getAttributeValue(2));
        this.parseStation((new ResourceGet()).getXML("http://www.vlille.fr/stations/xml-station.aspx?borne=" + stationId), station);
        return station;
    }

    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }

    private String convertStreamToString(InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    public Station parseStation(InputStream in, Station station) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in,"utf-16");
            parser.nextTag();
            return readDetailedStation(parser, station);
        } finally {
            in.close();
        }
    }

    private Station readDetailedStation(XmlPullParser parser, Station station) throws XmlPullParserException, IOException {
        /*parser.require(XmlPullParser.START_TAG, ns, "station");
        int next = parser.next();
        while (!(parser.getName() != null && next == XmlPullParser.END_TAG && parser.getName().equals("station"))) {
            if (parser.getEventType() == XmlPullParser.START_TAG) {
                String name = parser.getName();
                // Starts by looking for the entry tag
                if (name.equals("adress")) {
                    station.setAddress(readAddress(parser));
                } else if (name.equals("bikes")) {
                    station.setNbBikes(readNbBikes(parser));
                } else if (name.equals("attachs")) {
                    station.setNbAttachs(readAttachs(parser));
                } else {
                    skip(parser);
                }
            }

            next = parser.next();
        }*/
        int event = parser.getEventType();
        while (event != XmlPullParser.END_DOCUMENT)
        {
            String name=parser.getName();
            switch (event){
                case XmlPullParser.START_TAG:
                    if(name.equals("bikes")) {
                        event = parser.next();
                        if(event == XmlPullParser.TEXT) {
                            String text = parser.getText();
                            station.setNbBikes(text);
                        }
                    }
                    else if(name.equals("attachs")) {
                        event = parser.next();
                        if(event == XmlPullParser.TEXT) {
                            String text = parser.getText();
                            station.setNbAttachs(text);
                        }
                    }
                    else if(name.equals("adress")) {
                        event = parser.next();
                        if(event == XmlPullParser.TEXT) {
                            String text = parser.getText();
                            station.setAddress(text);
                        }
                    }
                    break;
            }
            event = parser.next();
        }

        return station;
    }
}
