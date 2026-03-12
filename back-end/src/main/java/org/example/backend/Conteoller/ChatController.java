package org.example.backend.Conteoller;

import jakarta.annotation.Resource;
import org.example.backend.Entity.pojo.RestBean;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ai")
public class ChatController {

    /**
     * spring ai 自动装配的 可以直接注入使用
     */
    @Resource
    private OpenAiChatModel openAiChatModel;

   @GetMapping("/chat")
    public RestBean<String> chat(@RequestParam(value = "message") String message) {
        String called=openAiChatModel.call(message);
//        System.out.println(called);
        return RestBean.success("cg",called);
    }
}
