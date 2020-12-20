/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import DAO.KhachHangDAO;
import DTO.KhachHangDTO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author DELL
 */
public class KhachHangBUS {

    public static ArrayList<KhachHangDTO> dskh;
    private int row;

    public KhachHangBUS() {
    }

    public void readDSKH() {
        KhachHangDAO data = new KhachHangDAO();
        if (dskh == null) {
            dskh = new ArrayList<KhachHangDTO>();
        }
        dskh = data.readDSKH();
    }

    public KhachHangDTO getInfoRow(String code) {
        KhachHangDTO KH = new KhachHangDTO();
        readDSKH();
        for (KhachHangDTO tempkh : KhachHangBUS.dskh) {
            if (tempkh.getMakh().equals(code)) {
                KH = tempkh;
                break;
            }
        }
        return KH;
    }

    public boolean add(KhachHangDTO kh) {
        if ("".equals(kh.getMakh())) {
            JOptionPane.showMessageDialog(null, "Mã khách hàng không được để trống");
            return false;
        } else {
            String pattern = "^KH[0-9]{2}$";
            if (!Pattern.matches(pattern, kh.getMakh())) {
                JOptionPane.showMessageDialog(null, "Mã khách hàng không hợp lệ");
                return false;
            } else {
                readDSKH();
                for (KhachHangDTO tempkh : KhachHangBUS.dskh) {
                    if (tempkh.getMakh().equals(kh.getMakh())) {
                        JOptionPane.showMessageDialog(null, "Mã khách hàng hành bị trùng");
                        return false;
                    }
                }
            }
        }
        if ("".equals(kh.getHo())) {
            JOptionPane.showMessageDialog(null, "Xin nhập họ");
            return false;
        }
        if ("".equals(kh.getTen())) {
            JOptionPane.showMessageDialog(null, "Xin nhập tên");
            return false;
        }
        if ("".equals(kh.getSdt())) {
            JOptionPane.showMessageDialog(null, "Xin nhập SĐT");
            return false;
        }
        if ("".equals(kh.getDiachi())) {
            JOptionPane.showMessageDialog(null, "Xin nhập địa chỉ");
            return false;
        }
        if ("".equals(kh.getNgaysinh())) {
            JOptionPane.showMessageDialog(null, "Ngày sinh không được để trống");
            return false;
        }
        /*else {
            String pattern = "((0[1-9])|[1-2][0-9]|3[0-1])[/]((0[1-9]|1[0-2]))[/]\\d{4}$" ;
            if(!Pattern.matches(pattern, kh.getNgaysinh())){
                JOptionPane.showMessageDialog(null,"Ngày sinh không hợp lệ (dd/mm/yyyy)");
                return false;
            } else{
                // kiểm tra ngày tháng hơp lệ
            }
        }*/
        if ("".equals(kh.getGioitinh())) {
            JOptionPane.showMessageDialog(null, "Xin chọn giới tính");
            return false;
        }
        KhachHangDAO data = new KhachHangDAO();
        data.add(kh);
        JOptionPane.showMessageDialog(null, "Thêm thành công");
        return true;
    }

    public boolean delete(String ma) {
        int index = -1;
        if ("".equals(ma)) {
            JOptionPane.showMessageDialog(null, "Mã khách hàng không được để trống");
            return false;
        } else {
            String pattern = "^KH[0-9]{2}$";
            if (!Pattern.matches(pattern, ma)) {
                JOptionPane.showMessageDialog(null, "Mã khách hàng không hợp lệ");
                return false;
            } else {
                readDSKH();
                boolean flag = false;
                for (KhachHangDTO tempkh : KhachHangBUS.dskh) {
                    index++;
                    if (tempkh.getMakh().equals(ma)) {
                        flag = true;
                        break;
                    }
                }
                if (flag == false) {
                    JOptionPane.showMessageDialog(null, "Mã khách hàng không tồn tại");
                    return false;
                }
            }
        }
        KhachHangDAO data = new KhachHangDAO();
        data.delete(ma);
        dskh.remove(index);
        JOptionPane.showMessageDialog(null, "Xoá thành công");
        return true;
    }

    public boolean update(KhachHangDTO kh) {
        if ("".equals(kh.getMakh())) {
            JOptionPane.showMessageDialog(null, "Mã khách hàng không được để trống");
            return false;
        } else {
            String pattern = "^KH[0-9]{2}$";
            if (!Pattern.matches(pattern, kh.getMakh())) {
                JOptionPane.showMessageDialog(null, "Mã khách hàng không hợp lệ");
                return false;
            } else {
                readDSKH();
//                for (KhachHangDTO tempkh : KhachHangBUS.dskh){
//                    if(tempkh.getMakh().equals(kh.getMakh())){
//                        JOptionPane.showMessageDialog(null,"Mã khách hàng hành bị trùng");
//                        return false;
//                    }
//                }
            }
        }
        if ("".equals(kh.getHo())) {
            JOptionPane.showMessageDialog(null, "Xin nhập họ");
            return false;
        }
        if ("".equals(kh.getTen())) {
            JOptionPane.showMessageDialog(null, "Xin nhập tên");
            return false;
        }
        if ("".equals(kh.getSdt())) {
            JOptionPane.showMessageDialog(null, "Xin nhập SĐT");
            return false;
        }
        if ("".equals(kh.getDiachi())) {
            JOptionPane.showMessageDialog(null, "Xin nhập địa chỉ");
            return false;
        }
        if ("".equals(kh.getNgaysinh())) {
            JOptionPane.showMessageDialog(null, "Ngày sinh không được để trống");
            return false;
        }
        /*else {
            String pattern = "((0[1-9])|[1-2][0-9]|3[0-1])[/]((0[1-9]|1[0-2]))[/]\\d{4}$" ;
            if(!Pattern.matches(pattern, kh.getNgaysinh())){
                JOptionPane.showMessageDialog(null,"Ngày sinh không hợp lệ (dd/mm/yyyy)");
                return false;
            } else{
                // kiểm tra ngày tháng hơp lệ
            }
        }*/
        if ("".equals(kh.getGioitinh())) {
            JOptionPane.showMessageDialog(null, "Xin nhập giới tính");
            return false;
        }
        int index = -1;
        for (KhachHangDTO x : dskh) {
            index++;
            if (x.getMakh().equals(kh.getMakh())) {
                break;
            }
        }
        KhachHangDAO data = new KhachHangDAO();
        data.update(kh);
        dskh.set(index, kh);
        JOptionPane.showMessageDialog(null, "Sửa thành công");
        return true;
    }

    public String Timtenkh(String ma) {
        for (KhachHangDTO kh : dskh) {
            if (kh.getMakh().equals(ma)) {
                return kh.getHo() + " " + kh.getTen();
            }
        }
        return " ";
    }

    public ArrayList FindAD(int TuoiTu, int TuoiDen) {
        ArrayList<KhachHangDTO> dstk = new ArrayList<>();
        try {
            int tu, den;
            if ("Tuổi từ...".equals(TuoiTu)) {
                tu = 1;
            } else {
                tu = TuoiTu;
            }

            if ("Tuổi đến...".equals(TuoiDen)) {
                den = 99;
            } else {
                den = TuoiDen;
            }

            for (KhachHangDTO x : dskh) {
                Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(x.getNgaysinh());
                Date now = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
                String year1 = sdf.format(date1);
                String yearnow = sdf.format(now);
                int Tuoi = Integer.parseInt(yearnow) - Integer.parseInt(year1);
                if (Tuoi >= TuoiTu && Tuoi <= TuoiDen) {
                    dstk.add(x);
                }
            }
        } catch (ParseException ex) {
            java.util.logging.Logger.getLogger(KhachHangBUS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dstk;
    }
}
