import java.io.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

class Spider
{
	private static final max_pages_to_visit =10;
	private set<String> pages_visited = new Hashset<String>();
	private list<String> pages_to_visit = new LinkedList<String>();
	
	private String nextUrl( )
	{
		String url;
		do
		{
				url = pages_to_visit.remove(0);
				
		}while(this.pages_visited.contains(url));
		this.pages_visted.add(url);
		
		return url;
		
	}
	
	
	public void search(String url , String word)
	{
		while(this.pages_visited.size()<max_pages_to_visit)
		{
			String currentUrl;
			SpiderLeg leg=new SpiderLeg();
			if(this.pages_to_visit.isEmpty())
			{
					currentUrl=url;
					this.pages_visited.add(url);
			}
			else
			{
				currentUrl=this.nextUrl();
			}
			leg.crawl(currentUrl);
			boolean success= leg.searchWord(word);
			if(success)
			{
				System.out.println("Success keyword "+ word+" found at "+ currentUrl);
				break;
			}
			this.pages_to_visit.addAll(leg.getLinks());
		}
		System.out.println("visited "+this.pages_visited.size()+"web pages");
		
}

class SpiderTest
{
	public static void main(String args[])
	{
		Spider spider=new Spider();
		spider.search("http://arstechnica.com/", "computer");
	}
}

	