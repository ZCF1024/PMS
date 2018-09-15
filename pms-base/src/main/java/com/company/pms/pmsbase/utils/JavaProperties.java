package com.company.pms.pmsbase.utils;

import java.util.Properties;
import java.util.Set;

/**
 * @Author zcf
 * @Create 2018/8/1 9:29
 * @Desc
 */
public class JavaProperties {

    public static void main(String[] args) {

        Properties p = System.getProperties();

        System.out.println("Java 运行时环境版本: "+p.getProperty("java.version"));
        System.out.println("Java 运行时环境供应商: "+p.getProperty("java.vendor"));
        System.out.println("Java 供应商的 URL: "+p.getProperty("java.vendor.url"));
        System.out.println("Java 的安装路径: "+p.getProperty("java.home"));
        System.out.println("Java 虚拟机规范版本: "+p.getProperty("java.vm.specification.version"));
        System.out.println("Java 虚拟机规范供应商: "+p.getProperty("java.vm.specification.vendor"));
        System.out.println("Java 虚拟机规范名称: "+p.getProperty("java.vm.specification.name"));
        System.out.println("Java 虚拟机实现版本: "+p.getProperty("java.vm.version"));
        System.out.println("Java 虚拟机实现供应商: "+p.getProperty("java.vm.vendor"));
        System.out.println("Java 虚拟机实现名称: "+p.getProperty("java.vm.name"));
        System.out.println("Java 运行时环境规范版本: "+p.getProperty("java.specification.version"));
        System.out.println("Java 运行时环境规范供应商: "+p.getProperty("java.specification.vendor"));
        System.out.println("Java 运行时环境规范名称: "+p.getProperty("java.specification.name"));
        System.out.println("当前类的格式版本号: "+p.getProperty("java.class.version"));
        System.out.println("当前类所在的路径: "+p.getProperty("java.class.path"));
        System.out.println("加载库时搜索的路径列表: "+p.getProperty("java.library.path"));
        System.out.println("默认的临时文件路径: "+p.getProperty("java.io.tmpdir"));
        System.out.println("要使用的 JIT 编译器的名称: "+p.getProperty("java.compiler"));
        System.out.println("一个或多个扩展目录的路径: "+p.getProperty("java.ext.dirs"));
        System.out.println("操作系统的名称: "+p.getProperty("os.name"));
        System.out.println("操作系统的架构: "+p.getProperty("os.arch"));
        System.out.println("操作系统的版本: "+p.getProperty("os.version"));
        System.out.println("当前操作系统的文件分隔符: "+p.getProperty("file.separator"));
        System.out.println("当前操作系统的路径分隔符: "+p.getProperty("path.separator"));
        System.out.println("当前操作系统的行分隔符: "+p.getProperty("line.separator"));

        System.out.println("用户的当前账户名称: "+p.getProperty("user.name"));
        System.out.println("用户的当前工作目录: "+p.getProperty("user.dir"));
        System.out.println("用户的主目录: "+p.getProperty("user.home"));

        Properties prop = System.getProperties();
        Set<Object> keySet = prop.keySet();
        for(Object obj : keySet){
            System.out.println("System Property: {"+obj.toString()+","+System.getProperty(obj.toString())+"}");
        }

    }


}