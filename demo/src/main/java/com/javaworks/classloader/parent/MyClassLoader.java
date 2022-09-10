package com.javaworks.classloader.parent;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

/**
 * Created by Intellij IDEA.
 * User:  penglong.huang
 * Date:  2022/9/10
 */
public class MyClassLoader extends ClassLoader {
    public static void main(String[] args) {
        MyClassLoader myClassLoader = new MyClassLoader(getSystemClassLoader());
        try {
            myClassLoader.loadClass("aa");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public MyClassLoader(ClassLoader parent) {
        super(parent);
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        ClassLoader systemClassLoader = getSystemClassLoader().getParent();
        //System.out.println(systemClassLoader);
        Class<?> aClass = null;
        try {
            aClass = systemClassLoader.loadClass(name);
        } catch (Exception e) {
        }


        if (aClass == null) {
            aClass = findClass(name);
            System.out.println("findclass");
        } else {
            System.out.println("loadclass");
        }
        return aClass;
    }

    private byte[] getClassBytes(File file) throws Exception {
        // 这里要读入.class的字节，因此要使用字节流
        FileInputStream fis = new FileInputStream(file);
        FileChannel fc = fis.getChannel();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        WritableByteChannel wbc = Channels.newChannel(baos);
        ByteBuffer by = ByteBuffer.allocate(1024);

        while (true) {
            int i = fc.read(by);
            if (i == 0 || i == -1) {
                break;
            }
            by.flip();
            wbc.write(by);
            by.clear();
        }

        fis.close();

        return baos.toByteArray();
    }

    private File getClassFile(String name) {
        File file = new File("D:/codes/github/avaWorks/demo/target/classes/com/javaworks/gc/gc.class");
        return file;
    }

    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        File file = getClassFile(name);
        try {
            byte[] bytes = getClassBytes(file);
            Class<?> c = this.defineClass(name, bytes, 0, bytes.length);
            return c;
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new ClassNotFoundException(name);
    }
}