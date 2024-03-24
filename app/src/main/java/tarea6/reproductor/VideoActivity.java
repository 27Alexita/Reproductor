package tarea6.reproductor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import tarea6.reproductor.databinding.ActivityVideoBinding;

public class VideoActivity extends AppCompatActivity implements MediaAdapter.OnMediaClickListener {

    private ActivityVideoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVideoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupRecyclerView();
    }

    private void setupRecyclerView() {
        //MediaAdapter adapter = new MediaAdapter(this, MediaItem.getMediaList()); // Asegúrate de filtrar por videos si es necesario
        //binding.recyclerViewVideo.setAdapter(adapter);
        binding.recyclerViewVideo.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onPlayClicked(MediaItem item) {

    }
}