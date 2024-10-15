# instanton-demo

Demo repository for the session: "Unlocking Dramatic Savings: Optimize your App and Database Footprint with Serverless Java [2281]" at the TechXchange Conference 2024 on Wednesday, Oct 23 in Las Vegas. https://reg.tools.ibm.com/flow/ibm/techxchange24/sessioncatalog/page/sessioncatalog?search=2281

## Prerequisites to checkpoint/restore a containerized application

Open Liberty version 23.0.0.6 or later supports running with InstantOn only on x86-64/amd64 architectures. All our testing was done on RHEL 9.0 and Ubuntu 22.04, but it might also be possible to run on other Linux distributions and versions if they have the following prerequisites:

The kernel must support the Linux CAP_CHECKPOINT_RESTORE capability. This capability was introduced in kernel version 5.9.

The latest version of Podman for your Linux distribution must be installed.

The Linux distribution must allow the execution of privileged container builds using Podman or Docker.

For more information about the runtime and host build system prerequisites, see the [Open Liberty InstantOn documentation](https://openliberty.io/docs/latest/instanton.html#prereq).

## Build and run an image without Checkpoint

    podman build --tag demo-standard --file Dockerfile-standard
    podman run --name demo-standard --rm -p 9011:9080 demo-standard

## Build and run an image with Checkpoint

    sudo podman build \
    --tag demo-instanton \
    --file Dockerfile-instanton \
    --cap-add=CHECKPOINT_RESTORE \
    --cap-add=SYS_PTRACE \
    --cap-add=SETPCAP \
    --security-opt seccomp=unconfined
    

Build time for the image only increases slightly.

    podman run \
    --name demo-instanton \
    --rm \
    --cap-add=CHECKPOINT_RESTORE \
    --cap-add=SETPCAP \
    --security-opt seccomp=unconfined \
    -p 9012:9080 \
    demo-instanton

