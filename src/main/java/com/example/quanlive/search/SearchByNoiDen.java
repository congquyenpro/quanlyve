package com.example.quanlive.search;


import com.example.quanlive.VeMayBay;

import java.util.ArrayList;

public class SearchByNoiDen extends Searcher {
    public void SearchByNoiDen(){

    };

    @Override
    public ArrayList<VeMayBay> search(String data, ArrayList<VeMayBay> ticketlist) {
        ArrayList<VeMayBay> output = new ArrayList<>();
        for (VeMayBay veMayBay : ticketlist) {
            if (veMayBay.getNoiDen().toLowerCase().trim().contains(data.toLowerCase().trim())) {
                output.add(veMayBay);
            }
        }
        return output;
    }

}