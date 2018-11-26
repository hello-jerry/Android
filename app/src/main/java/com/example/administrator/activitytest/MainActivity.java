package com.example.administrator.activitytest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button zero;
    private Button one;
    private Button two;
    private Button three;
    private Button four;
    private Button five;
    private Button six;
    private Button seven;
    private Button eight;
    private Button nine;

    private Button dot;//小数点
    private Button clear;//清空
    private Button del;//删除
    private Button div;//除
    private Button multi;//乘
    private Button minus;//减
    private Button plus;//加
    private Button equals;//等于

    private Button sin;
    private Button cos;
    private Button tan;
    private Button log;

    private EditText edittext;//输入框
    private double dou=0;//结果

    private boolean flag;//清空标识

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
         zero =(Button) findViewById(R.id.zero);
         one =(Button) findViewById(R.id.one);
         two =(Button) findViewById(R.id.two);
         three =(Button) findViewById(R.id.three);
         four =(Button) findViewById(R.id.four);
         five =(Button) findViewById(R.id.five);
         six =(Button) findViewById(R.id.six);
         seven =(Button) findViewById(R.id.seven);
         eight =(Button) findViewById(R.id.eight);
         nine =(Button) findViewById(R.id.nine);
         dot=(Button) findViewById(R.id.dot);//小数点
         div=(Button) findViewById(R.id.div);//除
         multi=(Button) findViewById(R.id.multi);//乘
         minus=(Button) findViewById(R.id.minus);//减
         plus=(Button) findViewById(R.id.plus);//加
         equals=(Button) findViewById(R.id.equals);//等于
         clear=(Button) findViewById(R.id.clear);//清空
         del=(Button) findViewById(R.id.del);//删除
        /*
         sin=(Button)findViewById(R.id.sin);
         cos=(Button)findViewById(R.id.cos);
         tan=(Button)findViewById(R.id.tan);
         log=(Button)findViewById(R.id.log);
         */
         edittext=(EditText)findViewById(R.id.edittext);

         zero.setOnClickListener(this);
         one.setOnClickListener(this);
         two.setOnClickListener(this);
         three.setOnClickListener(this);
         four.setOnClickListener(this);
         five.setOnClickListener(this);
         six.setOnClickListener(this);
         seven.setOnClickListener(this);
         eight.setOnClickListener(this);
         nine.setOnClickListener(this);
         dot.setOnClickListener(this);
         div.setOnClickListener(this);
         multi.setOnClickListener(this);
         minus.setOnClickListener(this);
         plus.setOnClickListener(this);
         equals.setOnClickListener(this);
         clear.setOnClickListener(this);
         del.setOnClickListener(this);
         /*
         sin.setOnClickListener(this);
         cos.setOnClickListener(this);
         tan.setOnClickListener(this);
         log.setOnClickListener(this);
         */
    }

    public void onClick(View v) {
        String str = edittext.getText().toString();
        switch (v.getId()) {
            case R.id.zero:
            case R.id.one:
            case R.id.two:
            case R.id.three:
            case R.id.four:
            case R.id.five:
            case R.id.six:
            case R.id.seven:
            case R.id.eight:
            case R.id.nine:
            case R.id.dot:
                //小数点
                if (flag) {
                    flag = false;
                    str = "";
                    edittext.setText("");
                }
                //我们只要点击键盘，相应的数字添加在EditText上
                edittext.setText(str+((Button)v).getText());
                break;
            /*case R.id.sin:
            case R.id.cos:
            case R.id.tan:
            case R.id.log:
                if (flag) {
                    flag = false;
                    str = "";
                    edittext.setText("");
                }
                edittext.setText(str + ((Button) v).getText() + " ");
                break;*/
            case R.id.plus://加
            case R.id.minus://减
            case R.id.multi://乘
            case R.id.div://除
                if (flag) {
                    flag = false;
                    str = "";
                    edittext.setText("");
                }
                edittext.setText(str + " " + ((Button) v).getText() + " ");
                break;
            case R.id.del://删除
                  if(flag){
                    flag = false;
                    str = "";
                    edittext.setText("");
                  }
                  if(str != null && !str.equals(" ")){
                    edittext.setText(str.substring(0,str.length() - 1));
                }break;
            case R.id.clear://清空
                flag=false;
                str="";
                edittext.setText("");
                break;
            case R.id.equals://等于
                getResult();
                break;
        }
    }
    private void getResult(){
        String result=edittext.getText().toString();
        //当我们的输入框是null或者" "时我们不进行操作
        if (result==null || result.equals(" ")){
            return ;
        }
        //判断输入框是否有操作符，条件是前后是否存在空格，不存在则不进行操作
        if(!result.contains(" ")){
            return ;
        }
        if(flag){
            flag=false;
            return ;
        }
        flag=true;
        String str1=result.substring(0,result.indexOf(" "));
        String op=result.substring(result.indexOf(" ")+1,result.indexOf(" ")+2);
        String str2=result.substring(result.indexOf(" ")+3);//运算
        if(!str1.equals("")&&!str2.equals("")){
            double d1=Double.parseDouble(str1);
            double d2=Double.parseDouble(str2);
            if(op.equals("+")){
                dou=d1+d2;
            }
            else if(op.equals("-")){
                dou=d1-d2;
            }
            else if(op.equals("x")){
                dou=d1*d2;
            }
            else if(op.equals("÷")){
                if(d2==0){
                    dou=0;
                }
                else {
                    dou = d1 / d2;
                }
            }/*
            else if(op.equals("sin")){
                dou=Math.sin(d2);
            }
            else if(op.equals("cos")){
                dou=Math.cos(d2);
            }
            else if(op.equals("tan")){
                dou=Math.tan(d2);
            }
            else if(op.equals("log")){
                dou=Math.log(d2);
            }
            */
            //判断double类型的数小数部分是否为0
            if(dou%1==0){
                int i=(int) dou;
                edittext.setText(i+"");
            }
            else{
                edittext.setText(dou + "");
            }
        } else if (!str1.equals("") && str2.equals("")) {  //第二种情况:str2是空
            //这种情况就不运算了
            edittext.setText(result);
        } else if (str1.equals("") && !str2.equals("")) {  //第三种情况:str1是空
            //这种情况我们就需要判断了，因为此时str1 = 0;
            double d3 = Double.parseDouble(str2);
            if (op.equals("+")) {  //加
                dou = 0 + d3;
            } else if (op.equals("-")) { //减
                dou = 0 - d3;
            } else if (op.equals("x")) { //乘
                dou = 0;
            } else if (op.equals("÷")) {  //除
                //除数为0不计算
                dou = 0;
            }
            /*
            else if(op.equals("sin")){
                dou=Math.sin(d3);
            }
            else if(op.equals("cos")){
                dou=Math.cos(d3);
            }
            else if(op.equals("tan")){
                dou=Math.tan(d3);
            }
            else if(op.equals("log")){
                dou=Math.log(d3);
            }
            */
            //我们之前把他强转为double类型了，但是如果没有小数点，我们就是int类型
            if (!str2.contains(".")) {
                int i = (int) dou;
                edittext.setText(i + "");
            } else {  //如果有小数点
                edittext.setText(dou + "");
            }
        } else {  //最后一种情况，他们两个都是空
            //我们清空掉
            edittext.setText("");
        }
    }
}
