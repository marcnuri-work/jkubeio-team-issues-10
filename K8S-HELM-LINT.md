# Helm: helm-lint task/goal

https://github.com/eclipse/jkube/issues/2613

## Description

As an Eclipse JKube user, I want to be able to examine a chart for possible issues, so that I can fix them before packaging and deploying it.

## Proposal

### JKube Kit

JKube Kit exposes a new method `lint` that lints the chart provided in the `HelmConfig` argument.
This allows to lint the charts Eclipse JKube generates.

- The linting is performed by executing [helm-java `lint` command](https://github.com/manusa/helm-java#lint).
- The user is able to provide the following linting options through the HelmConfig in the `lint` field and properties.
  - `strict`: `jkube.helm.lint.strict`
  - `quiet`: `jkube.helm.lint.quiet`
- The linting messages are logged in `white` (`[[W]]`).
- If linting fails:
  - The messages are logged using the `error` logger.
  - A RuntimeException is thrown with the message `Linting failed`.
- If linting succeeds:
  - The messages are logged using the `info` logger.
  - A message is logged using the `info` logger with the message `Linting successful`.

### Maven plugins

The Kubernetes Maven Plugin and OpenShift Maven Plugin expose a new `helm-lint` goal in a `HelmLintMojo` and `OpenshiftHelmLintMojo` that extends the basic `HelmMojo` (following a similar approach to that of `HelmPushMojo`).

The mojos are bound by default to the `LifecyclePhase.INTEGRATION_TEST` phase. 

### Gradle plugins

The Kubernetes Gradle Plugin and OpenShift Gradle Plugin expose a new `helmLint` task in a `KubernetesHelmLintTask` and `OpenShiftHelmLintTask` that extends the basic `AbstractJKubeTask` task.

An integration test proves that the task can be executed both, with a successful and a failed linting.

### Documentation

The documentation exposes for each of the Maven, Gradle, Kubernetes, and OpenShift plugins the new lint goal/task.

It includes a section with the linting options that can be provided in the `HelmConfig` argument and properties.
