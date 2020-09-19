package cn.abalone.community.provider;

import cn.abalone.community.entity.GitHubUser;
import cn.abalone.community.entity.dto.AccessTokenDTO;
import com.alibaba.fastjson.JSON;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Create by Abalone
 * CreateTime: 2020/9/19 23:02
 */

@Component
public class GitHubProvider {
    public String getAccessToken(AccessTokenDTO dto) throws IOException {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(dto));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        assert response.body() != null;
        String string = response.body().string();
        String[] splits = string.split("&");//返回的accessToken中有多个值,以&号拆开
        String token = splits[0].split("=")[1];//第一个值为token,通过=将access_token=去掉
        System.out.println("accessToken: " + token);
        return token;
    }

    public GitHubUser getUserByGitHub(String accessToken) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + accessToken)
                .build();
        Response response = client.newCall(request).execute();
        assert response.body() != null;
        String string = response.body().string();
        return JSON.parseObject(string, GitHubUser.class);
    }

    public AccessTokenDTO getTokenDTO(String id, String secret, String code) {
        AccessTokenDTO dto = new AccessTokenDTO();
        dto.setClient_id(id);
        dto.setClient_secret(secret);
        dto.setCode(code);
        return dto;
    }
}
