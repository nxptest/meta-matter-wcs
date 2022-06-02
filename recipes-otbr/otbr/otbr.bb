PN = "otbr"
SUMMARY = "OTBR on i.MX boards"
DESCRIPTION = "OTBR applications"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=87109e44b2fda96a8991f27684a7349c"

SRC_URI = "gitsm://github.com/openthread/ot-br-posix.git;branch=main"

SRCREV = "72bb3d45684f837e67e7f1ec20a9a8e3ac4a4419"

S = "${WORKDIR}/git"
FILES_${PN} += "lib/systemd"
FILES_${PN} += "usr/share"

DEPENDS += " jsoncpp avahi boost "
RDEPENDS_${PN} += " jsoncpp libavahi-client "

inherit cmake
EXTRA_OECMAKE = "-GNinja -DOTBR_BORDER_ROUTING=ON -DOTBR_REST=ON -DOTBR_WEB=ON -DBUILD_TESTING=OFF -DOTBR_DBUS=ON -DOTBR_DNSSD_DISCOVERY_PROXY=ON -DOTBR_SRP_ADVERTISING_PROXY=ON -DOT_THREAD_VERSION=1.2 -DOTBR_INFRA_IF_NAME=mlan0" 
