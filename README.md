# Introduction
This repo contains the i.MX MPU project Matter related Yocto recipes. Below is a list of modules that will be built with the meta-matter repo integrated.
 - Matter (CHIP) : https://github.com/project-chip/connectedhomeip
 - OpenThread Daemon: https://github.com/openthread/openthread
 - OpenThread Border Router: https://github.com/openthread/ot-br-posix

All the software components revision are based on [project Matter TE9](https://github.com/project-chip/connectedhomeip/commits/TE9).

The the Following Matter related binaries will be installed into the Yocto image root filesystem with this recipe repo:
 - ot-daemon: OpenThread Daemon for OpenThread client using OpenThread v1.2 spec (For IW612)
 - ot-client-ctl: OpenThread ctrl tool for OpenThread client using OpenThread v1.2 spec (For IW612)
 - otbr-agent: OpenThread Border Router agent using OpenThread v1.2 spec (For IW612)
 - ot-ctl: OpenThread Border Router ctrl tool using OpenThread v1.2 spec (For IW612)
 - otbr-web: OpenThread Border Router web management daemon

# How to build the Yocto image with integrated OpenThread Border Router
Yocto build environment must first be setup.

The Yocto source code is maintained with a repo manifest, the tool repo is used to download the source code.
This document is tested with the i.MX Yocto 5.10.35_2.0.0 release. The hardware tested in this documentation is the i.MX 8M Mini EVK.
Run the commands below to download this release:

    $ mkdir ~/bin
    $ curl http://commondatastorage.googleapis.com/git-repo-downloads/repo > ~/bin/repo
    $ chmod a+x ~/bin/repo
    $ export PATH=${PATH}:~/bin

    $ mkdir yocto # this directory will be the top directory of the Yocto source code
    $ cd yocto
    $ repo init -u https://source.codeaurora.org/external/imx/imx-manifest -b imx-linux-hardknott -m imx-5.10.35-2.0.0.xml
    $ repo sync
Then integrate the meta-matter recipe into the Yocto code base

    $ cd ${MY_YOCTO}/sources/
    $ git clone https://github.com/nxptest/meta-matter-wcs.git

To build the Yocto Project, some packages need to be installed. The list of packages required are:

    $ sudo apt-get install gawk wget git-core diffstat unzip texinfo gcc-multilib \
    build-essential chrpath socat cpio python3 python3-pip python3-pexpect \
    xz-utils debianutils iputils-ping python3-git python3-jinja2 libegl1-mesa libsdl1.2-dev \
    pylint3 xterm npm

More information about the downloaded Yocto release can be found in the corresponding i.MX Yocto Project User’s Guide which can be found at [NXP official website](http://www.nxp.com/imxlinux).

Change the current directory to the top directory of the Yocto source code and execute the commands below to generate the Yocto images.

    $ MACHINE=imx8mmevk DISTRO=fsl-imx-xwayland source sources/meta-matter-wcs/tools/imx-iot-setup.sh bld-xwayland
    $ bitbake imx-image-multimedia

After execution of previous two commands, the Yocto images will be generated under directory ${MY_YOCTO}/bld-xwayland/tmp/deploy/images/imx8mmevk: imx-boot-imx8mmevk-sd.bin-flash_evk and imx-image-multimedia-imx8mmevk.wic.bz2.

The Universal Update Utility (UUU) is used to download images to different devices on an i.MX board. Download UUU version 1.4.193 or later from https://github.com/NXPmicro/mfgtools/releases and execute the commands below to build it.

    $ tar xvf uuu_source-1.4.193.tar.gz
    $ cd uuu-1.4.193
    $ cmake .
    $ make

UUU should be used to program the output files to eMMC by running the commands below.

    $ sudo uuu -b emmc_all imx-boot-imx8mmevk-sd.bin-flash_evk imx-image-multimedia-imx8mmevk.wic.bz2

# How to build OpenThread Border Router with Yocto SDK

# How to setup OpenThread Border Router environment within the Yocto
