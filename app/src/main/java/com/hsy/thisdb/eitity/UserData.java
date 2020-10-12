package com.hsy.thisdb.eitity;

/**
 * @项目名: ThisDb
 * @类位置: com.hsy.thisdb.eitity
 * @创始人: hsy
 * @创建时间: 2019/12/9 10:28
 * @类描述:
 * @修改人: hsy
 * @修改时间: 2019/12/9 10:28
 * @修改描述:
 */
public class UserData {
    private int ID;
    private String Username;
    private String Fullname;
    private String RightCodes;
    private int IsUsing;
    private String Password;

    public UserData(int ID, String username, String fullname, String rightCodes, int isUsing, String password) {
        this.ID = ID;
        Username = username;
        Fullname = fullname;
        RightCodes = rightCodes;
        IsUsing = isUsing;
        Password = password;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String fullname) {
        Fullname = fullname;
    }

    public String getRightCodes() {
        return RightCodes;
    }

    public void setRightCodes(String rightCodes) {
        RightCodes = rightCodes;
    }

    public int getIsUsing() {
        return IsUsing;
    }

    public void setIsUsing(int isUsing) {
        IsUsing = isUsing;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
