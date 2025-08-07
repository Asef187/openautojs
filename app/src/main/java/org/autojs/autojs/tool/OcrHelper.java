package org.autojs.autojs.tool;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import com.googlecode.tesseract.android.TessBaseAPI;
import java.io.File;

public class OcrHelper {
    private TessBaseAPI tessBaseAPI;

    public OcrHelper(String dataPath, String language) {
        tessBaseAPI = new TessBaseAPI();
        tessBaseAPI.init(dataPath, language);
    }

    public String recognizeImage(Bitmap bitmap) {
        tessBaseAPI.setImage(bitmap);
        return tessBaseAPI.getUTF8Text();
    }

    public String recognizeImageFromBase64(String base64Image) {
        byte[] decoded = Base64.decode(base64Image, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(decoded, 0, decoded.length);
        return recognizeImage(bitmap);
    }

    public void close() {
        if (tessBaseAPI != null) {
            tessBaseAPI.end();
        }
    }
}
