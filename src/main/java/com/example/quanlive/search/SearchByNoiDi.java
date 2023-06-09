package com.example.quanlive.search;


import com.example.quanlive.VeMayBay;

import java.util.ArrayList;

public class SearchByNoiDi extends Searcher {
    public void SearchByNoiDi(){

    };

    @Override
    public ArrayList<VeMayBay> search(String data, ArrayList<VeMayBay> ticketlist) {
        ArrayList<VeMayBay> output = new ArrayList<>();
        for (VeMayBay veMayBay : ticketlist) {
            if (veMayBay.getNoiDi().toLowerCase().trim().contains(data.toLowerCase().trim())) {
                output.add(veMayBay);
            }
        }
        return output;
    }

}