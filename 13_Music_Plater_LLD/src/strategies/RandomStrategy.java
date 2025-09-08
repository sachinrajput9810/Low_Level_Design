package strategies;

import models.PlayList;
import models.Song;

import java.util.List;
import java.util.Random;
import java.util.Stack;

public class RandomStrategy implements PlayStrategy {

    private PlayList currentPlayList ;
    private List<Song> remainingSongs ;
    private Stack<Song> history ;
    private Random random ;

    public RandomStrategy() {
        currentPlayList = null ;
        random = new Random() ;
    }

    @Override
    public void setPlaylist(PlayList playlist) {
        currentPlayList = playlist ;
        if(currentPlayList == null || currentPlayList.getSize() == 0) throw new RuntimeException("Playlist is empty") ;
        remainingSongs = currentPlayList.getSongs() ;
        history = new Stack<>() ;
    }

    @Override
    public Song next() {
        if(currentPlayList == null || currentPlayList.getSongs().isEmpty()) throw new RuntimeException("Playlist is empty") ;
        if(remainingSongs.isEmpty()) throw new RuntimeException("Playlist is empty") ;
        int randomIndex = random.nextInt(remainingSongs.size()) ;
        Song selectedSong = remainingSongs.get(randomIndex) ;
        int lastIndex = remainingSongs.size() - 1 ;
        remainingSongs.set(randomIndex , remainingSongs.get(lastIndex)) ;
        remainingSongs.remove(lastIndex) ;
        history.push(selectedSong) ;
        return selectedSong ;
    }

    @Override
    public boolean hasNext() {
        return currentPlayList != null && !remainingSongs.isEmpty() ;
    }

    @Override
    public Song previous() {
        if(history.isEmpty()) throw new RuntimeException("History is empty") ;
        return history.pop() ;
    }

    @Override
    public boolean hasPrevious() {
        return false;
    }


}
