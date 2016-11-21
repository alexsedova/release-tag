package com.praqma.gradle.releasetag

import org.gradle.api.Plugin
import org.gradle.api.Project
import static TagUtils.scanForRootGitDir

public class ReleaseTagPlugin implements Plugin<Project> {

    void apply(Project project) {

        project.tasks.create("printReleaseTag", PrintReleaseTagTask)
        project.tasks.create("applyReleaseTag", ApplyReleaseTagTask)

    }
}
