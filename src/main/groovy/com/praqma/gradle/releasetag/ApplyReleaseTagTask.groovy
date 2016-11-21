package com.praqma.gradle.releasetag

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class ApplyReleaseTagTask extends DefaultTask {

    @TaskAction
    def printTag() {
        println "Appllllly"
    }
}
