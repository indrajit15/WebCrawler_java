import java.io.*;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

class SpiderLeg
{
	private final static USER_AGENT="Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1"
	private list<String> links= new LinkedList<String> ();
	private Document html_doc;
	
	public boolean crawl(String url)
	{
		try
		{
			Connection connection=Jsoup.connect(url).userAgent(USER_AGENT);
			Document doc =connection.get();
			this.html_doc=doc;
			
			if(connection.response().statusCode()==200)
				System.out.prinntln("\n Visting web page at " +url);
			if(!connection.contentType().contains(text/html))
				{
					System.out.prinntln("Html file contains unknown characters");
					return false;
				}
			Elements links_on_page=doc.select("a[href]");
			System.out.println("Found("+links_on_page.size()+ ")links");
			for(Element link: links_on_page)
			{
				this.links.add(link.absUrl("href"));
			}
			return true;
		}
		catch(IOException e)
		{
			System.out.println("Exception occured : "+e);
			return false;
		}
	
	}
	public boolean searchWord(String word)
	{
		if(this.html_doc==null)
		{
			System.out.println("Error , Call crawl() b efore search ");
			return false;
		}
		System.out.println("Searching for the word....");
		String bodyText=this.html_doc.body().text();
		return bodyText.toLowerCase().contains(word.toLowerCase());
	}
	
	public List<String> getLinks()
	{
		return this.links;
	}
}
	