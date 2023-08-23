package kr.farmstory1.dto;

import java.io.File;
import java.text.DecimalFormat;
import java.util.UUID;

public class ProductDTO {

	private int pNo;
	private int type;
	private String pName;
	private int price;
	private int delivery;
	private int stock;
	private int sold;
	private String thumb1;
	private String thumb2;
	private String thumb3;
	private String seller;
	private String etc;
	private String rdate;
	
	public ProductDTO() {
		
	}
	
	//추가
	private String path;
	public ProductDTO(String path) {
		this.path = path;
	}
	
	
	public int getpNo() {
		return pNo;
	}
	public void setpNo(int pNo) {
		this.pNo = pNo;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	//추가
	public void setType(String type) {
		this.type = Integer.parseInt(type);
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public int getPrice() {
		return price;
	}
	//추가
	public String getPriceWithComma() {
		DecimalFormat df = new DecimalFormat("###,###");
		return df.format(price);
	}
	public void setPrice(int price) {
		this.price = price;
	}
	//추가
	public void setPrice(String price) {
		this.price = Integer.parseInt(price);
	}
	public int getDelivery() {
		return delivery;
	}
	//추가
	public String getDeliveryWithComma() {
		DecimalFormat df = new DecimalFormat("###,###");
		return df.format(delivery);
	}
	public void setDelivery(int delivery) {
		this.delivery = delivery;
	}
	//추가
	public void setDelivery(String delivery) {
		this.delivery = Integer.parseInt(delivery);
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	//추가
	public void setStock(String stock) {
		this.stock = Integer.parseInt(stock);
	}
	public int getSold() {
		return sold;
	}
	public void setSold(int sold) {
		this.sold = sold;
	}
	public String getThumb1() {
		return thumb1;
	}
	
	public void setThumb1(String thumb1) {
		this.thumb1 = thumb1;
	}
	//thumb1,2,3 setter변경
	public void setThumb1ForRename(String thumb1) {
		this.thumb1 = fileRename(thumb1);
	}
	public String getThumb2() {
		return thumb2;
	}
	public void setThumb2(String thumb2) {
		this.thumb2 = thumb2;
	}
	//thumb1,2,3 setter변경
	public void setThumb2ForRename(String thumb2) {
		this.thumb2 = fileRename(thumb2);
	}
	public String getThumb3() {
		return thumb3;
	}
	public void setThumb3(String thumb3) {
		this.thumb3 =thumb3;
	}
	//thumb1,2,3 setter변경
	public void setThumb3ForRename(String thumb3) {
		this.thumb3 = fileRename(thumb3);
	}
	public String getSeller() {
		return seller;
	}
	public void setSeller(String seller) {
		this.seller = seller;
	}
	public String getEtc() {
		return etc;
	}
	public void setEtc(String etc) {
		this.etc = etc;
	}
	//변경
	public String getRdate() {
		return rdate.substring(0, 10);
	}
	public void setRdate(String rdate) {
		this.rdate = rdate;
	}
	
	//추가 - 파일명 수정(oName -> sName)
	public String fileRename(String thumb) {
	int i = thumb.lastIndexOf(".");
	String ext = thumb.substring(i);
	
	String uuid = UUID.randomUUID().toString();
	String sName = uuid + ext;
	
	File f1 = new File(path+ "/" + thumb);
	File f2 = new File(path+ "/" + sName);
	f1.renameTo(f2);
	
	return sName;
	}
	
	

}
