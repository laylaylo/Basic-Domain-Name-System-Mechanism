package question;

import javax.swing.text.html.HTMLDocument.Iterator;

/**
 * Defines a class that controls a simulation of a basic DNS program.
 * @author leylayayladere
 * @version 1.0
 */
public class main {
	
	public static void main(String[] args) {
		
DnsTree tree1=new DnsTree();
		
		tree1.getAllRecords();//ağaç boşken kayıt alma 
		
		tree1.insertRecord("boun.edu.tr", "1.1.1.1");//domain ekleme
		System.out.println(tree1.getAllRecords());

		
		tree1.insertRecord("boun.edu.tr", "1.1.1.1");//aynı domaini bir daha ekleme
		System.out.println(tree1.getAllRecords());

		
		tree1.insertRecord("registration.boun.edu.tr", "1.1.1.2");//var olan domainin üzerine parça ekleme
		System.out.println(tree1.getAllRecords());
		
		
		
		System.out.println(tree1.removeRecord("boun.edu.tr"));//çocuğu olan nodu silme
		System.out.println(tree1.getAllRecords());
		
		
		System.out.println(tree1.removeRecord("boun.edu.tr","1.1.1.1"));//var olmayan bir ip adresini silme
		System.out.println(tree1.getAllRecords());

		
		System.out.println(tree1.removeRecord("registration.boun.edu.tr", "1.1.1.2"));//tek ip si olan ve çocuğu olmayan domaini silme
		System.out.println(tree1.getAllRecords());

		
		
		
		tree1.insertRecord("wer.edu.tr", "7.7.7.7");//node ekleme
		System.out.println(tree1.getAllRecords());

		
		tree1.insertRecord("wer.edu.tr", "8.8.8.8");//var olan noda ip ekleme
		System.out.println(tree1.getAllRecords());

		
		System.out.println(tree1.queryDomain("wer.edu.tr"));//2 id li domainde robin kontrolü
		System.out.println(tree1.queryDomain("wer.edu.tr"));
		System.out.println(tree1.queryDomain("wer.edu.tr"));
		
		
		System.out.println(tree1.removeRecord("wer.edu.tr", "8.8.8.8"));//iki ip si olan domainden bir ip silme
		System.out.println(tree1.getAllRecords());

		tree1.insertRecord("online.wer.edu.tr", "7.7.8.8");//var olan domainin üzerine parça ekleme
		System.out.println(tree1.getAllRecords());

		System.out.println(tree1.removeRecord("wer.edu.tr","7.7.7.7"));//tek ip si olan ve çocuğu olan nodun ip sini silme
		System.out.println(tree1.getAllRecords());
		
		System.out.println(tree1.queryDomain("wer.edu.tr"));//ip si olmayan domainde robin kontrolü
		System.out.println(tree1.getAllRecords());
		
		
		
		
		System.out.println(tree1.removeRecord("boun.edu.tr","1234"));//olmayan bir ip yi silme
		System.out.println(tree1.getAllRecords());
		
		
		Client koray= new Client("2.3.4.5",tree1);
		Client baki=new Client("1.2.3.4",tree1);
		Client müco= new Client("3.4.5.6",tree1);
		
		tree1.insertRecord("wer.edu.tr", "5.5.5.5");//ip si olmayan node a id ekleme
		System.out.println(tree1.getAllRecords());

		tree1.insertRecord("wer.edu.tr", "9.9.9.9");//bir ip si olan node a id ekleme
		
		System.out.println(koray.sendRequest("wer.edu.tr"));
		System.out.println(baki.sendRequest("wer.edu.tr"));
		System.out.println(koray.sendRequest("wer.edu.tr"));
		System.out.println(müco.sendRequest("wer.edu.tr"));
		
		tree1.insertRecord("edu.tr", "146555244478");//var olan noda ip ekleme
		System.out.println(tree1.getAllRecords());
		
		System.out.println(tree1.queryDomain("wer.edu.tr"));//robin kontrolü
		
		System.out.println(tree1.removeRecord("edu.tr"));//çocuğu olan noddan ip silme
		System.out.println(tree1.getAllRecords());
		
		
		tree1.insertRecord("edevlet.org","0.2.3.4.5.6");
		tree1.insertRecord("edevlet1.org","1.2.3.4.5.7");
		tree1.insertRecord("edevlet2.org","2.2.3.4.5.8");
		tree1.insertRecord("edevlet3.org","3.2.3.4.5.9");
		tree1.insertRecord("edevlet4.org","4.2.3.4.5.0");
		tree1.insertRecord("edevlet5.org","5.2.3.4.5.1");
		tree1.insertRecord("edevlet6.org","6.2.3.4.5.2");
		tree1.insertRecord("edevlet7.org","7.2.3.4.5.3");
		tree1.insertRecord("edevlet8.org","8.2.3.4.5.4");
		tree1.insertRecord("edevlet9.org","9.2.3.4.5.5");
		tree1.insertRecord("edevlet10.org","10.2.3.4.4.6");
		tree1.insertRecord("edevlet11.org","111.2.3.4.3.6");
		tree1.insertRecord("edevlet12.org","1.2.7.4.2.6");
		tree1.insertRecord("edevlet13.org","1.2.9.4.5.0");
		tree1.insertRecord("edevlet.org","1.2.11.4.5.0");
		tree1.insertRecord("edevlet1.org","1.2.1.4.5.0");
		tree1.insertRecord("edevlet2.org","1.2.2.4.5.0");
		tree1.insertRecord("edevlet3.org","1.2.0.4.5.0");


		System.out.println(koray.sendRequest("edevlet.org"));
		System.out.println(koray.sendRequest("edevlet.org"));

		System.out.println(koray.sendRequest("edevlet1.org"));
		System.out.println(koray.sendRequest("edevlet1.org"));
		System.out.println(koray.sendRequest("edevlet2.org"));
		System.out.println(koray.sendRequest("edevlet2.org"));
		System.out.println(koray.sendRequest("edevlet2.org"));
		System.out.println(koray.sendRequest("edevlet2.org"));
		System.out.println(koray.sendRequest("edevlet7.org"));
		System.out.println(koray.sendRequest("edevlet8.org"));
		System.out.println(koray.sendRequest("edevlet9.org"));
		System.out.println(koray.sendRequest("edevlet10.org"));
		System.out.println(koray.sendRequest("edevlet11.org"));
		System.out.println(koray.sendRequest("edevlet12.org"));
		System.out.println(koray.sendRequest("edevlet13.org"));
		System.out.println(koray.sendRequest("edevlet.org"));
		System.out.println(koray.sendRequest("edevlet.org"));
		System.out.println(koray.sendRequest("edevlet.org"));
		System.out.println(koray.sendRequest("edevlet.org"));
		System.out.println(koray.sendRequest("edevlet.org"));
		System.out.println(koray.sendRequest("edevlet.org"));
		System.out.println(koray.sendRequest("edevlet.org"));
		
//		
//		DnsTree t = new DnsTree();
//		Client first = new Client("2018400216", t);
//		
//		t.insertRecord("cambridge.ac.uk", "8.8.8.8");
//		t.insertRecord("cmpe.boun.edu.tr", "2.2.2.2");
//		t.insertRecord("boun.edu.tr", "1.1.1.1");
//		t.insertRecord("developer.twitter.com", "6.6.6.6");
//		t.insertRecord("mail.google.com", "4.4.4.4");
//		t.insertRecord("google.com", "3.3.3.3");
//		t.insertRecord("bam", "1");
//		t.insertRecord("bam", "2");
//		t.insertRecord("bam", "3");
//		t.insertRecord("bbc.ac.uk", "7.7.7.7");
//		t.removeRecord("bam","1");
//		t.removeRecord("bam","2");
//	
//	
//	
//		
//		
//		System.out.println(first.sendRequest("bam"));
//		System.out.println(first.sendRequest("bam"));
//		System.out.println(first.sendRequest("bam"));
//		System.out.println(first.sendRequest("bam"));
//		System.out.println(first.sendRequest("bam"));
//		System.out.println(first.sendRequest("bam"));
//		System.out.println(first.sendRequest("bbc.ac.uk"));
//		System.out.println(first.getCacheList()[3]);
//		
//		
//		System.out.println(t.removeRecord("bam", "33333"));
//		System.out.println(t.getRoot().getChildNodeList().get("bam").getIpAddresses());
//		
//		
//		System.out.println(t.getRoot().getChildNodeList());
//		
//		System.out.println(t.getAllRecords());
		

	}
	

}
