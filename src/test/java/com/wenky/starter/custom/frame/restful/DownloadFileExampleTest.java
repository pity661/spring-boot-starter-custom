package com.wenky.starter.custom.frame.restful;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DownloadFileExampleTest {
  @Autowired private DownloadFileExample downloadFileExample;

  @Test
  public void test() {
    downloadFileExample.downloadFile();
  }
}
