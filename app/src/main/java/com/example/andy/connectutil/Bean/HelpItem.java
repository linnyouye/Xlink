package com.example.andy.connectutil.Bean;

/**
 * Created by 95815 .
 * Date:2017/4/4.
 * Writter: waiwen .
 * E-mail:iwaiwen@163.com .
 */

public class HelpItem {

    private String helpName;
    private int image_helpInfo;
    public HelpItem(String name, int Res){
    this.helpName = name;
    this.image_helpInfo =Res;

}
    public String getHelpName() {
        return helpName;
    }

    public void setHelpName(String helpName) {
        this.helpName = helpName;
    }

    public int getHelpInfo() {
        return image_helpInfo;
    }

    public void setHelpInfo(int Res) {
        this.image_helpInfo= Res;
    }
}
