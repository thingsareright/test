package servlet;

import util.MyDatabaseUtil;
import util.MyDbUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {

    private  final MyDbUtil myDbUtil = new MyDatabaseUtil().getMyDaUtilImpl();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user_name = request.getParameter("name");
        String password = request.getParameter("password");
        String tel = request.getParameter("phone");
        String sql = "SELECT * FROM user WHERE user_name =\"" + user_name + "\"";
        Object flag = myDbUtil.doDataSelect(sql, "user_name");
        if(null == flag){
            //需要注册逻辑
            sql = "INSERT INTO user(user_name, password, tel) values(\"" + user_name +"\", \"" + password + "\", \""
                    + tel + "\")";
            flag = myDbUtil.doDataChange(sql);
            if (null == flag){
                //注册失败逻辑
                response.sendRedirect("/register.html");
            } else {
                //注册成功逻辑
                response.sendRedirect("/login.html");
            }
        } else {
            //已经注册逻辑
            response.sendRedirect("/index.html");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
