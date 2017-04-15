package crawler.main;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/21.
 */
public class Crawler implements PageProcessor
{
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);
    public String job;//招聘工作
    public String company;//公司名字
    public String exp;//工作经验
    public String salary;//工资
    public String workArea;//工作地点
    public String education;//学历
    public String age;//年龄限制
    public String recruitPeople;//招聘人数
    public String information;
    public String value;
    public String pageBoxUrl;//分页信息
    public ResumeInfo resumeinfo;
    public FileSave filesave = new FileSave();
    public void process(Page page) {
        /*解析页面中的招聘页面的url：value[1] = 'http://beihai.ganji.com/jianli/2600686746x.htm'*/
        value = page.getHtml().xpath
                ("div[@data-widget='app/ms_v2/wanted/list.js#companyAjaxBid']/dl/dt/a/@href").all().toString();
        //简历太多，检测是否有分页
        pageBoxUrl = page.getHtml().xpath("div[@class='pageBox']/ul/li/a/@href").all().toString();
        pageBoxUrl = pageBoxUrl.substring(1,pageBoxUrl.lastIndexOf(']'));

        //因为value的值是列表的的形式存储的所以，将前后的'['和']'去掉
        value = value.substring(1,value.lastIndexOf(']'));

        for (String list:value.split(", ")
                ) {
            //判断list是url还是数字
            if(list.length() == 10){//是数值
                page.addTargetRequest("http://beihai.ganji.com/zpshichangyingxiao/"+list+"x.htm");
            }
            else if(list != null && list.length() !=10){//是url
                page.addTargetRequest(list);
            }
            else{//为空
                continue;
            }
            company = page.getHtml().xpath
                    ("//*[@id=\"companyName\"]/span/a/text()").toString();
            recruitPeople = page.getHtml().xpath
                    ("//*[@id=\"wrapper\"]/div[4]/div[1]/div[4]/ul/li[6]/em/text()").toString();//招聘规模
            job = page.getHtml().xpath
                    ("div[@class='d-c-left-hear']/h1/text()").toString();//招聘职位
            age = page.getHtml().xpath
                    ("//*[@id=\"wrapper\"]/div[4]/div[1]/div[4]/ul/li[5]/em/text()").toString();//年龄限制
            education = page.getHtml().xpath("//*[@id=\"wrapper\"]/div[4]/div[1]/div[4]/ul/li[3]/em/text()").toString();
            exp = page.getHtml().xpath
                    ("//*[@id=\"wrapper\"]/div[4]/div[1]/div[4]/ul/li[4]/em/text()").toString();
            salary = page.getHtml().xpath
                    ("//*[@id=\"wrapper\"]/div[4]/div[1]/div[4]/ul/li[2]/em/text()").toString();
            workArea = page.getHtml().xpath
                    ("//*[@id=\"wrapper\"]/div[4]/div[1]/div[4]/ul/li[@class='fl w-auto']/em/text()").toString();
            /*输出个人简历信息测试*/
            if (company != null) {
                System.out.println(
                        "job='" + job + '\'' +
                        ", company='" + company + '\'' +
                        ", exp='" + exp + '\'' +
                        ", salary='" + salary + '\'' +
                        ", age='" + age + '\'' +
                        ", education='" + education + '\'' +
                        ", recruitPeople='" + recruitPeople + '\'' +
                        ", workArea='" + workArea + '\'' +
                        '}');
                //将信息写到本地
                //filesave.saveFile(information,"D://Resume.txt");
            }
        }
        //解析招聘的信息
//        resumeinfo = new ResumeInfo();
//        resumeinfo.analyse(value,page);
//        information = resumeinfo.toString();
//        if (information.split(",")[0] != null){
//            System.out.println(information);
//            //将信息写到本地
//
//        }

        //简历太多，检测是否有分页
        String pageBoxUrlExp = pageBoxUrl.split(", ")[1];
        //System.out.println("这是有分页"+pageBoxUrlExp);
        if (pageBoxUrlExp != null) {
            for (String pbe:pageBoxUrl.split(", ")
                 ) {
                if (pbe == null){
                    System.out.println(pbe+" is null");

                }                //System.out.println("新加入"+pbe);
                else{
                    page.addTargetRequest(pbe);
                }

            }
        }
    }
    public Site getSite() {
        return site;
    }
}
