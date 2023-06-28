package org.eu.hanana.cirno.csgo.res;

public class ResLoc {
    public String domain,path;
    public ResLoc(String domain,String path){
        this.domain=domain;
        this.path=path;
    }
    public ResLoc(String path){
        this(path.split(":")[0],path.split(":")[1]);
    }
}
