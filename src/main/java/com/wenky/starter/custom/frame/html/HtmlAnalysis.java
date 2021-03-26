package com.wenky.starter.custom.frame.html;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @program: spring-boot-starter-custom
 * @description: example
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-03-25 10:36
 */
@Component
public class HtmlAnalysis {
  @Autowired private RestTemplate template;

  public void htmlAnalysis() throws IOException {
    String requestUrl =
        "http://tools.scientiamobile.com/?user-agent-string=Mozilla/5.0 (Linux; Android 9; V1816A Build/PKQ1.180819.001; wv) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.84 Mobile Safari/537.36 VivoBrowser/9.1.15.0";
    //    String htmlResult = template.getForObject(requestUrl, String.class);
    //    Document document = Jsoup.parse(htmlResult);
    Document document = Jsoup.connect(requestUrl).get();
    for (Element element : document.getElementsByTag("tr")) {
      if ("complete_device_name".equals(element.getElementsByClass("col-md-4 key").html())) {
        System.out.println(element.getElementsByClass("col-md-8 value").html());
        break;
      }
    }
  }
}
