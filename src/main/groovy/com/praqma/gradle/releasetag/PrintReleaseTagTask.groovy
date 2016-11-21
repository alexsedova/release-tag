package com.praqma.gradle.releasetag

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class PrintReleaseTagTask extends DefaultTask {

    @TaskAction
    def printTag() {
        def walker = new GitTreeWalker()
        println walker.getReleaseTag()
    }
}
