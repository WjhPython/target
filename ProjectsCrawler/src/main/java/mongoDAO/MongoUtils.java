package mongoDAO;

import com.mongodb.*;
import crawler.main.CrawlerPrivateResume;


import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Created by Administrator on 2017/5/9.
 */
public class MongoUtils {
        /*
        * 连接数据库工具类
        * */
        private MongoClient mongoClient;

        private DB db = null;

        private DBCollection dbCollection = null;

        private DBObject dbObject = null;

        private CrawlerPrivateResume crawlerPrivateResume = null;

        private static MongoUtils instance;

        private static final int HASHMAPSIZE = 100;

        private static final String HOSTNAME = "120.24.97.160";

        private static final int PORT  = 27017;

        private static final String USERNAME = "jobsdb";

        private static final String PASSWORD = "db4jobs";

        private static final String DATABASE = "admin";

        private static final String COLLECTION = "job";

        private HashMap<String,String> hashMap = new HashMap<String, String>();

        private Logger logger = LoggerFactory.getLogger(getClass());

        public MongoUtils(){
                try {
                        /*数据库登录验证*/
                        ServerAddress serverAddress = new ServerAddress(HOSTNAME,PORT);
                        List<ServerAddress> serverAddressList = new ArrayList<ServerAddress>();
                        serverAddressList.add(serverAddress);
                        MongoCredential mongoCredential = MongoCredential.createMongoCRCredential(USERNAME,DATABASE,PASSWORD.toCharArray());
                        List<MongoCredential> mongoCredentialList = new ArrayList<MongoCredential>();
                        mongoCredentialList.add(mongoCredential);
                        mongoClient = new MongoClient(serverAddressList,mongoCredentialList);
                        db = mongoClient.getDB(DATABASE);
                        dbCollection = db.getCollection(COLLECTION);
                }catch(Exception e){
                        logger.error(e.getMessage()+"数据库连接失败");
                }
        }
        public static MongoUtils getInstance(){
                if (instance == null){
                        instance = new MongoUtils();
                }
                return instance;
        }
        public void getMongo(){


        }
        public void add(CrawlerPrivateResume crawlerPrivateResume){
                this.crawlerPrivateResume = crawlerPrivateResume;
                int hash = crawlerPrivateResume.toString().hashCode();
                synchronized (hashMap) {//为数据库hashMap集合添加锁
                        hashMap.put(String.valueOf(hash),
                                crawlerPrivateResume.toString());
                        if (hashMap.size() >= HASHMAPSIZE) {
                                dbObject = new BasicDBObject(hashMap);
                                dbCollection.insert(dbObject);
                                hashMap.clear();
                        }
                }


        }
}
