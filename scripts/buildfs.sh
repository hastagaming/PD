#!/system/bin/sh
# PD Rootfs Constructor
# This script initializes the Linux directory structure

ROOT_DIR="/data/data/com.pd.app/files/rootfs"

echo "[PD] Creating Linux directory structure..."

# Create standard FHS (Filesystem Hierarchy Standard) folders
mkdir -p $ROOT_DIR/bin
mkdir -p $ROOT_DIR/etc
mkdir -p $ROOT_DIR/home
mkdir -p $ROOT_DIR/root
mkdir -p $ROOT_DIR/tmp
mkdir -p $ROOT_DIR/usr/bin
mkdir -p $ROOT_DIR/usr/lib
mkdir -p $ROOT_DIR/usr/sbin
mkdir -p $ROOT_DIR/var/log
mkdir -p $ROOT_DIR/proc
mkdir -p $ROOT_DIR/sys
mkdir -p $ROOT_DIR/dev
mkdir -p $ROOT_DIR/mnt/sdcard

# Set correct permissions
chmod 755 $ROOT_DIR
chmod 700 $ROOT_DIR/root
chmod 1777 $ROOT_DIR/tmp

echo "[PD] Moving identity files..."
# Move passwd and group files we created earlier to etc
mv /data/data/com.pd.app/files/passwd $ROOT_DIR/etc/passwd
mv /data/data/com.pd.app/files/group $ROOT_DIR/etc/group

echo "[PD] Rootfs base is ready for PRoot."
