package fi.crowmoore.mustakynnys;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

/**
 * Created by Crowmoore on 05-Jul-17.
 */

public class DownloadImage extends AsyncTask<String, Void, Bitmap> {

    ImageView image;

    public DownloadImage(ImageView image) {
        this.image = image;
    }

    protected Bitmap doInBackground(String... urls) {
        String url = urls[0];
        Bitmap icon = null;
        try {
            InputStream in = new java.net.URL(url).openStream();
            icon = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return icon;
    }

    protected void onPostExecute(Bitmap result) {
        image.setImageBitmap(result);
    }

}
