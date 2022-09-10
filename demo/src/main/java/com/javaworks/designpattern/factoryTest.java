package com.javaworks.designpattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Intellij IDEA.
 * User:  penglong.huang
 * Date:  2022/9/10
 */
public class factoryTest {

}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Demo {
    String name;

}

class SimpleDemoFactory implements DemoFactory {

    @Override
    public Demo getDemo() {
        Demo demo = new Demo();
        demo.setName("test");
        return demo;
    }

    @Override
    public Demo getDemo(String name) {
        return new Demo(name);
    }
}

interface DemoFactory {
    Demo getDemo();

    Demo getDemo(String name);

}

