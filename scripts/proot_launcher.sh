#!/system/bin/sh
# Advanced PD Launcher

BINARY_PATH="/data/data/com.pd.app/files/bin"
ROOTFS_PATH="/data/data/com.pd.app/files/rootfs"

# Check for storage permission
if [ ! -d "/sdcard/Android" ]; then
    echo "[PD] Error: Please grant 'All Files Access' first!"
    exit 1
fi

# Execute PRoot with optimized flags
$BINARY_PATH/proot \
    --kill-on-exit \
    --link2symlink \
    -0 \
    -r $ROOTFS_PATH \
    -b /dev:/dev \
    -b /proc:/proc \
    -b /sys:/sys \
    -b /storage/emulated/0:/sdcard \
    -b /data/data/com.pd.app/files:/home \
    /bin/sh -c "source /etc/profile; /bin/bash"
