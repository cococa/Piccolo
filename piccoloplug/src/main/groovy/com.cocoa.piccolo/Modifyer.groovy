package com.cocoa.piccolo

import javassist.*

public class Modifyer {

    private static ClassPool pool = ClassPool.getDefault()

    public static void injectDir(String path, String packageName) {
        println "injectDir---------"
        pool.appendClassPath(path)
        pool.appendClassPath("/Users/sj/Library/Android/sdk/platforms/android-24/android.jar")
//      pool.appendClassPath("/Users/sj/Documents/myApp/LearmProject/Android/MixpanelTest/app/build/intermediates/classes/release")


        CtClass viewClass = pool.getCtClass("android.view.View")

        File dir = new File(path)
        if (dir.isDirectory()) {
            dir.eachFileRecurse { File file ->
                String filePath = file.absolutePath

                if (filePath.endsWith(".class")
                        && !filePath.contains('R$')
                        && !filePath.contains('R.class')
                        && !filePath.contains("BuildConfig.class")) {
                    int index = filePath.indexOf(packageName);

                    boolean isMyPackage = index != -1;

                    if (isMyPackage) {

                        int end = filePath.length() - 6 // .class = 6
                        String className = filePath.substring(index, end)
                                .replace('\\', '.').replace('/', '.')
                        CtClass c = pool.getCtClass(className)


                        println "injectDir---------" + c.getName()

                        if (c.isFrozen()) {
                            c.defrost()
                        }

                        try {
//                            println "injectDir---------" + c.getName()
                            CtMethod method = c.getDeclaredMethod("onClick", [viewClass] as CtClass[])
                            method.insertBefore("com.cocoa.piccolo.piccolo.Logger.log(view);");

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
