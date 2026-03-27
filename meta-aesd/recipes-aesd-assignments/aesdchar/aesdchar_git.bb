# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://github.com/cu-ecen-aeld/assignments-3-and-later-siva7699.git;protocol=https;branch=main"
SRC_URI += " file://aesdchar-init"
# Modify these as desired
PV = "1.0+git${SRCPV}"
SRCREV = "ce63dfa6c7fd19a51410be9cef4723eb5694547b"

S = "${WORKDIR}/git"

inherit module update-rc.d

INITSCRIPT_NAME = "aesdchar-init"
INITSCRIPT_PARAMS = "defaults 98"

EXTRA_OEMAKE += " -C ${STAGING_KERNEL_DIR} M=${S}/aesd-char-driver"

do_install() {
    module_do_install
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/aesdchar-init ${D}${sysconfdir}/init.d/aesdchar-init
}

FILES:${PN} += "${sysconfdir}/init.d/aesdchar-init"
