package com.bnc.gradle;

public class VersionPluginExtension {

  private int major = 0;
  private int minor = 0;
  private String qualifiedBranch = "master";
  private String ciEnvironmentVariable = "CI";
  private String postfix = "SNAPSHOT";

  public void setMajor(int major) {
    this.major = major;
  }

  public void setMinor(int minor) {
    this.minor = minor;
  }

  public void setPostfix(String postfix) {
    this.postfix = postfix;
  }

  public void setQualifiedBranch(String qualifiedBranch) {
    this.qualifiedBranch = qualifiedBranch;
  }

  public void setCiEnvironmentVariable(String ciEnvironmentVariable) {
    this.ciEnvironmentVariable = ciEnvironmentVariable;
  }

  public int getMajor() {
    return major;
  }

  public int getMinor() {
    return minor;
  }

  public String getQualifiedBranch() {
    return qualifiedBranch;
  }

  public String getCiEnvironmentVariable() {
    return ciEnvironmentVariable;
  }

  public String getPostfix() {
    return postfix;
  }

  @Override
  public String toString() {
    String version = String.format("%s.%s", major, minor);
    if (!calculatePatch().isEmpty()) {
      version += "." + calculatePatch();
    }

    if (!calculatePostfix().isEmpty()) {
      version += "-" + calculatePostfix();
    }
    return version;
  }

  private String calculatePatch() {
    if (System.getenv("TRAVIS_BUILD_NUMBER") != null) {
      return System.getenv("TRAVIS_BUILD_NUMBER");
    }
    return "";
  }

  private String calculatePostfix() {
    if (!isMasterBranch()) {
      return postfix;
    }
    return "";
  }

  private boolean isMasterBranch() {
    return "true".equals(System.getenv(ciEnvironmentVariable)) &&
        qualifiedBranch.equals(System.getenv("TRAVIS_BRANCH")) &&
        "false".equals(System.getenv("TRAVIS_PULL_REQUEST"));
  }
}
