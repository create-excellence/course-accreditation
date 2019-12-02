package com.excellent.accreditation.untils;


import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUntil {
    public static List<Map<Integer, String>> readExcelContentByList(MultipartFile file) {

        List<Map<Integer, String>> list = new ArrayList<Map<Integer,String>>();
        Workbook wb = null;
        Row row=null;
        //判断是否为excel类型文件

        if(null==file||null==file.getOriginalFilename()||(!file.getOriginalFilename().endsWith(".xls")&&!file.getOriginalFilename().endsWith(".xlsx")))
        {
            System.out.println("文件不是excel类型");
            return null;
        }

        InputStream  fis =null;
        try
        {
            //获取一个绝对地址的流
            fis =file.getInputStream();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }

        if(file.getOriginalFilename().endsWith(".xls")){
            try
            {
                //2003版本的excel，用.xls结尾
                wb = new HSSFWorkbook(fis);//得到工作簿

            }
            catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }else{
            try
            {
                //2007版本的excel，用.xlsx结尾
                wb = new XSSFWorkbook(fis);//得到工作簿
            } catch (IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return null;
            }

        }

        Sheet sheet = wb.getSheetAt(0);
        // 得到总行数
        int rowNum = sheet.getLastRowNum();
        row = sheet.getRow(0);
        int colNum = row.getPhysicalNumberOfCells();
        // 正文内容应该从第二行开始,第一行为表头的标题
        for (int i = 1; i <= rowNum; i++) {
            row = sheet.getRow(i);
            if(row==null){return  list;}
            int j = 0;
            Map<Integer,String> map = new HashMap<Integer, String>();
            while (j < colNum) {
                if( row.getCell(j)!=null){
                    row.getCell(j).setCellType(Cell.CELL_TYPE_STRING);
                    map.put(j, row.getCell(j).getStringCellValue());
                }
                j++;
            }
            list.add(map);
        }
        return list;
    }

    public static HSSFWorkbook getHSSFWorkbook(String sheetName, String []title, String [][]values, HSSFWorkbook wb){

        // 第一步，创建一个HSSFWorkbook，对应一个Excel文件
        if(wb == null){
            wb = new HSSFWorkbook();
        }

        // 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(sheetName);


        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
        HSSFRow row = sheet.createRow(0);

        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

        //声明列对象
        HSSFCell cell = null;


        //创建标题
        for(int i=0;i<title.length;i++){
            sheet.autoSizeColumn(i);//i为第几列，需要全文都单元格居中的话，需要遍历所有的列数
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(style);
        }

        //创建内容
        for(int i=0;i<values.length;i++){
            row = sheet.createRow(i + 1);
            for(int j=0;j<values[i].length;j++){
                //将内容按顺序赋给对应的列对象
                row.createCell(j).setCellValue(values[i][j]);
            }
        }
        return wb;
    }
}