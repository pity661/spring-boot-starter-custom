package com.wenky.starter.custom.frame.html;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HtmlAnalysisTest {
  @Autowired private HtmlAnalysis htmlAnalysis;

  @Test
  public void htmlAnalysisTest() throws IOException {
    htmlAnalysis.htmlAnalysis();
  }
}
