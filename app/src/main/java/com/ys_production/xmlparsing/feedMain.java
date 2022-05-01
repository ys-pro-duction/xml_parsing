package com.ys_production.xmlparsing;

public class feedMain {
    private String name,artist,releasedate,imageURL;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getReleasedate() {
        return releasedate;
    }

    public void setReleasedate(String releasedate) {
        this.releasedate = releasedate;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @Override
    public String toString() {
        return "name : " + name + "\n" + "artist : " + artist + "\n"+ "releasedate : " + releasedate + "\n" + "imageURL : " + imageURL + "\n";
    }
}

