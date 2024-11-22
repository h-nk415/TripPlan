package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.User;
import com.example.demo.form.UserForm;
import com.example.demo.security.PasswordGenerator;
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
        return "userForm"; 
    }

    @PostMapping("/login/create")
    public String registerUser(@Valid @ModelAttribute("userForm") UserForm userForm,
                               BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("userForm", userForm);
            return "userForm"; // バリデーションエラーがあれば登録画面に戻す
        }

        // パスワードのハッシュ化
        String hashedPassword = PasswordGenerator.hashGenerate(userForm.getPassword());

        // UserForm を User エンティティに変換して登録
        User user = new User();
        user.setEmail(userForm.getEmail());
        user.setPassword(hashedPassword);
        user.setDisplayname(userForm.getDisplayname());
        
        userService.insertUser(user);

        model.addAttribute("message", "登録が完了しました");
        return "redirect:/login"; // 登録完了後、ログイン画面にリダイレクト
    }



}
