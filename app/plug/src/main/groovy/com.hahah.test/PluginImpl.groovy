package com.hahah.test

import com.android.build.gradle.AppExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.compile.JavaCompile

public class PluginImpl implements Plugin<Project> {
    void apply(Project project) {
//        project.task('testTask') << {
        def android = project.extensions.findByType(AppExtension)
        android.registerTransform(new MyTransform(project))
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