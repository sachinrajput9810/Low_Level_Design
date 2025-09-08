package strategies;

import models.PlayList;
import models.Song;

public class SequentialStrategy implements PlayStrategy{
    private PlayList currentPlayList ;
    private int currentIndex ;

    public SequentialStrategy() {
        currentPlayList = null ;
        currentIndex = -1 ;
    }

    @Override
    public void setPlaylist(PlayList playList) {
        currentPlayList = playList ;
        currentIndex = -1 ;
    }

    @Override
    public Song next() {
        if(currentPlayList == null || currentPlayList.getSongs().isEmpty()) throw new RuntimeException("Playlist is empty") ;
        currentIndex++ ;
        return currentPlayList.getSongs().get(currentIndex) ;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < currentPlayList.getSongs().size() - 1 ;
    }

    @Override
    public Song previous() {
        if(currentPlayList == null || currentPlayList.getSongs().isEmpty()) throw new RuntimeException("Playlist is empty") ;
        
        // If we're at the beginning of the playlist, wrap around to the end
        if (currentIndex <= 0) {
            currentIndex = currentPlayList.getSongs().size() - 1;
        } else {
            currentIndex--;
        }
        
        return currentPlayList.getSongs().get(currentIndex);
    }

    @Override
    public boolean hasPrevious() {
        return currentIndex >= 0 && currentPlayList != null && !currentPlayList.getSongs().isEmpty();
    }
}
