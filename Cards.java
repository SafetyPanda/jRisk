
public class Cards 
{
	private String cardType;
	private int cardsRemaning;
	private int 
	
	
	
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
	
	
}
