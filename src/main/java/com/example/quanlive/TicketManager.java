package com.example.quanlive;
import com.example.quanlive.search.Searcher;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.io.File;

//Nguyen Van Huy, Nguyen Cong Quyen
public class TicketManager {
    private ArrayList<VeMayBay> planeTickets = new ArrayList<>();
    private Searcher searcher;
    //method này để đọc dữ liêu từ database rồi lưu vào mảng rồi render ra đữu liệu
    TicketManager() {
        File file = new File("src\\main\\resources\\com\\example\\quanlive\\database.txt");
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");
                planeTickets.add(new VeMayBay(data[0], Integer.parseInt(data[1]), data[2], data[3], Double.parseDouble(data[4]), data[5], data[6], data[7]));
            }
            scanner.close();
        } catch(Exception e) {
            System.out.println(e);
        }

    }
    //method này để ghi dữ liệu từ ngwuofi dùng nhập vào rồi lưu vào database
    public void write() {

        Charset charset = Charset.forName("utf-8");
        try (BufferedWriter bw = Files
                .newBufferedWriter(Paths.get("src\\main\\resources\\com\\example\\quanlive\\database.txt"), charset)) {
            Iterator<VeMayBay> it = planeTickets.iterator();
            while (it.hasNext()) {
                String string = it.next().toString();
                bw.append(string);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    // Getter method for plane tickets,khi method này dc gọi nó sẽ trả về mảng dữ liệu lưu trữ trong database
    public ArrayList<VeMayBay> getPlaneTickets() {
        return planeTickets;
    }

    /**
     * Add plane ticket.
     * @param planeTicket vé mới
     */
    public void addPlaneTicket(VeMayBay planeTicket) {
        planeTickets.add(planeTicket);
    }

    /**
     * delete plane ticket.
     * @param planeTicket vé mới
     */
    public void deletePlaneTicket(VeMayBay planeTicket) {
        planeTickets.remove(planeTicket);
    }

    /**
     * Setter method for searcher.
     */
    //method này được thực thi khi gọi,nó nhận một object nhưu 1 parameter rồi gán cho attribute searchẻ giá trị nó nhận được
    //hàm này thực thi với mục đích sẽ tìm kiếm theo kiểu cảu class nào theo mong muốn của người dùng
    public void setSearcher(Searcher searcher) {
        this.searcher = searcher;
    }


    /**
     * Search by specific field.
     */
    //sau khi xác định sẽ tìm kiếm theo cách nào rồi sau đó gọi method này để tìm kiếm theo cách đó và method này sẽ return về một mảng
    //mà người dùng muốn tìm và sau đó render ra màn hình.Từ searcher ta sẽ gọi đến method search của class searcher đó,ta sẽ truyền 2 tham số
    //parameter đàu tiên là input người dùng nhập vào muốn tìm kiếm,parameter thứ 2 là mảng mà ta đọc từ database ra.
    public ArrayList<VeMayBay> search(String data) {
        return searcher.search(data, planeTickets);
    }

    //method này sẽ nhận 2 parameter là 2 object từ class Date là startDay và endDay.Để tính tổng tiền trong 1 khoảng thời gian,
    //ta duyệt từng phần tử trong mảng từu database rồi dùng method before và after trong class Date để làm điều kiên,nếu thoả mãn ta
    //sẽ lấy ra giá tiền của vé đó rồi cộng lại vào biến sumPrice,cuối cùng return sumPrice là tổng giá tiền vé trong 1 khoảng time mà người dùng nhập
    public double computePrice(Date startDay, Date endDay) {
        double sumPrice = 0;
        for (VeMayBay ticket : planeTickets) {
            Date ticketDate = new Date(ticket.getNgayBanVe());
            if (ticketDate.before(endDay) && ticketDate.after(startDay)) {
                sumPrice += Double.parseDouble(ticket.getGiaVe());
            }
        }
        return sumPrice;
    }

}
