# Makefile for PD Project
ARCH = aarch64-linux-android
NDK_TARGET = aarch64-linux-android26

build-core:
	cd core && cargo ndk -t $(ARCH) build --release
	mkdir -p android/app/src/main/assets/bin/
	cp core/target/$(ARCH)/release/pd-daemon android/app/src/main/assets/bin/

install-adb:
	adb push scripts/launch_daemon.sh /data/local/tmp/
	adb shell chmod +x /data/local/tmp/launch_daemon.sh
	@echo "Run 'adb shell /data/local/tmp/launch_daemon.sh' to start."

all: build-core install-adb
