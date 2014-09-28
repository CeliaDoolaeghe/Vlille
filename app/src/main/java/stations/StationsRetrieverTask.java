package stations;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Celia on 21/09/2014.
 */
public class StationsRetrieverTask extends AsyncTask<Void, Void, List<Station>> {

    private ArrayAdapter<Station> adapter;
    private String httpURL;

    public StationsRetrieverTask(String httpURL, ArrayAdapter<Station> adapter) {
        this.httpURL = httpURL;
        this.adapter = adapter;
    }

    @Override
    protected List<Station> doInBackground(Void... params) {
        try {
            return (new StationsParser().parse(new ResourceGet().getXML(httpURL)));
        } catch (XmlPullParserException e) {
            Log.e("celia",e.toString());
        } catch (IOException e) {
            Log.e("celia",e.toString());
        }
        return new ArrayList<Station>();
    }

    @Override
    protected void onPostExecute(List<Station> stations) {
        adapter.addAll(stations);
    }
}
