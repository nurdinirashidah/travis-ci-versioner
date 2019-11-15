# travis-ci-versioner

This is a gradle plugin which generates a version number using the Travis CI build number.

### Usage for modern gradle versions _(>= 2.1)_
```
plugins {
  id "com.bnc.gradle.travis-ci-versioner" version "1.1.0"
}
```

## Documentation

### Tasks

#### Version

The plugin automatically registers a task `version` which you can call to print out the calculated version of your project:

```
$ ./gradlew version
:version
Version: 0.0.2-SNAPSHOT

BUILD SUCCESSFUL

Total time: 1.769 secs
```

### Configuration

It is possible to configure the plugin inside the `build.gradle`.

~~~~
travisVersioner {
  major integer
  minor integer
  qualifiedBranch string (the release branch, it is expected only a single branch is used for releasing)
  ciEnvironmentVariable string (the environment variable which is set in the CI environment)
  postfix string (the string which is appended to the version when CI is not running against the release branch)
}

~~~~

If you do not provide such a configuration (or fill only partial configuration) the following defaults will be used
- _major_: `0`
- _minor_: `0`
- _qualifiedBranch_: `'master'`
- _ciEnvironmentVariable_: `'CI'`
- _postfix_: `'SNAPSHOT'`

### Releasing this plugin ###

Versions are stored as annotated tags in git. [Semantic versioning](http://semver.org) is used.

To create a new release, e.g. 1.2.3:

    git tag -a 1.2.3 -m "New release"
    git push --tags

If changes are made after version 1.2.3 then the version number be '1.3.0-SNAPSHOT' (default a minor change).

To upload the plugin to the Gradle Plugin Portal, run:

    gradle clean build publishPlugins

Note that credentials are required for uploads. They should be placed in e.g. your
~/.gradle/gradle.properties for `uploadArchives`.
See [gradle.properties](gradle.properties) for more information.
