#!/bin/bash
# Internal Linux Bootstrap Script

echo "Configuring PD environment..."

# Setup environment variables
export HOME=/root
export PATH=/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin
export TERM=xterm-256color

# Create mount points for external access
mkdir -p /sdcard
mkdir -p /data/external

echo "Environment ready."
echo "Access ZArchiver files via /data/external"
/bin/bash
