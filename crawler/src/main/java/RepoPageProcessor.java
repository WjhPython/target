/**
 * Created by Administrator on 2017/3/27.
 * 定制该网页的爬取器
 * author：阿朕
 */

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class RepoPageProcessor implements PageProcessor {
    private Site site;
    private String date;
    private TransferToDB transferToDB;
    private HashMap<String,String> hashMap = new HashMap<String, String>();

    public RepoPageProcessor(Cookie cookies){
        site = Site.me().setSleepTime(1)
                //添加cookie之前一定要先设置主机地址，否则cookie信息不生效
                .setDomain("http://www.ganji.com")
                //添加请求头，有些网站会根据请求头判断该请求是由浏览器发起还是由爬虫发起的
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:52.0) Gecko/20100101 Firefox/52.0")
                .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
                .addHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
                .addHeader("Accept- Encoding", "gzip, deflate")
                .addHeader("Referer", "http://anshan.ganji.com/qzshichangyingxiao/")
                .addHeader("Cookie", "statistics_clientid=me; citydomain=anshan; ganji_uuid=2787610725439670074555; __utma=32156897.1859382308.1489799497.1492437633.1492607267.12; __utmz=32156897.1490664530.6.5.utmcsr=anshan.ganji.com|utmccn=(referral)|utmcmd=referral|utmcct=/qzshichangyingxiao/; ganji_xuuid=8a20ab2d-d6db-40ba-c4a6-ae996227419e.1489720134467; webimverran=17; t3=2; NTKF_T2D_CLIENTID=guest04A42BCF-C559-1CB1-A134-DEF878449B16; sscode=9F9uYNxJ101fYGCH9FHsmJ3z; GanjiEmail=842618916%40qq.com; GanjiUserName=%E8%8C%83%E6%B3%BD%E6%9C%95; GanjiUserInfo=%7B%22user_id%22%3A332149104%2C%22email%22%3A%22842618916%40qq.com%22%2C%22username%22%3A%22%5Cu8303%5Cu6cfd%5Cu6715%22%2C%22user_name%22%3A%22%5Cu8303%5Cu6cfd%5Cu6715%22%2C%22nickname%22%3A%22Mr.+%5Cu8303%22%7D; bizs=%5B%5D; last_name=%E8%8C%83%E6%B3%BD%E6%9C%95; Hm_lvt_acb0293cec76b2e30e511701c9bf2390=1492151015,1492437630,1492607264,1492607632; Hm_lvt_655ab0c3b3fdcfa236c3971a300f3f29=1492151018,1492437630,1492607264,1492607632; Hm_lvt_8da53a2eb543c124384f1841999dcbb8=1490948345,1492151038,1492438000,1492607641; lg=1; ganji_login_act=1492607640553; __utmb=32156897.3.10.1492607267; __utmt=1; GANJISESSID=2aa26ad58ee701d66fa9b531a56c7050; Hm_lpvt_acb0293cec76b2e30e511701c9bf2390=1492607641; Hm_lpvt_655ab0c3b3fdcfa236c3971a300f3f29=1492607632; _gl_tracker=%7B%22ca_source%22%3A%22-%22%2C%22ca_name%22%3A%22-%22%2C%22ca_kw%22%3A%22-%22%2C%22ca_id%22%3A%22-%22%2C%22ca_s%22%3A%22self%22%2C%22ca_n%22%3A%22-%22%2C%22ca_i%22%3A%22-%22%2C%22sid%22%3A76432585743%7D; __utmc=32156897; Hm_lpvt_8da53a2eb543c124384f1841999dcbb8=1492607641")
                .addHeader("Connection", "keep-alive")
                .addHeader("Upgrade-Insecure-Requests", "1")
                .addHeader("Cache-Control", "max-age=0")
                ;
        //添加抓包获取的cookie信息
        for (String[] strings : cookies.cookies){
            site.addCookie(strings[0], strings[1]);
        }

        //初始化当前时间
        Date d = new Date();
        System.out.println("当前日期：" + d);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateNowStr = sdf.format(d);
        date = dateNowStr;

        //连接数据库
        transferToDB = TransferToDB.getInstance();
    }
    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等

    @Override
    // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
    public void process(Page page) {
        //long l1 = System.currentTimeMillis();
        // 部分二：定义如何抽取页面信息，并保存下来
        if (page.getUrl().toString().indexOf("x.htm") < 0){
            List<String> tempUrl;
            //dl.list-noimg:nth-child(4) > a:nth-child(2)
            System.out.println(page.getHtml().xpath("div[@data-widget='app/ms_v2/findjob/list.js#clickLocationLog']/dl/a/@href").all());
            tempUrl = page.getHtml().xpath("div[@data-widget='app/ms_v2/findjob/list.js#clickLocationLog']/dl/a/@href").all();
            System.out.println(tempUrl);
            //http://luan.ganji.com/jianli/A_ZwNkAmN1ZGtkZ0pkZGZ5ZQt3AQR2nwV0BQLmAwplZGxx.htm
            //tempUrl = page.getHtml().links().regex("(http://[a-z]+\\.ganji\\.com/jianli/[A-Z,a-z]\\_[A-Z,a-z,1-9]+x.htm)").all();
            tempUrl.add(page.getHtml().$(".next","href").toString());
            page.addTargetRequests(tempUrl);

            return;
        }

        //System.out.println("*********************************");
        page.putField("date", date);
        page.putField("location", page.getHtml().$(".fc-city", "text"));
        page.putField("uuid", page.getUrl().toString().substring(page.getUrl().toString().lastIndexOf('/')+1, page.getUrl().toString().lastIndexOf('x')));
        page.putField("name", page.getHtml().$(".name-line > strong:nth-child(1)", "text"));
        page.putField("education", page.getHtml().$(".college-line > div:nth-child(1) > b:nth-child(2)", "text"));
        page.putField("workYears", page.getHtml().$(".college-line > div:nth-child(2) > b:nth-child(2)", "text"));
        page.putField("salary", page.getHtml().$(".salary-line > div:nth-child(1) > b:nth-child(2)", "text"));
        page.putField("workArea", page.getHtml().$(".salary-line > div:nth-child(2) > b:nth-child(2)", "text"));
        page.putField("jobIntention", page.getHtml().$(".tend-line > b:nth-child(2) > a", "text").all());

        //数据整理并批量传输至数据库
        transferToDB.transferToDB(page);

/*
        //保存至本地txt文件
        try {
            //long l1 = System.currentTimeMillis();
            FileOperation.createFile(page);
//            long l2 = System.currentTimeMillis();
//            System.out.println("file time is :"+(l2-l1));
        }catch (Exception e) {
            System.out.println("无效页面: ");
            e.printStackTrace();
        }
*/

        // 部分三：从页面发现后续的url地址来抓取
        List<String> tempUrl;
        tempUrl = page.getHtml().links().regex("(http://[a-z]+\\.ganji\\.com/jianli/[\\d]+x.htm)").all();
        tempUrl.add(page.getHtml().$(".next","href").toString());
        page.addTargetRequests(tempUrl);
        //long l2 = System.currentTimeMillis();
        //System.out.println("file time is :"+(l2-l1));
    }

    @Override
    public Site getSite() {
        return site;
    }
}
