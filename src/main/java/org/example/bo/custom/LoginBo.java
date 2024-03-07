package org.example.bo.custom;

public interface LoginBo extends SuperBo{
    boolean checkAdmin();

    boolean validate(String userName, String password);
}
