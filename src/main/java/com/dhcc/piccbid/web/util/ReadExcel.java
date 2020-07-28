package com.dhcc.piccbid.web.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ReadExcel {
	
	public static String[][] readExcel(String path, String config, HttpServletRequest request, HttpServletResponse response) throws IOException
    {
		
		String storageFileName = request.getServletContext().getRealPath("") + path;
        config = storageFileName + config;
        File f=new File(config);
        int flag =  getExcelStyle(config);
        if(!f.exists()){
        	System.out.println("config"+config);
        	System.out.println("不存在");
            return null;
        }
        FileInputStream fs = new FileInputStream(f);
        //create a workbook
        Workbook wb = null;
        if(flag == 1){
        	wb =  new HSSFWorkbook(fs);
        }else if(flag == 2){
        	wb =  new XSSFWorkbook(fs);
        }else{
            return null;
        }
        Sheet sheet = wb.getSheetAt(0);
        int rows=sheet.getLastRowNum();
        Row firstRow=sheet.getRow(0);
        int columns=firstRow.getLastCellNum();
        String[][] data=new  String[rows+1][columns]; 
          for(int rownum=0;rownum<=sheet.getLastRowNum();rownum++)    {
                 Row row = sheet.getRow(rownum); //for (Cell cell : row)
                 if (row == null) {
                	 System.out.println("没有数据");
                     continue;
                 }
                String value="";
                for(int cellnum=0;cellnum<columns;cellnum++){
                    Cell cell=row.getCell(cellnum);
                    if(cell==null){
                        continue;
                    }else {
                        value="";
                    }
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_STRING:
                            value=cell.getRichStringCellValue().getString();
                            break;
                        case Cell.CELL_TYPE_NUMERIC:
                            //value=Double.toString((int)cell.getNumericCellValue());
                            cell.setCellType(Cell.CELL_TYPE_STRING);
                            value = cell.getStringCellValue();
                            break;
                        case Cell.CELL_TYPE_BOOLEAN:
                            value=Boolean.toString(cell.getBooleanCellValue());
                            break;
                        case Cell.CELL_TYPE_FORMULA:
                            value=cell.getCellFormula().toLowerCase();
                            break;
                        default:
                            value=cell.getRichStringCellValue().getString();
                    }
                    data[rownum][cellnum]=value;
                }
                
          }
        return data;
        
    }
	/** 
	* 方法名:          getExcelStyle
	* 方法功能描述:     判断选择的excl文件格式
	* @param:         是包含汉字的字符串
	* @return 1.97-2003格式Excel文件 2.2007+新格式Excel文件
	* @Author:        崔娟娟
	* @Create Date:   2016年11月2日 下午2:45:41
	*/
	private static Integer getExcelStyle(String path) {
		int result = 0;
		if (path!=null || path!="") {
		    String str = path.substring(path.lastIndexOf("."), path.length());
			if (".xls".equals(str))
			  result = 1;
			else if (".xlsx".equals(str))
			  result = 2;
			}
		return result;
	}
}
