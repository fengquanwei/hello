package com.fengquanwei.hello.javase.jdk.other;


import com.fengquanwei.hello.javase.jdk.basic.OnePieceRoleEnum;

import java.util.ListResourceBundle;

/**
 * 自定义的资源绑定类
 *
 * @author fengquanwei
 * @create 2017/8/4 18:04
 **/
public class OnePieceResourceBundle_zh_CN extends ListResourceBundle {
    private static final Object[][] contents = new Object[][]{
            {"Luffy", OnePieceRoleEnum.LUFFY},
            {"LuffyName", OnePieceRoleEnum.LUFFY.getChineseName()},
            {"Nami", OnePieceRoleEnum.NAMI},
            {"NamiName", OnePieceRoleEnum.NAMI.getChineseName()}
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
