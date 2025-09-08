package strategies;

import models.PlayList;
import models.Song;

public interface PlayStrategy {
    public void setPlaylist(PlayList playlist);
    Song next() ;
    boolean hasNext() ;
    Song previous() ;
    boolean hasPrevious() ;
    default void addToNext(Song song) {}
}
