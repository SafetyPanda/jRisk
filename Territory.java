// Author: James Gillman
// Final Project
// 12/11/17
// Keeps info on territory, how many armies, current x coordinates, y coordinates, and which playerOwns it.

public class Territory
{
	//
	private int armies; // How many Armies is on Current Territory.
	private int xCoord; // Gotta get that Y Coordinate from Player.
	private int yCoord; // Gotta get that Y Coordinate from Player.
	
	private Player playerOwns; //Which player owns this territory?
	// private Territory link; Is this even needed???
	
	public Territory()
	{
		armies = -1;
		playerOwns = null;
		yCoord = -1;
		xCoord = -1;
	}

	public Territory( Player currentPlayer, int y, int x)
	{
		armies = -1;
		playerOwns = currentPlayer;
		yCoord = y;
		xCoord = x;
		
	}

	
	
	//Getters and Setters//
	
	
	
	/**
	 * @return the armies
	 */
	public int getArmies( )
	{
		return armies;
	}

	/**
	 * @return the xCoord
	 */
	public int getxCoord( )
	{
		return xCoord;
	}

	/**
	 * @return the yCoord
	 */
	public int getyCoord( )
	{
		return yCoord;
	}

	/**
	 * @return the playerOwns
	 */
	public Player getPlayerOwns( )
	{
		return playerOwns;
	}

	/**
	 * @param armies the armies to set
	 */
	public void setArmies( int armies )
	{
		this.armies = armies;
	}

	/**
	 * @param xCoord the xCoord to set
	 */
	public void setxCoord( int xCoord )
	{
		this.xCoord = xCoord;
	}

	/**
	 * @param yCoord the yCoord to set
	 */
	public void setyCoord( int yCoord )
	{
		this.yCoord = yCoord;
	}

	/**
	 * @param playerOwns the playerOwns to set
	 */
	public void setPlayerOwns( Player playerOwns )
	{
		this.playerOwns = playerOwns;
	}








}
