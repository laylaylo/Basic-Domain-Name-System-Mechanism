
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package question;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Represents a dns node.
 * @author leylayayladere
 * @version 1.0
 */
public class DnsNode {
	
	/**
	 * In order to maintaining the tree structure.
	 */
	private Map<String, DnsNode> childNodeList = new TreeMap<String, DnsNode>();
	/**
	 * Shows whether the current <code>DnsNode</code> has a valid IP address or not.
	 */
	private boolean validDomain;
	/**
	 * The set is for storing IP addresses of this <code>DnsNode</code>.
	 */
	private Set<String> ipAddresses = new TreeSet<String>();
	/**
	 * In order to follow the Round Robin mechanism while querying a domain name within the DNS.
	 */
	private Queue<String> ipRoundRobin = new LinkedList<String>();
	/**
	 * The full domain name of this <code>DnsNode</code>.
	 */
	private String domainName;
	
	/**
	 * Creates a <code>DnsNode</code> with an empty <code>childNodeList</code> and <code>ipAddresses</code>.
	 */
	public DnsNode() {
		this.getChildNodeList().clear();
		this.getIpAddresses().clear();;
		this.getIpRoundRobin().clear();
		this.setValidDomain(false);
	}
	
	/**
	 * Retrieves the values and keys of a <code>childNodeList</code> <i>map<i>.
	 * @return A collection data type.
	 */
	public Map<String, DnsNode> getChildNodeList() {
		return childNodeList;
	}
	
	/**
	 * Sets the values and keys of a <code>childNodeList</code> <i>map<i>.
	 * @param childNodeList A variable of type collection.
	 */
	public void setChildNodeList(Map<String, DnsNode> childNodeList) {
		this.childNodeList = childNodeList;
	}
	
	/**
	 * Retrieves whether this <code>DnsNode</code> is valid or not.
	 * @return A boolean data type.
	 */
	public boolean isValidDomain() {
		return validDomain;
	}
	
	/**
	 * Sets whether this <code>DnsNode</code> is valid or not.
	 * @param validDomain A variable of type boolean.
	 */
	public void setValidDomain(boolean validDomain) {
		this.validDomain = validDomain;
	}
	
	/**
	 * Retrieves the values of a <code>ipAddresses</code> <i>set<i>.
	 * @return A collection data type.
	 */
	public Set<String> getIpAddresses() {
		return ipAddresses;
	}
	
	/**
	 * Sets the values of a <code>ipAddresses</code> <i>set<i>.
	 * @param ipAddresses A variable of type collection.
	 */
	public void setIpAddresses(Set<String> ipAddresses) {
		this.ipAddresses = ipAddresses;
	}

	/**
	 * Retrieves the values of a <code>ipRoundRobin</code> <i>queue<i>.
	 * @return A queue data type.
	 */
	public Queue<String> getIpRoundRobin() {
		return ipRoundRobin;
	}

	/** 
	 * Sets the values of a <code>ipRoundRobin</code> <i>queue<i>.
	 * @param ipRoundRobin A variable of type queue.
	 */
	public void setIpRoundRobin(Queue<String> ipRoundRobin) {
		this.ipRoundRobin = ipRoundRobin;
	}
	
	/**
	 * Retrieves the full domain name of this <code>DnsNode</code> .
	 * @return A String type.
	 */
	public String getDomainName() {
		return domainName;
	}
	
	/** 
	 * Sets the full domain name of a <code>DnsNode</code>.
	 * @param domainName A variable of type String.
	 */
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
	
	
}


//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

