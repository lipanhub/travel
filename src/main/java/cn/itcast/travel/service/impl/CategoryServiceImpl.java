package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.CategoryDao;
import cn.itcast.travel.dao.impl.CategoryDaoImpl;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public List<Category> findAll() {

        //TODO 以下是加入Redis缓存的优化代码，由于没有安装Redis  所以直接从数据库中查询

/*        Jedis jedis = JedisUtil.getJedis();
        Set<String> categorySet = jedis.zrange("category",0,-1);

        List<Category> categories = null;

        if(null == categorySet || categorySet.size() == 0){
            System.out.println("dataBase...");
            categories = categoryDao.findAll();
            for(Category item:categories){
                jedis.zadd("category",item.getCid(),item.getCname());
            }

        }else{
            System.out.println("redis....");
            categories = new ArrayList<Category>();
            for(String item:categorySet){
                Category category = new Category();
                category.setCname(item);
                categories.add(category);
            }
        }
        return categories;*/

        return categoryDao.findAll();
    }
}
