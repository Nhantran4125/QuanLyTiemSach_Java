/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Report;

/**
 *
 * @author DELL
 */
import BUS.*;
import DTO.*;



//import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
//import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ReportPDF {
    
    HoaDonBUS hoadonbus = new HoaDonBUS();
    KhachHangBUS khachhangbus = new KhachHangBUS();
    NhanVienBUS nhanvienbus = new NhanVienBUS();
    CTHDBUS cthoadonbus = new CTHDBUS();
    KhuyenMaiBUS khuyenmaibus = new KhuyenMaiBUS();

//    public ReportPDF(String mahd) throws FileNotFoundException, IOException {
//        init(mahd);
//        File myFile = new File("BillReport/" + mahd + ".pdf");
//
//        Desktop.getDesktop().open(myFile);
//    }
    
    public ReportPDF(String mahd){
        try {
            init(mahd);
            File myFile = new File("BillReport/" + mahd + ".pdf");
            
            Desktop.getDesktop().open(myFile);
        } catch (IOException ex) {
            //Logger.getLogger(ReportPDF.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"??ang t???o h??a ????n");
        }
    }

    public String Chuyentien(String fm) {
        Locale vietnam = new Locale("vi", "VN");
        NumberFormat fmmoney = NumberFormat.getCurrencyInstance(vietnam);
        String c = fmmoney.format(new BigDecimal(fm.toString()));
        return c;
    }

    public void init(String mahd) throws FileNotFoundException, IOException {

        HoaDonDTO hd = new HoaDonDTO();
        hoadonbus.docDSHD();
        khachhangbus.readDSKH();
        nhanvienbus.readDSNV();
        cthoadonbus.docCTHDtheoHD(mahd);
        hd = hoadonbus.TimHDtheoMa(mahd);
        //khuyenmaibus.docDSKMT(); 
        khuyenmaibus.docDSKMD();
        PdfWriter writer = new PdfWriter("BillReport/" + mahd + ".pdf");
        PdfFont font = PdfFontFactory.createFont("font/OpenSans-Regular.ttf", PdfEncodings.IDENTITY_H, true);

        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf, PageSize.A5);
        PdfPage pdfPage = pdf.addNewPage();

        Paragraph tencuahang = new Paragraph("Ti???m s??ch ABC");
        tencuahang.setTextAlignment(TextAlignment.CENTER).setFont(font);
        Paragraph diachi = new Paragraph("273 An D????ng V????ng, Ph?????ng 3, Qu???n 5, H??? Ch?? Minh");
        diachi.setTextAlignment(TextAlignment.CENTER).setFont(font);
        Paragraph row = new Paragraph("----------------------------------------------");
        row.setTextAlignment(TextAlignment.CENTER).setFont(font);

        document.add(tencuahang);
        document.add(diachi);
        document.add(row);

        Paragraph phieuthanhtoan = new Paragraph("H??a ????n Thanh To??n");
        phieuthanhtoan.setTextAlignment(TextAlignment.CENTER).setFont(font);
        Paragraph hoadonso = new Paragraph("H??a ????n S???: " + hd.getMahd());
        hoadonso.setFont(font);
        Paragraph khachhang = new Paragraph("Kh??ch h??ng: " + hd.getMakh() + " - " + khachhangbus.Timtenkh(hd.getMakh()));
        khachhang.setFont(font);
        Paragraph nhanvien = new Paragraph("Nh??n vi??n: " + hd.getManv() + " - " + nhanvienbus.Timtennv(hd.getManv()));
        nhanvien.setFont(font);
        Paragraph thoigian = new Paragraph("Ng??y l???p: " + hd.getNgaylaphd());
        thoigian.setFont(font);

        document.add(phieuthanhtoan);
        document.add(hoadonso);
        document.add(khachhang);
        document.add(nhanvien);
        document.add(thoigian);
        document.add(row);

        Paragraph phieuthanhtoanct = new Paragraph("Chi Ti???t H??a ????n");
        phieuthanhtoanct.setTextAlignment(TextAlignment.CENTER).setFont(font);

        float[] pointColumnWidths = {120F, 85F, 85F, 85F};
        Table table = new Table(pointColumnWidths);
        table.addHeaderCell(new Cell().add("S???n ph???m").setBackgroundColor(Color.GREEN).setTextAlignment(TextAlignment.CENTER).setFont(font));
        table.addHeaderCell(new Cell().add("S??? l?????ng").setBackgroundColor(Color.GREEN).setTextAlignment(TextAlignment.CENTER).setFont(font));
        table.addHeaderCell(new Cell().add("????n gi??").setBackgroundColor(Color.GREEN).setTextAlignment(TextAlignment.CENTER).setFont(font));
        table.addHeaderCell(new Cell().add("Th??nh ti???n").setBackgroundColor(Color.GREEN).setTextAlignment(TextAlignment.CENTER).setFont(font));

        for (CTHDDTO cthd : CTHDBUS.dscthd) {
            //table.addCell(new Cell().add(cthd.getTen()).setTextAlignment(TextAlignment.CENTER).setFont(font));
            SachBUS sachbus = new SachBUS();
            sachbus.docDSSP();
            SachDTO sach = new SachDTO();
            sach = sachbus.timSach(cthd.getMasach()); // t??m th??ng tin s??ch theo m?? s??ch
            table.addCell(new Cell().add(sach.getTensach()).setTextAlignment(TextAlignment.CENTER).setFont(font));
            table.addCell(new Cell().add(String.valueOf(cthd.getSoluong())).setTextAlignment(TextAlignment.CENTER).setFont(font));
            //table.addCell(new Cell().add(Chuyentien(String.valueOf(cthd.getDongia()))).setTextAlignment(TextAlignment.CENTER).setFont(font));
            table.addCell(new Cell().add(Chuyentien(String.valueOf(sach.getDongia()))).setTextAlignment(TextAlignment.CENTER).setFont(font));
            int thanhtien = sach.getDongia() * cthd.getSoluong();
            table.addCell(new Cell().add(Chuyentien(String.valueOf(thanhtien))).setTextAlignment(TextAlignment.CENTER).setFont(font));
        }

        document.add(phieuthanhtoanct);
        document.add(table);

        //Paragraph tongsoluong = new Paragraph("T???ng s??? l?????ng: " + hd.getSoluongtong());
        //tongsoluong.setFont(font);
        Paragraph tongtien = new Paragraph("T???ng ti???n: " + Chuyentien(String.valueOf(hd.getTongtien())));
        tongtien.setFont(font);
        //Paragraph giamgia = new Paragraph("M?? KM : " + hd.getMakm() + " - T??? l??? gi???m : " + khuyenmaibus.duyetTile(hd.getMakm()) + "% - M?? t??? KM : " + khuyenmaibus.duyetMota(hd.getMakm()));
        //giamgia.setFont(font);
        //Paragraph tienphaitra = new Paragraph("Ti???n ph???i tr???: " + Chuyentien(String.valueOf(hd.getThanhtien())));
        Paragraph tiengiamgia = new Paragraph("Ti???n khuy???n m??i:"+Chuyentien(String.valueOf(hd.getTienkm())));
        tiengiamgia.setFont(font);
        Paragraph tienphaitra = new Paragraph("Ti???n ph???i tr???: " + Chuyentien(String.valueOf(hd.getThuctra())));
        tienphaitra.setFont(font);

        //document.add(tongsoluong);
        document.add(tongtien);
        //document.add(giamgia);
        document.add(tiengiamgia);
        document.add(tienphaitra);

        document.close();

    }

    public static void main(String[] args) throws IOException {
        //ReportPDF test = new ReportPDF("HD01");
        ReportPDF test = new ReportPDF("1");
    }
}
