package servlet;

import util.MyDatabaseUtil;
import util.MyDbUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    private  final MyDbUtil myDbUtil = new MyDatabaseUtil().getMyDaUtilImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user_name = request.getParameter("name");
        String password = request.getParameter("password");
        String sql = "SELECT * FROM user WHERE user_name =\"" + user_name + "\" and password =\"" + password
                + "\"";
        Object flag = myDbUtil.doDataSelect(sql, "user_name");
        if(null == flag){
            //登录失败逻辑 TODO
            response.sendRedirect("/login.html");
        } else {
            request.getSession().setAttribute("USER_NAME", user_name);
            response.sendRedirect("/index.html");
        }

    }
}
