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

/**
 * ユーザーに関連する操作を管理するコントローラークラスです。
 * ユーザーのログイン、登録を行うエンドポイントを提供します。
 */
@Controller
@RequiredArgsConstructor
public class UserController {

    /** ユーザーに関連するサービス */
    private final UserService userService;
    
    /**
     * ログイン画面を表示します。
     * 
     * @return ログインフォームのビュー名
     */
    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // login.html を返す
    }

    /**
     * ユーザー登録画面を表示します。
     * 新規登録用のフォームを初期化して表示します。
     * 
     * @param model モデルにフォームオブジェクトを追加
     * @return ユーザー登録フォームのビュー名
     */
    @GetMapping("/login/entry")
    public String showRegisterForm(Model model) {
        model.addAttribute("userForm", new UserForm());
        return "userForm"; // userForm.html を返す
    }

    /**
     * ユーザーを登録します。
     * ユーザー情報をフォームから取得し、バリデーションを行った後、ユーザーを登録します。
     * 
     * @param userForm ユーザー情報を含むフォーム
     * @param bindingResult バリデーションエラーを格納するオブジェクト
     * @param model 画面にデータを渡すためのモデル
     * @return 登録が成功した場合はログイン画面にリダイレクト、エラーがあれば登録フォームを再表示
     */
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
        
        userService.insertUser(user); // ユーザーをサービスで登録

        model.addAttribute("message", "登録が完了しました");
        return "redirect:/login"; // 登録完了後、ログイン画面にリダイレクト
    }
}
