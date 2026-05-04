#!/system/bin/sh
# PD (Proot-Distro) Launcher Script
# This script must be executed via ADB to grant shell privileges

APP_DATA_DIR="/data/data/com.pd.app/files"
DAEMON_BIN="$APP_DATA_DIR/pd-daemon"

echo "[PD] Starting daemon with shell privileges..."

# Check if daemon binary exists
if [ ! -f "$DAEMON_BIN" ]; then
    echo "[PD] Error: Daemon binary not found in $APP_DATA_DIR"
    exit 1
fi

# Run daemon in background
chmod +x "$DAEMON_BIN"
nohup "$DAEMON_BIN" > "$APP_DATA_DIR/daemon.log" 2>&1 &

echo "[PD] Daemon is running in background."
echo "[PD] You can now open the PD app."
