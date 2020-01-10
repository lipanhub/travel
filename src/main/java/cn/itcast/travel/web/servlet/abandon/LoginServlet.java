/*package cn.itcast.travel.web.servlet.abandon;

import ResultInfo;
import User;
import UserService;
import UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map<String, String[]> map = request.getParameterMap();
        User tmp_user = new User();

        try {
            BeanUtils.populate(tmp_user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        UserService service = new UserServiceImpl();
        User user = service.login(tmp_user);

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

        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),info);
    }
}*/
