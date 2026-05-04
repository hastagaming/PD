mod terminal;

use tokio::net::UnixListener;
use tokio::io::{AsyncReadExt, AsyncWriteExt};
use std::fs;

#[tokio::main]
async fn main() -> Result<(), Box<dyn std::error::Error>> {
    let socket_path = "/data/local/tmp/pd.sock";
    
    // Clean up existing socket
    let _ = fs::remove_file(socket_path);
    
    let listener = UnixListener::bind(socket_path)?;
    println!("[PD-Daemon] Listening on {}", socket_path);
    
    // Set socket permissions so the app can connect
    fs::set_permissions(socket_path, fs::Permissions::from_mode(0o777))?;

    loop {
        let (mut socket, _) = listener.accept().await?;
        println!("[PD-Daemon] Connection received from Frontend");

        tokio::spawn(async move {
            // Placeholder for PRoot execution logic
            // Example: Proot into a Linux Distro
            let shell = terminal::spawn_shell("/system/bin/sh", vec![]);
            println!("[PD-Daemon] Shell spawned with PID: {}", shell.pid);
            
            // Bridge logic between Socket and PTY goes here
        });
    }
}
