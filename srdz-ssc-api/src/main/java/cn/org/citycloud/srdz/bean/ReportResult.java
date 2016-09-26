package cn.org.citycloud.srdz.bean;

public class ReportResult {
	
	private String dateTime;
	
	private float supplierMoney;
	
	private float saleMoney;
	
	private int orders;
	
	private float profit;

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public float getSupplierMoney() {
		return supplierMoney;
	}

	public void setSupplierMoney(float supplierMoney) {
		this.supplierMoney = supplierMoney;
	}

	public float getSaleMoney() {
		return saleMoney;
	}

	public void setSaleMoney(float saleMoney) {
		this.saleMoney = saleMoney;
	}

	public int getOrders() {
		return orders;
	}

	public void setOrders(int orders) {
		this.orders = orders;
	}

	public float getProfit() {
		return profit;
	}

	public void setProfit(float profit) {
		this.profit = profit;
	}

	

}
