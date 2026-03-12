package org.example.backend.Conteoller;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.example.backend.Entity.pojo.RestBean;
import org.example.backend.Entity.pojo.User;
import org.example.backend.Service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Resource
    UserService userService;

    @PostMapping("login")
    public RestBean<String> login(@RequestParam String username,
                                @RequestParam String password,
                                HttpSession session
    ){
        User loginUser=userService.login(username,password);
        if(loginUser == null)
        {
            return RestBean.failure(401,"账号或密码错误");
        }else{
            loginUser.setPassword("*");
            session.setAttribute("user",loginUser);
            return RestBean.success("登入成功","登入成功");
        }
    }

    @GetMapping("myinfo")
    public RestBean<User> getMyInfo(HttpSession session){
        if(session.getAttribute("user")==null) return RestBean.failure(401,"未登入");
        User theUser=(User) session.getAttribute("user");
        return RestBean.success("成功",theUser);

    }
}
