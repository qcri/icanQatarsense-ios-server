package com.qsense.transfer;

import java.util.List;


/**
 * @author Kushalkant
 *
 */
public class MessagePaginationTO{	

	private int currentPageNumber;	
		
	private int totalNoOfPages;	
	
	private int itemsPerPage;	
	
	private List<UserMessageTO> messages;

	/**
	 * @return the messages
	 */
	public List<UserMessageTO> getMessages() {
		return messages;
	}

	/**
	 * @param messages the messages to set
	 */
	public void setMessages(List<UserMessageTO> messages) {
		this.messages = messages;
	}

	/**
	 * @return the currentPageNumber
	 */
	public int getCurrentPageNumber() {
		return currentPageNumber;
	}

	/**
	 * @param currentPageNumber the currentPageNumber to set
	 */
	public void setCurrentPageNumber(int currentPageNumber) {
		this.currentPageNumber = currentPageNumber;
	}

	/**
	 * @return the totalNoOfPages
	 */
	public int getTotalNoOfPages() {
		return totalNoOfPages;
	}

	/**
	 * @param totalNoOfPages the totalNoOfPages to set
	 */
	public void setTotalNoOfPages(int totalNoOfPages) {
		this.totalNoOfPages = totalNoOfPages;
	}

	/**
	 * @return the itemsPerPage
	 */
	public int getItemsPerPage() {
		return itemsPerPage;
	}

	/**
	 * @param itemsPerPage the itemsPerPage to set
	 */
	public void setItemsPerPage(int itemsPerPage) {
		this.itemsPerPage = itemsPerPage;
	}

}
