package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {

    private UserService userService = new UserServiceImpl();

    public void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String check = request.getParameter("check");
        HttpSession session = request.getSession();

        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");
        if(checkcode_server == null || !checkcode_server.equalsIgnoreCase(check)){
            ResultInfo info = new ResultInfo();
            info.setFlag(false);
            info.setErrorMsg("验证码错误");
            writeValue(info,response);
            return;
        }




        Map<String, String[]> map = request.getParameterMap();

        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }



        boolean flag = userService.regist(user);

        ResultInfo info = new ResultInfo();

        if(flag){
            info.setFlag(true);
        }else{
            info.setFlag(false);
            info.setErrorMsg("注册失败,账号已存在");
        }

        writeValue(info,response);
    }

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> map = request.getParameterMap();
        User tmp_user = new User();

        try {
            BeanUtils.populate(tmp_user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


        User user = userService.login(tmp_user);

        ResultInfo info = new ResultInfo();

        if(null == user){
            info.setFlag(false);
            info.setErrorMsg("用户名或密码错误");
        }else if(user.getStatus().equalsIgnoreCase("N")){
            info.setFlag(false);
            info.setErrorMsg("该账号未激活，请先前往邮箱激活");
        }else{
            request.getSession().setAttribute("user",user);//登录成功标记
            info.setFlag(true);
        }

        writeValue(info,response);
    }


    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Object user = request.getSession().getAttribute("user");
        writeValue(user,response);
    }
    public void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath() + "/login.html");

    }
    public void active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String code  = request.getParameter("code");
        if(null != code){

            boolean flag = userService.active(code);

            String msg = null;
            if(flag){

                msg = "激活成功，请<a href='http://localhost/travel/login.html'>登录</a>";
            }else{
                msg = "激活失败，请联系管理员!";
            }
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(msg);
        }

    }


}
