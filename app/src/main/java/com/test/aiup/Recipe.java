package com.test.aiup;

/**
 * Created by suvarna on 06-06-2018.
 */

public class Recipe {

    String sTitle;
    String sDescription;
    String sWrittenby;
    String sCity;
    int iThumbnail;
    int iNumOfLikes;

    public Recipe(String sTitle, String sDescription, String sWrittenby, String sCity, int iThumbnail, int iNumOfLikes) {
        this.sTitle = sTitle;
        this.sDescription = sDescription;
        this.sWrittenby = sWrittenby;
        this.sCity = sCity;
        this.iThumbnail = iThumbnail;
        this.iNumOfLikes = iNumOfLikes;
    }

    public String getsTitle() {
        return sTitle;
    }

    public void setsTitle(String sTitle) {
        this.sTitle = sTitle;
    }

    public String getsDescription() {
        return sDescription;
    }

    public void setsDescription(String sDescription) {
        this.sDescription = sDescription;
    }

    public String getsWrittenby() {
        return sWrittenby;
    }

    public void setsWrittenby(String sWrittenby) {
        this.sWrittenby = sWrittenby;
    }

    public String getsCity() {
        return sCity;
    }

    public void setsCity(String sCity) {
        this.sCity = sCity;
    }

    public int getiThumbnail() {
        return iThumbnail;
    }

    public void setiThumbnail(int iThumbnail) {
        this.iThumbnail = iThumbnail;
    }

    public int getiNumOfLikes() {
        return iNumOfLikes;
    }

    public void setiNumOfLikes(int iNumOfLikes) {
        this.iNumOfLikes = iNumOfLikes;
    }
}
