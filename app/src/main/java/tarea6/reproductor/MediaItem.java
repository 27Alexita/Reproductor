package tarea6.reproductor;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MediaItem {

    private String nombre;
    private String descripcion;
    private String tipo; // "0" para audio, "1" para video, "2" para streaming
    private String uri;
    private String imagen; // Nombre del archivo de imagen en los assets

    // Constructor
    public MediaItem(String nombre, String descripcion, String tipo, String uri, String imagen) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.uri = uri;
        this.imagen = imagen;
    }

    // Getters y setters
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public String getTipo() { return tipo; }
    public String getUri() { return uri; }
    public String getImagen() { return imagen; }


    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public void setUri(String uri) { this.uri = uri; }
    public void setImagen(String imagen) { this.imagen = imagen; }

    // Métodos para obtener los identificadores de recursos drawable
    public int getTipoImagenDrawable() {
        switch (tipo) {
            case "0": return R.drawable.audiocard;
            case "1": return R.drawable.videocard;
            case "2": return R.drawable.streamingcard;
            default: return R.drawable.ic_launcher_background; // Drawable por defecto
        }
    }

    public int getPlayButtonDrawable() {
        return R.drawable.play; // Drawable del botón de play
    }
}
