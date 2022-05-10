//************************************************************************* */
//这是一个六级单词速记的程序，主要功能有:                                     
//汉英速记：给出一个英文和四个中文，选择正确的一项，错误两项以上会退出，限时300s
//英汉速记：给出一个中文，在输入框内写出对应的英文，错误两项以上会退出，限时500s
//两个都正确的单词会标记为已掌握，之后不会再出现
//依赖：需要jxl支持
//需要一个六级单词excel表格，第一列为英文，第二列为中文
//可能会有bug
//************************************************************************ */
public class App {
    public static void main(String[] args) throws Exception {
        new Mainframe();
    }
}