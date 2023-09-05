# instanton-demo

Demo repository for the session "High Performance Persistence with WebSphere Liberty InstantOn [3653]" at the TechXchange Conference 2023 on Wednesday, Sep 13 in Las Vegas. https://www.ibm.com/community/ibm-techxchange-catalog/?search=3653#/

## Prerequisites to checkpoint/restore a containerized application

Currently, Open Liberty version 23.0.0.6 or later supports running with InstantOn only on x86-64/amd64 architectures. All our testing was done on RHEL 9.0 and Ubuntu 22.04 but it might also be possible to run on other Linux distributions and versions if they have the following prerequisites:

The kernel must support the Linux CAP_CHECKPOINT_RESTORE capability. This capability was introduced in kernel version 5.9.

The latest available version of Podman for the Linux distribution must be installed.

The Linux distribution must allow the execution of privileged container builds by using Podman or Docker.

For more information about the runtime and host build system prerequisites, see the [Open Liberty InstantOn documentation](https://openliberty.io/docs/latest/instanton.html#prereq).

## Build and run an image without Checkpoint

    podman build --tag demo-standard --file Dockerfile-standard
    podman run --name demo-standard --rm -p 9011:9080 demo-standard

## Build and run an image with Checkpoint

    sudo podman build \
    --tag demo-instanton \
    --file Dockerfile-instanton \
    --cap-add=CHECKPOINT_RESTORE \
    --cap-add=SYS_PTRACE\
    --cap-add=SETPCAP \
    --security-opt seccomp=unconfined
    

Build time for the image only increses slightly.

    podman run \
    --name demo-instanton \
    --rm \
    --cap-add=CHECKPOINT_RESTORE \
    --cap-add=SETPCAP \
    --security-opt seccomp=unconfined \
    -p 9012:9080 \
    demo-instanton

