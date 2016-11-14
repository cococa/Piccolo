package com.cocoa.piccolo

import com.android.build.gradle.AppExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

public class PluginImpl implements Plugin<Project> {


    void apply(Project project) {


        project.extensions.create('tt', Params)
        println "my name is ${project.tt.username}"

        def android = project.extensions.findByType(AppExtension)
        android.registerTransform(new MyTransform(project))

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
}