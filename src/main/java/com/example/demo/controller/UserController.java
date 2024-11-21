package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.User;
import com.example.demo.form.UserForm;
import com.example.demo.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor

public class UserController {

    private final UserService userService;
    
    // ログイン画面を表示
    @GetMapping("/login")
    public String showLoginForm() {
    	return "login"; // login.html を返す
    }

    // ユーザー登録画面を表示
    @GetMapping("/login/entry")
    public String showRegisterForm(Model model) {
        model.addAttribute("userForm", new UserForm());
        return "userForm"; // register.html を返す
    }

    // ユーザー登録処理
    @PostMapping("/login/create")
    public String registerUser(@Valid @ModelAttribute("userForm") UserForm userForm,BindingResult bindingResult, Model model) {
        
    	if (bindingResult.hasErrors()) {
            return "register"; // バリデーションエラーがあれば登録画面に戻す
        }

        // UserForm を User エンティティに変換して登録
        User user = new User(null, userForm.getEmail(), userForm.getPassword(), userForm.getDisplayname());
        userService.insertUser(user);

        model.addAttribute("message", "登録が完了しました");
        return "redirect:/login"; // 登録完了後、ログイン画面にリダイレクト
    }

}
