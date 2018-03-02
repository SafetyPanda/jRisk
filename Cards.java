
public class Cards 
{
	private String cardType;
	private int cardNumber;
	
	
	
	public Cards()
	{
		cardType = "NOTHING HERE";
		cardNumber = -1;
	}
	
	public Cards(String name, int num)
	{
		cardType = name;
		cardNumber = num;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public int getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	
	
}
