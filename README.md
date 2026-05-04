# 🛡️ PD (Private Distro) - Universal Linux Emulator

**PD** is a high-performance, universal Linux distribution emulator for Android. Built for flexibility and speed, it combines a **Rust** backend daemon, **PRoot** for rootless virtualization, and an integrated **VNC** environment to provide a seamless desktop GUI experience on mobile hardware.

---

## 🚀 Key Features

* **Dynamic Distro Loader**: Load your favorite Linux distributions via `.iso` or `.tar.gz` directly from your device storage.
* **Intelligent BusyBox Auto-Scanner**: Automatically scans the distro's binary headers (ELF) to detect architecture and inject the most compatible BusyBox version (v1.21.1) without user intervention.
* **Built-in VNC Environment**: Run full desktop environments (XFCE4, LXDE, etc.) internally with an optimized GUI bridge.
* **Rust-Powered Core**: A high-efficiency backend daemon manages processes, mounting, and terminal sessions securely.
* **Minimal Bootstrap Startup**: The app starts with a clean slate, installing only the essential core scripts before you choose your distro.

---

## 📁 Project Structure

```text
PD_Project/
├── .github/workflows/    # CI/CD Pipelines (Auto-Build Signed APKs)
├── android/              # Frontend Android Application (Kotlin)
│   ├── app/              # Main Application Module (ScannerCore, UI, Drawables)
│   └── gradle/           # Gradle Wrapper & Build Configurations
├── core/                 # Backend Daemon (Rust)
└── scripts/              # Automation & Injection Scripts (Bash/Bootstrap)

```
## 🛠️ Build Instructions
### 1. Prerequisites
 * Android SDK & NDK (API 26+)
 * Rust Toolchain (Target: aarch64-linux-android)
 * OpenJDK 17
### 2. Compile Backend (Rust)
```bash
cd core
cargo ndk -t aarch64-linux-android build --release
cp target/aarch64-linux-android/release/pd-daemon ../android/app/src/main/assets/bin/

```
### 3. Build Signed APK (Android)
```bash
cd android
chmod +x gradlew
./gradlew assembleRelease

```
## 🤖 How to Use
 1. **Initialization**: Launch the app and wait for the bootstrap.sh to initialize the core environment.
 2. **Select Distro**: Tap **"Pick ISO"** and select your Linux distro file from your local storage.
 3. **Auto-Setup**: The system intelligently scans the architecture and fetches the specific BusyBox binary required from busybox.net.
 4. **Launch**: Hit **"OK"** to enter the Terminal or switch to **GUI Mode** for a full desktop experience.
## 🔑 CI/CD & Signing (GitHub Actions)
To enable automated signed builds, configure the following **Secrets** in your GitHub repository settings:
| Secret Name | Description |
|---|---|
| KEYSTORE_BASE64 | Your .jks file converted to Base64 string (base64 pd-project.jks | tr -d '\n') |
| KEYSTORE_PASSWORD | The password for your keystore file |
| KEY_ALIAS | The alias for your signing key (e.g., pd_alias) |
| KEY_PASSWORD | The password for the specific key alias |
## 👨‍💻 Developer
 * **Commander/Lead Developer**: Nasa
 * **Target Device**: Realme Note 50 (Android 15)
 * **Organization**: hastagaming
## 📜 License
This project is licensed under the MIT License.