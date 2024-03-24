package tarea6.reproductor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MediaAdapter extends RecyclerView.Adapter<MediaAdapter.ViewHolder> {

    private Context context;
    private List<MediaItem> mediaList;

    private OnMediaClickListener onMediaClickListener;

    public interface OnMediaClickListener {
        void onPlayClicked(MediaItem item);
    }

    public MediaAdapter(Context context, List<MediaItem> mediaList) {
        this.context = context;
        this.mediaList = mediaList;
    }

    public MediaAdapter(Context context, List<MediaItem> mediaList, OnMediaClickListener onMediaClickListener) {
        this.context = context;
        this.mediaList = mediaList;
        this.onMediaClickListener = onMediaClickListener;
    }

    @NonNull
    @Override
    public MediaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.media_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MediaAdapter.ViewHolder holder, int position) {
        MediaItem item = mediaList.get(position);
        holder.textViewNombre.setText(item.getNombre());
        holder.textViewDescripcion.setText(item.getDescripcion());

        // Elimina la extensión del archivo de imagen y convierte el nombre a minúsculas para el ID del recurso
        String imageName = item.getImagen().substring(0, item.getImagen().lastIndexOf('.')).toLowerCase();
        // Asegúrate de que los nombres de imagen no contienen la extensión ".jpg" y están en minúsculas
        int imageResId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
        holder.imageViewPrincipal.setImageResource(imageResId);

        // Configura las demás imágenes
        holder.imageViewTipoArchivo.setImageResource(item.getTipoImagenDrawable());
        holder.imageViewPlay.setImageResource(item.getPlayButtonDrawable());

        // Configurar el listener para reproducir el archivo
        holder.imageViewPlay.setOnClickListener(v -> {
            if(onMediaClickListener != null){
                onMediaClickListener.onPlayClicked(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mediaList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewNombre, textViewDescripcion;
        private ImageView imageViewPrincipal, imageViewPlay, imageViewTipoArchivo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNombre = itemView.findViewById(R.id.textViewNombre);
            textViewDescripcion = itemView.findViewById(R.id.textViewDescripcion);
            imageViewPrincipal = itemView.findViewById(R.id.imageViewPrincipal);
            imageViewPlay = itemView.findViewById(R.id.imageViewPlay);
            imageViewTipoArchivo = itemView.findViewById(R.id.imageViewTipoArchivo);
        }
    }
}
