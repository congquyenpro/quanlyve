package com.example.quanlive.search;


import com.example.quanlive.VeMayBay;

import java.util.ArrayList;

public class SearchByTime extends Searcher {
    public void SearchByTime(){

    };

    @Override
    public ArrayList<VeMayBay> search(String data, ArrayList<VeMayBay> ticketlist) {
        ArrayList<VeMayBay> output = new ArrayList<>();
        for (VeMayBay veMayBay : ticketlist) {
            if (veMayBay.getNgayGioBay().toLowerCase().trim().contains(data.toLowerCase().trim())) {
                output.add(veMayBay);
            }
        }
        return output;
    }

}