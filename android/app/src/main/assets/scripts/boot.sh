#!/bin/bash
# boot.sh - Distro di Eksternal, Eksekusi Terisolasi

# Path distro di memori internal/eksternal yang bisa diakses user
# Contoh: /sdcard/PD/ubuntu_rootfs
DISTRO_PATH="$1" 

# Path binary internal (tetap di internal agar bisa di-execute)
BIN_DIR="/data/data/com.pd.app/files/bin"

# Jalankan PRoot dengan isolasi filesystem
$BIN_DIR/proot -r $DISTRO_PATH \
        -0 -w /root \
        -b /dev -b /proc -b /sys \
        -b /sdcard:/sdcard \
        --link2symlink \
        /usr/bin/env -i \
        HOME=/root \
        TERM=xterm-256color \
        PATH=/usr/bin:/usr/sbin:/bin:/sbin \
        /bin/bash --login
