package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.FavoriteService;
import cn.itcast.travel.service.RouteService;
import cn.itcast.travel.service.impl.FavoriteServiceImpl;
import cn.itcast.travel.service.impl.RouteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {

    RouteService routeService = new RouteServiceImpl();
    FavoriteService favoriteService = new FavoriteServiceImpl();

    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageSizeStr = request.getParameter("pageSize");
        String currentPageStr = request.getParameter("currentPage");
        String cidStr = request.getParameter("cid");

        String rname = request.getParameter("rname");

        rname = new String(rname.getBytes("iso-8859-1"), "utf-8");


        int cid = 0;
        if (cidStr != null && cidStr.length() != 0 && !cidStr.equals("null")) {
            cid = Integer.parseInt(cidStr);
        }

        int pageSize = 5;
        if (pageSizeStr != null && pageSizeStr.length() != 0) {
            pageSize = Integer.parseInt(pageSizeStr);
        }

        int currentPage = 1;
        if (currentPageStr != null && currentPageStr.length() != 0) {
            currentPage = Integer.parseInt(currentPageStr);
        }

        PageBean<Route> pageBean = routeService.pageQuery(cid, currentPage, pageSize, rname);

        writeValue(pageBean, response);

    }

    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int rid = Integer.parseInt(request.getParameter("rid"));

        Route route = routeService.findOne(rid);

        writeValue(route, response);
    }

    public void isFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int rid = Integer.parseInt(request.getParameter("rid"));

        int uid = 0;

        User user = (User)request.getSession().getAttribute("user");
        if(null !=user){
            uid = user.getUid();
        }

        boolean flag = favoriteService.isFavorite(rid,uid);

        writeValue(flag,response);

    }

    public void addFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ResultInfo info = new ResultInfo();

        int rid = Integer.parseInt(request.getParameter("rid"));


        User user = (User)request.getSession().getAttribute("user");

        if(null != user){
            int uid = uid = user.getUid();
            boolean flag = favoriteService.add(rid,uid);
            if(flag){
                info.setFlag(true);
            }else{
                info.setFlag(false);
                info.setErrorMsg("服务器错误");
            }
        }else{
            info.setFlag(false);
            info.setErrorMsg("未登陆");
        }

        writeValue(info,response);

    }

}
