package com.cocoa.piccolo

import com.android.build.gradle.AppExtension
import groovy.util.slurpersupport.GPathResult
import org.gradle.api.Plugin
import org.gradle.api.Project

public class PluginImpl implements Plugin<Project> {


    private File sdkDir;


    void apply(Project project) {


        def android = project.extensions.findByType(AppExtension)

        findSdk(project)
        println findPackageName(project)

        println sdkDir.toString() + File.separator + "platforms" + File.separator + "android-24" + File.separator + "android.jar";

        android.registerTransform(new MyTransform(project, ""))

//        def android = project.extensions.findByType(AppExtension)

//        Params extension=  project.extensions.create('tt',Params)
//        project.task("printMessage")<< {
//            println extension.username
//        }
//        AndroidManifest androidManifest = new AndroidManifest();

//        try {
//            AndroidManifest androidManifest = new AndroidManifest();
//
////            androidManifest.load(project.file("src/main/AndroidManifest.xml"))
//
//            List<PropertyValue> pp = androidManifest.metaPropertyValues;
//            for (PropertyValue p : pp) {
//
//                println "PropertyValue" + p.getName() + "---" + p.getValue()
//            }
//
//            println "sdk dir" + project.getRootDir().toString()
//
//            println project.getRootProject().name
//
//
//        } catch (Exception e) {
//            println e.toString()
//        }

//        try {
//            project.extensions.create('tt', Params.class, project)
//
//            Params params = project.extensions.findByName('tt')
//
//            println project.
//            println "params.username:" + params.username
//
//        } catch (Exception e) {
//            println e.toString()
//        }
//
//
//        def android = project.extensions.findByType(AppExtension)
//
//
//        ProductFlavor aa = android.defaultConfig;
//        println aa.getApplicationId() + "---" + aa.getApplicationIdSuffix()
//
//        android.registerTransform(new MyTransform(project))

        //      project.task('testTask') << {

//            Params params = project.extensions.findByType(Params)
//            def parasm = project.extensions.findByType('params')
//
//            print "my name is " + params.username
//        }

//        project.afterEvaluate {
//            android.applicationVariants.each { variant ->
//                String name = variant.name as String
//                println "variant" + name
//                JavaCompile javaCompileTask = variant.javaCompile  //variantData.javaCompileTask
//                println "javaCompileTask" + javaCompileTask
//
//                println "Dependency" + javaCompileTask.getDependencyCacheDir();
//
//                println "Dependency  bootClasspath" +javaCompileTask.options.bootClasspath
////                javaCompileTask.classpath.each { classPool.appendClassPath(it.absolutePath) }
//                println "Dependency  absolutePath" +javaCompileTask.destinationDir.absolutePath
//
//            }
//        }

    }

    /**
     * find the sdk path
     * @param project
     */
    private void findSdk(Project project) {

        def localProperties = new File(project.getRootDir(), "local.properties")
        if (!localProperties) {
            throw new RuntimeException("No local.properties file found at ${localProperties}.")
        }
        Properties properties = new Properties()
        localProperties.withInputStream { instr ->
            properties.load(instr)
        }
        def sdkDirProp = properties.getProperty('sdk.dir')
        if (!sdkDirProp) {
            throw new RuntimeException("No sdk.dir property defined in local.properties file.")
        }
        sdkDir = new File(sdkDirProp)
        if (!sdkDir.directory) {
            throw new RuntimeException("The SDK directory '$sdkDir' specified in local.properties does not exist.")
        }
    }
    /**
     *  get project package name
     * @param project
     * @return
     */
    private String findPackageName(Project project) {
        File file = new File(project.projectDir.toString() + File.separator + "src" + File.separator + "main" + File.separator + "AndroidManifest.xml")
        def xparser = new XmlSlurper()
        GPathResult gpathResult = xparser.parse(file)
        String packageName = gpathResult.@package
        return packageName.replace(".", "/");
    }


}