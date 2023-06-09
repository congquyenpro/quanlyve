

package com.example.quanlive.search;


import com.example.quanlive.VeMayBay;

import java.util.ArrayList;

public class SearchByPrice extends Searcher{
    public void SearchByPrice(){

    };

    @Override
    public ArrayList<VeMayBay> search(String data, ArrayList<VeMayBay> ticketlist) {
        ArrayList<VeMayBay> output = new ArrayList<>();
        for (VeMayBay veMayBay : ticketlist) {
//            if (veMayBay.getGiaVe().toLowerCase().trim().contains(data.toLowerCase().trim())) {
//                output.add(veMayBay);
//            }
            if(Double.parseDouble(veMayBay.getGiaVe()) > Double.parseDouble(data)) {
                output.add(veMayBay);
            }

        }
        return output;
    }
}
