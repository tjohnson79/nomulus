package(
    default_testonly = 1,
    default_visibility = ["//java/google/registry:registry_project"],
)

licenses(["notice"])  # Apache 2.0

load("//java/com/google/testing/builddefs:GenTestRules.bzl", "GenTestRules")

java_library(
    name = "tools",
    srcs = glob([
        "*.java",
    ]),
    resources = glob([
        "testdata/*.*",
    ]),
    deps = [
        "//java/com/google/common/annotations",
        "//java/com/google/common/base",
        "//java/com/google/common/collect",
        "//java/com/google/common/io",
        "//java/com/google/common/net",
        "//java/com/google/common/reflect",
        "//java/google/registry/config",
        "//java/google/registry/flows",
        "//java/google/registry/keyring/api",
        "//java/google/registry/model",
        "//java/google/registry/rde",
        "//java/google/registry/request",
        "//java/google/registry/tmch",
        "//java/google/registry/tools",
        "//java/google/registry/tools/params",
        "//java/google/registry/tools/server",
        "//java/google/registry/util",
        "//java/google/registry/xjc",
        "//java/google/registry/xml",
        "//javatests/google/registry/model",
        "//javatests/google/registry/rde",
        "//javatests/google/registry/testing",
        "//javatests/google/registry/xml",
        "//third_party/java/appengine:appengine-api-testonly",
        "//third_party/java/appengine:appengine-remote-api-link",
        "//third_party/java/jcommander",
        "//third_party/java/joda_money",
        "//third_party/java/joda_time",
        "//third_party/java/json_simple",
        "//third_party/java/jsr305_annotations",
        "//third_party/java/jsr330_inject",
        "//third_party/java/junit",
        "//third_party/java/mockito",
        "//third_party/java/objectify:objectify-v4_1",
        "//third_party/java/re2j",
        "//third_party/java/truth",
    ],
)

GenTestRules(
    name = "GeneratedTestRules",
    test_files = glob(["**/*Test.java"]),
    deps = [":tools"],
)
