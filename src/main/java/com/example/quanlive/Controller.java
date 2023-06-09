package com.example.quanlive;


import com.example.quanlive.search.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private TableView<VeMayBay> table;

    @FXML
    private TableColumn<VeMayBay, String> tenNguoiMuaColumn;

    @FXML
    private TableColumn<VeMayBay, Integer> soCMTColumn;

    @FXML
    private TableColumn<VeMayBay, String> noiDiColumn;

    @FXML
    private TableColumn<VeMayBay, String> noiDenColumn;
    @FXML
    private TableColumn<VeMayBay, Double> quangDuongColumn;
    @FXML
    private TableColumn<VeMayBay, String> ngayGioBayColumn;
    @FXML
    private TableColumn<VeMayBay, String> ngayBanVeColumn;
    @FXML
    private TableColumn<VeMayBay, String> loaiVeColumn;
    @FXML
    private TableColumn<VeMayBay, String> giaVeColumn;

    private ObservableList<VeMayBay> VeMayBayList;

    @FXML
    private TextField tenNguoiMuaText;

    @FXML
    private TextField soCMTText;

    @FXML
    private TextField noiDiText;

    @FXML
    private TextField noiDenText;

    @FXML
    private TextField quangDuongText;

    @FXML
    private TextField ngayGioBayText;

    @FXML
    private TextField ngayBanVeText;

    @FXML
    private TextField loaiVeText;

    @FXML
    private ComboBox myComboBox;

    @FXML
    private ComboBox selectsearch;

    @FXML
    private TextField textSearch;

    @FXML
    private TextField textStart;

    @FXML
    private TextField textEnd;

    @FXML
    private Label showTotal;


    private TicketManager ticketManager = new TicketManager();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        VeMayBayList = FXCollections.observableArrayList(ticketManager.getPlaneTickets());

        tenNguoiMuaColumn.setCellValueFactory(new PropertyValueFactory<VeMayBay,String>("tenNguoiMua"));
        soCMTColumn.setCellValueFactory(new PropertyValueFactory<VeMayBay,Integer>("soCMT"));
        noiDiColumn.setCellValueFactory(new PropertyValueFactory<VeMayBay,String>("noiDi"));
        noiDenColumn.setCellValueFactory(new PropertyValueFactory<VeMayBay,String>("noiDen"));
        quangDuongColumn.setCellValueFactory(new PropertyValueFactory<VeMayBay,Double>("quangDuong"));
        ngayGioBayColumn.setCellValueFactory(new PropertyValueFactory<VeMayBay,String>("ngayGioBay"));
        ngayBanVeColumn.setCellValueFactory(new PropertyValueFactory<VeMayBay,String>("ngayBanVe"));
        loaiVeColumn.setCellValueFactory(new PropertyValueFactory<VeMayBay,String>("loaiVe"));
        giaVeColumn.setCellValueFactory(new PropertyValueFactory<VeMayBay,String>("giaVe"));

        table.setItems(VeMayBayList);
        myComboBox.getItems().add("Thương gia");
        myComboBox.getItems().add("Phổ thông");
        selectsearch.getItems().add("Theo tên");
        selectsearch.getItems().add("Theo nơi đến");
        selectsearch.getItems().add("Theo nơi đi");
        selectsearch.getItems().add("Theo ngày giờ bay");
        selectsearch.getItems().add("Vé tiền cao hơn nhập vào");


    }

    // addition function- Nguyen Cong Quyen
//    sau nhập input vào các field ta ấn nút add thì sẽ chạy method này.Ta tạo 1 object mới newVemayBay
//    mà có các giá trị truyền vào từ các field,rồi sau đó add object mới này vào mảng VeMayBayList để render ra giao diên,
//    rồi sau đó ta write mảng mới vào databse.
//    Khi call method add thì ta sẽ lấy ra được dữ liệu về loại vé sau đó truyền loại vé nhưu tham số vào method tinhTien
//    của object newVeMayBay từ đó ta cso dữu liệu về giá tiền của chuyến đi

    public void add(ActionEvent e) {
        VeMayBay newVeMayBay = new VeMayBay(tenNguoiMuaText.getText(), Integer.parseInt(soCMTText.getText()),
                noiDiText.getText(), noiDenText.getText(), Double.parseDouble(quangDuongText.getText()),
                ngayGioBayText.getText(), ngayBanVeText.getText(),
                myComboBox.getSelectionModel().getSelectedItem() + "");

        //kiem tra loai ve va tinh tien
        newVeMayBay.tinhTien(String.valueOf(myComboBox.getSelectionModel().getSelectedItem()));

        VeMayBayList.add(newVeMayBay);
        ticketManager.addPlaneTicket(newVeMayBay);
        ticketManager.write();
    }

    //delet function - Nguyen Cong Quyen
//    sau khi chọn một hàng muốn xoá ta ấn nút delete thì method delete sẽ execute.Ta bắt event kkhi ấn chọn hàng muốn xoá
//    sau đó ta dùng hàm remove rồi truyền parameter là hàm được chọn rồi dữ liệu hàng đó sẽ bị xoá

    public void delete (ActionEvent e){
        VeMayBay selected = table.getSelectionModel().getSelectedItem();
        VeMayBayList.remove(selected);
        ticketManager.deletePlaneTicket(selected);
        ticketManager.write();

    }

    //edit function - Nguyen Manh Kien
//    nhập input dữ liệu vào các field,khi ấn vào hàng muốn edit ta sẽ bắt event hàng selected rồi sau đó tìm index của
//    hàng đó.Khi ấn nút edit thì ta sẽ set các dữ liệu mới cho hàng đó và set nó lại theo index vào mảng.Sau cùng sẽ render ra dữ liệu mới
//    và cập nhật lại database

    public void edit(ActionEvent e){
        VeMayBay selected = table.getSelectionModel().getSelectedItem();


        int index = ticketManager.getPlaneTickets().indexOf(selected);
        VeMayBay oldVeMayBay = ticketManager.getPlaneTickets().get(index);

        oldVeMayBay.setTenNguoiMua(tenNguoiMuaText.getText());
        oldVeMayBay.setSoCMT(Integer.parseInt(soCMTText.getText())); //Chuyen tu String ve Int, vi kieu nhap vao la String
        oldVeMayBay.setNoiDi(noiDiText.getText());
        oldVeMayBay.setNoiDen(noiDenText.getText());
        oldVeMayBay.setQuangDuong(Double.parseDouble(quangDuongText.getText()));// Chuyen tu String ve Double
        oldVeMayBay.setNgayGioBay(ngayGioBayText.getText());
        oldVeMayBay.setNgayBanVe(ngayBanVeText.getText());
        oldVeMayBay.setLoaiVe(myComboBox.getSelectionModel().getSelectedItem()+"");
        oldVeMayBay.tinhTien(String.valueOf(myComboBox.getSelectionModel().getSelectedItem()));


        VeMayBayList.set(index, oldVeMayBay);
        ticketManager.write();
    }

    // Các hàm thao tác với giao diện cũng cần tham chiếu lại cho ticketmanager để
    // xử lý ví dụ như đã sửa ở hàm add.

    /**
     * Khi bấm nút search chọn searcher thì phải setSearcher cho TicketManager
     */
    //khi chọn tìm kiếm theo cách nào ta sẽ bắt event rồi lấy ra text từ đó ra,rồi ta sẽ xét trường hợp
    //xem sẽ dùng hàm search nào để áp ứng yêu cầu người dùng.Ta sẽ dùng method setSearcher ở TicketManager để xét attribute search
    //bằng class search cụ thể
    public void typeSearch(ActionEvent e){
        if(selectsearch.getValue() == "Theo tên"){
                ticketManager.setSearcher(new SearchByName());
        }
        if(selectsearch.getValue() == "Theo nơi đi"){
            ticketManager.setSearcher(new SearchByNoiDi());
        }
        if(selectsearch.getValue() == "Theo nơi đến"){
            ticketManager.setSearcher(new SearchByNoiDen());
        }
        if(selectsearch.getValue() == "Theo ngày giờ bay"){
            ticketManager.setSearcher(new SearchByTime());
        }
        if(selectsearch.getValue() == "Vé tiền cao hơn nhập vào"){
            ticketManager.setSearcher(new SearchByPrice());
        }
    }

    /**
     * Khi search cụ thể thì dùng hàm search của TicketManager
     */
    //khi ấn vào nút search thì method này sẽ được thực thi,từ TicketManager ta gọi đến method search và truyền tham số
    //là dữ liêu cần tìm và sau đó method search sẽ trả về 1 mảng chứa những thông tin cần thiết và render dữ liệu cần tìm ra màn hình
    public void searching(ActionEvent e){
        table.setItems(FXCollections.observableArrayList(ticketManager.search(textSearch.getText())));
    }

    //Sau khi lọc để tìm các hàng có thoogn tin theo yêu cầu,giwof ta muốn quay lại bẳng dữ liệu ban đầu thì ta ấn nút getAll sẽ gọi đến method này
    //sau đó set lại dữ liệu cho bảng bằng mảng ban đầu chưa tất cả dữ liệu rồi sau đó render ra giao diện
    public void getAllInfor(ActionEvent e){
        table.setItems(VeMayBayList);
    }

    //khi ấn nút tính tổng doanh thu thì method này sẽ thực thi,ta sẽ lấy dữ liệu từ input người dùng và truyền vào tạo 2 object từ class Date
    //rồi chueyeenf vào method computePrice của class TicketManager nhưu 2 tham số.Sau cùng ta sẽ nhân được dữ liệu trả về là giá tiền cần tìm
    //rồi sau đó render dữ liệu đó
    public void computePrice(ActionEvent e){
        String cost = String.format("%.1f", ticketManager.computePrice(new Date(textStart.getText()), new Date(textEnd.getText())));
        showTotal.setText(cost);
    }


}
