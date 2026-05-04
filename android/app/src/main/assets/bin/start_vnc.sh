#!/bin/bash
# Start VNC Server inside the container

export DISPLAY=:1
export USER=root
VNC_PORT=5901

echo "[PD-VNC] Starting VNC Server on port $VNC_PORT..."

# Remove old locks
rm -rf /tmp/.X1-lock /tmp/.X11-unix/X1

# Start TigerVNC or TightVNC
vncserver :1 -geometry 1280x720 -depth 24

# Start NoVNC bridge (Web-based VNC for the WebView)
# This allows the Android WebView to connect via HTTP
/usr/share/novnc/utils/launch.sh --vnc localhost:$VNC_PORT --listen 6080 &

echo "[PD-VNC] GUI is ready. Open VNC Screen in PD App."
