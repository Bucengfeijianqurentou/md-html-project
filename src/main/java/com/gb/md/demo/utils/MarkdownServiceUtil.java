package com.gb.md.demo.utils;

import lombok.RequiredArgsConstructor;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor // Lombok 会自动生成包含 parser 和 renderer 的构造函数
public class MarkdownServiceUtil {

    // 直接从 Spring 容器中注入配置好的 Bean
    private final Parser parser;
    private final HtmlRenderer renderer;

    public String parse(String markdown) {
        if (markdown == null) {
            return "";
        }
        Node document = parser.parse(markdown);
        return renderer.render(document);
    }
}
