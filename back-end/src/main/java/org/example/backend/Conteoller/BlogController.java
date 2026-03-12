package org.example.backend.Conteoller;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.example.backend.Entity.pojo.Blog;
import org.example.backend.Entity.pojo.RestBean;
import org.example.backend.Entity.pojo.User;
import org.example.backend.Entity.vo.BlogVO;
import org.example.backend.Service.BlogService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/blog")
public class BlogController {
    @Resource
    BlogService blogService;

    @PostMapping("addNew")
    public RestBean<String> addNewBlog(
            HttpSession session,
            @RequestParam("title") String title,
            @RequestParam("content") String content
    ) {
            User user=(User) session.getAttribute("user");
        if (blogService.addNewBlog(new Blog(title, user.getId(), new Date(), content)) > 0)
            return RestBean.success("发布成功～");
        else return RestBean.failure(503, "出现错误，请联系管理员");
    }

    @GetMapping("getInroduce")
    public RestBean<List<BlogVO>> getInroduceBlog(){
        return RestBean.success("获取成果",blogService.getOrderByBlogs());
    }

    @GetMapping("getBlog")
    public RestBean<BlogVO> getBlog(@RequestParam("id")int id){
        return RestBean.success("成功",blogService.getBlogById(id));
    }


}
