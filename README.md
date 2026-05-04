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
## 🤖 How to Use
 1. **Initialization**: Launch the app and wait for the bootstrap.sh to initialize the core environment.
 2. **Select Distro**: Tap **"Pick ISO"** and select your Linux distro file from your local storage.
 3. **Auto-Setup**: The system intelligently scans the architecture and fetches the specific BusyBox binary required from busybox.net.
 4. **Launch**: Hit **"OK"** to enter the Terminal or switch to **GUI Mode** for a full desktop experience.
## 👨‍💻 Developer
 * **Commander/Lead Developer**: Nasa
 * **Target Device**: Realme Note 50 (Android 15)
 * **Organization**: hastagaming
## 📜 License
This project is licensed under the MIT License.