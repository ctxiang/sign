package Action;

import org.apache.catalina.Session;
import sun.misc.BASE64Encoder;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
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

@WebServlet(name = "SignIn", urlPatterns = "/SignIn")
public class SignIn extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BASE64Encoder b = new BASE64Encoder();
        String result = "";
        BufferedReader in = null;
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String uName = request.getParameter("uname");
        String uPwd = request.getParameter("pwd");
        uPwd = b.encode(uPwd.getBytes());
        String req = "{\"iq\" : {\"mobileVersion\" : \"11.1.2\",\"namespace\" : \"LoginRequest\",\"model\" : \"1\",\"query\" : {\"deviceId\" :\"332681ded91f86841504b5cef5d4ef1fd5353bcad9e1c632b69f160aa851af72\",\"password\" : \"" + uPwd + "\",\"languageType\" : \"0\",\"mnc\" : \"00\",\"brandID\" : \"FEEP\",\"token\" : \"332681ded91f86841504b5cef5d4ef1fd5353bcad9e1c632b69f160aa851af72\",\"name\" : \"" + uName + "\"},\"resolution\" : \"750.000000X1334.000000\",\"version\" : \"6.6.15\"}}";
//        String url = "http://oa.flyrise.cn:8089/servlet/mobileServlet?json="+ URLEncoder.encode(req,"utf-8");
        String url = "http://10.62.20.202:8080/servlet/mobileServlet?json="+ URLEncoder.encode(req,"utf-8");
        URL realUrl = new URL(url);
        URLConnection conn = realUrl.openConnection();
        conn.setRequestProperty("accept", "*/*");
        conn.setRequestProperty("connection", "Keep-Alive");
//        conn.setRequestProperty("json",req);
        // 设置超时时间
        conn.setConnectTimeout(10000);
        conn.setReadTimeout(10000);
        // 建立实际的连接
        conn.connect();
        // 获取所有响应头字段
        Map< String,List< String>> map = conn.getHeaderFields();
        // 遍历所有的响应头字段
        String jsessionid = "";
        for (String key : map.keySet()) {
            System.out.println(key + "--->" + map.get(key));
            if("Set-Cookie".equals(key)) {
                jsessionid = map.get(key).get(0);
                HttpSession session = request.getSession();
                session.setAttribute("session", jsessionid);
            }

        }
        // 定义BufferedReader输入流来读取URL的响应
        in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) {
            result += "\n" + line;
        }
        System.out.println(result);
        if (result.indexOf("userID") != -1) {
            request.getRequestDispatcher("/signIn.jsp").forward(request, response);

        }
        OutputStream output = response.getOutputStream();
        output.write(result.getBytes("UTF-8"));
    }

    public static void main(String[] args) {
        String psw = "123";
        BASE64Encoder b = new BASE64Encoder();
        String base = b.encode(psw.getBytes());
        System.out.println(base);
    }
}
