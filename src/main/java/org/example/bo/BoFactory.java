package org.example.bo;

import org.example.bo.custom.SuperBo;
import org.example.bo.custom.impl.LoginBoImpl;
import org.example.bo.custom.impl.SaveBranchBoImpl;
import org.example.bo.custom.impl.SignUpBoImpl;

public class BoFactory {

    public static BoFactory boFactory;

    private  BoFactory(){
    }
    public SuperBo getBo(BoTypes type){
        switch (type){
            case SIGN_UP:
                return new SignUpBoImpl();
            case LOGIN:
                return new LoginBoImpl();
            case SAVE_BRANCH:
                return new SaveBranchBoImpl();
            default:
                return null;
        }

    }

    public static BoFactory getBoFactory() {
        return boFactory==null?new BoFactory(): boFactory;
    }

    public static enum BoTypes {
        SIGN_UP,LOGIN,SAVE_BRANCH
    }
}
