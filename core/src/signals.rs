use nix::sys::signal::{self, Signal};
use nix::unistd::Pid;

pub fn cleanup_process(pid: i32) {
    println!("[PD-Daemon] Cleaning up process group for PID: {}", pid);
    let _ = signal::kill(Pid::from_raw(pid), Signal::SIGTERM);
}
