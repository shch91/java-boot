package shch91.app.export;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author QiaoJiafei
 * @version 创建时间：2016年1月5日 上午10:17:46
 * 类说明
 */
public class TestLogin {

     private  static  HttpClient client = HttpClients.createDefault();

    public static void main(String args[]) {
        try {
            getCookieString();
          //getRedirecctUrl();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void getRedirecctUrl() throws Exception{



        String url = "http://ykjcx.yundasys.com/go_wsd.php";



        HttpPost httpPost = new HttpPost(url);

        //请求头
        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");


        //cookie

       httpPost.setHeader("Cookie","PHPSESSID=cvh1p2vf6vhurpbaaabhq8ehi5");
        //参数
        List<BasicNameValuePair> pair =new ArrayList<BasicNameValuePair>();
        pair.add(new BasicNameValuePair("lang", "C"));
        pair.add(new BasicNameValuePair("hh", "23"));
        pair.add(new BasicNameValuePair("wen", "1901976842484"));
        pair.add(new BasicNameValuePair("yzm", "14"));

        httpPost.setEntity(new UrlEncodedFormEntity(pair, "utf-8"));

        HttpResponse response = client.execute(httpPost);

        //----------判断是否重定向开始
        int code = response.getStatusLine().getStatusCode();
        String newuri="";
        if (code == 302) {
            // 跳转的目标地址是在 HTTP-HEAD 中的
            Header header = response.getFirstHeader("location");
            // 这就是跳转后的地址，再向这个地址发出新申请，以便得到跳转后的信息是啥。
            newuri = header.getValue();
            System.out.println(newuri);
            System.out.println(code);

            HttpGet httpGet = new HttpGet(newuri);

            response = client.execute(httpGet);
            code = response.getStatusLine().getStatusCode();
            System.out.println("login"+code);
        }

        //------------重定向结束
        HttpEntity entity = null;
        entity = response.getEntity();
        String s2 = EntityUtils.toString(entity, "UTF-8");
        System.out.println(s2);


    }


    public static void  getCookieString() throws Exception {
        String url = "http://ykjcx.yundasys.com/go.php?wen=1901976842484";

        CookieStore cookieStore = new BasicCookieStore();
        HttpClient httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();

        HttpGet get=new HttpGet(url);

        //HttpGet httpGet = new HttpGet("http://ykjcx.yundasys.com:1602/wsd/ykjcx/cxend.jsp?wen=932768335c96e2fbcadea07d621db147a9a9d9cbcc01bc0efe1b6f");

/*
            HttpPost httpPost = new HttpPost(url);
      //请求头
        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");

        //参数
        List<BasicNameValuePair> pair =new ArrayList<BasicNameValuePair>();

        pair.add(new BasicNameValuePair("wen", "1901976842484"));


        httpPost.setEntity(new UrlEncodedFormEntity(pair, "utf-8"));*/

        HttpResponse response = httpClient.execute(get);

        System.out.println(response);
        System.out.println("end");

    }


}