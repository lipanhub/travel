package com.scau.lipan.travel.web.servlet;

import com.scau.lipan.travel.domain.Category;
import com.scau.lipan.travel.service.CategoryService;
import com.scau.lipan.travel.service.impl.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/category/*")
public class CategoryServlet extends BaseServlet {

    CategoryService categoryService = new CategoryServiceImpl();

    public void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Category> categories = categoryService.findAll();

        writeValue(categories,response);



    }
}
