package cn.miss.spring.spider.parse;

import cn.miss.spring.spider.bean.Question;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/10/16.
 */
@Component
public class QuestionParser extends AbstractParser {

    private Pattern pattern = Pattern.compile("(（.+）)(.+)");

    private final static String mathJye = "border: 0 none; direction: ltr; line-height: normal; display: inline-block; float: none; font-family: 'JyeMath','Times New Roman','宋体'; font-size: 15px; font-style: normal; font-weight: normal; letter-spacing: 1px; line-height: normal; margin: 0; padding: 0; text-align: left; text-indent: 0; text-transform: none; white-space: nowrap; word-spacing: normal; word-wrap: normal; -webkit-text-size-adjust: none;";
    private final static String mathJyeDivAndSpan = "border: 0 none; margin: 0; padding: 0; line-height: normal; text-align: left; height: auto; _height: auto; white-space: normal;";
    private final static String mathJyeTable = "border-collapse: collapse; margin: 0; padding: 0; text-align: center; vertical-align: middle; line-height: normal; font-size: inherit; *font-size: 100%; _font-size: 100%; font-style: normal; font-weight: normal; border: 0; float: none; display: inline-block; *display: inline; zoom: 0; ";
    private final static String mathJyeTableTd = "padding: 0; font-size: inherit; line-height: normal; white-space: nowrap; border: 0 none; width: auto; _height: auto;";


    @Override
    public Question optionsParse(Element element) {
        final Question question = new Question();
        question.setId(element.attr("id"));
        parseTitle(element.getElementsByClass("pt1").first(), question);
        parseOption(element.getElementsByClass("pt2").first(), question);
        return question;
    }

    @Override
    public Question spacesParse(Element element) {
        return null;
    }


    private void parseTitle(Element element, Question question) {
        final List<Node> nodes = element.childNodes();
        StringBuilder description = new StringBuilder();
        for (Node node : nodes) {
            switch (node.nodeName()) {
                case "#comment":
                    break;
                case "#text":
                    final String s = node.outerHtml();
                    final String trim = s.replace("\n", "").trim();
                    description.append(trim);
                    break;
                case "br":
                    description.append("<br />");
                    break;
                case "a":
                    final Element a = (Element) node;
                    final String href = a.attr("href");
                    question.setSourceId(href.substring(href.lastIndexOf("/") + 1));
                    question.setSource(a.html());
                    break;
                case "span":
                    final Element span = (Element) node;
                    final String className = span.className();
                    if (!Objects.equals(className, "qseq")) {
                        spanAppendStyle(span);
                        description.append(span.toString());
                    }
                    break;
                default:
                    final Element el = (Element) node;
                    description.append(el.toString());

            }
        }
        final String trim = description.toString().trim();
        if (question.getSource() == null) {
            patternTitle(trim, question);
        } else {
            question.setDescription(trim);
        }
    }


    private void spanAppendStyle(Element span) {
        if (Objects.equals(span.className(), "MathJye")) {
            styleAppend(new Elements(Collections.singletonList(span)), mathJye);
            final Elements div = span.getElementsByTag("div");
            styleAppend(div, mathJyeDivAndSpan);
            final Elements spans = span.getElementsByTag("span");
            styleAppend(spans, mathJyeDivAndSpan);
            final Elements tables = span.getElementsByTag("table");
            styleAppend(tables, mathJyeTable);
            final Elements tds = span.getElementsByTag("td");
            styleAppend(tds, mathJyeTableTd);
        }
    }

    private void styleAppend(Elements elements, String append) {
        for (Element element : elements) {
            final String style = element.attr("style");
            element.attr("style", style + ";" + append);
        }
    }

    private void patternTitle(String title, Question question) {
        final Matcher matcher = pattern.matcher(title);
        if (matcher.find()) {
            question.setSource(matcher.group(1));
            question.setDescription(matcher.group(2));
        } else {
            question.setDescription(s);
        }
    }

    private void parseOption(Element element, Question question) {
        final Elements labels = element.getElementsByTag("label");
        List<String> options = new ArrayList<>(labels.size());
        List<String> trueOptions = new ArrayList<>(labels.size());
        question.setOptions(options);
        question.setTrueOptions(trueOptions);
        for (Element label : labels) {
            final Elements children = label.children();
            StringBuilder optionStr = new StringBuilder();
            if (children.size() > 0) {
                final List<Node> nodes = label.childNodes();
                for (Node node : nodes) {
                    switch (node.nodeName()) {
                        case "#text":
                            optionStr.append(node.outerHtml());
                            break;
                        case "span":
                            Element span = (Element) node;
                            spanAppendStyle(span);
                            optionStr.append(span.toString());
                            break;
                        default:
                            optionStr.append(node.toString());
                            break;
                    }
                }
            } else {
                optionStr.append(label.html());
            }
            final String result = optionStr.toString();
            options.add(result);
            if (Objects.equals(" s", label.className())) {
                trueOptions.add(result);
            }
        }
    }


    private static final String sourceHref = "http://www.jyeoo.com/%s/report/detail/%s";
    private static final String s = "";

}
