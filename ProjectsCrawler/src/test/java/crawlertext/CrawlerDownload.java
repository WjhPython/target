package crawlertext;

import crawler.main.Crawler;
import crawler.main.FileSaveAndRead;
import crawler.main.ParseFile;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Text crawlerDownload page
 * Created by Administrator on 2017/3/25.
 */
public class CrawlerDownload {
    private Page page;
    private ParseFile PF = new ParseFile();
    private List<String> list = new LinkedList<String>();
    public void runCrawler() {
        Crawler crawler = new Crawler();
        Spider spider = Spider.create(new Crawler());
        list = PF.reFile();
        for (String urls:list
             ) {
            spider.addUrl(urls);//http://anshan.ganji.com/zpdianhuaxiaoshou/
        }
        spider.thread(5).start();


    }
    public static void main(String[] args){
        CrawlerDownload CD = new CrawlerDownload();
        CD.runCrawler();
    }
}
