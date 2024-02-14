# Helm: helm-uninstall task/goal

https://github.com/eclipse/jkube/issues/2666

## Description

As an Eclipse JKube user, I want to be able to uninstall a chart I've previously installed using [`helm-install`](K8S-HELM-INSTALL.md), so that I can remove it from my Kubernetes/OpenShift cluster.

## Proposal

### JKube Kit

JKube Kit exposes a new method `uninstall` that uninstalls the chart release with the name provided in the `HelmConfig` argument (or the one inferred from the project's configuration).

The chart should have been previously installed using the `install` method.

- The uninstallation is performed by executing [helm-java `uninstall` command](https://github.com/manusa/helm-java#uninstall).
- The user is able to provide the following uninstallation options through the HelmConfig:
  - `releaseName`: `jkube.helm.release.name`<br/>
    Optional, if not specified, the release name should be inferred following the same procedure used to compute default image and resource names.
- If the uninstallation succeeds a message `${installName} uninstalled` is logged.
- If the uninstallation fails, error message is logged.

### Maven plugins

The Kubernetes Maven Plugin and OpenShift Maven Plugin expose a new `helm-uninstall` goal in a `HelmUninstallMojo` and `OpenshiftHelmUninstallMojo` that extends the basic `HelmMojo` (following a similar approach to that of `HelmPushMojo` and `HelmLintMojo`).

The mojos are bound by default to the `LifecyclePhase.INSTALL` phase.

Tests should be added for each of the mojos and also for `KubernetesPluginTest`, `OpenShiftPluginTest`, and `GeneratedPluginDescriptorTest`.

### Gradle plugins

The Kubernetes Gradle Plugin and OpenShift Gradle Plugin expose a new `helmUninstall` task in a `KubernetesHelmUninstallTask` and `OpenShiftHelmUninstallTask` that extends the basic `AbstractJKubeTask` task.

Individual tests should be added for the new tasks and also for the plugins to verify task precedence.

An [e2e test](https://github.com/jkubeio/jkube-integration-tests) proves that the task can be executed both, with a successful and a failed installation.

### Documentation

The documentation exposes for each of the Maven, Gradle, Kubernetes, and OpenShift plugins the new uninstall goal/task.

It includes a section with the (un)installation options that can be provided in the `HelmConfig` argument and properties.

Documentation should be reviewed to replace any reference to the `helm uninstall` CLI command with the new `helm-uninstall` goal/task.
If no such reference exists, maybe we should consider adding one, especially in the context of the `helm-install` goal/task.
