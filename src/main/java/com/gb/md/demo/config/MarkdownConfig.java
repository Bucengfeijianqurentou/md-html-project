package com.gb.md.demo.config;

import org.commonmark.Extension;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.ext.heading.anchor.HeadingAnchorExtension;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class MarkdownConfig {

    /**
     * 定义所有需要的扩展
     * 这里是一个私有方法，供下面两个 Bean 共用，保证解析和渲染的配置一致
     */
    private List<Extension> getExtensions() {
        return Arrays.asList(
                TablesExtension.create(),       // 表格支持
                HeadingAnchorExtension.create() // 标题自动生成 ID
        );
    }

    /**
     * 注册 Parser Bean
     */
    @Bean
    public Parser markdownParser() {
        return Parser.builder()
                .extensions(getExtensions())
                .build();
    }

    /**
     * 注册 HtmlRenderer Bean
     */
    @Bean
    public HtmlRenderer htmlRenderer() {
        return HtmlRenderer.builder()
                .extensions(getExtensions())
                .escapeHtml(true) // 为了安全，默认转义 HTML 标签防止 XSS
                .build();
    }
}
