package com.atguigu.demo.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author YiCloud
 * @date 2020/12/21 - 20:57
 */
public class TestEsayExcel {

    public static void main(String[] args) {
        //实现excel写的操作
        //write();
        read();
    }

    private static void write(){
        //设置写入文件夹地址和excel文件名称
        String filename = "C:\\Users\\Mechrevo\\Desktop\\write.xlsx";
        //调用easyexcel里面的方法实现写操作
        EasyExcel.write(filename,DemoData.class).sheet("学生列表").doWrite(getData());
    }

    private static void read(){
        //设置写入文件夹地址和excel文件名称
        String filename = "C:\\Users\\Mechrevo\\Desktop\\write.xlsx";
        EasyExcel.read(filename,DemoData.class,new ExcelListener()).sheet().doRead();
    }

    private static List<DemoData> getData(){
        List<DemoData> list = new ArrayList<>();
        for (int i=0;i<10;i++){
            DemoData item = new DemoData();
            item.setSno(i);
            item.setSname("张三"+i);
            list.add(item);
        }
        return list;
    }

}
