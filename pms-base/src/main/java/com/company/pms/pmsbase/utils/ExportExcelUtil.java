package com.company.pms.pmsbase.utils;


import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author zcf
 * @Create 2018/8/1 13:31
 * @Desc 导出信息到excel文件
 */
public class ExportExcelUtil<T> {

    /**
     * 导出信息到Excel
     * @param title Excel文件标题信息
     * @param headers 实体中所有的属性及对应名字
     * @param dataset 实体集合
     * @param out 输出文件输出流，写入到哪个文件
     * @param filterCol 需要过滤的列在 hearders 中下标(从0开始)
     * @throws IOException
     */
    public void exportExcel(String title, String[][] headers, Collection<T> dataset,
                OutputStream out, List<Integer> filterCol) throws IOException {
        exportExcel(title, headers, dataset, out, "yyyy-MM-dd", filterCol);
    }

    /**
     * @param datePattern 实体属性中时间类型的显示格式, 如："yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss"
     */
    public void exportExcel(String title, String[][] headers, Collection<T> dataset,
                            OutputStream out, String datePattern, List<Integer> filterCol) throws IOException {

        if(headers == null || dataset == null ||out == null || datePattern == null){
            System.out.println("headers/dateset/out/datePattern参数不能为空");
            return;
        }
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(title);

        int columnNum = filterCol == null ? headers.length : headers.length - filterCol.size();
        CellRangeAddress region = new CellRangeAddress(0, 0, 0, columnNum - 1);
        sheet.addMergedRegion(region);

        // 得到palette颜色板，可调整和自定义颜色
        HSSFPalette palette = workbook.getCustomPalette();
        palette.setColorAtIndex(HSSFColor.TEAL.index, (byte) 68, (byte) 119, (byte) 123);
        palette.setColorAtIndex(HSSFColor.GREY_25_PERCENT.index, (byte) 242, (byte) 242, (byte) 242);
        palette.setColorAtIndex(HSSFColor.LIGHT_TURQUOISE.index, (byte) 189, (byte) 216, (byte) 219);
        palette.setColorAtIndex(HSSFColor.GREY_50_PERCENT.index, (byte) 230, (byte) 232, (byte) 234);

        // 设置第一行标题的字体样式
        HSSFFont titleFont = workbook.createFont();
        titleFont.setFontHeightInPoints((short) 20);  //设置字号
        titleFont.setColor(HSSFColor.TEAL.index);
        titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

        //设置第一行标题样式
        HSSFCellStyle titleStyle = workbook.createCellStyle();
        titleStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        titleStyle.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);
        titleStyle.setFillPattern(HSSFCellStyle.ALT_BARS);
        titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        titleStyle.setFont(titleFont);

        HSSFRow row0 = sheet.createRow(0);
        HSSFCell titleCell = row0.createCell(0);
        titleCell.setCellValue(title);
        titleCell.setCellStyle(titleStyle);
        row0.setHeightInPoints(45);

        // 第二行属性名
        region = new CellRangeAddress(1, 1, 0, columnNum - 1);
        sheet.addMergedRegion(region);

        HSSFRow row1 = sheet.createRow(1);
        HSSFCellStyle lineStyle = workbook.createCellStyle();
        lineStyle.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
        lineStyle.setFillBackgroundColor(HSSFColor.LIGHT_TURQUOISE.index);
        lineStyle.setFillPattern(HSSFCellStyle.ALT_BARS);
        row1.createCell(0).setCellStyle(lineStyle);
        row1.setHeightInPoints(5);

        HSSFFont headerFont = workbook.createFont();
        headerFont.setFontHeightInPoints((short) 15);  //设置字号
        headerFont.setColor(HSSFColor .WHITE.index);
        headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

        // 属性值样式
        HSSFRow row2 = sheet.createRow(2);
        HSSFCellStyle headerStyle = workbook.createCellStyle();
        //设置背景色
        headerStyle.setFillForegroundColor(HSSFColor.TEAL.index);
        headerStyle.setFillBackgroundColor(HSSFColor.TEAL.index);
        headerStyle.setFillPattern(HSSFCellStyle.ALT_BARS);
        headerStyle.setFont(headerFont);
        // 设置边框
        headerStyle.setBorderTop(HSSFCellStyle.BORDER_THICK);
        headerStyle.setTopBorderColor(HSSFColor.WHITE.index);
        headerStyle.setBorderRight(HSSFCellStyle.BORDER_THICK);
        headerStyle.setRightBorderColor(HSSFColor.WHITE.index);
        headerStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        row2.setHeightInPoints(35);

        int n = 0;
        for (int i = 0; i < headers.length; i++) {
            if(filterCol != null && filterCol.contains(i)){
                continue;
            }
            //动态控制列宽
            sheet.setColumnWidth(n, headers[i][1].length() * 6 * 256);
            HSSFCell cell = row2.createCell(n++);
            cell.setCellValue(headers[i][1]);
            cell.setCellStyle(headerStyle);
        }

        // 属性值字体
        HSSFFont contentFont = workbook.createFont();
        contentFont.setFontHeightInPoints((short) 13);

        HSSFCellStyle contentStyle = workbook.createCellStyle();
        contentStyle.setFillForegroundColor(HSSFColor.LEMON_CHIFFON.index);
        contentStyle.setFillBackgroundColor(HSSFColor.LEMON_CHIFFON.index);
        contentStyle.setFillPattern(HSSFCellStyle.ALT_BARS);
        contentStyle.setFont(contentFont);
        contentStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        contentStyle.setBorderBottom(HSSFCellStyle.BORDER_THICK);
        contentStyle.setBottomBorderColor(HSSFColor.WHITE.index);
        contentStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

        // 声明一个画图的顶级管理器， 用于显示图片
        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
        Iterator iterator = dataset.iterator();
        Integer index = 3;
        while (iterator.hasNext()) {
            HSSFRow row = sheet.createRow(index++);
            row.setHeightInPoints(25);
            T t = (T) iterator.next();
            BeanWrapper srcBean = new BeanWrapperImpl(t);
            n = 0;
            for(Integer i = 0; i<headers.length; i++){
                if(filterCol != null && filterCol.contains(i)){
                    continue;
                }
                HSSFCell cell = row.createCell(n++);
                cell.setCellStyle(contentStyle);
                String textValue = null;
                Long longValue = null;
                Double doubleValue = null;
                Object value = srcBean.getPropertyValue(headers[i][0]);
                if(value instanceof Boolean){
                    boolean bValue = (Boolean) value;
                    textValue = bValue ? headers[i][2] : headers[i][3];
                }else if(value instanceof Date){
                    Date date = (Date) value;
                    textValue = TimeUtil.getTime(date, datePattern);
                }else if(value instanceof Integer){
                    longValue = ((Integer) value).longValue();
                }else if(value instanceof Long){
                    longValue = (Long) value;
                }else if(value instanceof Float){
                    doubleValue = ((Float) value).doubleValue();
                }else if(value instanceof Double){
                    doubleValue = (double) value;
                }else if(value instanceof byte[]){
                    // 有图片时，设置行高为60px;
                    row.setHeightInPoints(60);
                    // 设置图片所在列宽度为80px,注意这里单位的一个换算
                    sheet.setColumnWidth(i, (short) (35.7 * 80));
                    byte[] bsValue = (byte[]) value;
                    HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0,
                            1023, 255, (short) 6, index, (short) 6, index);
                    anchor.setAnchorType(2);
                    patriarch.createPicture(anchor, workbook.addPicture(
                            bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));
                }else{
                    // 其它数据类型都当作字符串简单处理
                    // System.out.println(headers[i][0]);
                    if(value == null){
                        value = "未知";
                    }
                    textValue = value.toString();
                }

                if(longValue != null){
                    cell.setCellValue(longValue);
                }else if(doubleValue != null){
                    cell.setCellValue(doubleValue);
                }else {
                    cell.setCellValue(textValue);
                }
            }
        }

        workbook.write(out);
        out.close();
    }

}
