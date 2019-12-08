package com.zjxu97.costume.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ImportExcelUtil {
    private final static String excel2003L = ".xls";    //2003- 版本的excel
    private final static String excel2007U = ".xlsx";   //2007+ 版本的excel
    private final static String TABLE_NAME = "`location`";
    public static final String URL = "jdbc:mysql://localhost:3306/costume?useUnicode=true&characterEncoding=utf-8&useSSL=false";
    public static final String USER = "root";
    public static final String PASSWORD = "19970701";

    /**
     * Excel导入
     */
    private static List<List<String>> getBankListByExcel(InputStream in, String fileName) throws Exception {
        List<List<String>> list;
        //创建Excel工作薄
        Workbook work = getWorkbook(in, fileName);
        Sheet sheet;
        Row row;
        Cell cell;
        list = new ArrayList<>();
        //遍历Excel中所有的sheet
        for (int i = 0; i < work.getNumberOfSheets(); i++) {
            sheet = work.getSheetAt(i);
            if (sheet == null) {
                continue;
            }
            //遍历当前sheet中的所有行
            //包涵头部，所以要小于等于最后一列数,这里也可以在初始值加上头部行数，以便跳过头部
            for (int j = 0; j <= sheet.getLastRowNum(); j++) {
                //读取一行
                row = sheet.getRow(j);
                //去掉空行和表头
                if (row == null || row.getFirstCellNum() == j) {
                    continue;
                }
                //遍历所有的列
                List<String> li = new ArrayList<>();
                for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
                    cell = row.getCell(y);
                    li.add(cell == null ? "" : cell.toString());
                }
                list.add(li);
            }
        }
        return list;
    }

    /**
     * 描述：根据文件后缀，自适应上传文件的版本
     */
    private static Workbook getWorkbook(InputStream inStr, String fileName) throws Exception {
        Workbook wb;
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if (excel2003L.equals(fileType)) {
            wb = new HSSFWorkbook(inStr);  //2003-
        } else if (excel2007U.equals(fileType)) {
            wb = new XSSFWorkbook(inStr);  //2007+
        } else {
            throw new Exception("error!");
        }
        return wb;
    }

    public static void main(String[] args) throws Exception {
        String filepath = "/Users/zjxu97/Files/area.xlsx";
        //1.加载驱动程序
        Class.forName("com.mysql.jdbc.Driver");
        //2. 获得数据库连接
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        //3.操作数据库，实现增删改查
        Statement stmt = conn.createStatement();

        FileInputStream inputStream = new FileInputStream(new File(filepath));
        List<List<String>> list = ImportExcelUtil.getBankListByExcel(inputStream, filepath);
//        System.out.print(list);
        List<Location> locationList = new ArrayList<>();
        for (List<String> strings : list) {
            Location location = new Location();
            for (int i = 0; i < strings.size(); i++) {
                String item = strings.get(i);
                switch (i) {
                    case 0:
                        item = item.substring(0, item.length() - 2);
                        location.setId(item);
                        break;
                    case 1:
                        item = item.substring(0, item.length() - 2);
                        location.setParentId(item);
                        break;
                    case 2:
                        item = item.substring(0, item.length() - 2);
                        location.setLevelType(item);
                        break;
                    case 3:
                        location.setName(item);
                        break;
                    case 4:
                        location.setProvince(item);
                        break;
                    case 5:
                        location.setCity(item);
                        break;
                    case 6:
                        location.setDistrict(item);
                        break;
                    default:
                        break;
                }
            }
            locationList.add(location);
        }

        for (Location location : locationList) {
            String sql = "INSERT INTO " + TABLE_NAME
                    + " VALUES( '"
                    + location.getId() + "' , '"
                    + location.getParentId() + "' , '"
                    + location.getLevelType() + "' , '"
                    + location.getName() + "' , '"
                    + location.getProvince() + "' , '"
                    + location.getCity() + "' , '"
                    + location.getDistrict() + "' );";

//            System.out.println(location.toString());
            System.out.println(sql);
            int i = stmt.executeUpdate(sql);
            System.out.println("      " + i);
        }

    }
}


