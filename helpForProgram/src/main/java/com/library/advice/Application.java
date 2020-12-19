package com.library.advice;

import javafx.util.Pair;

import java.util.*;

/**
 *
 * @author Asswei
 */
public class Application {
    public static void main(String[] args) throws Exception {
        Map<Pair<String,String>,Integer> libraryMap = new HashMap<>();
        String filePath1 = "F:\\Graduate\\ASE2020\\DrawCode\\draw3D.java";
        String filePath2 = "F:\\Graduate\\ASE2020\\DrawCode\\re6g3y-3d_geometry_template-master\\3d_geometry_template\\src\\main\\java\\girvate\\GrivateNode.java";
        String filePath3 = "F:\\Graduate\\ASE2020\\DrawCode\\re6g3y-3d_geometry_template-master\\3d_geometry_template\\src\\main\\java\\frameWork\\Drawer.java";
        String filePath4 = "F:\\Graduate\\ASE2020\\DrawCode\\re6g3y-3d_geometry_template-master\\3d_geometry_template\\src\\main\\java\\frameWork\\Link.java";
        String filePath5 = "F:\\Graduate\\ASE2020\\DrawCode\\re6g3y-3d_geometry_template-master\\3d_geometry_template\\src\\main\\java\\frameWork\\Node.java";
        String filePath6 = "F:\\Graduate\\ASE2020\\DrawCode\\re6g3y-3d_geometry_template-master\\3d_geometry_template\\src\\main\\java\\frameWork\\Paint.java";

        //根据一个文件名列表，生成一个MAP
        List<String> fileList = new ArrayList<>();
        fileList.add(filePath1);
        fileList.add(filePath2);
        fileList.add(filePath3);
        fileList.add(filePath4);
        fileList.add(filePath5);
        fileList.add(filePath6);
        for(String s:fileList){
            GetLibrary.getMap(s, libraryMap);
        }
        //打印字典信息
        //getLibrary.printMap(libraryMap);
        //根据用户输入的库名，查找他出现次数最多的pair对
        //String libraryName = "java.awt.geom.Line2D";
        String libraryName = "java.awt.Graphics";
        Set<Pair<String,String>> keys=libraryMap.keySet();
        int maxNum = 0;
        String candidateLibrary = "";
        for(Pair<String,String> key:keys) {
            if(libraryName.equals(key.getKey()) || libraryName.equals(key.getValue())){
                int value = libraryMap.get(key);
                if(value > maxNum){
                    maxNum = value;
                    if(libraryName.equals(key.getKey())) {
                        candidateLibrary = key.getValue();
                    }else{
                        candidateLibrary = key.getKey();
                    }
                }
            }
        }
        System.out.println("您可能需要使用这个类库："+candidateLibrary);
    }

}
