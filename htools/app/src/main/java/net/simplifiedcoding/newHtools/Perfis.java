package net.simplifiedcoding.newHtools;

/**
 * Created by jeferson on 27/05/18.
 */

public class Perfis {
    private String download;
    private String upload;
    private String uid;


public Perfis(){

}

    public String getDownload() {
        return download;
    }

    public void setDownload(String download) {
        this.download = download;
    }

    public String getUpload() {
        return upload;
    }

    public void setUpload(String upload) {
        this.upload = upload;
    }


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }


    @Override
    public String toString() {
        return download;
    }

}
