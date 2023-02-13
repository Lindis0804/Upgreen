package com.ldh.upgreen.Model;

public class Place {
    private String _id = "";
    private String placeName = "";
    private String address = "";
    private String pic = "";

    public Place() {
    }

    ;

    public Place(String _id, String placeName, String address, String pic) {
        this._id = _id;
        this.placeName = placeName;
        this.address = address;
        this.pic = pic;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
