/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdminGUI;

/**
 *
 * @author lenovo-x220i
 */
import BUS.CTHDBUS;
import BUS.HoaDonBUS;
import DTO.CTHDDTO;
import DTO.HoaDonDTO;
import SupportGUI.SupportHoaDon;
//import GUI.DuyetGUI.SuaHDGUI;
//import GUI.DuyetGUI.ThemHDGUI;
//import Report.ReportPDF;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import java.io.IOException;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import static java.awt.Frame.HAND_CURSOR;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.event.MouseInputListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class HoaDonGUI extends JPanel {

    int choice = 0;

    public static DefaultTableModel modelHD, modelCTHD;
    JLabel lbmahd, lbmakh, lbmanv, lbngaymua, lbtongtien, lbtimkiem, lbsoluong, lbdongia, lbmasp, lbmakm, lbthanhtien, lbmacthd;
    JTextField txmahd, txmakh, txmanv, txngaymua, txtongtien, txtimkiem, txsoluong, txdongia, txmasp, txmakm, txthanhtien, txmacthd;
    JButton them, xoa, sua, reset, timkiem, chonmakh, chonmanv, chonmasp, chonmakm, next, preview, thunho, exit, inan;
    public static JTable tableHD, tableCTHD;
    //JTable tableHD, tableCTHD;
    JLabel lbhd, lbcthd, lbtop, lbexit, lbinfopbh;
    JComboBox cb;
    JPanel hd, cthd;
    Font font = new Font("Segoe UI", Font.BOLD, 18);
    Border border = BorderFactory.createLineBorder(new Color(33, 33, 33));
    //Border border = BorderFactory.createLineBorder(Color.RED);
    Border bordernull = BorderFactory.createEmptyBorder();
    int clickxoa = -1;//Nếu clickxoa == 0 xóa là xóa hóa đơn còn nếu bằng 1 là xóa CTHD bằng -1 là chưa chọn dòng để xóa 

    JButton back, tkncoff, tknc;
    JLabel lbtknc, muiten, muiten1, muiten2;
    JTextField txgiatu, txgiaden, txsltu, txslden, txngaytu, txngayden;
    Font fonttknc = new Font("Segoe UI", Font.PLAIN, 15);
    Border borderinput = BorderFactory.createLineBorder(new Color(30, 210, 96), 4);
    //Border borderinput = BorderFactory.createLineBorder(Color.red);

    public HoaDonGUI() {

        this.setSize(1350, 945);
        this.setLayout(null);
        this.setBorder(border);
        this.setBackground(new Color(237,241,255));

        ImageIcon hinhnutxuong = new ImageIcon(getClass().getResource("/HinhAnh/nutxuong.png"));
        ImageIcon hinhnutxuongf = new ImageIcon(getClass().getResource("/HinhAnh/nutxuongf.png"));
        ImageIcon hinhnutlen = new ImageIcon(getClass().getResource("/HinhAnh/nutlen.png"));
        ImageIcon hinhnutlenf = new ImageIcon(getClass().getResource("/HinhAnh/nutlenf.png"));
        ImageIcon hinhback = new ImageIcon(getClass().getResource("/HinhAnh/back.png"));
        ImageIcon hinhbackf = new ImageIcon(getClass().getResource("/HinhAnh/backf.png"));

        lbtop = new JLabel();
        lbtop.setBounds(0, 0, 1350, 65);
        lbtop.setBackground(new Color(18, 18, 18));
        lbtop.setOpaque(true);

        back = new JButton();
        back.setBounds(25, 25, 20, 20);
        back.setIcon(hinhback);
        back.setFocusPainted(false);
        back.setContentAreaFilled(false);
        back.setBorder(bordernull);
        back.setBackground(new Color(33, 33, 33));
       
       
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                back.setIcon(hinhbackf);
                inan.setEnabled(false);
                txtimkiem.setText("");
                while (modelHD.getRowCount() > 0) {
                    modelHD.removeRow(0);
                }
                for (HoaDonDTO hd : HoaDonBUS.dshd) {
                    Vector row = new Vector();
                    row.add(hd.getMahd());
                     row.add(hd.getMakm());
                    row.add(hd.getManv());
                    row.add(hd.getMakh());
                    row.add(hd.getNgaylaphd());
                    row.add(hd.getTongtien());
                    row.add(hd.getTienkm());
                    row.add(hd.getThuctra());
                   

                    //row.add(Chuyentien(String.valueOf(hd.getTongtien())));
                    // row.add(Chuyentien(String.valueOf(hd.getThanhtien())));
                    modelHD.addRow(row);
                }
                tableHD.setModel(modelHD);
            }
        });
        lbtop.add(back);
//
//        lbtknc = new JLabel();
//        lbtknc.setBounds(340, 65, 450, 215);
//        lbtknc.setBackground(new Color(100, 100, 100));
//        lbtknc.setOpaque(true);
//        this.add(lbtknc);

//        txgiatu = new JTextField();
//        txgiatu.setText("Giá từ...");
//        txgiatu.setFont(fonttknc);
//        txgiatu.setBounds(20, 20, 150, 40);
//        txgiatu.setBorder(border);
//        txgiatu.addFocusListener(new FocusListener() {
//            @Override
//            public void focusGained(FocusEvent e) {
//                txgiatu.setBorder(borderinput);
//                if (txgiatu.getText().equals("Giá từ...")) {
//                    txgiatu.setText("");
//                }
//            }
//
//            @Override
//            public void focusLost(FocusEvent e) {
//                txgiatu.setBorder(border);
//                if (txgiatu.getText().equals("")) {
//                    txgiatu.setText("Giá từ...");
//                }
//            }
//        });

//        txgiaden = new JTextField();
//        txgiaden.setText("Giá đến...");
//        txgiaden.setFont(fonttknc);
//        txgiaden.setBounds(220, 20, 150, 40);
//        txgiaden.setBorder(border);
//        txgiaden.addFocusListener(new FocusListener() {
//            @Override
//            public void focusGained(FocusEvent e) {
//                txgiaden.setBorder(borderinput);
//                if (txgiaden.getText().equals("Giá đến...")) {
//                    txgiaden.setText("");
//                }
//            }
//
//            @Override
//            public void focusLost(FocusEvent e) {
//                txgiaden.setBorder(border);
//                if (txgiaden.getText().equals("")) {
//                    txgiaden.setText("Giá đến...");
//                }
//            }
//        });

//        ImageIcon hinhmuiten = new ImageIcon(getClass().getResource("/HinhAnh/muiten.png"));
//        muiten = new JLabel();
//        muiten.setIcon(hinhmuiten);
//        muiten.setFont(fonttknc);
//        muiten.setBounds(180, 25, 40, 30);
//        muiten.setBorder(bordernull);
//        muiten.setFocusTraversalKeysEnabled(false);
//        muiten.setForeground(new Color(255, 255, 255));

//        lbtknc.add(txgiatu);
//        lbtknc.add(muiten);
//        lbtknc.add(txgiaden);
//
//        txsltu = new JTextField();
//        txsltu.setText("Số Lượng từ...");
//        txsltu.setFont(fonttknc);
//        txsltu.setBounds(20, 85, 150, 40);
//        txsltu.setBorder(border);
//        txsltu.addFocusListener(new FocusListener() {
//            @Override
//            public void focusGained(FocusEvent e) {
//                txsltu.setBorder(borderinput);
//                if (txsltu.getText().equals("Số Lượng từ...")) {
//                    txsltu.setText("");
//                }
//            }
//
//            @Override
//            public void focusLost(FocusEvent e) {
//                txsltu.setBorder(border);
//                if (txsltu.getText().equals("")) {
//                    txsltu.setText("Số Lượng từ...");
//                }
//            }
//        });

//        txslden = new JTextField();
//        txslden.setText("Số Lượng đến...");
//        txslden.setFont(fonttknc);
//        txslden.setBounds(220, 85, 150, 40);
//        txslden.setBorder(border);
//        txslden.addFocusListener(new FocusListener() {
//            @Override
//            public void focusGained(FocusEvent e) {
//                txslden.setBorder(borderinput);
//                if (txslden.getText().equals("Số Lượng đến...")) {
//                    txslden.setText("");
//                }
//            }
//
//            @Override
//            public void focusLost(FocusEvent e) {
//                txslden.setBorder(border);
//                if (txslden.getText().equals("")) {
//                    txslden.setText("Số Lượng đến...");
//                }
//            }
//        });
//
        ImageIcon hinhmuiten1 = new ImageIcon(getClass().getResource("/HinhAnh/muiten1.png"));
        muiten1 = new JLabel();
        muiten1.setIcon(hinhmuiten1);
        muiten1.setFont(fonttknc);
        muiten1.setBounds(180, 90, 40, 30);
        muiten1.setBorder(bordernull);
        muiten1.setFocusTraversalKeysEnabled(false);
        muiten1.setForeground(new Color(255, 255, 255));

//        lbtknc.add(txsltu);
//        lbtknc.add(muiten1);
//        lbtknc.add(txslden);
//
//        txngaytu = new JTextField();
//        txngaytu.setText("Ngày từ...");
//        txngaytu.setFont(fonttknc);
//        txngaytu.setBounds(20, 145, 150, 40);
//        txngaytu.setBorder(border);
//        txngaytu.addFocusListener(new FocusListener() {
//            @Override
//            public void focusGained(FocusEvent e) {
//                txngaytu.setBorder(borderinput);
//                if (txngaytu.getText().equals("Ngày từ...")) {
//                    txngaytu.setText("");
//                }
//            }
//
//            @Override
//            public void focusLost(FocusEvent e) {
//                txngaytu.setBorder(border);
//                if (txngaytu.getText().equals("")) {
//                    txngaytu.setText("Ngày từ...");
//                }
//            }
//        });
//        DatePickerSettings pickerSettings = new DatePickerSettings();
//        pickerSettings.setColor(DatePickerSettings.DateArea.TextClearLabel, new Color(30, 210, 96));
//        pickerSettings.setColor(DatePickerSettings.DateArea.CalendarBackgroundNormalDates, new Color(255, 255, 255));
//        pickerSettings.setColor(DatePickerSettings.DateArea.BackgroundMonthAndYearNavigationButtons, new Color(30, 210, 96));
//        pickerSettings.setColor(DatePickerSettings.DateArea.TextTodayLabel, new Color(249, 6, 59));
//
//        pickerSettings.setSizeDatePanelMinimumHeight(300);
//        pickerSettings.setSizeDatePanelMinimumWidth(300);
//        pickerSettings.setVisibleDateTextField(false);
//        DatePicker dPicker1 = new DatePicker(pickerSettings);
//        dPicker1.setBounds(20, 185, 30, 30);
//        dPicker1.setBackground(new Color(100, 100, 100));
//        dPicker1.setCursor(new Cursor(HAND_CURSOR));
//        dPicker1.addDateChangeListener((dce) -> {
//            txngaytu.setText(dPicker1.getDateStringOrEmptyString());
//        });

//        txngayden = new JTextField();
//        txngayden.setText("Ngày đến...");
//        txngayden.setFont(fonttknc);
//        txngayden.setBounds(220, 145, 150, 40);
//        txngayden.setBorder(border);
//        txngayden.addFocusListener(new FocusListener() {
//            @Override
//            public void focusGained(FocusEvent e) {
//                txngayden.setBorder(borderinput);
//                if (txngayden.getText().equals("Ngày đến...")) {
//                    txngayden.setText("");
//                }
//            }
//
//            @Override
//            public void focusLost(FocusEvent e) {
//                txngayden.setBorder(border);
//                if (txngayden.getText().equals("")) {
//                    txngayden.setText("Ngày đến...");
//                }
//            }
//        });
//
//        DatePickerSettings pickerSettings1 = new DatePickerSettings();
//        pickerSettings1.setColor(DatePickerSettings.DateArea.TextClearLabel, new Color(30, 210, 96));
//        pickerSettings1.setColor(DatePickerSettings.DateArea.CalendarBackgroundNormalDates, new Color(255, 255, 255));
//        pickerSettings1.setColor(DatePickerSettings.DateArea.BackgroundMonthAndYearNavigationButtons, new Color(30, 210, 96));
//        pickerSettings1.setColor(DatePickerSettings.DateArea.TextTodayLabel, new Color(249, 6, 59));
//
//        pickerSettings1.setSizeDatePanelMinimumHeight(300);
//        pickerSettings1.setSizeDatePanelMinimumWidth(300);
//        pickerSettings1.setVisibleDateTextField(false);
//        DatePicker dPicker2 = new DatePicker(pickerSettings1);
//        dPicker2.setBounds(220, 185, 30, 30);
//        dPicker2.setBackground(new Color(100, 100, 100));
//        dPicker2.setCursor(new Cursor(HAND_CURSOR));
//        dPicker2.addDateChangeListener((dce) -> {
//            txngayden.setText(dPicker2.getDateStringOrEmptyString());
//        });

        ImageIcon hinhmuiten2 = new ImageIcon(getClass().getResource("/HinhAnh/muiten1.png"));
        muiten2 = new JLabel();
        muiten2.setIcon(hinhmuiten1);
        muiten2.setFont(fonttknc);
        muiten2.setBounds(180, 150, 40, 30);
        muiten2.setBorder(bordernull);
        muiten2.setFocusTraversalKeysEnabled(false);
        muiten2.setForeground(new Color(255, 255, 255));

//        lbtknc.add(txngaytu);
//        lbtknc.add(dPicker1);
//        lbtknc.add(muiten2);
//        lbtknc.add(txngayden);
//        lbtknc.add(dPicker2);

//        ImageIcon hinhtimkiem = new ImageIcon(getClass().getResource("/HinhAnh/timkiem.png"));
//        timkiem = new JButton();
//        timkiem.setBounds(380, 0, 70, 215);
//        timkiem.setBackground(new Color(30, 210, 96));
//        timkiem.setIcon(hinhtimkiem);
//        timkiem.setFont(new Font("Segoe UI", Font.BOLD, 20));
//        timkiem.setFocusPainted(false);
//        timkiem.setCursor(new Cursor(HAND_CURSOR));
//        timkiem.setBorder(bordernull);
//        timkiem.addMouseListener(new MouseListener() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                try {
//                    timkiemnangcao();
//                } catch (ParseException ex) {
//                    Logger.getLogger(HoaDonGUI.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//
//            @Override
//            public void mousePressed(MouseEvent e) {
//
//            }
//
//            @Override
//            public void mouseReleased(MouseEvent e) {
//
//            }
//
//            @Override
//            public void mouseEntered(MouseEvent e) {
//                timkiem.setBackground(new Color(249, 6, 59));
//            }
//
//            @Override
//            public void mouseExited(MouseEvent e) {
//                timkiem.setBackground(new Color(30, 210, 96));
//            }
//        });

//        lbtknc.add(timkiem);
//        lbtknc.setVisible(false);

        tknc = new JButton();
        tknc.setBounds(490, 18, 30, 30);
        tknc.setIcon(hinhnutxuong);
        tknc.setBorder(bordernull);
        tknc.setFocusTraversalKeysEnabled(false);
        tknc.setBackground(new Color(33, 33, 33));
        tknc.addMouseListener(new MouseListener() {
            // SẢN PHẨM GUI
            @Override
            public void mouseClicked(MouseEvent e) {
                lbtknc.setVisible(true);
                tknc.setVisible(false);
                tkncoff.setVisible(true);
                
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

        });

        tkncoff = new JButton();
        tkncoff.setBounds(490, 18, 30, 30);
        tkncoff.setIcon(hinhnutlenf);
        tkncoff.setBorder(bordernull);
        tkncoff.setFocusTraversalKeysEnabled(false);
        tkncoff.setBackground(new Color(33, 33, 33));
        tkncoff.setVisible(false);
        tkncoff.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                lbtknc.setVisible(false);
                tknc.setVisible(true);
                tkncoff.setVisible(false);
                
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

        });

//        lbtop.add(tkncoff);
//        lbtop.add(tknc);

        txtimkiem = new JTextField(18);
        txtimkiem.setFont(font);
        txtimkiem.setBounds(70, 18, 250, 32);
        txtimkiem.setBorder(border);
       // String choose[] = {"Theo Mã HĐ", "Theo Mã KH", "Theo Mã NV", "Theo Mã KM", "Theo Ngày", "Theo SL Tổng", "Theo T.Tiền", "Theo Th.Tiền"};
       String choose[] = {"Theo Mã HĐ","Theo Mã KM", "Theo Mã NV", "Theo Mã KH"};
        cb = new JComboBox(choose);
        cb.setBounds(340, 18, 130, 32);
        cb.setFont(new Font("Segoe UI", Font.BOLD, 16));
        cb.setBorder(border);
        cb.setBackground(new Color(255, 255, 255));

        lbexit = new JLabel();
        lbexit.setBackground(new Color(33, 33, 33));
        lbexit.setBounds(1170, 0, 180, 35);

        ImageIcon hinhexit = new ImageIcon(getClass().getResource("/HinhAnh/thoat.png"));
        exit = new JButton();
        exit.setBounds(120, 0, 60, 35);
        exit.setBackground(new Color(30, 30, 30));
        exit.setIcon(hinhexit);
        exit.setBorder(bordernull);
        exit.setFocusPainted(false);
        exit.setHorizontalTextPosition(SwingConstants.LEFT);
        exit.addMouseListener(new MouseListener() {
            // SẢN PHẨM GUI
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                exit.setBackground(Color.red);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                exit.setBackground(new Color(30, 30, 30));
            }

        });
 
   
    
        lbexit.add(exit);
        lbtop.add(lbexit);
        lbtop.add(cb);
        lbtop.add(txtimkiem);

        // Hóa đơn
        hd = new JPanel();
        hd.setLayout(null);
       // hd.setBounds(100, 100, 1020, 360);
        hd.setBounds(30, 100, 900, 360);
        hd.setBackground(new Color(237,241,255));
       //hd.setBackground(Color.RED);
        TitledBorder TTborder1 = new TitledBorder("Hóa Đơn");
        TTborder1.setTitleJustification(TitledBorder.LEFT);
        TTborder1.setTitlePosition(TitledBorder.TOP);
        TTborder1.setTitleColor(new Color(33,33,33));
        //TTborder1.setTitleColor(Color.red);
        TTborder1.setTitleFont(font);
        hd.setBorder(TTborder1);

        Vector header = new Vector();
        header.add("Mã HĐ");
        header.add("Mã KM");
        header.add("Mã NV");
        header.add("Mã KH");
        header.add("Ngày Lập");
        header.add("Tổng tiền");
        header.add("Tiền KM");
        header.add("Thực trả");


        modelHD = new DefaultTableModel(header, 0);
        tableHD = new JTable();
        tableHD.setModel(modelHD);
        tableHD.setFillsViewportHeight(true);
        
        tableHD.setBorder(border);
        tableHD.setFillsViewportHeight(true);
        
        tableHD.setBackground(new Color(255, 255, 255));
        tableHD.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        tableHD.getColumnModel().getColumn(0).setPreferredWidth(20);
        tableHD.getColumnModel().getColumn(1).setPreferredWidth(40);
        tableHD.getColumnModel().getColumn(2).setPreferredWidth(40);
        tableHD.getColumnModel().getColumn(3).setPreferredWidth(50);
        tableHD.getColumnModel().getColumn(4).setPreferredWidth(60);
        tableHD.getColumnModel().getColumn(5).setPreferredWidth(20);
        tableHD.getColumnModel().getColumn(6).setPreferredWidth(20);
        tableHD.getColumnModel().getColumn(7).setPreferredWidth(10);

        tableHD.getTableHeader().setForeground(new Color(255, 255, 255));
        tableHD.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 20));
        tableHD.getTableHeader().setBackground(new Color(31,73,91));
        tableHD.getTableHeader().setBorder(border);
        tableHD.setRowHeight(30);

        JScrollPane bangHD = new JScrollPane(tableHD);
        //bangHD.setBounds(10, 30, 1000, 320);
        bangHD.setBounds(10, 30, 880, 320);
        hd.add(bangHD);

        //Đọc DSHD
        HoaDonBUS bushd = new HoaDonBUS();
        if (HoaDonBUS.dshd == null) {
            bushd.docDSHD();
        }
        for (HoaDonDTO hd : HoaDonBUS.dshd) {
            Vector row = new Vector();
            row.add(hd.getMahd());
             row.add(hd.getMakm());
            row.add(hd.getManv());
            row.add(hd.getMakh());
            row.add(hd.getNgaylaphd());
            row.add(hd.getTongtien());
            row.add(hd.getTienkm());
            row.add(hd.getThuctra());
           
            //row.add(Chuyentien(String.valueOf(hd.getTongtien())));
            //row.add(Chuyentien(String.valueOf(hd.getThanhtien())));
            modelHD.addRow(row);
        }

        //Chi tiết HĐ    
        cthd = new JPanel();
        cthd.setLayout(null);
       // cthd.setBounds(100, 500, 620, 330);
        cthd.setBounds(30, 470, 620, 200);
       cthd.setBackground(new Color(237,241,255));
       
        TitledBorder TTborder2 = new TitledBorder("Chi Tiết Hóa Đơn");
        TTborder2.setTitleJustification(TitledBorder.LEFT);
        TTborder2.setTitlePosition(TitledBorder.TOP);
        TTborder2.setTitleColor(new Color(33,33,33));
        TTborder2.setTitleFont(font);
        cthd.setBorder(TTborder2);
        
        header = new Vector();
        header.add("Mã HĐ");
        header.add("Mã SP");
        header.add("Số Lượng");
        header.add("Thành tiền");
        header.add("Tiền KM");
        

        modelCTHD = new DefaultTableModel(header, 0);
        tableCTHD = new JTable();
        tableCTHD.setModel(modelCTHD);
        tableCTHD.setFillsViewportHeight(true);
        tableCTHD.setBorder(border);
        tableCTHD.setFillsViewportHeight(true);
        tableCTHD.setBackground(new Color(255, 255, 255));
        tableCTHD.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        tableCTHD.getTableHeader().setForeground(new Color(255, 255, 255));
        tableCTHD.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 20));
        tableCTHD.getTableHeader().setBackground(new Color(31,73,91));
        tableCTHD.getTableHeader().setBorder(border);
        tableCTHD.setRowHeight(30);

        JScrollPane bangCTHD = new JScrollPane(tableCTHD);
        //bangCTHD.setBounds(10, 30, 600, 290);
        bangCTHD.setBounds(10, 30, 600, 150);
        cthd.add(bangCTHD);

        //SỰ KIỆN CỦA 2 BẢNG
        tableHD.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                modelCTHD.setRowCount(0);   // Dùng để clear Table CTHD
                tableCTHD.setModel(modelCTHD);
                int i = tableHD.getSelectedRow();
                if (i >= 0) 
                {
                    inan.setEnabled(true);
                    String ma = tableHD.getValueAt(i, 0).toString();    //Lấy mã hd
                    clickxoa = 0; // Xóa HĐ
                    CTHDBUS busct = new CTHDBUS();
                    busct.docCTHDtheoHD(ma);
                    for (CTHDDTO cthd : CTHDBUS.dscthd) // Với mỗi CTHD nằm trong dscthd của BUS
                    {
                        Vector row = new Vector();
                        row.add(cthd.getMahd());
                        row.add(cthd.getMasach());
                        row.add(cthd.getSoluong());
                        row.add(cthd.getThanhtien());
                        row.add(cthd.getTienkm());
                        //row.add(Chuyentien(String.valueOf(cthd.getDongia())));
                        modelCTHD.addRow(row);
                    }
                    tableCTHD.setModel(modelCTHD);
                }

            }

            //Lệnh xàm
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                //int i = tableHD.getSelectedRow();
                JTable table = (JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    JOptionPane.showMessageDialog(null, "Đừng có double click vô Table chứ :))");
                }
            }
        });
        
        tableCTHD.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tableHD.clearSelection();
                clickxoa = 1;
            }

            //Lệnh xàm
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                //int i = tableHD.getSelectedRow();
                JTable table = (JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    JOptionPane.showMessageDialog(null, "Đừng có double click vô Table chứ :))");
                }
            }

        });

        // button
        ImageIcon hinhthem = new ImageIcon(getClass().getResource("/HinhAnh/them.png"));
        ImageIcon hinhthem1 = new ImageIcon(getClass().getResource("/HinhAnh/them1.png"));
        them = new JButton();
        them.setEnabled(false);
        them.setBounds(680, 480, 130, 50);
        them.setBackground(new Color(237,241,255));
        them.setBorder(bordernull);
        them.setIcon(hinhthem);
        them.setFocusPainted(false);
        them.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                ThemHDGUI test = new ThemHDGUI();
//                test.setView("Manager");
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                them.setIcon(hinhthem1);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                them.setIcon(hinhthem);
            }

        });

        ImageIcon hinhxoa = new ImageIcon(getClass().getResource("/HinhAnh/xoa.png"));
        ImageIcon hinhxoa1 = new ImageIcon(getClass().getResource("/HinhAnh/xoa1.png"));
        xoa = new JButton();
        //them.setBounds(680, 480, 130, 50);
        xoa.setBounds(680, 540, 130, 50);
        xoa.setBackground(new Color(237,241,255));
        xoa.setBorder(bordernull);
        xoa.setIcon(hinhxoa);
        xoa.setFocusPainted(false);
        xoa.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (clickxoa == -1) 
                {
                    JOptionPane.showMessageDialog(null, "Chọn hóa đơn hoặc chi tiết hóa đơn để xóa");
                } else 
                    if (clickxoa == 0) 
                    {
                    int i = tableHD.getSelectedRow();
                    int output = JOptionPane.showConfirmDialog(null, "Có chắc muốn xóa hóa đơn này", "XÓA", JOptionPane.YES_NO_OPTION);
                    if (output == JOptionPane.YES_OPTION) {
                        HoaDonBUS bushd = new HoaDonBUS();
                        if (HoaDonBUS.dshd == null) 
                        {
                            bushd.docDSHD();
                        }
                        //Khởi tạo giá trị HĐ
                        HoaDonDTO hd = new HoaDonDTO();
                        hd.setMahd((String) modelHD.getValueAt(i, 0));
                        hd.setNgaylaphd((String) modelHD.getValueAt(i, 4));
                        //Integer.valueOf(modelCTHD.getValueAt(j, 3).toString())
                        hd.setTongtien(Integer.valueOf(modelHD.getValueAt(i, 5).toString()));
                        hd.setTienkm(Integer.valueOf(modelHD.getValueAt(i, 6).toString()));
                        hd.setThuctra(Integer.valueOf(modelHD.getValueAt(i, 7).toString()));
                        //hd.setSoluongtong(Integer.valueOf(modelHD.getValueAt(i, 5).toString()));
//                        try {
//                            hd.setTongtien(Integer.valueOf(Chuyenint(modelHD.getValueAt(i, 5).toString())));
//                        } catch (ParseException ex) {
//                            Logger.getLogger(HoaDonGUI.class.getName()).log(Level.SEVERE, null, ex);
//                        }
//                        try {
//                            hd.setTongtien(Integer.valueOf(Chuyenint(modelHD.getValueAt(i, 7).toString())));
//                        } catch (ParseException ex) {
//                            Logger.getLogger(HoaDonGUI.class.getName()).log(Level.SEVERE, null, ex);
//                        }
                        //Xóa HĐ ( Đồng thời xóa toàn bộ CTHĐ liên quan trong BUS và DAO)
                        bushd.XoaHD(hd);
                        //Xóa HĐ GUI
                        modelHD.removeRow(i);
                        tableHD.setModel(modelHD);
                        //Xóa CTHD GUI
                        modelCTHD.setRowCount(0);
                        tableCTHD.setModel(modelCTHD);
                    }
                }
                    else if (clickxoa == 1) // xóa chi tiết hóa đơn
                {
                    int j = tableCTHD.getSelectedRow();
                    int output = JOptionPane.showConfirmDialog(null, "Có chắc muốn xóa CT hóa đơn này", "XÓA", JOptionPane.YES_NO_OPTION);
                    if (output == JOptionPane.YES_OPTION) {
                        CTHDBUS buscthd = new CTHDBUS();  // thêm mới 
                        if (CTHDBUS.dscthd == null) 
                        {
                            buscthd.docallCTHD();
                        }
                        //Tạo 1 cthd và chuẩn bị xóa
                        CTHDDTO cthd = new CTHDDTO();
                        cthd.setMahd((String) modelCTHD.getValueAt(j, 0));
                        cthd.setMasach((String) modelCTHD.getValueAt(j, 1));
                        cthd.setSoluong(Integer.valueOf(modelCTHD.getValueAt(j, 2).toString()));
                        cthd.setThanhtien(Integer.valueOf(modelCTHD.getValueAt(j, 3).toString()));
                        cthd.setTienkm(Integer.valueOf(modelCTHD.getValueAt(j, 4).toString()));
                        //Trả về hóa đơn gốc của chi tiết hóa đơn
                        HoaDonBUS bushd = new HoaDonBUS();
                        HoaDonDTO hd = new HoaDonDTO();
                        hd = bushd.TimHDtheoCTHD(cthd);
                        //Xóa đi chi tiết hóa đơn
                        CTHDBUS busct = new CTHDBUS();
                        busct.XoaCTHDtheoHD(cthd);
                        //HD chỉnh lại giá trị tổng tiền và số lượng sau khi xóa CTHĐ(DTO)
                       hd.setTongtien(hd.getTongtien()-(cthd.getThanhtien()));
                        
                    hd.setThuctra(hd.getTongtien() - (hd.getTienkm()));
//                        hd.setThanhtien(hd.getThanhtien() - (cthd.getDongia() * cthd.getSoluong()));
                        //  hd.setSoluongtong(hd.getSoluongtong() - cthd.getSoluong());
                        //Cập nhật lại HD mới sau khi chỉnh sửa (BUS + DAO)
                        
                        //bushd.SuaHD(hd);
                        //Xóa dòng CTHD
                        modelCTHD.removeRow(j);
                        tableCTHD.setModel(modelCTHD);
                        //Cập nhật lại HD mới sau khi chỉnh sửa (GUI)
                        if (j == 0) 
                        {
                            bushd.XoaHD(hd);//Xóa hóa đơn nếu là CTHD cuối cùng
                            for (int i = 0; i < tableHD.getRowCount(); i++) {
                                if (hd.getMahd().equals(tableHD.getValueAt(i, 0))) {
                                    modelHD.removeRow(i);
                                    tableHD.setModel(modelHD);
                                    break;
                                }
                            }
                        } else {
                            for (int i = 0; i < tableHD.getRowCount(); i++) {
                                if (hd.getMahd().equals(tableHD.getValueAt(i, 0))) {
                                    // modelHD.setValueAt(hd.getSoluongtong(), i, 5);
                                    modelHD.setValueAt(hd.getTongtien(), i, 2);
                                    modelHD.setValueAt(hd.getThuctra(), i, 4);
                                    // modelHD.setValueAt(hd.getThanhtien(), i, 7);
                                    tableHD.setModel(modelHD);
                                    break;
                                }
                            }
                        }

                        //END
                    }
                }

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                xoa.setIcon(hinhxoa1);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                xoa.setIcon(hinhxoa);
            }

        });

        ImageIcon hinhsua = new ImageIcon(getClass().getResource("/HinhAnh/sua.png"));
        ImageIcon hinhsua1 = new ImageIcon(getClass().getResource("/HinhAnh/sua1.png"));
        sua = new JButton();
        sua.setEnabled(false);
        //  xoa.setBounds(680, 540, 130, 50);
        sua.setBounds(680,600, 130, 50);
        sua.setBackground(new Color(237,241,255));
        sua.setBorder(bordernull);
        sua.setIcon(hinhsua);
        sua.setFocusPainted(false);
        sua.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                if (clickxoa == -1) {
//                    JOptionPane.showMessageDialog(null, "Chọn HĐ để sửa");
//                }
//                int i = tableHD.getSelectedRow();
//                if (i >= 0) {
//                    SuaHDGUI testsua = new SuaHDGUI();
//                    testsua.setView();
//                } else {
//                    JOptionPane.showMessageDialog(null, "Chọn HĐ để sửa, không chọn chi tiết hóa đơn");
//                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                sua.setIcon(hinhsua1);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                sua.setIcon(hinhsua);
            }

        });
        ImageIcon hinhxuat1 = new ImageIcon(getClass().getResource("/HinhAnh/in.png"));
        inan = new JButton();
        inan.setEnabled(false);
        inan.setIcon(hinhxuat1);
        //  xoa.setBounds(680, 540, 130, 50);
        inan.setBounds(820, 540, 150, 70);
        inan.setBorder(bordernull);
        inan.setBackground(new Color(33, 33, 33));
        inan.setFocusPainted(false);
        inan.setContentAreaFilled(false);
        inan.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
//                int i = tableHD.getSelectedRow();
//                if (i >= 0) {
//                    try {
//                        ReportPDF in = new ReportPDF(tableHD.getValueAt(i, 0).toString());
//                    } catch (IOException ex) {
//                        Logger.getLogger(HoaDonGUI.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//
//                } else {
//                    JOptionPane.showMessageDialog(null, "Vui lòng chọn bảng hóa đơn để in hóa đơn");
//                }

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
//          hóa dơn
        this.add(hd);

//          chi tiết hđ
        this.add(cthd);

//      
        this.add(them);
        this.add(xoa);
        this.add(sua);
        this.add(inan);
        this.add(lbtop);

        cb.addActionListener((ActionEvent e) -> {
            choice = cb.getSelectedIndex();
            System.out.println(choice);
        });

        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(tableHD.getModel());
        tableHD.setRowSorter(rowSorter);

        txtimkiem.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                 String text = txtimkiem.getText();
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text + ".*", choice));
                }
                
                


//                try {
//                    String text = txtimkiem.getText();
//                    if (choice == 2) {
//                        if (text.trim().length() == 0) {
//                            rowSorter.setRowFilter(null);
//                        } else {
//                           // rowSorter.setRowFilter(RowFilter.regexFilter(Chuyentien(text), choice));
//                           rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, choice));
//                        }
//                    }
//
//                    if (choice != 7 && choice != 6) {
//                        if (text.trim().length() == 0) {
//                            rowSorter.setRowFilter(null);
//                        } else {
//                            rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, choice));
//                        }
//                    }
//                } catch (NumberFormatException ex) {
//                    JOptionPane.showMessageDialog(null, "Khi tìm kiếm theo giá buộc phải nhập số");
//                }

            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                 String text = txtimkiem.getText();
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)^" + text + ".*", choice));
                }
 //               try {
 //                   String text = txtimkiem.getText();
//                    if (choice == 7) {
//                        if (text.trim().length() == 0) {
//                            rowSorter.setRowFilter(null);
//                        } else {
//                            rowSorter.setRowFilter(RowFilter.regexFilter(Chuyentien(text), choice));
//                        }
//                    }
//                    if (choice == 6) {
//                        if (text.trim().length() == 0) {
//                            rowSorter.setRowFilter(null);
//                        } else {
//                            rowSorter.setRowFilter(RowFilter.regexFilter(Chuyentien(text), choice));
//                        }
//                    }
//                    if (choice != 7 && choice != 6) {
//                        if (text.trim().length() == 0) {
//                            rowSorter.setRowFilter(null);
//                        } else {
//                            rowSorter.setRowFilter(RowFilter.regexFilter("(?i)^" + text + ".*", choice));
//                        }
//                    }
//                } catch (NumberFormatException ex) {
//                    JOptionPane.showMessageDialog(null, "Khi tìm kiếm theo giá buộc phải nhập số");
//                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
               // this.setVisible(true);

    }

    public int Chuyenint(String fm) throws ParseException {
        Locale vietnam = new Locale("vi", "VN");
        NumberFormat fmmoney = NumberFormat.getCurrencyInstance(vietnam);
        Number d = fmmoney.parse(fm);
        BigDecimal bd = new BigDecimal(d.toString());
        int a = bd.intValue();
        return a;
    }

    public String Chuyentien(String fm) {
        Locale vietnam = new Locale("vi", "VN");
        NumberFormat fmmoney = NumberFormat.getCurrencyInstance(vietnam);
        String c = fmmoney.format(new BigDecimal(fm.toString()));
        return c;
    }

    public String ChuyenString(Date date) {
        SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
        String fmd = fm.format(date);
        return fmd;
    }

    public Date ChuyenDate(String fmd) throws ParseException {
        Date date = new Date();
        SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
        Date d = fm.parse(fmd);
        return d;
    }

//    public void timkiemnangcao() throws ParseException {
//        int giatu;
//        int giaden;
//        int sltu;
//        int slden;
//        String ngaytu;
//        String ngayden;
//
//        if ("".equals(txgiatu.getText()) || "Giá từ...".equals(txgiatu.getText())) {
//            giatu = 0;
//        } else {
//            giatu = Integer.valueOf(txgiatu.getText());
//        }
//
//        if ("".equals(txgiaden.getText()) || "Giá đến...".equals(txgiaden.getText())) {
//            giaden = 100000000;
//        } else {
//            giaden = Integer.valueOf(txgiaden.getText());
//        }
//
//        if ("".equals(txsltu.getText()) || "Số Lượng từ...".equals(txsltu.getText())) {
//            sltu = 0;
//        } else {
//            sltu = Integer.valueOf(txsltu.getText());
//        }
//
//        if ("".equals(txslden.getText()) || "Số Lượng đến...".equals(txslden.getText())) {
//            slden = 1000;
//        } else {
//            slden = Integer.valueOf(txslden.getText());
//        }
//        if ("".equals(txngaytu.getText()) || "Ngày từ...".equals(txngaytu.getText())) {
//            ngaytu = "1999-05-26";
//        } else {
//            ngaytu = txngaytu.getText();
//        }
//
//        if ("".equals(txngayden.getText()) || "Ngày đến...".equals(txngayden.getText())) {
//            Date date = new Date();
//            ngayden = ChuyenString(date);
//        } else {
//            ngayden = txngayden.getText();
//        }
//        HoaDonBUS bushd = new HoaDonBUS();
//        if (HoaDonBUS.dshd == null) {
//            bushd.docDSHD();
//        }
//
//        ArrayList<HoaDonDTO> dstk = new ArrayList<>();
//        dstk = bushd.Timkiemnangcao(giatu, giaden, sltu, slden, ngaytu, ngayden);
//
//        modelHD.setRowCount(0);
//        tableHD.setModel(modelHD);
//
//        for (HoaDonDTO hd : dstk) {
//            Vector row = new Vector();
//            row.add(hd.getMahd());
//            row.add(hd.getMakh());
//            row.add(hd.getManv());
//            row.add(hd.getMakm());
//            row.add(hd.getNgaymua());
//            row.add(hd.getSoluongtong());
//            row.add(Chuyentien(String.valueOf(hd.getTongtien())));
//            row.add(Chuyentien(String.valueOf(hd.getThanhtien())));
//            modelHD.addRow(row);
//        }

        //lbtknc.setBounds(340, 65, 450, 215);
    //}

//    public static void main(String[] args) {
//        HoaDonGUI a = new HoaDonGUI();
//        a.setVisible(true);
//        JFrame f = new JFrame();
//        f.setSize(1500, 800);
//        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        f.add(a);
//        f.setVisible(true);
//
//    }
}
