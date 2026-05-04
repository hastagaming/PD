use std::process::Command;

pub fn enable_gui_mode() {
    println!("[PD-Daemon] Enabling Graphical Mode...");
    
    // Execute the VNC starter script inside the proot environment
    let status = Command::new("/bin/bash")
        .arg("/usr/bin/start_vnc.sh")
        .status()
        .expect("Failed to start VNC server");

    if status.success() {
        println!("[PD-Daemon] VNC Server and NoVNC Bridge are active.");
    }
}
