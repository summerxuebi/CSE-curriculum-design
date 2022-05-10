import java.io.File;
import java.util.Random;
import jxl.*;

class Excelget{//读取表格中的数据
    Workbook book;
    Sheet sheet;
    int hand;
    String ans;
    String getEnglish(int num,int mode){//随机抽取一个英文单词，mode等于1时表示抽取的为没有完全掌握的单词，其他时为所有单词
        try {
            book = Workbook.getWorkbook(new File("CET6.xls"));
            sheet = book.getSheet("Sheet1");
        } catch (Exception e) {
            System.out.println("error is :" + e);
        }
        Cell cell = sheet.getCell(1, num);
        Cell cell2 = sheet.getCell(3, num);
        Cell cell3 = sheet.getCell(4, num);
        if(mode == 1){
            if(cell2.getContents().equals("1") && cell3.getContents().equals("1")) ans = null;
            else ans = cell.getContents();
        }
        else if(mode == 2) ans = cell.getContents();
        else{
            if(cell2.getContents().equals("1") && cell3.getContents().equals("1")) ans = null;
            else if(cell2.getContents().equals("-1") && cell3.getContents().equals("-1")) 
            {
                hand = -1;
                ans = cell.getContents();
            }
            else ans = cell.getContents();
        }
        book.close();
        return ans;
    }
    String getChinese(int num,int mode){//随机抽取一个中文单词，mode作用同上
        try {
            book = Workbook.getWorkbook(new File("CET6.xls"));
            sheet = book.getSheet("Sheet1");
        } catch (Exception e) {
            System.out.println("error is :" + e);
        }
        Cell cell = sheet.getCell(2, num);
        Cell cell2 = sheet.getCell(3, num);
        Cell cell3 = sheet.getCell(4, num);
        if(mode == 1){
            if(cell2.getContents().equals("1") && cell3.getContents().equals("1")) ans = null;
            else ans = cell.getContents();
        }
        else if(mode == 2) ans = cell.getContents();
        else{
            if(cell2.getContents().equals("1") && cell3.getContents().equals("1")) ans = null;
            else if(cell2.getContents().equals("-1") && cell3.getContents().equals("-1")) 
            {
                hand = -1;
                ans = cell.getContents();
            }
            else ans = cell.getContents();
        }
        book.close();
        return ans;
    }
}
class Englishchinese{//英中挑战的类
    String english;
    String rightchinese;
    String wrongchinese1;
    String wrongchinese2;
    String wrongchinese3;
    String c1 = null;
    String c2 = null;
    String c3 = null;
    String c4 = null;
    int ran;
    void getEn(){//读取数据
        Random r = new Random();
        Excelget e = new Excelget();
        ran = r.nextInt(816);
        english = e.getEnglish(ran,1);
        while(english.equals(null)){//当返回值为空时说明找到的单词为已掌握单词，需要重新找一个
            ran = r.nextInt(816);
            english = e.getEnglish(ran,1);
        }
        rightchinese = e.getChinese(ran,1);//找到单词对应的释义
        //找三个互不相同且与正确释义不同的释义
        wrongchinese1 = e.getChinese(r.nextInt(816), 2);
        while(wrongchinese1.equals(rightchinese)) wrongchinese1 = e.getChinese(r.nextInt(816), 2);
        wrongchinese2 = e.getChinese(r.nextInt(816), 2);
        while(wrongchinese2.equals(rightchinese) || wrongchinese2.equals(wrongchinese1)) wrongchinese2 = e.getChinese(r.nextInt(816), 2);
        wrongchinese3 = e.getChinese(r.nextInt(816), 2);
        while(wrongchinese3.equals(rightchinese) || wrongchinese3.equals(wrongchinese1) || wrongchinese3.equals(wrongchinese2)) wrongchinese3 = e.getChinese(r.nextInt(816), 2);
        //将四个释义随机分配到四个字符串中
        ran = r.nextInt(3) + 1;
        if(ran == 1) c1 = rightchinese;
        else if(ran == 2) c2 = rightchinese;
        else if(ran == 3) c3 = rightchinese;
        else if(ran == 4) c1 = rightchinese;
        if(c1 == null) c1 = wrongchinese1;
        else if(c2 == null) c2 = wrongchinese1;
        else if(c3 == null) c3 = wrongchinese1;
        else if(c4 == null) c4 = wrongchinese1;
        if(c1 == null) c1 = wrongchinese2;
        else if(c2 == null) c2 = wrongchinese2;
        else if(c3 == null) c3 = wrongchinese2;
        else if(c4 == null) c4 = wrongchinese2;
        if(c1 == null) c1 = wrongchinese3;
        else if(c2 == null ) c2 = wrongchinese3;
        else if(c3 == null) c3 = wrongchinese3;
        else if(c4 == null) c4 = wrongchinese3;
    }
}
class Chineseenglish{//中英挑战的类
    String chinese;
    String english;
    int ran;
    int handl = 0;
    void getCh(){//读取数据
        Random r = new Random();
        Excelget e = new Excelget();
        ran = r.nextInt(816);
        chinese = e.getChinese(ran,3);
        while(chinese.equals(null)){
            ran = r.nextInt(816);
            chinese = e.getChinese(ran,3);
        }
        if(e.hand == -1) handl = -1;
        english = e.getEnglish(ran, 1);
    }
} 