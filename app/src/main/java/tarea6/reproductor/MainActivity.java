package tarea6.reproductor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import tarea6.reproductor.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    // Declaración de variables
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inicialización del binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Configuración de los botones
        setupButtons();
    }

    // Método para configurar los botones
    private void setupButtons() {
        // Botón para abrir la actividad de audio
        binding.imageButtonAudio.setOnClickListener(v -> openAudioActivity());

        // Botón para abrir la actividad de video
        binding.imageButtonVideo.setOnClickListener(v -> openVideoActivity());

        // Botón para abrir la actividad de streaming
        binding.imageButtonStreaming.setOnClickListener(v -> openStreamingActivity());
    }

    // Método para abrir la actividad de audio
    private void openAudioActivity() {
        Intent audioIntent = new Intent(this, AudioActivity.class);
        startActivity(audioIntent);
    }

    // Método para abrir la actividad de video
    private void openVideoActivity() {
        Intent videoIntent = new Intent(this, VideoActivity.class);
        startActivity(videoIntent);
    }

    // Método para abrir la actividad de streaming
    private void openStreamingActivity() {
        Intent streamingIntent = new Intent(this, StreamingActivity.class);
        startActivity(streamingIntent);
    }
}