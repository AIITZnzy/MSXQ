package org.example.backend.Service.Impl;

import jakarta.annotation.Resource;
import org.example.backend.Entity.pojo.Blog;
import org.example.backend.Entity.vo.BlogVO;
import org.example.backend.Mapper.BlogMapper;
import org.example.backend.Mapper.UserMapper;
import org.example.backend.Service.BlogService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
    @Resource
    BlogMapper blogMapper;
    @Resource
    UserMapper userMapper;
    @Override
    public int addNewBlog(Blog blog) {
        return blogMapper.insertNewBlog(blog);
    }

    @Override
    public List<BlogVO> getOrderByBlogs() {
        List<BlogVO> result=new ArrayList<>();
        for (Blog blog : blogMapper.getBlogsOrderById()){
            System.out.println(userMapper.getUsernameById(blog.getAuthorId()));
            result.add(new BlogVO(blog,userMapper.getUsernameById(blog.getAuthorId())));
        }
        return result;
    }

    @Override
    public BlogVO getBlogById(int id) {
        Blog blog = blogMapper.getBlogById(id);
        return  new BlogVO(blog,userMapper.getUsernameById(blog.getAuthorId()));
    }
}
