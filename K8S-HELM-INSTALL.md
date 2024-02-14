# Helm: helm-install task/goal

https://github.com/eclipse/jkube/issues/2663

## Description

As an Eclipse JKube user, I want to be able to install the chart JKube generates, so that I can deploy it to my Kubernetes/OpenShift cluster.

## Proposal

### JKube Kit

JKube Kit exposes a new method `install` that installs the chart available in the coordinates (output directory, helm type, and so on -see `resolveTarballFile`-) provided in the `HelmConfig` argument.

- The installation is performed by executing [helm-java `install` command](https://github.com/manusa/helm-java#install).
- The user is able to provide the following installation options through the HelmConfig:
  - `installName`: `jkube.helm.install.name`<br/>
    Optional, if not specified, the install name should be inferred following the same procedure used to compute default image and resource names.
  - `installDependencyUpdate`: `jkube.helm.install.dependencyUpdate`<br/>
    Optional, if not provided should default to `true`.<br/>
    https://github.com/eclipse/jkube/issues/2110
  - `installWaitReady`: `jkube.helm.install.waitReady`
- The installation result is logged, fields provided include:
  - `name`
  - `namespace`
  - `status`
  - `revision`
  - `lastDeployed`
- If installation fails, error message is logged

### Maven plugins

The Kubernetes Maven Plugin and OpenShift Maven Plugin expose a new `helm-install` goal in a `HelmInstallMojo` and `OpenshiftHelmInstallMojo` that extends the basic `HelmMojo` (following a similar approach to that of `HelmPushMojo` and `HelmLintMojo`).

The mojos are bound by default to the `LifecyclePhase.INSTALL` phase.

Tests should be added for each of the mojos and also for `KubernetesPluginTest`, `OpenShiftPluginTest`, and `GeneratedPluginDescriptorTest`.

### Gradle plugins

The Kubernetes Gradle Plugin and OpenShift Gradle Plugin expose a new `helmInstall` task in a `KubernetesHelmInstallTask` and `OpenShiftHelmInstallTask` that extends the basic `AbstractJKubeTask` task.

Individual tests should be added for the new tasks and also for the plugins to verify task precedence.

An [e2e test](https://github.com/jkubeio/jkube-integration-tests) proves that the task can be executed both, with a successful and a failed installation.

### Documentation

The documentation exposes for each of the Maven, Gradle, Kubernetes, and OpenShift plugins the new install goal/task.

It includes a section with the installation options that can be provided in the `HelmConfig` argument and properties.

Documentation should be reviewed to replace any reference to the `helm install` CLI command with the new `helm-install` goal/task.
