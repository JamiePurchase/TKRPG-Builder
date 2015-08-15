package audio;

public class Audio
{
    public static void loadAudio()
    {
        // Music
        AudioPlayer.load("music/theme1.wav", "MUSIC1");
        
        // Sound
        AudioPlayer.load("sound/error.mp3", "ERROR");
    }
    
}