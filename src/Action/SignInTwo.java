package Action;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

@WebServlet(name = "SignInTwo", urlPatterns = "/SignInTwo")
public class SignInTwo extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession s = request.getSession();
        String session = (String)s.getAttribute("session");
        String result = "";
        BufferedReader in = null;
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String uName = request.getParameter("name");
        String uPwd = request.getParameter("pwd");
        OutputStream output = response.getOutputStream();
        String req = "{\"iq\" : {\"namespace\" : \"LocationRequest\",\"model\" : \"0\",\"query\" : {\"latitude\" : \"22.371652\",\"longitude\" : \"113.573696\",\"name\" : \"广东飞企互联科技股份有限公司\",\"address\" : \"珠海市软件园路1号南方软件园B1栋5层\"}}}";
//        String url = "http://oa.flyrise.cn:8089/servlet/mobileServlet?json="+ URLEncoder.encode(req,"utf-8");
        String url = "http://10.62.20.202:8080/servlet/mobileServlet?json="+ URLEncoder.encode(req,"utf-8");
        URL realUrl = new URL(url);
        URLConnection conn = realUrl.openConnection();
        conn.setRequestProperty("accept", "*/*");
        conn.setRequestProperty("connection", "Keep-Alive");
        conn.setRequestProperty("cookie", session);
//        conn.setRequestProperty("json",req);
        // 设置超时时间
        conn.setConnectTimeout(10000);
        conn.setReadTimeout(10000);
        // 建立实际的连接
        conn.connect();
        // 获取所有响应头字段
        Map< String,List< String>> map = conn.getHeaderFields();
        // 遍历所有的响应头字段
        for (String key : map.keySet()) {
            System.out.println(key + "--->" + map.get(key));
        }
        // 定义BufferedReader输入流来读取URL的响应
        in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) {
            result += "\n" + line;
        }
        System.out.println(result);
        if (result.indexOf("\"errorCode\":\"0\"") != -1) {
            output.write("Sign In Success!".getBytes("UTF-8"));
        } else {
            output.write(result.getBytes("UTF-8"));
        }
    }
}
