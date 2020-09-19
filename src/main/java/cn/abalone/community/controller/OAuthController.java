package cn.abalone.community.controller;

import cn.abalone.community.entity.GitHubUser;
import cn.abalone.community.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

import static cn.abalone.community.OAuthData.G_ID;
import static cn.abalone.community.OAuthData.G_SECRET;

/**
 * Create by Abalone
 * CreateTime: 2020/9/19 22:08
 */

@Controller
@RequestMapping("/oauth")
public class OAuthController {
    @Autowired
    private GitHubProvider gitHubProvider;
    /**
     * 接入第三方登录(GitHub)
     *
     * @param code github回调函数提供的代码
     * @return 返回到GitHub指定的位置
     */
    @GetMapping("/github")
    public String github(@RequestParam("code") String code)throws IOException {
        GitHubUser user = gitHubProvider.getUserByGitHub(gitHubProvider.getAccessToken(gitHubProvider.getTokenDTO(G_ID,G_SECRET,code)));//获取用户数据,得到user对象
        System.out.println(user);
        return "index";
    }
    @GetMapping("/test")
    public String test(){
        System.out.println("id: "+G_ID);
        System.out.println("secret: "+G_SECRET);
        return "index";
    }
}
