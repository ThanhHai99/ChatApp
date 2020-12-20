
package DTO;


public class KhachHangDTO {
    private String makh,ho,ten,sdt,diachi,ngaysinh,gioitinh;
    public KhachHangDTO(String makh,String ho,String ten,String sdt,String diachi,String ngaysinh,String gioitinh){
        this.makh=makh;
        this.ho=ho;
        this.ten=ten;
        this.sdt=sdt;
        this.diachi=diachi;
        this.ngaysinh=ngaysinh;
        this.gioitinh=gioitinh;
    }
    public KhachHangDTO(){
        
    }

    public String getMakh() {
        return makh;
    }

    public String getHo() {
        return ho;
    }

    public String getTen() {
        return ten;
    }

    public String getSdt() {
        return sdt;
    }

    public String getDiachi() {
        return diachi;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public String getGioitinh() {
        return gioitinh;
    }
    
    public void setMakh(String makh) {
        this.makh = makh;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }
    
}
