package com.wenky.starter.custom.aspect;

import com.wenky.starter.custom.aspect.example.bean.Boy;
import com.wenky.starter.custom.aspect.example.bean.Human;
import com.wenky.starter.custom.aspect.example.bean.Man;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HumanAspectTest {
  @Autowired private Boy boy;
  @Autowired private Human human;
  @Autowired private Man man;

  @Test
  public void test() {
    System.out.println("---------------------This is a Human.");
    human.say("hello!");
    human.jump();
    human.run();

    System.out.println("---------------------This is a Man.");
    man.say("hello!");
    man.jump();
    man.run();

    System.out.println("---------------------This is a Boy.");
    boy.say("hello!");
    boy.jump();
    boy.run();
  }
}
