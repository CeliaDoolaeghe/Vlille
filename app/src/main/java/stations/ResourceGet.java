package stations;

import android.util.Log;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Celia on 20/09/2014.
 */
public class ResourceGet {

    public InputStream getXML(String httpURL) {
        InputStream inputStream = null;

        try {
            final URL url = new URL(httpURL);
            final URLConnection connection = url.openConnection();
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);
            connection.connect();

            inputStream = connection.getInputStream();

            BufferedReader stream = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            StringBuffer buffer = new StringBuffer();
            while ((line = stream.readLine()) != null) {
                buffer.append(line);
            }

            inputStream = new ByteArrayInputStream(buffer.toString().getBytes("utf-16"));

        } catch (Exception e) {
            Log.e("celia", "Error during xml reading", e);
        }

        return inputStream;
    }
}

