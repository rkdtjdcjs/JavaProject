package Project;

public class Buds2DTO {
	private String mDate;		// 기준일
	private String mName;		// 기준모델
	private int mPrice;		//가격
	
	public String getmDate() {
		return mDate;
	}
	public void setmDate(String mDate) {
		this.mDate = mDate;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public int getmPrice() {
		return mPrice;
	}
	public void setmPrice(int mPrice) {
		this.mPrice = mPrice;
	}
	@Override
	public String toString() {
		return "Buds2DTO [mDate=" + mDate + ", mName=" + mName + ", mPrice=" + mPrice + "]";
	}
	public Buds2DTO() {
		super();
	}
	public Buds2DTO(String mDate, String mName, int mPrice) {
		super();
		this.mDate = mDate;
		this.mName = mName;
		this.mPrice = mPrice;
	}
}
