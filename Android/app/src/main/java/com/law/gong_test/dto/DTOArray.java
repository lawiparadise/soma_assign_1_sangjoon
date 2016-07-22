package com.law.gong_test.dto;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by GDGO on 2016-07-22.
 */
public class DTOArray implements Serializable {
    ArrayList<DTOAll> list;

    public ArrayList<DTOAll> getList() {
        return list;
    }

    public void setList(ArrayList<DTOAll> list) {
        this.list = list;
    }
}
