package stations;

import java.io.Serializable;

/**
 * Created by Celia on 20/09/2014.
 */
public class Station implements Serializable {

    private String id;
    private String name;
    private String nbBikes;
    private String nbAttachs;
    private String address;
    private String lng;
    private String lat;

    public Station(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNbBikes() {
        return nbBikes;
    }

    public void setNbBikes(String nbBikes) {
        this.nbBikes = nbBikes;
    }

    public String getNbAttachs() {
        return nbAttachs;
    }

    public void setNbAttachs(String nbAttachs) {
        this.nbAttachs = nbAttachs;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    @Override
    public String toString() {
        return this.getName() + "\nvelos : "+this.getNbBikes()+" places : "+this.getNbAttachs();
    }
}