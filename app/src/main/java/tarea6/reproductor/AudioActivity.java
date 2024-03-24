package tarea6.reproductor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.MediaController.MediaPlayerControl;
import android.widget.SeekBar;

import java.util.List;

import tarea6.reproductor.databinding.ActivityAudioBinding;

public class AudioActivity extends AppCompatActivity implements MediaAdapter.OnMediaClickListener, MediaPlayerControl {

    // Declaración de variables
    private ActivityAudioBinding binding;
    private MediaPlayer mediaPlayer;
    private MediaController mediaController;
    private AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inicializo el binding
        binding = ActivityAudioBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Configuro el recyclerView
        setupRecyclerView();

        // Inicializo el reproductor
        setupMediaPlayer();

        // Configuro la seekBar
        setupVolumeControl();

        // Configurar el botón de regreso
        setupBackButton();
    }

    // Método para configurar el recyclerView
    private void setupRecyclerView() {
        // Cargar los datos desde el archivo JSON
        List<MediaItem> mediaList = MediaContentLoader.loadMediaFromJSON(this); // Asegúrate de que el nombre del archivo sea correcto
        MediaAdapter adapter = new MediaAdapter(this, mediaList, this);

        // Configuro el recyclerView
        binding.recyclerViewAudio.setAdapter(adapter);
        binding.recyclerViewAudio.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setupMediaPlayer() {
        mediaPlayer = new MediaPlayer();
        mediaController = new MediaController(this);
        // Asigna el MediaPlayer al MediaController
        mediaController.setMediaPlayer(this);
        mediaController.setAnchorView(binding.recyclerViewAudio);
        // Permite que los controles de MediaController aparezcan sobre la vista anclada
        mediaController.setEnabled(true);
    }

    private void setupVolumeControl() {
        // Inicializa el AudioManager
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        // Configura la SeekBar para controlar el volumen
        binding.seekBarVolume.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        binding.seekBarVolume.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));

        binding.seekBarVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Cambiar el volumen del sistema al progreso del SeekBar
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Opcionalmente, puedes implementar algo aquí
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Opcionalmente, puedes implementar algo aquí
            }
        });
    }

    private void setupBackButton() {
        // Encuentra el botón por su ID y configura el evento de clic
        Button buttonBack = binding.buttonBack; // Asegúrate de que buttonBack esté incluido en tu clase de binding
        buttonBack.setOnClickListener(v -> {
            // Finaliza la actividad actual
            finish();
        });
    }

    @Override
    public void onPlayClicked(MediaItem item) {
        int audioResId = getResources().getIdentifier(item.getUri(), "raw", getPackageName());
        try{
            mediaPlayer.reset();
            AssetFileDescriptor afd = getResources().openRawResourceFd(audioResId);
            if (afd == null) return;
            mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            afd.close();
            mediaPlayer.prepare(); // Prepárate para la reproducción sincrónica
            mediaPlayer.start();
            mediaController.show(); // Muestra los controles
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    // Implementación de MediaPlayerControl
    @Override
    public void start() {
        mediaPlayer.start();
    }

    @Override
    public void pause() {
        mediaPlayer.pause();
    }

    @Override
    public int getDuration() {
        return mediaPlayer.getDuration();
    }

    @Override
    public int getCurrentPosition() {
        return mediaPlayer.getCurrentPosition();
    }

    @Override
    public void seekTo(int pos) {
        mediaPlayer.seekTo(pos);
    }

    @Override
    public boolean isPlaying() {
        return mediaPlayer != null && mediaPlayer.isPlaying();
    }

    @Override
    public int getBufferPercentage() {
        return 0; // No es necesario para reproducción local
    }

    @Override
    public boolean canPause() {
        return true;
    }

    @Override
    public boolean canSeekBackward() {
        return true;
    }

    @Override
    public boolean canSeekForward() {
        return true;
    }

    @Override
    public int getAudioSessionId() {
        return mediaPlayer.getAudioSessionId();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}