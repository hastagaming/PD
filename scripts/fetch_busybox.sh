#!/system/bin/sh
# PD BusyBox Auto-Downloader

URL=$1
DEST="/data/data/com.pd.app/files/bin/busybox"

echo "[PD] Auto-detected architecture. Fetching BusyBox..."
curl -L "$URL" -o "$DEST"

if [ -f "$DEST" ]; then
    chmod +x "$DEST"
    echo "[PD] BusyBox successfully integrated."
    # Symbolically link all applets
    $DEST --install -s /data/data/com.pd.app/files/bin/
else
    echo "[PD] Error: Failed to fetch BusyBox."
fi
