package com.davila.taximetrochile;

/**
 * Created by danielavila on 29-03-16.
 */
public class Record {

    private int _id;
    private String _to;
    private String _from;
    private int _fee;

    public Record(){
    }

    public Record(String to, String from, int fee) {
        this._to = to;
        this._from = from;
        this._fee = fee;
    }

    //Seters
    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_to(String _to) {
        this._to = _to;
    }

    public void set_from(String _from) {
        this._from = _from;
    }

    public void set_fee(int _fee) {
        this._fee = _fee;
    }

    //Getters
    public String get_to() {
        return _to;
    }

    public int get_id() {
        return _id;
    }

    public String get_from() {
        return _from;
    }

    public int get_fee() {
        return _fee;
    }

}
