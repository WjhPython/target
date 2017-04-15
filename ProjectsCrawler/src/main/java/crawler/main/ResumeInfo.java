package crawler.main;

import us.codecraft.webmagic.Page;

/**
 * 解析一个人的简历信息
 * Created by Administrator on 2017/3/28.
 */
public class ResumeInfo {
    private Page page;
    public String job;//招聘工作
    public String company;//公司名字
    public String exp;//工作经验
    public String salary;//工资
    public String workArea;//工作地点
    public String education;//学历
    public String age;//年龄限制
    public String recruitPeople;//招聘人数
    public void analyse(String value,Page page){
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
                //System.out.println(name + sex + job + age + education + exp + money + workArea);
            }
        }

    }

    @Override
    public String toString() {
        return "ResumeInfo{" +
                "job='" + job + '\'' +
                ", company='" + company + '\'' +
                ", exp='" + exp + '\'' +
                ", salary='" + salary + '\'' +
                ", age='" + age + '\'' +
                ", education='" + education + '\'' +
                ", recruitPeople='" + recruitPeople + '\'' +
                ", workArea='" + workArea + '\'' +
                '}';
    }
}
