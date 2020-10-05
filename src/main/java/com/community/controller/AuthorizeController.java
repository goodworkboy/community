package com.community.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.community.dto.AccessTokenDTO;
import com.community.dto.GithubUser;
import com.community.mapper.UserMapper;
import com.community.model.User;
import com.community.provider.GithubProvider;

@Controller
public class AuthorizeController {
	@Autowired
	private GithubProvider githubProvider;
	@Value("${github.client.id}")
	private String clientId;
	@Value("${github.client.secret}")
	private String clientSecret;
	@Value("${github.redirect.url}")
	private String redirectUrl;
	
	@Autowired
	private UserMapper usermapper;
	
	@GetMapping("/callback")
	
	public String callback(@RequestParam(value="code")String code,
			@RequestParam("state")String state,
			HttpServletRequest request) {
		AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
		accessTokenDTO.setCode(code);
		accessTokenDTO.setClient_id(clientId);
		accessTokenDTO.setState(state);
		accessTokenDTO.setClient_secret(clientSecret);
		accessTokenDTO.setRedirect_uri(redirectUrl);
		String accessToken=githubProvider.getAccessToken(accessTokenDTO);
		GithubUser githubUser=githubProvider.getUser(accessToken);
		if(githubUser!=null) {
			User user=new User();
			user.setToken(UUID.randomUUID().toString());
			user.setName(githubUser.getName());
			user.setAccountId(String.valueOf(githubUser.getId()));
			System.out.println(githubUser.getId());
			user.setGetCreate(System.currentTimeMillis());
			user.setGetModified(user.getGetCreate());
			usermapper.insert(user);
			request.getSession().setAttribute("user", githubUser);
			return "index";
		}else {
			return  "redirect:/";
		}
	}
}
