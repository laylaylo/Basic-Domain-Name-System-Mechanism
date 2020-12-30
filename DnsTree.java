
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package question;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;

/**
 * Represents a dns tree.
 * @author leylayayladere
 * @version 1.0
 */
public class DnsTree {
	
	/**
	 * The root of this <code>DnsTree</code>.
	 */
	private DnsNode root;
	
	private Map<String, Set<String>>  allRecords = new TreeMap<String, Set<String>>();	
	
	/**
	 * Creates a <code>DnsTree</code>.
	 */
	public DnsTree () {
		this.root = new DnsNode();
	}
	
	/**
	 * Inserts a new record for a given <code>domainName</code>.
	 * @param domainName A variable of type String.
	 * @param ipAddress A variable of type String.
	 */
	public void insertRecord(String domainName, String ipAddress) {
		
		DnsNode temp = this.root;
		
		if(domainName.charAt(0) == '.') {
			domainName = domainName.substring(1);
		}
		
		String keepDomainName = domainName;
		
		while(domainName.contains(".")) {
			String subdomainName = domainName.substring(domainName.lastIndexOf('.')+1);
			domainName = domainName.substring(0, domainName.lastIndexOf('.'));
			if(!temp.getChildNodeList().containsKey(subdomainName)) {
				DnsNode subNode = new DnsNode();
				temp.getChildNodeList().put(subdomainName, subNode);
				temp = subNode;
			}else {
				temp = temp.getChildNodeList().get(subdomainName);
			}
		}
		
		if(!temp.getChildNodeList().containsKey(domainName)) {
			DnsNode addIp = new DnsNode();
			temp.getChildNodeList().put(domainName, addIp);
			addIp.getIpAddresses().add(ipAddress);
			addIp.setValidDomain(true);
			addIp.setDomainName(keepDomainName);
			addIp.getIpRoundRobin().add(ipAddress);
			this.allRecords.put(addIp.getDomainName(), addIp.getIpAddresses());
		}else {
			temp.getChildNodeList().get(domainName).getIpAddresses().add(ipAddress);
			temp.getChildNodeList().get(domainName).getIpRoundRobin().add(ipAddress);
			if(this.allRecords.containsKey(keepDomainName)) {
				this.allRecords.get(keepDomainName).add(ipAddress);
			}else {		
				this.allRecords.put(keepDomainName, temp.getChildNodeList().get(domainName).getIpAddresses());
			}
		}
		

	}

	/**
	 * Removes the <code>DnsNode</code> with the given <code>domainName</code> from this <code>DnsTree</code>.
	 * @param domainName A variable of type String.
	 * @return A boolean data type.
	 */
	public boolean removeRecord(String domainName) {
		
		DnsNode temp = this.root;
		
		if(domainName.charAt(0) == '.') {
			domainName = domainName.substring(1);
		}
		
		String fullDomainName = domainName;
		
		while(domainName.contains(".")) {
			String subdomainName = domainName.substring(domainName.lastIndexOf('.')+1);
			if(temp.getChildNodeList().containsKey(subdomainName)) {
				domainName = domainName.substring(0, domainName.lastIndexOf('.'));
				temp = temp.getChildNodeList().get(subdomainName);
			}else {
				return false;
			}
		}

		if(temp.getChildNodeList().containsKey(domainName)) {
			if(temp.getChildNodeList().get(domainName).getChildNodeList().isEmpty()) {
				temp.getChildNodeList().remove(domainName);
			}else {
				temp.getChildNodeList().get(domainName).getIpAddresses().clear();
				temp.getChildNodeList().get(domainName).getIpRoundRobin().clear();
				temp.getChildNodeList().get(domainName).setValidDomain(false);
				this.allRecords.remove(fullDomainName);
			}
			return true;
		}else {
			return false;
		}
		
	}
	
	/**
	 * Removes the given <code>ipAddress</code> of a <code>DnsNode</code> with the given <code>domainName</code>.
	 * @param domainName A variable of type String.
	 * @param ipAddress A variable of type String.
	 * @return A boolean data type.
	 */
	public boolean removeRecord(String domainName, String ipAddress) {
		
		DnsNode temp = this.root;
		
		if(domainName.charAt(0) == '.') {
			domainName = domainName.substring(1);
		}
		
		String fullDomainName = domainName;
		
		while(domainName.contains(".")) {
			String subdomainName = domainName.substring(domainName.lastIndexOf('.')+1);
			if(temp.getChildNodeList().containsKey(subdomainName)) {
				domainName = domainName.substring(0, domainName.lastIndexOf('.'));
				temp = temp.getChildNodeList().get(subdomainName);
			}else {
				return false;
			}
		}

		if(temp.getChildNodeList().containsKey(domainName)) {
			if(temp.getChildNodeList().get(domainName).getIpAddresses().contains(ipAddress)) {
				temp.getChildNodeList().get(domainName).getIpAddresses().remove(ipAddress);
				temp.getChildNodeList().get(domainName).getIpRoundRobin().remove(ipAddress);
				this.allRecords.get(fullDomainName).remove(ipAddress);
				if(temp.getChildNodeList().get(domainName).getIpAddresses().isEmpty()) {
					temp.getChildNodeList().get(domainName).setValidDomain(false);
					this.allRecords.remove(fullDomainName);
				}
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
		
	}
	
	/**
	 * Queries a domain name within the DNS, following the Round Robin mechanism.
	 * @param domainName A variable of type String.
	 * @return A String type.
	 */
	public String queryDomain(String domainName) {
		
		DnsNode temp = this.root;
		
		if(domainName.charAt(0) == '.') {
			domainName = domainName.substring(1);
		}
		
		while(domainName.contains(".")) {
			String subdomainName = domainName.substring(domainName.lastIndexOf('.')+1);
			if(temp.getChildNodeList().containsKey(subdomainName)) {
				domainName = domainName.substring(0, domainName.lastIndexOf('.'));
				temp = temp.getChildNodeList().get(subdomainName);
			}else {
				return null;
			}
		}
		
		if(temp.getChildNodeList().containsKey(domainName) && !temp.getChildNodeList().get(domainName).getIpAddresses().isEmpty()) {
			String ipAddress = temp.getChildNodeList().get(domainName).getIpRoundRobin().poll();
			temp.getChildNodeList().get(domainName).getIpRoundRobin().add(ipAddress);
			return ipAddress;
		}else {
			return null;
		}

	}
	
	
	public Map<String, Set<String>> getAllRecords() {
		return this.allRecords;
	}
	
	/**
	 * Retrieves the value of a <code>root</code> of this <code>DnsTree</code>.
	 * @return An object <code>DnsNode</code> data type.
	 */
	public DnsNode getRoot() {
		return root;
	}

	/**
	 * Sets the value of a <code>root</code> of this <code>DnsTree</code>.
	 * @param root A variable of type object <code>DnsNode</code>.
	 */
	public void setRoot(DnsNode root) {
		this.root = root;
	}
	
}


//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

