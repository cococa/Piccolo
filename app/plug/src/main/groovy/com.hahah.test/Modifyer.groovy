package com.hahah.test

import javassist.*

public class Modifyer {


    private static ClassPool pool = ClassPool.getDefault()

    public static void injectDir(String path, String packageName) {
        println "injectDir---------"
        pool.appendClassPath(path)
        pool.appendClassPath("/Users/sj/Library/Android/sdk/platforms/android-24/android.jar")
        pool.appendClassPath("/Users/sj/Documents/myApp/LearmProject/Android/MixpanelTest/app/build/intermediates/classes/release")


        CtClass viewClass = pool.getCtClass("android.view.View")

        File dir = new File(path)
        if (dir.isDirectory()) {
            dir.eachFileRecurse { File file ->
                String filePath = file.absolutePath



                //确保当前文件是class文件，并且不是系统自动生成的class文件
                if (filePath.endsWith(".class")
                        && !filePath.contains('R$')
                        && !filePath.contains('R.class')
                        && !filePath.contains("BuildConfig.class")) {
                    // 判断当前目录是否是在我们的应用包里面
                    int index = filePath.indexOf(packageName);

                    boolean isMyPackage = index != -1;

                    if (isMyPackage) {

                        int end = filePath.length() - 6 // .class = 6
                        String className = filePath.substring(index, end)
                                .replace('\\', '.').replace('/', '.')
                        //开始修改class文件
                        CtClass c = pool.getCtClass(className)


                        println "injectDir---------" + c.getName()

                        if (c.isFrozen()) {
                            c.defrost()
                        }

                        try {
//                            println "injectDir---------" + c.getName()
                            CtMethod method = c.getDeclaredMethod("onClick", [viewClass] as CtClass[])
                            method.insertBefore("com.cocoa.test.Logger.log();");

                        } catch (Exception e) {
                            println "injectDir---------" + e.toString()
                        }

                        c.writeFile(path)
                        c.detach()
                    }
                }
            }
        }
    }


}
