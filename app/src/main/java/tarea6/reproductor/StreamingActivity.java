package tarea6.reproductor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import tarea6.reproductor.databinding.ActivityStreamingBinding;

public class StreamingActivity extends AppCompatActivity implements MediaAdapter.OnMediaClickListener {

    private ActivityStreamingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStreamingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupRecyclerView();
    }

    private void setupRecyclerView() {
        //MediaAdapter adapter = new MediaAdapter(this, MediaItem.getMediaList()); // Asegúrate de filtrar por streaming si es necesario
        //binding.recyclerViewStreaming.setAdapter(adapter);
        binding.recyclerViewStreaming.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onPlayClicked(MediaItem item) {

    }
}