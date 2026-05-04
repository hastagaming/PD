#!/system/bin/sh
# Environment Setup for Fake Root (PRoot)

ROOTFS="/data/data/com.pd.app/files/rootfs"
PROOT_BIN="/data/data/com.pd.app/files/bin/proot"

echo "[PD] Preparing PRoot environment..."

# Execute PRoot with bind mounts for full storage access
# Binding /sdcard allows access to ZArchiver and other file managers
$PROOT_BIN \
    -0 \
    -r "$ROOTFS" \
    -b /dev -b /proc -b /sys \
    -b /storage/emulated/0:/sdcard \
    -b /storage/emulated/0/Android/data:/data/data/external \
    /usr/bin/bash
