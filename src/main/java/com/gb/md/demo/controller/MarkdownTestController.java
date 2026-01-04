package com.gb.md.demo.controller;

import com.gb.md.demo.utils.MarkdownServiceUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller; // 注意是 Controller
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller //这里我们需要使用 @Controller (而不是 @RestController)，因为我们要返回视图页面，而不是 JSON 数据。
@RequestMapping("/test")
@RequiredArgsConstructor
public class MarkdownTestController {

    private final MarkdownServiceUtil markdownServiceUtil;

    @GetMapping("/markdown")
    public String showMarkdown(Model model) {
        // 1. 模拟一段复杂的 Markdown 文本（包含标题、代码块、表格、列表）
        String originalMarkdown = """
                # 欢迎使用 CommonMark
                
                这是一个 **Spring Boot + Thymeleaf** 的整合测试。
                
                ## 1. 代码高亮示例
                ```java
                public static void main(String[] args) {
                    System.out.println("Hello World");
                }
                ```
                
                ## 2. 表格扩展测试
                | ID | 姓名 | 角色 |
                |---|---|---|
                | 1 | Admin | 管理员 |
                | 2 | User  | 普通用户 |
                
                ## 3. 引用与列表
                > 这是一个引用块。
                
                - [x] 任务一：配置 Bean
                - [x] 任务二：编写 Controller
                - [ ] 任务三：编写 HTML
                """;

        // 2. 调用 Service 将 Markdown 转为 HTML
        String htmlContent = markdownServiceUtil.parse(originalMarkdown);

        // 3. 将 HTML 放入 Model 中传递给页面
        model.addAttribute("articleContent", htmlContent);

        // 4. 返回模板名称 (对应 resources/templates/markdown-view.html)
        return "markdown-view";
    }
}