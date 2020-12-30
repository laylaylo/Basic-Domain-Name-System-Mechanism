
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package question;

import java.util.ArrayList;

/**
 * Represents a client.
 * @author leylayayladere
 * @version 1.0
 */
public class Client {
	
	/**
	 * In order to access the <code>DnsTree</code> structure.
	 */
	private DnsTree root;
	/**
	 * This is just for identifying the <code>Client</code>.
	 */
	private String ipAddress; 
	/**
	 * The cache of this <code>Client</code>.
	 */
	private CachedContent[] cacheList = new CachedContent[10];
	
	/**
	 * Creates a <code>Client</code> with specified <code>ipAddress</code> and <code>root</code>.
	 * @param ipAddress <i>Unique<i> <code>ipAddress</code> of this <code>Client</code>.
	 * @param root <code>DnsTree</code> structure which this <code>Client</code> will use.
	 */
	public Client(String ipAddress, DnsTree root) {
		this.setIpAddress(ipAddress);
		this.setRoot(root);
	}
	
	/**
	 * Returns the IP address of the requested <code>domainName</code>.
	 * @param domainName A variable of type String.
	 * @return A String type. 
	 */
	public String sendRequest(String domainName) {
		boolean inCacheList = false;
		String fromCacheList = "";
	
		for(int i=0; i < this.getCacheList().length; i++) {
			if(this.cacheList[i] != null) {
				if(this.cacheList[i].getDomainName().equals(domainName)) {
					inCacheList = true;
					this.cacheList[i].setHitNo(this.cacheList[i].getHitNo()+1);
					fromCacheList = this.cacheList[i].getIpAddress(); 
					break; 
				}
			}
		}
		
		if(!inCacheList) {
			String ipAddress = root.queryDomain(domainName);
			addToCache(domainName,ipAddress);
			return ipAddress; 
		}else {
			return fromCacheList;
		}
		
	}
	
	/**
	 * Adds the given <code>ipAddress</code> of the given <code>domainName</code> to <code>cacheList</code> of this <code>Client</code>.
	 * @param domainName A variable of type String.
	 * @param ipAddress A variable of type String.
	 */
	public void addToCache(String domainName, String ipAddress) {
		boolean availableSpace = false;
		
		int minHitNo;
		int kick = 0;
		if(this.cacheList[0] != null) {
			minHitNo = this.cacheList[0].getHitNo();
		}else {
			minHitNo = 100;
		}
		
		for(int i=0; i<this.getCacheList().length; i++) {
			if(this.cacheList[i] == null) {
				availableSpace = true;
				this.cacheList[i] = new CachedContent(domainName, ipAddress);
				break;
			}else {
				if(this.cacheList[i].getHitNo() < minHitNo) {
					minHitNo = this.cacheList[i].getHitNo();
					kick = i;
				}
			}
		}
		
		if(!availableSpace) {
			this.cacheList[kick].setDomainName(domainName);
			this.cacheList[kick].setIpAddress(ipAddress);
			this.cacheList[kick].setHitNo(1);
		}
		
	}
	
	/**
	 * Retrieves the value of a <code>root</code> of this <code>Client</code>.
	 * @return An object <code>DnsTree</code> data type.
	 */
	public DnsTree getRoot() {
		return root;
	}

	/**
	 * Sets the value of a <code>root</code> of this <code>Client</code>.
	 * @param root A variable of type object <code>DnsTree</code>.
	 */
	public void setRoot(DnsTree root) {
		this.root = root;
	}

	
	/**
	 * Retrieves the IP address of this <code>Client</code> .
	 * @return A String type.
	 */
	public String getIpAddress() {
		return ipAddress;
	}

	/**
	 * Sets the IP address of this <code>Client</code>.
	 * @param ipAddress A variable of type String.
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	/**
	 * Sets the values of of a <code>cacheList</code> <i>list<i>.
	 * @return A list data type.
	 */
	public CachedContent[] getCacheList() {
		return cacheList;
	}

	/**
	 * Retrieves the values of of a <code>cacheList</code> <i>list<i>.
	 * @param cacheList A variable of type list.
	 */
	public void setCacheList(CachedContent[] cacheList) {
		this.cacheList = cacheList;
	}

	private class CachedContent { //nested class
		
		private String domainName;
		private String ipAddress; 
		private int hitNo = 0;;
		
		public CachedContent(String domainName, String ipAddress) {
			this.domainName = domainName;
			this.ipAddress = ipAddress;
		}
		
		public String getDomainName() {
			return domainName;
		}

		public void setDomainName(String domainName) {
			this.domainName = domainName;
		}
		
		public String getIpAddress() {
			return ipAddress;
		}

		public void setIpAddress(String ipAddress) {
			this.ipAddress = ipAddress;
		}
		
		public int getHitNo() {
			return hitNo;
		}

		public void setHitNo(int hitNo) {
			this.hitNo = hitNo;
		}
		
	}
	
	
	
}


//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

