package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
 
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "item")

public class item {
	@XmlElement(name = "title")
	private String title;
	@XmlElement(name = "description")
	private String description;
	@XmlElement(name = "link")
	private String link;
	@XmlElement(name = "pubDate")
	private String pubDate;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getPubDate() {
		return pubDate;
	}
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

}


/* <item>
		<title>11 แข้งยอดเยี่ยมนัดที่ 3 ศึกพรีเมียร์ลีก 2013-14 โดย...ไทยรัฐออนไลน์</title>
		<guid isPermaLink="false">http://www.thairath.co.th/content/sport/367350</guid>
		<description>จบไปแล้ว 3 นัด ศึกพรีเมียร์ลีก อังกฤษ ฤดูกาล 2013-14 มีจ่าฝูงเดี่ยวๆ แล้ว นั่นคือ &quot;หงส์แดง&quot; ลิเวอร์พูล ที่ชนะรวด 3 นัด มี 9 แต้มเต็มทีมเดียว สร้างความงงงวยให้กับเกจิลูกหนังหลายสำนักว่ามาดีเกินคาด แถมยังเป็นทีมเดียวที่ไม่เสียประตูด้วย จึงไม่แปลกที่ทีมข่าวกีฬาไทยรัฐ ออนไลน์ จะเลือกมาอยู่ในทีมยอดเยี่ยมสัปดาห์นี้มากกว่าทีมอื่นสักหน่อย ซึ่งจะใช้ระบบ 4-5-1 พร้อมกับผู้จัดการทีมยอดเยี่ยม... </description>
		<link>http://www.thairath.co.th/content/sport/367350</link>
		<enclosure url="http://static.cdn.thairath.co.th/media/content/2013/09/02/367350/hr1667/120.jpg" type="image/jpeg"/>
		<pubDate>Tue, 03 Sep 2013 11:00:00 +0700</pubDate>
	</item>
	
*/
