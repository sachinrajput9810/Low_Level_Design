package models;

import java.util.ArrayList;
import java.util.List;

public class PlayList {
    private String playListName;
    private List<Song> songList;

    public PlayList(String playListName) {
        this.playListName = playListName;
        songList = new ArrayList<>();
    }

    public void addSongToPlaylist(Song song) {
        if(song == null) throw new RuntimeException("Song cannot be null");
        songList.add(song);
    }

    public String getPlayListName() {
        return playListName;
    }

    public List<Song> getSongs() {
        return songList;
    }

    public int getSize() {
        return songList.size();
    }









}

