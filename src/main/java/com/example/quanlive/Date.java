package com.example.quanlive;

public class Date {
    private int year;
    private int month;
    private int day;

    //Nguyen Van Huy - method before, method after

    //method này sẽ nhận 1 string của người dùng rồi sau đó tách ra và set cho attributes day,month,year giá trị kiểu int.
    public Date(String input) {
        String[] date = input.split("/");
        day = Integer.parseInt(date[0]);
        month = Integer.parseInt(date[1]);
        String stringYear = "";
        year = Integer.parseInt(date[2]);
    }


    //method này nhận vào giá trị ngày ngày kết thúc và sẽ xét các trường hợp nếu ngày bán vé mà trước ngày kết thúc thì ta sẽ return true,
    //còn không sẽ set false cho các trường hợp còn lại
    public boolean before(Date endDate) {
        if(this.year > endDate.year){
            return false;
        }
        if(this.year == endDate.year){
            if(this.month > endDate.month ){
                return false;
            }
            if(this.day > endDate.day){
                return false;
            }
            return true;
        }
        return true;

    }

    //method này nhận vào giá trị ngày ngày bắt đầu và sẽ xét các trường hợp nếu ngày bán vé mà sau ngày ban đầu thì ta sẽ return true,
    //còn không sẽ set false cho các trường hợp còn lại
    public boolean after(Date startDate) {
        if(this.year < startDate.year){
            return false;
        }
        if(this.year == startDate.year){
            if(this.month < startDate.month ){
                return false;
            }
            if(this.day < startDate.day){
                return false;
            }
            return true;
        }
        return true;

    }
}

