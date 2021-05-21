package com.example.dduplacement;

public class modal_class_for_registered_company {

    String name,tech,role,type,company_package, bond,comeDate, lastDate, cgpi_above;


    public modal_class_for_registered_company(String name, String tech, String role, String type, String company_package, String bond, String comeDate, String lastDate, String cgpi_above) {
        this.name = name;
        this.tech = tech;
        this.role = role;
        this.type = type;
        this.company_package = company_package;
        this.bond = bond;
        this.comeDate = comeDate;
        this.lastDate = lastDate;
        this.cgpi_above = cgpi_above;
    }

    public modal_class_for_registered_company() {
    }

    public String getCgpi_above() {
        return cgpi_above;
    }

    public void setCgpi_above(String cgpi_above) {
        this.cgpi_above = cgpi_above;
    }

    public String getComeDate() {
        return comeDate;
    }

    public void setComeDate(String comeDate) {
        this.comeDate = comeDate;
    }

    public String getLastDate() {
        return lastDate;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }



    public String getBond() {
        return bond;
    }

    public void setBond(String bond) {
        this.bond = bond;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTech() {
        return tech;
    }

    public void setTech(String tech) {
        this.tech = tech;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCompany_package() {
        return company_package;
    }

    public void setCompany_package(String company_package) {
        this.company_package = company_package;
    }

}
