package com.sleepz.onnoff.springboot.web;

//import com.jojoldu.book.springboot.config.auth.LoginUser;

import com.sleepz.onnoff.springboot.config.auth.LoginUser;
import com.sleepz.onnoff.springboot.config.auth.dto.SessionUser;
import com.sleepz.onnoff.springboot.domain.user.User;
import lombok.RequiredArgsConstructor;
import com.sleepz.onnoff.springboot.service.PostsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.sleepz.onnoff.springboot.service.PostsService;
import com.sleepz.onnoff.springboot.web.dto.PostsResponseDto;
import org.springframework.web.bind.annotation.PathVariable;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
