package tarea6.reproductor;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MediaContentLoader {

    public static List<MediaItem> loadMediaFromJSON(Context context) {
        List<MediaItem> mediaList = new ArrayList<>();
        try {
            // Abre el archivo JSON desde la carpeta raw
            InputStream is = context.getResources().openRawResource(R.raw.medialist);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String json = new String(buffer, "UTF-8");

            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("recursos_list");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonMedia = jsonArray.getJSONObject(i);
                String nombre = jsonMedia.getString("nombre");
                String descripcion = jsonMedia.getString("descripcion");
                String tipo = jsonMedia.getString("tipo");
                String uri = jsonMedia.getString("URI");
                String imagen = jsonMedia.getString("imagen");
                mediaList.add(new MediaItem(nombre, descripcion, tipo, uri, imagen));
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return mediaList;
    }
}
