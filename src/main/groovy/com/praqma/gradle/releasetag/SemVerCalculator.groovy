package com.praqma.gradle.releasetag

import groovy.transform.CompileStatic

@CompileStatic
class SemVerCalculator {

    private static final int TYPE_MAJOR = 2
    private static final int TYPE_MINOR = 1
    private static final int TYPE_PATCH = 0
    private static final int TYPE_UNDEF = Integer.MAX_VALUE

    // This function for the case if we need to configure commit specials titles. It is possible
    // to customize that commit message we want to catch to step release.
    int commitType(String title) {
        if (title.startsWith("patch:")) TYPE_PATCH
        else if (title.startsWith("minor:")) TYPE_MINOR
        else if (title.startsWith("major:")) TYPE_MAJOR
        else TYPE_UNDEF
    }

    // Here we calculate new release tag. The next release tag is based on the latest release
    // plus major or/and minor or/and patch unit. For now we will step every major/minor/patch
    // we found in listCommit.
    Version next(Version lastVersion, List<String> listCommit) {
        Version middle = semNumbers(listCommit)
        if(middle.major != 0) {
            return new Version(lastVersion.major + middle.major,
                                middle.minor,
                                middle.patch)
        }
        else if(middle.minor != 0) {
            return new Version(lastVersion.major,
                               lastVersion.minor + middle.minor,
                               middle.patch)
        }
        return new Version(lastVersion.major,
                           lastVersion.minor,
                           lastVersion.patch + middle.patch)
        //new Version(lastVersion.major, lastVersion.minor, lastVersion.patch + 1)
    }
    // The function should find if there is major or/and minor or/and patch commits are in the list and
    // increase appropriate field.
    Version semNumbers(List<String> listCommits) {
        Version increment = new Version(0, 0, 0)
        for (int i = 0; i < (listCommits.size() - 1); i++){
            if (listCommits[i].contains("major")) {
                increment.major = 1
                return increment
            }
            else if (listCommits[i].contains("minor") && increment.minor == 0) {
                increment.minor = 1
            }
            else if (listCommits[i].contains("patch") && increment.patch == 0) {
                increment.patch = 1
            }
        }
        return increment
    }

}
