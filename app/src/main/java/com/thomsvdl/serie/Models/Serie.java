package com.thomsvdl.serie.Models;

public class Serie {

    private String name;
    private int episode = 1;
    private int season = 1;

    public Serie(String name, int episode, int season) {
        this.name = name;
        this.episode = episode;
        this.season = season;
    }

    public Serie(String name) {
        this.name = name;
        this.episode = 1;
        this.season = 1;
    }

    public String getName() {
        return name;
    }

    public int getEpisode() {
        return episode;
    }

    public int getSeason() {
        return season;
    }

    public void setEpisode(int episode) {
        this.episode = episode;
    }

    public void setSeason(int season) {
        this.season = season;
    }
}
