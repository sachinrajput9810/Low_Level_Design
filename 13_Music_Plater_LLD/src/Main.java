import enums.DeviceType;
import enums.PlayingStrategyType;

public class Main {
    public static void main(String[] args) {//TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        try {
            MusicPlayerApplication app = MusicPlayerApplication.getInstance() ;
            // creating songs
            app.createSongInLibrary("Dhoom Machale" , "Visha" ) ;
            app.createSongInLibrary("Ooo Mahi", "Arjit Singh");
            app.createSongInLibrary("Kabhi Khushi Kabhie Gham" , "Arijit Singh" ) ;
            app.createSongInLibrary("Mere Yaar Hai" , "Arijit Singh" ) ;
            app.createSongInLibrary("Mere khawabon ki Malika" , "KK" ) ;
            app.createSongInLibrary("Blue eyes" , "Honey Singh") ;
            app.createSongInLibrary("Chandni Chakkar" , "Honey Singh" ) ;
            app.createSongInLibrary("Dil Diyan Gallan" , "Arijit Singh" );

            // creating playlist
            app.createPlayList("Bollywood vibes") ;
            // adding songs
            app.addSongToPlaylist("Bollywood vibes" , "Dhoom Machale") ;
            app.addSongToPlaylist("Bollywood vibes" , "Ooo Mahi") ;
            app.addSongToPlaylist("Bollywood vibes" , "Kabhi Khushi Kabhie Gham") ;
            app.addSongToPlaylist("Bollywood vibes" , "Mere Yaar Hai") ;
            app.addSongToPlaylist("Bollywood vibes" , "Mere khawabon ki Malika") ;

            // connecting audio device
            app.connectAudioDevice(DeviceType.BLUETOOTH) ;

            // play-pause single song
            app.playSingleSong("Dhoom Machale") ;
            app.pauseCurrentSong("Dhoom Machale") ;
            app.playSingleSong("Dhoom Machale") ;

            System.out.println("\n --- Sequential Play  ---   \n") ;
            app.selectPlayStrategy(PlayingStrategyType.SEQUENTIAL) ;
            app.loadPlaylist("Bollywood vibes") ;
            app.playAllTracks() ;

            System.out.println("\n --- Random Play  ---   \n") ;
            app.selectPlayStrategy(PlayingStrategyType.RANDOM) ;
            app.loadPlaylist("Bollywood vibes") ;
            app.playAllTracks() ;

            System.out.println(" --- Previous track in sequential play  ---   \n") ;
            app.selectPlayStrategy(PlayingStrategyType.SEQUENTIAL) ;
            app.loadPlaylist("Bollywood vibes") ;
            app.playAllTracks();
            
            // Reset to the beginning of the playlist for previous track demo
            app.loadPlaylist("Bollywood vibes");
            app.playPreviousTrack() ;
            app.playPreviousTrack() ;
        }
        catch (Exception e) {
            System.out.println(" Error : " + e.getMessage());
        }
    }
}