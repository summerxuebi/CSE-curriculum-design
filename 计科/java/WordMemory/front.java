import javax.swing.*;
import java.awt.Font;
import java.awt.event.*;
import java.io.File;
import java.util.Random;
import jxl.Workbook;
import jxl.write.*;

class Wirteexcel{
    private static  WritableWorkbook wbook;
    private static  Workbook rbook;
    void writein(int x,int y,String mes)
    {
        try {
            rbook = Workbook.getWorkbook(new File("CET6.xls"));
            wbook = Workbook.createWorkbook(new File("CET6.xls"),rbook);
        } catch (Exception e) {
        }
        WritableSheet wsheet = wbook.getSheet("Sheet1");
        WritableCell wcell = wsheet.getWritableCell(x,y);
        try {
            Label l = (Label) wcell;
            l.setString(mes);
            wbook.write(); 
            wbook.close();
            rbook.close();
        } catch (Exception e){
        }
       
    }
}
class Mainframe extends JFrame implements ActionListener{
    private JPanel jpl = new JPanel();
    private JPanel ejpl = new JPanel();
    private JPanel cjpl = new JPanel();
    private JButton ecc = new JButton("英-汉挑战");
    private JButton cec = new JButton("汉-英挑战");
    private JLabel jlb = new JLabel("六级单词速记");
    private JButton ch1 = new JButton();
    private JButton ch2 = new JButton();
    private JButton ch3 = new JButton();
    private JButton ch4 = new JButton();
    private JLabel eng = new JLabel();
    private JLabel time = new JLabel();
    private JLabel wrong = new JLabel();
    private JLabel chi = new JLabel();
    private JLabel tip = new JLabel();
    private JTextField jft = new JTextField();
    private boolean flag = false;
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == ecc){
            flag = false;
            this.remove(jpl);
            this.add(ejpl);
            SwingUtilities.updateComponentTreeUI(this);
            Showtime st = new Showtime();
            st.tim = 300;
            Thread t = new Thread(st);
            t.start();
            Ectest ec;
            ec = new Ectest();
        }
        else if(e.getSource() == cec){
            flag = false;
            this.remove(jpl);
            this.add(cjpl);
            SwingUtilities.updateComponentTreeUI(this);
            Showtime st = new Showtime();
            st.tim = 600;
            Thread t = new Thread(st);
            t.start();
            Cetest ce = new Cetest();
        }
    }
    public Mainframe(){
        Font jlbfont = new Font("楷体_GB2312",Font.BOLD|Font.ITALIC,40);
        Font chfont = new Font("楷体_GB2312",Font.BOLD|Font.ITALIC,28);
        jlb.setFont(jlbfont);
        eng.setFont(jlbfont);
        chi.setFont(chfont);
        this.add(jpl);
        jpl.add(jlb);
        jpl.add(ecc);
        jpl.add(cec);
        ecc.addActionListener(this);
        cec.addActionListener(this);
        jpl.setLayout(null);
        jlb.setLocation(350,20);    jlb.setSize(500,100);
        ecc.setLocation(350,200);   ecc.setSize(250,40);
        cec.setLocation(350,300);   cec.setSize(250,40);      
        this.setSize(1000,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
    }

class Showtime implements Runnable{
    int tim;
    void getTime(int t)
    {
        tim = t;
    }
    public void run() {
        int lefttime = tim;
        while(lefttime >= 0)
        {
            String showtime = "当前剩余时间:" + Integer.toString(lefttime)  + "秒"; 
            time.setText(showtime);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) { 
            }
            lefttime--;
            if(flag == true) lefttime = 0;
        }
    }
}

class Ectest implements ActionListener{
    private String rightch;
    private int right;
    private int tot;
    private int wrongtot;
    private int poi;
    public Ectest(){
        ejpl.add(ch1);  ejpl.add(ch2);  ejpl.add(ch3);  ejpl.add(ch4);  ejpl.add(eng);  ejpl.add(time); ejpl.add(wrong);
        ejpl.setLayout(null);
        ch1.addActionListener(this);
        ch2.addActionListener(this);
        ch3.addActionListener(this);
        ch4.addActionListener(this);
        eng.setLocation(400,50);    eng.setSize(500,100);
        ch1.setLocation(200,200);   ch1.setSize(250,40);
        ch2.setLocation(550,200);   ch2.setSize(250,40);
        ch3.setLocation(200,400);   ch3.setSize(250,40);
        ch4.setLocation(550,400);   ch4.setSize(250,40);
        time.setLocation(700,500);  time.setSize(120,20);
        wrong.setLocation(700,520); wrong.setSize(120,20);     
        Englishchinese en = new Englishchinese();
        poi = en.ran;
        en.getEn();
        eng.setText(en.english);
        rightch = en.rightchinese;
        ch1.setText(en.c1);  ch2.setText(en.c2);  ch3.setText(en.c3);  ch4.setText(en.c4);
        wrong.setText("错误:0道");
    }
    public void actionPerformed(ActionEvent e){
        Wirteexcel w = new Wirteexcel();
        if(e.getSource() == ch1){
            if(ch1.getText().equals(rightch)) right = 1;
            else right = -1; 
        }
        if(e.getSource() == ch2){
            if(ch2.getText().equals(rightch)) right = 1;
            else right = -1; 
        }
        if(e.getSource() == ch3){
            if(ch3.getText().equals(rightch)) right = 1;
            else right = -1; 
        }
        if(e.getSource() == ch4){
            if(ch4.getText().equals(rightch)) right = 1;
            else right = -1; 
        }
        tot++;
        if(right == 1) w.writein(3,poi,"1");
        else{
            w.writein(3,poi,"-1");
            if(wrongtot == 2)
            {
                tot = 0;    wrongtot = 0; 
                flag = true;
                Mainframe.this.remove(ejpl);
                Mainframe.this.add(jpl);
                SwingUtilities.updateComponentTreeUI(Mainframe.this);
            }
            else{
                wrongtot ++;
                if(wrongtot == 1) wrong.setText("错误:1道");
                else if(wrongtot == 2) wrong.setText("错误:2道");   
            }
        }
        if(tot == 20)
        {
            tot = 0;    wrongtot = 0; 
            flag = true;
            Mainframe.this.remove(ejpl);
            Mainframe.this.add(jpl);
            SwingUtilities.updateComponentTreeUI(Mainframe.this);
        }
        Englishchinese en = new Englishchinese();
        en.getEn();
        eng.setText(en.english);
        rightch = en.rightchinese;
        ch1.setText(en.c1);  ch2.setText(en.c2);  ch3.setText(en.c3);  ch4.setText(en.c4);
    }   
}
class Cetest implements ActionListener{
    private String eng;
    private int to;
    private int wrongto;
    private int poi;
    public Cetest(){
        cjpl.add(chi);  cjpl.add(tip);  cjpl.add(jft);  cjpl.add(time); cjpl.add(wrong);
        cjpl.setLayout(null);
        chi.setLocation(300,50);    chi.setSize(500,100);
        tip.setLocation(300,100);    tip.setSize(500,100);
        jft.setLocation(300,300);   jft.setSize(500,50);
        time.setLocation(700,500);  time.setSize(120,20);
        wrong.setLocation(700,520); wrong.setSize(120,20);
        wrong.setText("错误:0道");
        jft.addActionListener(this);
        Chineseenglish ch = new Chineseenglish();
        ch.getCh();
        poi = ch.ran;
        chi.setText(ch.chinese);
        eng = ch.english;
        jft.setText("");
        Random r = new Random();
        int i1,i2;
        char s1,s2;
        i1 = r.nextInt(eng.length());
        i2 = r.nextInt(eng.length());
        s1 = eng.charAt(i1);
        s2 = eng.charAt(i2);
        i1++;   i2++;
        tip.setText("提示：第" + Integer.toString(i1) + "个单词为" + s1 + ",第" + Integer.toString(i2) + "个单词为" + s2);
    }
    public void actionPerformed(ActionEvent e) {
        Wirteexcel w = new Wirteexcel();    
        if(jft.getText().equals(eng)){
            w.writein(4,poi,"1");
        }
        else{
            w.writein(4,poi,"-1");
            if(wrongto == 2)
            {
                to = 0;
                wrongto = 0;
                flag = true;
                Mainframe.this.remove(cjpl);
                Mainframe.this.add(jpl);
                SwingUtilities.updateComponentTreeUI(Mainframe.this);
            }
            else{
                wrongto ++;
                if(wrongto == 1) wrong.setText("错误:1道");
                else if(wrongto == 2) wrong.setText("错误:2道");   
            }

        }
        to++;
        if(to == 20){
            to = 0;
            wrongto = 0;
            flag = true;
            Mainframe.this.remove(cjpl);
            Mainframe.this.add(jpl);
            SwingUtilities.updateComponentTreeUI(Mainframe.this);
        }
        else{
            Chineseenglish ch = new Chineseenglish();
            ch.getCh();
            chi.setText(ch.chinese);
            eng = ch.english;
            jft.setText("");
            Random r = new Random();
            int i1,i2;
            char s1,s2;
            i1 = r.nextInt(eng.length());
            i2 = r.nextInt(eng.length());
            s1 = eng.charAt(i1);
            s2 = eng.charAt(i2);
            i1++;   i2++;
            if(ch.handl != -1) tip.setText("提示：第" + Integer.toString(i1) + "个单词为" + s1 + ",第" + Integer.toString(i2) + "个单词为" + s2);
            else tip.setText("无提示");
        }
    }
}
}
