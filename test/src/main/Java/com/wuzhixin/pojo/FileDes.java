package com.wuzhixin.pojo;

public class FileDes {
    private String id;
    private String f_id;
    private Integer f_size;
    private String  f_type;
    private String  f_name;
    private String  f_creattime;
    private String  f_dir;

    public FileDes() {

    }

    public FileDes(String id, String f_id, Integer f_size, String f_type, String f_name, String f_creattime, String f_dir) {
        this.id = id;
        this.f_id = f_id;
        this.f_size = f_size;
        this.f_type = f_type;
        this.f_name = f_name;
        this.f_creattime = f_creattime;
        this.f_dir = f_dir;
    }

    public FileDes(Integer f_size, String f_type, String f_name, String f_creattime, String f_dir) {
        this.f_size = f_size;
        this.f_type = f_type;
        this.f_name = f_name;
        this.f_creattime = f_creattime;
        this.f_dir = f_dir;
    }


    public FileDes(String f_id, Integer f_size, String f_type, String f_name, String f_creattime, String f_dir) {

        this.f_id = f_id;
        this.f_size = f_size;
        this.f_type = f_type;
        this.f_name = f_name;
        this.f_creattime = f_creattime;
        this.f_dir = f_dir;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String  getF_id() {
        return f_id;
    }

    public void setF_id(String f_id) {
        this.f_id = f_id;
    }

    public Integer getF_size() {
        return f_size;
    }

    public void setF_size(Integer f_size) {
        this.f_size = f_size;
    }

    public String getF_type() {
        return f_type;
    }

    public void setF_type(String f_type) {
        this.f_type = f_type;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getF_creattime() {
        return f_creattime;
    }

    public void setF_creattime(String f_creattime) {
        this.f_creattime = f_creattime;
    }

    public String getF_dir() {
        return f_dir;
    }

    public void setF_dir(String f_dir) {
        this.f_dir = f_dir;
    }

    @Override
    public String toString() {
        return "FileDes{" +
                "f_id=" + f_id +
                ", f_size=" + f_size +
                ", f_type='" + f_type + '\'' +
                ", f_name='" + f_name + '\'' +
                ", f_creattime='" + f_creattime + '\'' +
                ", f_dir='" + f_dir + '\'' +
                '}';
    }
}
